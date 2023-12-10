package com.company.dsa;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilesUtils {
    private static int counter = 0;
    private static String SHPACIQ = " ";

    public static void traverseDirectories(String path) {

        File directory = new File(path);

        File[] files = directory.listFiles();

        if (files.length == 0) {
            return;
        }
        System.out.printf("%s%s:%n", SHPACIQ.repeat(counter), directory.getName());
        for (File file : files) {
            if (file.isDirectory()) {
                counter += 2;
                traverseDirectories(file.getPath());
                counter -= 2;
            } else {
                System.out.printf("%s%s%n", SHPACIQ.repeat(counter + 2), file.getName());
            }
        }
    }

    public static List<String> findFiles(String path, String extension) {
        List<String> output = new ArrayList<>();

        File dir = new File(path);
        File[] files = dir.listFiles();

        if (files.length == 0) {
            return output;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                output.addAll(findFiles(file.getPath(), extension));
            } else {
                if (file.getName().endsWith(extension)) {
                    output.add(file.getName());
                }
            }
        }
        return output;
    }

    public static boolean fileExists(String path, String fileName) {
        File dir = new File(path);
        File[] files = dir.listFiles();

        if (files.length == 0) {
            return false;
        }
        boolean flag = false;

        for (File file : files) {
            if (file.isDirectory()) {
                flag = fileExists(file.getPath(), fileName);
            } else {
                if (file.getName().equals(fileName)) {
                    return true;
                }
            }
            if (flag) {
                return flag;
            }
        }
        return flag;
    }

    public static Map<String, Integer> getDirectoryStats(String path) {
        Map<String, Integer> stats = new HashMap<>();
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files.length == 0) {
            return stats;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                getDirectoryStats(file.getPath())
                        .forEach((key, value) -> stats.put(key, stats.getOrDefault(key, 0) + value));

            } else {
                String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                stats.put(ext, stats.getOrDefault(ext, 0) + 1);
            }
        }
        return stats;
    }
}
