package com.app.persistence.data.reader.validator;

import com.app.persistence.data.reader.validator.exception.DataValidatorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.app.persistence.data.reader.validator.DataValidator.validatePositiveInt;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatePositiveIntTest {
    @ParameterizedTest
    @ValueSource(ints = {-10, -42, 0, -1})
    @DisplayName("When value is less than or equals zero")
    void test1(int value) {
        assertThatThrownBy(() -> validatePositiveInt(value))
                .isInstanceOf(DataValidatorException.class)
                .hasMessage("Value is less than or equals zero");
    }
}
