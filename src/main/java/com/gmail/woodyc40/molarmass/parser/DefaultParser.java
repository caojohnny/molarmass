package com.gmail.woodyc40.molarmass.parser;

import com.gmail.woodyc40.molarmass.data.PeriodicTable;
import com.gmail.woodyc40.molarmass.exception.IllegalExpressionException;
import com.gmail.woodyc40.molarmass.parser.state.AbstractState;
import com.gmail.woodyc40.molarmass.parser.state.RestState;
import com.gmail.woodyc40.molarmass.tree.DefaultTokenTree;
import com.gmail.woodyc40.molarmass.tree.TokenTree;

public class DefaultParser implements Parser {
    private final String input;
    private final PeriodicTable table;

    private AbstractState state = RestState.getInstance();

    public DefaultParser(String input, PeriodicTable table) {
        this.input = input;
        this.table = table;
    }

    @Override
    public TokenTree parse() throws IllegalExpressionException {
        DefaultTokenTree tree = new DefaultTokenTree();

        for (int i = 0; i < this.input.length(); i++) {
            char c = this.input.charAt(i);

            AbstractState oldState = this.state;
            this.state = this.state.getNext(c);

            if (this.state != oldState) {
                oldState.process(tree, this);
            }
        }

        // Run once again to complete the last state
        this.state.process(tree, this);

        return tree;
    }

    public PeriodicTable getTable() {
        return this.table;
    }
}
