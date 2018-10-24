package com.gmail.woodyc40.molarmass.parser.state;

import com.gmail.woodyc40.molarmass.data.Element;
import com.gmail.woodyc40.molarmass.exception.IllegalExpressionException;
import com.gmail.woodyc40.molarmass.parser.Parser;
import com.gmail.woodyc40.molarmass.tree.DefaultTokenTree;
import com.gmail.woodyc40.molarmass.tree.node.AbstractNode;
import com.gmail.woodyc40.molarmass.tree.node.ElementNode;
import com.gmail.woodyc40.molarmass.tree.node.SubscriptNode;

public class ElementState extends AbstractState {
    private String symbol = "";
    private String qty = "";

    public ElementState(char c) {
        this.symbol += c;
    }

    @Override
    public AbstractState getNext(char c) {
        AbstractState newState = expect(c, ElementState.class, BeginGroupState.class, EndGroupState.class);
        if (newState != null) {
            return newState;
        } else if (isElementSymbol(c)) {
            this.symbol += c;
            return this;
        } else if (isDigit(c)) {
            this.qty += c;
            return this;
        }

        throw new IllegalExpressionException("Illegal start of new expression: " + c);
    }

    @Override
    public void process(DefaultTokenTree tree, Parser parser) {
        Element element;
        if (!this.symbol.isEmpty()) {
            element = parser.getTable().getElement(this.symbol);
            if (element == null) {
                throw new IllegalExpressionException("No such element: " + this.symbol);
            }
        } else {
            throw new IllegalExpressionException("Empty symbol?");
        }

        if (!this.qty.isEmpty()) {
            int qty;
            try {
                qty = Integer.parseInt(this.qty);
            } catch (NumberFormatException e) {
                throw new IllegalExpressionException("Cannot parse " + this.qty + " as a quantity");
            }

            AbstractNode subscript = new SubscriptNode(qty);
            tree.addChildToBranch(subscript);
            tree.newBranch();

            AbstractNode elementNode = new ElementNode(element);
            tree.addChildToBranch(elementNode);
            tree.endBranch();
        } else {
            AbstractNode elementNode = new ElementNode(element);
            tree.addChildToBranch(elementNode);
        }
    }
}
