package com.gmail.woodyc40.molarmass.tree;

import com.gmail.woodyc40.molarmass.tree.node.Node;

import java.util.function.Consumer;

public interface TokenTree {
    Node getRoot();

    void iterate(Consumer<Node> nodeConsumer);

    void iterateElements(Consumer<Node> nodeConsumer);
}
