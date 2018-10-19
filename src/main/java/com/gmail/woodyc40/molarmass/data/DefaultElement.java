package com.gmail.woodyc40.molarmass.data;

import com.google.gson.JsonObject;

public class DefaultElement implements Element {
    private final JsonObject elementData;

    public DefaultElement(JsonObject elementData) {
        this.elementData = elementData;
    }

    @Override
    public String getName() {
        return this.elementData.get("name").getAsString();
    }

    @Override
    public String getSymbol() {
        return this.elementData.get("symbol").getAsString();
    }

    @Override
    public int getAtomicNumber() {
        return this.elementData.get("number").getAsInt();
    }

    @Override
    public double getAtomicMass() {
        return this.elementData.get("atomic_mass").getAsDouble();
    }

    public JsonObject getElementData() {
        return this.elementData;
    }
}
