package com.gmail.woodyc40.molarmass.parser.state;

import com.gmail.woodyc40.molarmass.exception.IllegalExpressionException;
import com.gmail.woodyc40.molarmass.parser.Parser;
import com.gmail.woodyc40.molarmass.tree.DefaultTokenTree;
import com.gmail.woodyc40.molarmass.tree.node.GroupNode;

public class BeginGroupState extends AbstractState {
    @Override
    public AbstractState getNext(char c) {
        AbstractState newState = expect(c, ElementState.class);
        if (newState != null) {
            return newState;
        }

        throw new IllegalExpressionException("Next token must be element start");
    }

    @Override
    public void process(DefaultTokenTree tree, Parser parser) {
        // Placeholder node
        tree.addChildToBranch(new GroupNode());
        tree.newBranch();
    }
}
