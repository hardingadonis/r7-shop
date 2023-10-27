package io.hardingadonis.r7.model.detail;

public enum AdminRole {
    SUPER_ADMIN("super_admin"),
    ADMIN("admin");

    private final String label;

    private AdminRole(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
