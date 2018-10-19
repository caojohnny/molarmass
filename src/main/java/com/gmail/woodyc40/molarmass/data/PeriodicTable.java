package com.gmail.woodyc40.molarmass.data;

import javax.annotation.Nullable;

public interface PeriodicTable {
    boolean download();

    @Nullable
    Element getElement(String symbol);
}
