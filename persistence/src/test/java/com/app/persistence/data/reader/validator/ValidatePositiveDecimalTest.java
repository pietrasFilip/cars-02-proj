package com.app.persistence.data.reader.validator;

import com.app.persistence.data.reader.validator.exception.DataValidatorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.app.persistence.data.reader.validator.DataValidator.validatePositiveDecimal;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatePositiveDecimalTest {

    @Test
    @DisplayName("When its negative decimal")
    void test1() {
        var value = BigDecimal.valueOf(-0.01);
        assertThatThrownBy(() -> validatePositiveDecimal(value))
                .isInstanceOf(DataValidatorException.class)
                .hasMessage("Value is less than zero");
    }
}
