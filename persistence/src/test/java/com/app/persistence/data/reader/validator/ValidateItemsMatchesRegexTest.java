package com.app.persistence.data.reader.validator;

import com.app.persistence.data.reader.validator.exception.DataValidatorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static com.app.persistence.data.reader.validator.DataValidator.validateItemsMatchesRegex;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidateItemsMatchesRegexTest {
    @Test
    @DisplayName("When item is null or empty")
    void test1() {
        var regex = "[A-Z]*";
        var items = Set.of("TEST", "");
        assertThatThrownBy(() -> validateItemsMatchesRegex(regex, items))
                .isInstanceOf(DataValidatorException.class)
                .hasMessage("One or more items are null or empty");
    }

    @Test
    @DisplayName("When item set is null or empty")
    void test2() {
        var regex = "[A-Z]";
        var items = Collections.<String>emptySet();
        assertThatThrownBy(() -> validateItemsMatchesRegex(regex, items))
                .isInstanceOf(DataValidatorException.class)
                .hasMessage("Items set is null or empty");
    }

    @Test
    @DisplayName("When item does not match regex")
    void test3() {
        var regex = "[A-Z]";
        var items = Set.of("TeST", "T3sT", "tesT", "te$t");
        assertThatThrownBy(() -> validateItemsMatchesRegex(regex, items))
                .isInstanceOf(DataValidatorException.class)
                .hasMessage("One or more items does not match regex");
    }
}
