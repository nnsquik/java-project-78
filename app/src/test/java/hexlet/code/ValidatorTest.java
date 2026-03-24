package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {
    private static final int FIRST_MIN_LENGTH = 8;
    private static final int SECOND_MIN_LENGTH = 4;

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
        assertThat(schema.minLength(FIRST_MIN_LENGTH).minLength(SECOND_MIN_LENGTH).isValid("hello")).isTrue();
    }

    @Test
    public void testContains() {
        var schema = new Validator().string();
        schema.required();
        assertThat(schema.contains("by").isValid("Web site developed by John Doe")).isTrue();
        assertThat(schema.contains("byththe").isValid("Web site developed by John Doe")).isFalse();
        assertThat(schema.isValid("Web site developed by John Doe")).isFalse();
    }
}
