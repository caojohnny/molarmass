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
        AbstractState expect = expect(c, ElementState.class, BeginGroupState.class);
        if (expect != null) {
            return expect;
        }

        throw new IllegalExpressionException("Did not expect " + c);
    }

    @Override
    public void process(DefaultTokenTree tree, Parser parser) {
    }
}
