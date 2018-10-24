package com.gmail.woodyc40.molarmass.config;

public interface Config {
    String getPrompt();

    String getDataSource();

    String getOutputFormat();

    String getInvalidInputMessage();

    boolean getIsDebugging();
}
