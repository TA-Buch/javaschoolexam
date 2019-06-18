package com.tsystems.javaschool.tasks.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */

    final static String[] OPERATORS = {"+","-","/","*"};
    final static String DELIMETERS = String.join("",OPERATORS).concat("()");

    protected Stack<String> stackOperations = new Stack<>();
    protected Stack<String> stackPolishLine = new Stack<>();
    protected Stack<String> stackCalcExpr = new Stack<>();

    public String evaluate(String statement) {
        if (statement == null) return null;
        BigDecimal result;
        StringTokenizer tokenizingStatement = new StringTokenizer(statement, DELIMETERS, true);
        parseToPolishLine(tokenizingStatement);
        if (stackPolishLine.empty()) return null;
        Collections.reverse(stackPolishLine);
        stackCalcExpr.clear();
        while (!stackPolishLine.empty()) {
            String token = stackPolishLine.pop();
            if (isNumber(token)) {
                stackCalcExpr.push(token);
            } else if (isOperator(token)) {
                double operand1 = Double.parseDouble(stackCalcExpr.pop());
                double operand2 = Double.parseDouble(stackCalcExpr.pop());
                switch (token) {
                    case "+":
                        stackCalcExpr.push(String.valueOf(operand2+operand1));
                        break;
                    case "-":
                        stackCalcExpr.push(String.valueOf(operand2-operand1));
                        break;
                    case "*":
                        stackCalcExpr.push(String.valueOf(operand2*operand1));
                        break;
                    case "/":
                        stackCalcExpr.push(String.valueOf(operand2/operand1));
                        break;
                }
            }
        }
        result = BigDecimal.valueOf(Double.parseDouble(stackCalcExpr.pop()));
        result.setScale(4, RoundingMode.HALF_UP);
        return result.stripTrailingZeros().toPlainString();
    }

    protected void parseToPolishLine(StringTokenizer tokens) {
        String prev = "";
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if ((!tokens.hasMoreTokens() && isOperator(token)) || (isOperator(prev) && isOperator(token)))  {
                stackOperations.clear();
                stackPolishLine.clear();
                return ;
            }
            if (isNumber(token)) {
                stackPolishLine.push(token);
            } else if (isOperator(token)) {
                while (!stackOperations.empty()
                        && isOperator(stackOperations.lastElement())
                        && (getPriority(token)
                        <= getPriority(stackOperations.lastElement()))) {
                    stackPolishLine.push(stackOperations.pop());
                }
                stackOperations.push(token);
            } else if (token.equals("(")) {
                stackOperations.push(token);
            } else if (token.equals(")")) {
                while (!(stackOperations.lastElement().equals("("))) {
                    stackPolishLine.push(stackOperations.pop());
                    if (stackOperations.isEmpty()) {
                        stackOperations.clear();
                        stackPolishLine.clear();
                        return ;
                    }
                }
                stackOperations.pop();
            } else {
                stackOperations.clear();
                stackPolishLine.clear();
                return ;
            }
            prev = token;
        }
        while (!stackOperations.empty()) {
            stackPolishLine.push(stackOperations.pop());
        }
    }

    protected boolean isNumber (String token) {
        try {
            Double.parseDouble(token);
        }
        catch (Exception exc) {
            return false;
        }
        return true;
    }

    protected boolean isOperator(String token) {
        for (String op : OPERATORS)
            if (op.equals(token))
                return true;
        return false;
    }

    protected int getPriority(String operation) {
        if (operation.equals("+") || operation.equals("-"))
            return 1;
        else
            return 2;
    }
}
