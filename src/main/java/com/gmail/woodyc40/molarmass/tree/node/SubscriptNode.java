package com.gmail.woodyc40.molarmass.tree.node;

import com.gmail.woodyc40.molarmass.data.Element;

public class SubscriptNode extends AbstractNode {
    private final int subscript;

    public SubscriptNode(int subscript) {
        this.subscript = subscript;
    }

    @Override
    public Element getElement() {
        return null;
    }

    @Override
    public int getCount(int start) {
        return start * subscript;
    }
}
