package org.testinfected.petstore.validation;

import java.io.Serializable;

public class NotEmpty implements Serializable, Constraint<String> {

    private static final String EMPTY = "empty";

    private String value;

    public NotEmpty(String value) {
        this.value = value == null? null : value.trim();
    }

    public String get() {
        return value;
    }

    public void check(Path path, Report report) {
        if (!satisfied()) report.violation(path, EMPTY, value);
    }

    private boolean satisfied() {
        return value != null && !value.isEmpty();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        @SuppressWarnings("unchecked")
        NotEmpty notEmpty = (NotEmpty) o;

        if (value != null ? !value.equals(notEmpty.value) : notEmpty.value != null) return false;

        return true;
    }

    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
