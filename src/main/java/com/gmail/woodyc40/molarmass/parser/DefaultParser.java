package com.gmail.woodyc40.molarmass.parser;

import com.gmail.woodyc40.molarmass.data.PeriodicTable;
import com.gmail.woodyc40.molarmass.exception.IllegalExpressionException;
import com.gmail.woodyc40.molarmass.parser.state.AbstractState;
import com.gmail.woodyc40.molarmass.parser.state.RestState;
import com.gmail.woodyc40.molarmass.tree.DefaultTokenTree;
import com.gmail.woodyc40.molarmass.tree.TokenTree;
import com.gmail.woodyc40.molarmass.tree.node.Node;

public class DefaultParser implements Parser {
    private final String input;
    private final PeriodicTable table;

    private AbstractState state = RestState.getInstance();

    public DefaultParser(String input, PeriodicTable table) {
        this.input = input;
        this.table = table;
    }

    @Override
    public TokenTree parse() throws IllegalExpressionException {
        DefaultTokenTree tree = new DefaultTokenTree();

        for (int i = 0; i < this.input.length(); i++) {
            char c = this.input.charAt(i);

            AbstractState oldState = this.state;
            this.state = this.state.getNext(c);

            System.out.println("After '" + c + "' = " + this.state.getClass().getSimpleName());
            tree.iterate(node -> {
                System.out.println("Node class: " + node.getClass().getSimpleName());
                System.out.println("Node parent: " + (node.getParent() == null ? "null" : node.getParent().getClass().getSimpleName()));
                System.out.println("Node children: ");
                for (Node child : node.getChildren()) {
                    System.out.println(" - " + child.getClass().getSimpleName());
                }
                System.out.println();
            });
            System.out.println("Tree branch root: " + tree.getBranchRoot().getClass().getSimpleName());
            System.out.println("Tree last insertion: " + tree.getLastInsertion().getClass().getSimpleName());
            if (this.state != oldState) {
                this.state.process(tree, this);
            }
        }

        // Run once again to complete the last state
        // this.state.process(tree, this);

        return tree;
    }

    public PeriodicTable getTable() {
        return this.table;
    }
}
