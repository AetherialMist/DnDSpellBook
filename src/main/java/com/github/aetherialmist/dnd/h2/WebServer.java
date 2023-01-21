package com.github.aetherialmist.dnd.h2;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;

import java.io.Closeable;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * Url: <a href="http://localhost:8082">http://localhost:8082</a>
 * Username: sa
 * Password: [empty]
 * JDBC URL: jdbc:h2:D:/Gits/dnd-app-v2/data/test
 */
@Slf4j
public class WebServer implements Runnable, Closeable {

    private final ExecutorService webServerExecutor = Executors.newSingleThreadExecutor();
    private volatile boolean running = false;
    private final AtomicReference<Server> server = new AtomicReference<>();
    private Future<?> webServerTask;

    public void start() {
        running = true;
        webServerTask = webServerExecutor.submit(this);
    }

    public void stop() {
        close();
    }

    @Override
    public void run() {
        startWebServer();
        sleepUntilMarkedForShutdown();
        shutdownWebServer();
        log.info("H2 WebServer has shutdown.");
    }

    private void startWebServer() {
        try {
            log.info("Starting H2 WebServer.");
            Server webServer = Server.createWebServer();
            server.set(webServer);
            server.get().start();
        } catch (Exception e) {
            running = false;
            log.error("Failed to start local H2 WebServer.");
        }
    }

    private void sleepUntilMarkedForShutdown() {
        CountDownLatch latch = new CountDownLatch(1);

        // Create and start task to check when the shutdown marker has been set
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> future = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            // If marked for shutdown AND latch is not zero
            if (!running && latch.getCount() > 0) {
                latch.countDown();
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS); // Check once every second

        // Wait for task to signal the shutdown marker has been set
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.warn("Interrupted while waiting for shutdown signal. Shutting down for safety.");
        }

        // Close out Task and ScheduledExecutorService
        future.cancel(false);
        shutdownExecutor(scheduledExecutorService, 2000);
    }

    private void shutdownWebServer() {
        if (server.get() != null) {
            server.get().stop();
            server.get().shutdown();
        }
    }

    @Override
    public void close() {
        log.info("Stopping H2 WebServer...");
        // Mark WebServer to close on next scheduled check
        running = false;

        try {
            log.info("Waiting for H2 WebServer to close gracefully.");
            webServerTask.get();
        } catch (Exception e) {
            log.error("Failed to wait for H2 WebServer to close gracefully.", e);
        }

        // Wait for WebServer task to close gracefully
        shutdownExecutor(webServerExecutor, 5000);
    }

    private void shutdownExecutor(ExecutorService executor, long timeoutMs) {
        executor.shutdown();
        try {
            boolean allTasksFinished = executor.awaitTermination(timeoutMs, TimeUnit.MILLISECONDS);
            if (!allTasksFinished) {
                log.error("ExecutorService still has running tasks!");
            }
        } catch (InterruptedException e) {
            log.error("ExecutorService may still have running tasks!");
        }
    }

}
