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
    public String evaluate(String statement) {
        if (statement == null) {
            return null;
        }
        PolishLineCalc polishLineCalc = new PolishLineCalc();
        String strExpr = polishLineCalc.calculate(statement);
        if (strExpr == null) {
            return null;
        }
        BigDecimal result;
        result = BigDecimal.valueOf(Double.parseDouble(strExpr));
        result.setScale(4, RoundingMode.HALF_UP);
        return result.stripTrailingZeros().toPlainString();
    }
}