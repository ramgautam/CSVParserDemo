package com.coda.test.util;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


@Slf4j
public class OutputResultRunnableTask implements Runnable {

    private final String fileName;
    private final String content;

    public OutputResultRunnableTask(String fileName, String content) {
        this.content = content;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            // default, create and write to it.
            Files.write(
                    Paths.get(fileName),
                    content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error("File Creation exception {}, {}", fileName, e.getMessage());
        }


    }
}
