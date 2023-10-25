package com.app.persistence.data.reader.validator;

import com.app.persistence.data.reader.validator.exception.DataValidatorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.app.persistence.data.reader.validator.DataValidator.validatePositiveDouble;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatePositiveDoubleTest {
    @ParameterizedTest
    @ValueSource(doubles = {-1.3, -10.42, -0.01, -120.498})
    @DisplayName("When number has negative value")
    void test1(double number) {
        assertThatThrownBy(() -> validatePositiveDouble(number))
                .isInstanceOf(DataValidatorException.class)
                .hasMessage("Given number is less than zero");
    }
}
