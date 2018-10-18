package com.gmail.woodyc40.molarmass.data;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PeriodicTableTest {
    @Given("^The periodic table is sourced from \"([^\"]*)\"$")
    public void sourcedFrom(String link) {
        throw new PendingException();
    }

    @Then("^The JSON data should be nonnull$")
    public void then() {
        throw new PendingException();
    }
}
