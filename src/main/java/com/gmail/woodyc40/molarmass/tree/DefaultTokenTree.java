package com.gmail.woodyc40.molarmass.tree;

import com.gmail.woodyc40.molarmass.exception.IllegalExpressionException;
import com.gmail.woodyc40.molarmass.tree.node.AbstractNode;
import com.gmail.woodyc40.molarmass.tree.node.Node;
import com.gmail.woodyc40.molarmass.tree.node.RootNode;

import java.util.function.Consumer;

public class DefaultTokenTree implements TokenTree {
    private final RootNode root = new RootNode();

    private AbstractNode branchRoot = this.root;
    private AbstractNode lastInsertion = this.root;

    public void addChildToBranch(AbstractNode node) {
        node.setParent(this.branchRoot);
        this.branchRoot.addChild(node);
        this.lastInsertion = node;
    }

    public AbstractNode replaceBranchRoot(AbstractNode node) {
        AbstractNode old = this.branchRoot;

        AbstractNode oldParent = old.getParent();
        oldParent.removeChild(old);
        oldParent.addChild(node);

        for (Node child : old.getChildren()) {
            node.addChild((AbstractNode) child);
            old.removeChild(child);
        }

        this.branchRoot = node;

        return old;
    }

    public void newBranch() {
        this.branchRoot = this.lastInsertion;
    }

    public void endBranch() {
        if (this.branchRoot == this.root) {
            throw new IllegalExpressionException("Cannot end branch here");
        }

        this.branchRoot = this.branchRoot.getParent();
    }

    public AbstractNode getBranchRoot() {
        return this.branchRoot;
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
