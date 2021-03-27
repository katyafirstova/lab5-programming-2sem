package model;

import core.CommandCollection;

public enum Color {
    BLACK("black"),
    WHITE("white"),
    BROWN("brown");

    private String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Color fromStr(String cmd) {
        for (Color s : Color.values()) {
            if (cmd != null && cmd.equals(s.name())) {
                return s;
            }
        }
        return null;
    }
}
