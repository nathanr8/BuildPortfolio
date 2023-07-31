package ui;

public enum ButtonNames {
    VIEW_PORTFOLIOS("View Portfolios"),
    SAVE("Save"),
    LOAD("Load"),
    VIEW_INVESTMENTS("View Investments");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    //EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
