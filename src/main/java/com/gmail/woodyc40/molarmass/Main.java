package com.gmail.woodyc40.molarmass;

import com.gmail.woodyc40.molarmass.config.Config;
import com.gmail.woodyc40.molarmass.config.DefaultConfig;
import com.gmail.woodyc40.molarmass.data.DefaultPeriodicTable;
import com.gmail.woodyc40.molarmass.data.Element;
import com.gmail.woodyc40.molarmass.data.PeriodicTable;
import com.gmail.woodyc40.molarmass.parser.DefaultParser;
import com.gmail.woodyc40.molarmass.parser.Parser;
import com.gmail.woodyc40.molarmass.parser.TokenTreeParser;
import com.gmail.woodyc40.molarmass.tree.TokenTree;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
    public static final String DEFAULT_CFG_PATH = '.' + File.separator + "config.json";

    private final Config config;
    private final PeriodicTable table;

    public Main(Config config, PeriodicTable table) {
        this.config = config;
        this.table = table;
    }

    public static void main(String[] args) throws IOException {
        String cfgPath = args.length >= 1 ? args[0] : DEFAULT_CFG_PATH;
        if (cfgPath.isEmpty()) {
            throw new IllegalArgumentException("Empty path");
        }

        p("Loading config...");
        Config config = new DefaultConfig(cfgPath);

        p("Loading periodic table data...");
        PeriodicTable table = new DefaultPeriodicTable(config.getDataSource());
        table.download();

        Main main = new Main(config, table);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(config.getPrompt());
            String input = scanner.nextLine();
            TokenTree tree = main.process(input);
            if (tree == null) {
                System.out.println();
                continue;
            }

            double molarMass = main.getMolarMass(tree);
            main.printMolarMass(molarMass);

            System.out.println();
        }
    }

    private static void p(String out) {
        System.out.println(out);
    }

    public TokenTree process(String input) {
        try {
            Parser parser = new DefaultParser(input, this.table);
            return parser.parse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getMolarMass(TokenTree tree) {
        TokenTreeParser tokenParser = new TokenTreeParser(tree);
        Map<Element, Integer> elements = tokenParser.getElementMap();

        double mass = 0.0;
        for (Entry<Element, Integer> entry : elements.entrySet()) {
            Element element = entry.getKey();
            Integer qty = entry.getValue();

            mass += qty * element.getAtomicMass();
        }

        return mass;
    }

    public void printMolarMass(double molarMass) {
        String fmt = this.config.getOutputFormat();
        fmt = fmt.replace("{0}", String.valueOf(molarMass));

        p(fmt);
    }
}
