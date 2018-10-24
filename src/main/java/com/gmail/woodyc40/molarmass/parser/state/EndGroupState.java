package com.gmail.woodyc40.molarmass.parser.state;

import com.gmail.woodyc40.molarmass.exception.IllegalExpressionException;
import com.gmail.woodyc40.molarmass.parser.Parser;
import com.gmail.woodyc40.molarmass.tree.DefaultTokenTree;
import com.gmail.woodyc40.molarmass.tree.node.AbstractNode;
import com.gmail.woodyc40.molarmass.tree.node.GroupNode;
import com.gmail.woodyc40.molarmass.tree.node.SubscriptNode;

public class EndGroupState extends AbstractState {
    private String qty = "";

    @Override
    public AbstractState getNext(char c) {
        AbstractState expect = expect(c, BeginGroupState.class, ElementState.class, EndGroupState.class);
        if (expect != null) {
            return expect;
        }

        if (isDigit(c)) {
            this.qty += c;
            return this;
        }

        throw new IllegalExpressionException("Must be one of a begin group, element, end group, or digit");
    }

    @Override
    public void process(DefaultTokenTree tree, Parser parser) {
        if (!this.qty.isEmpty()) {
            int qty;
            try {
                qty = Integer.parseInt(this.qty);
            } catch (NumberFormatException e) {
                throw new IllegalExpressionException("Subscript is not a number");
            }

            AbstractNode replaced = tree.replaceBranchRoot(new SubscriptNode(qty));
            if (!(replaced instanceof GroupNode)) {
                throw new IllegalExpressionException("Group end detected without a group start");
            }
        }

        tree.endBranch();
    }
}
