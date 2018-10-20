package com.gmail.woodyc40.molarmass.tree.node;

import com.gmail.woodyc40.molarmass.data.Element;

public class RootNode extends AbstractNode {
    @Override
    public Element getElement() {
        return null;
    }

    @Override
    public int getCount(int start) {
        return 1;
    }
}
