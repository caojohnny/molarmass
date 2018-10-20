package com.gmail.woodyc40.molarmass.parser;

import com.gmail.woodyc40.molarmass.data.PeriodicTable;
import com.gmail.woodyc40.molarmass.exception.IllegalExpressionException;
import com.gmail.woodyc40.molarmass.tree.TokenTree;

public interface Parser {
    TokenTree parse() throws IllegalExpressionException;

    PeriodicTable getTable();
}
