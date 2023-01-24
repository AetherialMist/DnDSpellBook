package com.github.aetherialmist.dnd.core.spells.service;

import com.github.aetherialmist.dnd.core.utils.FileIOException;
import com.github.aetherialmist.dnd.core.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class PersistentMap<K, V> implements Closeable {

    private final Yaml yaml = new Yaml(YamlDumpOptions.getInstance());
    protected final Map<K,V> data = new HashMap<>();
    private final String path;
    private final File file;

    public void put(K key, V value) {
        data.put(key, value);
    }

    public PersistentMap(String path) throws PersistentException, FileIOException {
        this.path = path;
        this.file = new File(path);
        FileUtils.createFileIfNotExists0(file);
    }

    public void save() throws PersistentException, FileIOException {
        FileWriter writer = FileUtils.obtainFileWriter(file);
        try {
            yaml.dump(data, writer);
        } catch (Exception e) {
            throw new PersistentException("Failed to write data to file: " + path, e);
        }
    }

    public void load() throws PersistentException, FileIOException {
        InputStream is = FileUtils.obtainInputStream(file);

        Map<K,V> rawData;
        try {
            //noinspection unchecked
            rawData = yaml.loadAs(is, Map.class);
        } catch (Exception e) {
            throw new PersistentException("Stored data is not of expected type: " + path, e);
        }

        data.clear();
        data.putAll(rawData);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (Map.Entry<K,V> entry : data.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }
        if (sb.length() > 1) {
            sb.delete(sb.lastIndexOf(","), sb.length());
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    public void close() {
        try {
            save();
        } catch (Exception e) {
            log.error("Failed to save data.", e);
        }
    }

}
