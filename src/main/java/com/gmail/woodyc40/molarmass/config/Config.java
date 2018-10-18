package com.gmail.woodyc40.molarmass.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private static final Gson GSON = new Gson();

    private final JsonObject config;

    public Config(String path) throws IOException {
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        this.config = GSON.fromJson(Files.newBufferedReader(filePath), JsonObject.class);
    }

    public String getPrompt() {
        return this.config.get("prompt").getAsString();
    }

    public String getDataSource() {
        return this.config.get("data-source").getAsString();
    }

    public String getOutputFormat() {
        return this.config.get("output-format").getAsString();
    }

    public String getInvalidInputMessage() {
        return this.config.get("invalid-input-message").getAsString();
    }
}
