package com.tsystems.javaschool.tasks.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    private PolishLineCalc polishLineCalc = new PolishLineCalc();
    private static final int SCALE = 4;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public String evaluate(String statement) {
        if (statement == null) {
            return null;
        }
        String strExpr = polishLineCalc.calculate(statement);
        if (strExpr == null) {
            return null;
        }
        return BigDecimal.valueOf(Double.parseDouble(strExpr))
                .setScale(SCALE, ROUNDING_MODE)
                .stripTrailingZeros()
                .toPlainString();
    }
}