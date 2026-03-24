package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Predicate<Map<?, ?>> sizeOfCheck = null;

    public MapSchema required() {
        super.required();
        return this;
    }

    @Override
    protected boolean isEmpty(Map<?, ?> value) {
        return false;
    }

    public MapSchema sizeof(int size) {
        if (sizeOfCheck != null) {
            getChecks().remove(sizeOfCheck);
        }
        sizeOfCheck = value -> value.size() == size;
        getChecks().add(sizeOfCheck);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        getChecks().add(map -> schemas.entrySet().stream()
                .allMatch(entry -> {
                    var key = entry.getKey();
                    BaseSchema<Object> schema = (BaseSchema<Object>) entry.getValue();
                    var value = map.get(key);
                    return schema.isValid(value);
                })
        );
        return this;
    }
}
