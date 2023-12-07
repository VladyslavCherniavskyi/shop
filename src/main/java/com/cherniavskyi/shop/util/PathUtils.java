package com.cherniavskyi.shop.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public final class PathUtils {

    private PathUtils() {
    }

    public static Path getAbsolutePath(String resource) {
        return Paths.get(
                Objects.requireNonNull(
                                PathUtils.class.getClassLoader()
                                        .getResource("photouploader")
                        )
                        .getPath(),
                resource
        );
    }
}

