package com.app.persistence.data.reader.validator;

import com.app.persistence.data.reader.validator.exception.DataValidatorException;

import java.math.BigDecimal;
import java.util.Set;

import static java.math.BigDecimal.ZERO;

public interface DataValidator <T>{
    T validate(T t);
    static double validatePositiveDouble(double value) {
        if (value < 0) {
            throw new DataValidatorException("Given number is less than zero");
        }
        return value;
    }
    static String validateMatchesRegex(String regex, String model) {
        if (model == null || model.isEmpty()) {
            throw new DataValidatorException("Model is null or empty");
        }
        if (!model.matches(regex)) {
            throw new DataValidatorException("Model does not match the regex");
        }
        return model;
    }

    static int validatePositiveInt(int value) {
        if (value <= 0) {
            throw new DataValidatorException("Value is less than or equals zero");
        }
        return value;
    }

    static Set<String> validateItemsMatchesRegex(String regex, Set<String> items) {
        if (items.isEmpty()) {
            throw new DataValidatorException("Items set is null or empty");
        }

        items
                .forEach(item -> {
                    if (!item.matches(regex)) {
                        throw new DataValidatorException("One or more items does not match regex");
                    }
                    if (item.isEmpty()) {
                        throw new DataValidatorException("One or more items are null or empty");
                    }
                });
        return items;
    }

    static BigDecimal validatePositiveDecimal(BigDecimal value) {
        if (value.compareTo(ZERO) < 0) {
            throw new DataValidatorException("Value is less than zero");
        }
        return value;
    }

    static <T> T validateNull(T t) {
        if (t == null) {
            throw new DataValidatorException("Is null");
        }
        return t;
    }
}
