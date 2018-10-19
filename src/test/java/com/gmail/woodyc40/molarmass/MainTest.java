package com.gmail.woodyc40.molarmass;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MainTest {
    @When("^the user enters \"([^\"]*)\"$")
    public void theUserEnters(String input) throws Throwable {
        throw new PendingException();
    }

    @Then("^\"([^\"]*)\" should be outputted$")
    public void shouldBeOutputted(String output) throws Throwable {
        throw new PendingException();
    }
}
