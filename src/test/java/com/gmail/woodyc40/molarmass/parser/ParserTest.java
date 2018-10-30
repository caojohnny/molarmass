package com.gmail.woodyc40.molarmass.parser;

import com.gmail.woodyc40.molarmass.Main;
import com.gmail.woodyc40.molarmass.config.Config;
import com.gmail.woodyc40.molarmass.config.DefaultConfig;
import com.gmail.woodyc40.molarmass.data.DefaultPeriodicTable;
import com.gmail.woodyc40.molarmass.data.Element;
import com.gmail.woodyc40.molarmass.data.PeriodicTable;
import com.gmail.woodyc40.molarmass.tree.TokenTree;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParserTest {
    private PeriodicTable data;
    private TokenTree tree;

    @Before
    public void before() throws IOException {
        Config config = new DefaultConfig(Main.DEFAULT_CFG_PATH);
        this.data = new DefaultPeriodicTable(config.getDataSource());
        this.data.download();
    }

    @When("^\"([^\"]*)\" is passed$")
    public void isPassed(String input) {
        Parser parser = new DefaultParser(input, this.data);
        this.tree = parser.parse();
    }

    @Then("^return \"([^\"]*)\"")
    public void thenReturn(String elements) {
        assertNotNull(this.tree);

        TokenTreeParser ttp = new TokenTreeParser(this.tree);
        Map<Element, Integer> map = ttp.getElementMap();

        Map<Element, Integer> sortedMap = new TreeMap<>(Comparator.comparing(Element::getSymbol));
        for (Entry<Element, Integer> entry : map.entrySet()) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        StringBuilder output = new StringBuilder();
        for (Entry<Element, Integer> entry : sortedMap.entrySet()) {
            output.append(entry.getKey().getSymbol()).append(" = ").append(entry.getValue()).append(", ");
        }
        output.delete(output.length() - 2, output.length());

        assertEquals(elements, output.toString());
    }
}
