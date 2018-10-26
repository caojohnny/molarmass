package com.gmail.woodyc40.molarmass.config;

import com.google.gson.JsonElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConfigTest {
    private Config config;

    @Given("^the configuration is at \"([^\"]*)\"$")
    public void theConfigIsAt(String path) throws Throwable {
        this.config = new DefaultConfig(path);
    }

    @Then("^the configuration should be nonnull$")
    public void then() {
        assertNotNull(this.config);
    }

    @Then("^string \"([^\"]*)\" should be configured to \"([^\"]*)\"$")
    public void stringShouldBeConfiguredTo(String key, String value) {
        if (this.config instanceof DefaultConfig) {
            DefaultConfig cfg = (DefaultConfig) this.config;
            JsonElement element = cfg.getData().get(key);

            assertNotNull(element);
            assertEquals(element.getAsString(), value);
        } else {
            throw new PendingException();
        }
    }

    @Then("^boolean \"([^\"]*)\" should be configured to \"([^\"]*)\"$")
    public void booleanShouldBeConfiguredTo(String key, String value) {
        if (this.config instanceof DefaultConfig) {
            DefaultConfig cfg = (DefaultConfig) this.config;
            JsonElement element = cfg.getData().get(key);

            assertNotNull(element);
            assertEquals(element.getAsBoolean(), Boolean.parseBoolean(value));
        } else {
            throw new PendingException();
        }
    }
}
