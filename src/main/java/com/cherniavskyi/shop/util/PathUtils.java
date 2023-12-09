package com.cherniavskyi.shop.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathUtils {

    private PathUtils() {
    }

    public static Path getAbsolutePath(String resource) {
        var rootPath = Paths.get("photouploader").toAbsolutePath().toString();
        return Paths.get(rootPath, resource);
    }
}

