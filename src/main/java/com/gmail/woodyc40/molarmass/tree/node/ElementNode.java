package com.gmail.woodyc40.molarmass.tree.node;

import com.gmail.woodyc40.molarmass.data.Element;

public class ElementNode extends AbstractNode {
    private final Element element;

    public ElementNode(Element element) {
        this.element = element;
    }

    @Override
    public int getCount(int start) {
        return start;
    }

    @Override
    public Element getElement() {
        return this.element;
    }

    @Override
    public void addChild(Node child) {
        throw new UnsupportedOperationException("Elements cannot have children");
    }
}
