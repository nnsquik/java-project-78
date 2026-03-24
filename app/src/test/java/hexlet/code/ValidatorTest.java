package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {
    @Test
    public void testWithoutRequired() {
        var schema = new Validator().string();
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    public void testRequired() {
        var schema = new Validator().string();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("hello")).isTrue();
    }

    @Test
    public void testMinLength() {
        var schema = new Validator().string();
        int firstMinLength = 8;
        int secondMinLength = 4;
        assertThat(schema.minLength(firstMinLength).minLength(secondMinLength).isValid("hello")).isTrue();
    }

    @Test
    public void testContains() {
        var schema = new Validator().string();
        schema.required();
        assertThat(schema.contains("by").isValid("Web site developed by John Doe")).isTrue();
        assertThat(schema.contains("byththe").isValid("Web site developed by John Doe")).isFalse();
        assertThat(schema.isValid("Web site developed by John Doe")).isFalse();
    }

    @Test
    public void testNumberWithoutRequired() {
        var schema = new Validator().number();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    public void testNumberRequired() {
        var schema = new Validator().number();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(10)).isTrue();
    }

    @Test
    public void testNumberPositive() {
        var schema = new Validator().number();
        schema.positive().required();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(10)).isTrue();
    }

    @Test
    public void testNumberRange() {
        var schema = new Validator().number();
        int rangeMin = 5;
        int rangeMax = 10;
        schema.required().positive().range(rangeMin, rangeMax);
        assertThat(schema.isValid(rangeMin)).isTrue();
        assertThat(schema.isValid(rangeMax)).isTrue();
        assertThat(schema.isValid(rangeMin - 1)).isFalse();
        assertThat(schema.isValid(rangeMax + 1)).isFalse();
    }

    @Test
    public void testMapWithoutRequired() {
        var schema = new Validator().map();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    public void testMapRequired() {
        var schema = new Validator().map();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
    }

    @Test
    public void testMapSizeOf() {
        var schema = new Validator().map();
        schema.required();

        var data = new HashMap<String, String>();
        assertThat(schema.isValid(data)).isTrue();

        int expectedSize = 2;
        schema.sizeOf(expectedSize);

        assertThat(schema.isValid(data)).isFalse();
        data.put("key1", "value2");
        assertThat(schema.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();
    }
}
