package com.gmail.woodyc40.molarmass.parser;

import com.gmail.woodyc40.molarmass.data.Element;
import com.gmail.woodyc40.molarmass.tree.TokenTree;
import com.gmail.woodyc40.molarmass.tree.node.Node;

import java.util.HashMap;
import java.util.Map;

public class TokenTreeParser {
    private final TokenTree tree;

    public TokenTreeParser(TokenTree tree) {
        this.tree = tree;
    }

    public Map<Element, Integer> getElementMap() {
        Map<Element, Integer> elements = new HashMap<>();
        this.countRecursively(elements, this.tree.getRoot(), 0);

        return elements;
    }

    private void countRecursively(Map<Element, Integer> elements, Node node, int count) {
        int next = node.getCount(count);

        Element element = node.getElement();
        if (element != null) {
            elements.put(element, next);
        } else {
            for (Node child : node.getChildren()) {
                this.countRecursively(elements, child, next);
            }
        }
    }
}
