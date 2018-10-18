package com.gmail.woodyc40.molarmass.config;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ConfigTest {
    private Config config;

    @Given("^The config is at \"([^\"]*)\"$")
    public void theConfigIsAt(String path) throws IOException {
        this.config = new Config(path);
    }

    @Then("^The configuration should be nonnull$")
    public void then() {
        assertNotNull(this.config);
    }
}
