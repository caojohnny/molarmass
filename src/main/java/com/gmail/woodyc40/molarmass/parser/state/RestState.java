package com.gmail.woodyc40.molarmass.parser.state;

import com.gmail.woodyc40.molarmass.exception.IllegalExpressionException;
import com.gmail.woodyc40.molarmass.parser.Parser;
import com.gmail.woodyc40.molarmass.tree.DefaultTokenTree;

public final class RestState extends AbstractState {
    private static final RestState INSTANCE = new RestState();

    private RestState() {
    }

    public static RestState getInstance() {
        return INSTANCE;
    }

    @Override
    public AbstractState getNext(char c) {
        if (isElementStart(c)) {
            return new ElementalState(c);
        } else {
            throw new IllegalExpressionException("Did not expect " + c);
        }
    }

    @Override
    public void process(DefaultTokenTree tree, Parser parser) {
        throw new UnsupportedOperationException("Did not reach the first token");
    }
}
