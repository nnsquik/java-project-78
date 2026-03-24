package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private List<Predicate<T>> checks = new ArrayList<>();
    private boolean required = false;

    protected abstract boolean isEmpty(T value);

    public final BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    public final boolean isRequired() {
        return required;
    }

    public final boolean isValid(T value) {
        if (value == null) {
            return !required;
        }

        if (required && isEmpty(value)) {
            return false;
        }
        return checks.stream().allMatch(check -> check.test(value));
    }

    public final List<Predicate<T>> getChecks() {
        return checks;
    }
}
