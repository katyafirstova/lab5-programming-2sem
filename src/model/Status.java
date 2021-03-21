package model;

public enum Status {
    FIRED("уволен"),
    HIRED("приянт на работу"),
    RECOMMENDED_FOR_PROMOTION("рекомендован к повышению"),
    REGULAR("постоянный работник"),
    PROBATION("испытательный срок");

    String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        String s = "FIRED";
        System.out.println(Status.fromStr(s).getName());

    }
    public static Status fromStr(String cmd) {
        for (Status s : Status.values()) {
            if (cmd != null && cmd.equals(s.name())) {
                return s;
            }
        }
        return null;
    }
}
