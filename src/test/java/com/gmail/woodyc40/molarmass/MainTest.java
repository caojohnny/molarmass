package com.gmail.woodyc40.molarmass;

import com.gmail.woodyc40.molarmass.config.Config;
import com.gmail.woodyc40.molarmass.config.DefaultConfig;
import com.gmail.woodyc40.molarmass.data.DefaultPeriodicTable;
import com.gmail.woodyc40.molarmass.data.PeriodicTable;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;

public class MainTest {
    private Main main;

    @Before
    public void before() throws IOException {
        Config config = new DefaultConfig(Main.DEFAULT_CFG_PATH);
        PeriodicTable table = new DefaultPeriodicTable(config.getDataSource());
        this.main = new Main(config, table);
    }

    @When("^the user enters \"([^\"]*)\"$")
    public void theUserEnters(String input) throws Throwable {
        throw new PendingException();
    }

    @Then("^\"([^\"]*)\" should be outputted$")
    public void shouldBeOutputted(String output) throws Throwable {
        throw new PendingException();
    }
}
