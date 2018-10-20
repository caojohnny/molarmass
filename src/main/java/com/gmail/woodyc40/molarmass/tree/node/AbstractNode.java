package com.gmail.woodyc40.molarmass.tree.node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractNode implements Node {
    private AbstractNode parent;
    private final Collection<Node> children =
            new ArrayList<>();

    @Override
    public AbstractNode getParent() {
        return this.parent;
    }

    @Override
    public Collection<Node> getChildren() {
        return Collections.unmodifiableCollection(this.children);
    }

    public void setParent(AbstractNode parent) {
        this.parent = parent;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
}
