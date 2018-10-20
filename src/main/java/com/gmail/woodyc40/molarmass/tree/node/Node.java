package com.gmail.woodyc40.molarmass.tree.node;

import com.gmail.woodyc40.molarmass.data.Element;

import java.util.Collection;

public interface Node {
    Node getParent();

    Collection<Node> getChildren();

    Element getElement();

    int getCount(int start);
}
