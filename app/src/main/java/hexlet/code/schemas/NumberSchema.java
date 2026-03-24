package hexlet.code.schemas;

import hexlet.code.BaseSchema;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema positive() {
        getChecks().add(value -> value > 0);
        return this;
    }

    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema range(int min, int max) {
        getChecks().add(value -> value >= min && value <= max);
        return this;
    }

    @Override
    protected boolean isEmpty(Integer value) {
        return false;
    }
}
