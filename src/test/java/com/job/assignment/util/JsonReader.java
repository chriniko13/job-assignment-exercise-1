package com.job.assignment.util;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public interface JsonReader {

    default String read(String filepath) throws Exception {
        URI uri = getClass().getClassLoader().getResource(filepath).toURI();
        Path path = Paths.get(uri);
        return Files.lines(path).collect(Collectors.joining());
    }
}
