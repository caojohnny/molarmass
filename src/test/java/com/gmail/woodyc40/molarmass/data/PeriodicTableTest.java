package com.gmail.woodyc40.molarmass.data;

import com.gmail.woodyc40.molarmass.Main;
import com.gmail.woodyc40.molarmass.config.Config;
import com.gmail.woodyc40.molarmass.config.DefaultConfig;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.io.IOException;

import static org.junit.Assert.*;

public class PeriodicTableTest {
    private PeriodicTable data;

    private boolean downloadSuccess;

    @Before
    public void before() throws IOException {
        Config config = new DefaultConfig(Main.DEFAULT_CFG_PATH);
        this.data = new DefaultPeriodicTable(config.getDataSource());
    }

    @And("^the data has been downloaded$")
    public void theDataHasBeenDownloaded() {
        this.downloadSuccess = this.data.download();
    }

    @Then("^the JSON data should be nonnull$")
    public void then() {
        assertNotNull(this.data);
        assertTrue(this.downloadSuccess);
    }

    @And("^the symbol of \"([^\"]*)\" should be \"([^\"]*)\"$")
    public void shouldBe(String symbol, String name) {
        Element element = this.data.getElement(symbol);
        assertNotNull(element);
        assertEquals(element.getName(), name);
    }
}
