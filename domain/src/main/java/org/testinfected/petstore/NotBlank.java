package org.testinfected.petstore;

import java.io.Serializable;

public class NotBlank implements Serializable, Constraint<String> {

    private static final String BLANK = "blank";

    private String value;

    public NotBlank(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

    public void check(String path, Validation validation) {
        if (!satisfied()) validation.report(path, BLANK, value);
    }

    private boolean satisfied() {
        return value != null && !value.trim().isEmpty();
    }
}
