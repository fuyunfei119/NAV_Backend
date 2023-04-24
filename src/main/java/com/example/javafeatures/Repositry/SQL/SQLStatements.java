package com.example.javafeatures.Repositry.SQL;

import java.util.ArrayList;
import java.util.List;

public class SQLStatements {
    public static final String AND = "AND";
    public static final List<String> FILTER_CONDITIONS = new ArrayList<>();

    static {
        FILTER_CONDITIONS.add(">");
        FILTER_CONDITIONS.add("<");
        FILTER_CONDITIONS.add("=");
        FILTER_CONDITIONS.add(">=");
        FILTER_CONDITIONS.add("<=");
        FILTER_CONDITIONS.add("<>");
        FILTER_CONDITIONS.add("..");
        FILTER_CONDITIONS.add("in");
    }

}
