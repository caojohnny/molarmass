package com.gmail.woodyc40.molarmass.config;

import com.google.gson.JsonElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConfigTest {
    private Config config;

    @Given("^the configuration is at \"([^\"]*)\"$")
    public void theConfigIsAt(String path) throws Throwable {
        this.config = new Config(path);
    }

    @Then("^the configuration should be nonnull$")
    public void then() {
        assertNotNull(this.config);
        assertNotNull(this.config.getData());
    }

    @Then("^\"([^\"]*)\" should be configured to \"([^\"]*)\"$")
    public void shouldMapTo(String key, String value) {
        JsonElement element = this.config.getData().get(key);
        assertNotNull(element);
        assertEquals(element.getAsString(), value);
    }
}
