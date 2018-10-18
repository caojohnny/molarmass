package com.gmail.woodyc40.molarmass.parser;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ParserTest {
    @When("^\"([^\"]*)\" is passed$")
    public void isPassed(String input) {
        throw new PendingException();
    }

    @Then("^return \"([^\"]*)\"")
    public void thenReturn(String elements) {
        throw new PendingException();
    }
}
