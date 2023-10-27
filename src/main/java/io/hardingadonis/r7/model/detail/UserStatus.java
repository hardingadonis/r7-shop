package io.hardingadonis.r7.model.detail;

public enum UserStatus {
    ACTIVATE("activate"),
    DEACTIVATE("deactivate");

    private final String label;

    private UserStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
