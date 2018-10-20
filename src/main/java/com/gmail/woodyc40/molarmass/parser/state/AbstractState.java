package com.gmail.woodyc40.molarmass.parser.state;

import com.gmail.woodyc40.molarmass.parser.Parser;
import com.gmail.woodyc40.molarmass.tree.DefaultTokenTree;

public abstract class AbstractState {
    public abstract AbstractState getNext(char c);

    public abstract void process(DefaultTokenTree tree, Parser parser);

    public static AbstractState expect(char c, Class<? extends AbstractState>... classes) {
        for (Class<? extends AbstractState> cls : classes) {
            if (cls == ElementalState.class && isElementStart(c)) {
                return new ElementalState(c);
            }

            if (cls == BeginGroupState.class && isGroupStart(c)) {
                return new BeginGroupState();
            }

            if (cls == EndGroupState.class && isGroupEnd(c)) {
                return new EndGroupState();
            }
        }

        return null;
    }

    public static boolean isElementStart(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isElementSymbol(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    public static boolean isGroupStart(char c) {
        return c == '(';
    }

    public static boolean isGroupEnd(char c) {
        return c == ')';
    }
}
