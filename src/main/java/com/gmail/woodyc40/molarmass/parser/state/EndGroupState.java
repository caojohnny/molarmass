package com.gmail.woodyc40.molarmass.parser.state;

import com.gmail.woodyc40.molarmass.parser.Parser;
import com.gmail.woodyc40.molarmass.tree.DefaultTokenTree;

public class EndGroupState extends AbstractState {
    @Override
    public AbstractState getNext(char c) {
        return null;
    }

    @Override
    public void process(DefaultTokenTree tree, Parser parser) {
    }
}
