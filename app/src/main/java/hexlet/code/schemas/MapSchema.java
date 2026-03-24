package hexlet.code.schemas;

import hexlet.code.BaseSchema;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Predicate<Map<?, ?>> sizeOfCheck = null;

    @Override
    protected boolean isEmpty(Map<?, ?> value) {
        return false;
    }

    public MapSchema sizeOf(int size) {
        if (sizeOfCheck != null) {
            getChecks().remove(sizeOfCheck);
        }
        sizeOfCheck = value -> value.size() == size;
        getChecks().add(sizeOfCheck);
        return this;
    }
}
