package hexlet.code.schemas;

public final class StringSchema {
    private int minLength = -1;
    private boolean required = false;
    private String containsSubstring = null;

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsSubstring = substring;
        return this;
    }

    public boolean isValid(String value) {
        if (value == null || value.isEmpty()) {
            return !required;
        }
        if (minLength >= 0 && value.length() < minLength) {
            return false;
        }
        if (containsSubstring != null && !value.contains(containsSubstring)) {
            return false;
        }
        return true;
    }
}
