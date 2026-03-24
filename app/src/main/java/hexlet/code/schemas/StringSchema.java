package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {
    private Predicate<String> minLengthCheck = null;

    public StringSchema required() {
        super.required();
        return this;
    }

    @Override
    public boolean isEmpty(String value) {
        return value.isEmpty();
    }

    public StringSchema minLength(int length) {
        if (minLengthCheck != null) {
            getChecks().remove(minLengthCheck);
        }

        minLengthCheck = value -> value.length() >= length;
        getChecks().add(minLengthCheck);
        return this;
    }

    public StringSchema contains(String substring) {
        getChecks().add(value -> value.contains(substring));
        return this;
    }
}
