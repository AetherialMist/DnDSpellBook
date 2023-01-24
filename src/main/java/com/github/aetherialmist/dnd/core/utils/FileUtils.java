package com.github.aetherialmist.dnd.core.utils;

import java.io.*;

public final class FileUtils {

    public static InputStream obtainInputStream(File file) throws FileIOException {
        String path = file.getPath();
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new FileIOException("Failed to create InputSteam for file: " + path, e);
        }
    }

    public static FileWriter obtainFileWriter(File file) throws FileIOException {
        String path = file.getPath();
        try {
            return new FileWriter(file);
        } catch (IOException e) {
            throw new FileIOException("Failed to create FileWriter for file: " + path, e);
        }
    }

    public static File createFileIfNotExists(String path) throws FileIOException {
        File file = new File(path);
        createFileIfNotExists(file);
        return file;
    }

    public static void createFileIfNotExists(File file) throws FileIOException {
        String path = file.getPath();
        createParentDirectoriesIfNeeded(path);
        createFileIfNotExists0(file);
    }

    public static void createFileIfNotExists0(File file) throws FileIOException {
        String path = file.getPath();
        if (!file.exists()) {
            boolean createdFile;
            try {
                createdFile = file.createNewFile();
            } catch (IOException e) {
                throw new FileIOException("Failed to create file at: " + path, e);
            }
            if (!createdFile) {
                throw new FileIOException("Failed to create file at: " + path);
            }
        }
    }

    public static void createParentDirectoriesIfNeeded(String path) throws FileIOException {
        String folder = path.substring(0, path.lastIndexOf('/'));
        File folderFile = new File(folder);
        boolean createdParentFolders = folderFile.mkdirs();
        if (!createdParentFolders) {
            throw new FileIOException("Failed to create parent directories for: " + path);
        }
    }
}
