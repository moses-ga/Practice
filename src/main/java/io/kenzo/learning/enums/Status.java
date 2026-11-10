package io.kenzo.learning.enums;

public enum Status {
    DRAFT("DRAFT"),
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    SUBMITTED("SUBMITTED"),
    SUSPENDED("SUSPENDED"),
    ACTIVE("Active"),
    REMOVED("Removed"),
    FAILED("FAILED"),
    PROCESSING("PROCESSING"),
    INACTIVE("Inactive");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
