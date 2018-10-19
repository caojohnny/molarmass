package com.gmail.woodyc40.molarmass.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private static final Gson GSON = new Gson();

    private final JsonObject data;

    public Config(String path) throws IOException {
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            Files.copy(this.getClass().getResourceAsStream("/config.json"), filePath);
        }

        try (Reader reader = Files.newBufferedReader(filePath)) {
            this.data = GSON.fromJson(reader, JsonObject.class);
        }
    }

    public String getPrompt() {
        return this.data.get("prompt").getAsString();
    }

    public String getDataSource() {
        return this.data.get("data-source").getAsString();
    }

    public String getOutputFormat() {
        return this.data.get("output-format").getAsString();
    }

    public String getInvalidInputMessage() {
        return this.data.get("invalid-input-message").getAsString();
    }

    public JsonObject getData() {
        return this.data;
    }
}
