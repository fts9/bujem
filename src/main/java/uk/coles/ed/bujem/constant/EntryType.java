package uk.coles.ed.bujem.constant;

public enum EntryType {
    NOTE("\u2043"),
    TASK("\u2202"),
    EVENT("\u25e6"),
    CUSTOM("\u2023");

    private String prefix;

    private EntryType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
