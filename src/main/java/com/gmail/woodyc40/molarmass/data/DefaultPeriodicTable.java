package com.gmail.woodyc40.molarmass.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class DefaultPeriodicTable implements PeriodicTable {
    private final String dataUrl;
    private final Map<String, Element> elements =
            new ConcurrentHashMap<>();

    public DefaultPeriodicTable(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    @Override
    public boolean download() {
        try {
            URL url = new URL(this.dataUrl);
            URLConnection con = url.openConnection();

            if (con instanceof HttpURLConnection) {
                int rc = ((HttpURLConnection) con).getResponseCode();
                if (rc != 200) {
                    throw new IOException("Received resp code " + rc);
                }
            }

            Gson gson = new GsonBuilder().setLenient().create();
            try (InputStreamReader reader = new InputStreamReader(con.getInputStream())) {
                JsonObject data = gson.fromJson(reader, JsonObject.class);
                JsonArray elements = data.getAsJsonArray("elements");
                for (JsonElement element : elements) {
                    JsonObject elementData = element.getAsJsonObject();
                    Element elem = new DefaultElement(elementData);

                    this.elements.put(elem.getSymbol(), elem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    @Nullable
    public Element getElement(String symbol) {
        return this.elements.get(symbol);
    }
}
