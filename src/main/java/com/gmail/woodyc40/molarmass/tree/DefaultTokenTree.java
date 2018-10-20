package com.gmail.woodyc40.molarmass.tree;

import com.gmail.woodyc40.molarmass.exception.IllegalExpressionException;
import com.gmail.woodyc40.molarmass.tree.node.AbstractNode;
import com.gmail.woodyc40.molarmass.tree.node.Node;
import com.gmail.woodyc40.molarmass.tree.node.RootNode;

import java.util.function.Consumer;

public class DefaultTokenTree implements TokenTree {
    private final RootNode root = new RootNode();

    private AbstractNode prev = this.root;
    private AbstractNode lastInsertion = this.root;

    public void addChildToPrev(AbstractNode node) {
        node.setParent(this.prev);
        this.prev.addChild(node);
        this.lastInsertion = node;
    }

    public void newBranch() {
        this.prev = this.lastInsertion;
    }

    public void endBranch() {
        if (this.prev == this.root) {
            throw new IllegalExpressionException("Cannot end branch here");
        }

        this.prev = this.prev.getParent();
    }

    public AbstractNode getLastInsertion() {
        return this.lastInsertion;
    }

    @Override
    public Node getRoot() {
        return this.root;
    }

    @Override
    public void iterate(Consumer<Node> nodeConsumer) {
        this.iterate(this.root, nodeConsumer);
    }

    @Override
    public void iterateElements(Consumer<Node> nodeConsumer) {
        this.iterate(this.root, child -> {
            if (child.getElement() != null) {
                nodeConsumer.accept(child);
            }
        });
    }

    private void iterate(Node node, Consumer<Node> consumer) {
        for (Node child : node.getChildren()) {
            consumer.accept(child);

            this.iterate(child, consumer);
        }
    }
}
