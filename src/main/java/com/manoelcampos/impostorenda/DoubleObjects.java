package com.manoelcampos.impostorenda;

public class DoubleObjects {
    public static double requiresNonNegative(double value) {
        if (value < 0) {
            throw new IllegalArgumentException(String.format("o valor não pode ser negativo: %.2f", value));
        }

        return value;
    }
}
