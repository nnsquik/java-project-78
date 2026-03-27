package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        super.required();
        return this;
    }

    @Override
    protected boolean isEmpty(Map<?, ?> value) {
        return false;
    }

    public MapSchema sizeof(int size) {
        getChecks().add(value -> value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
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
