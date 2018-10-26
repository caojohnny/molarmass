package com.gmail.woodyc40.molarmass;

import com.gmail.woodyc40.molarmass.config.Config;
import com.gmail.woodyc40.molarmass.config.DefaultConfig;
import com.gmail.woodyc40.molarmass.data.DefaultPeriodicTable;
import com.gmail.woodyc40.molarmass.data.PeriodicTable;
import com.gmail.woodyc40.molarmass.tree.TokenTree;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MainTest {
    private Main main;
    private TokenTree tree;

    @Before
    public void before() throws IOException {
        Config config = new DefaultConfig(Main.DEFAULT_CFG_PATH);
        PeriodicTable table = new DefaultPeriodicTable(config.getDataSource());
        table.download();
        this.main = new Main(config, table);
    }

    @When("^the user enters \"([^\"]*)\"$")
    public void theUserEnters(String input) throws Throwable {
        this.tree = this.main.process(input);
    }

    @Then("the molar mass should be {double}")
    public void theMolarMassShouldBeMass(double mass) throws Throwable {
        double calculate = this.main.getMolarMass(this.tree);
        assertEquals(mass, calculate, 0);
    }
}
