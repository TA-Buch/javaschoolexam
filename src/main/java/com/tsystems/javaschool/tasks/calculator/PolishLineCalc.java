package com.tsystems.javaschool.tasks.calculator;

import java.util.Collections;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PolishLineCalc {
    /**
     * Evaluate statement by representing expressions as Reverse Polish notation.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     * @return string value containing result of evaluation or null (in case of zero dividing)
     */

    private static final Set<String> OPERATORS = Stream.of("+","-","/","*").collect(Collectors.toSet());
    private static final  String DELIMITERS = String.join("",OPERATORS).concat("()");

    private Stack<String> stackOperations = new Stack<>();
    private Stack<String> stackPolishLine = new Stack<>();
    private Stack<String> stackCalcExpr = new Stack<>();

    public String calculate(String statement) {
        clearContext();
        StringTokenizer tokenizingStatement = new StringTokenizer(statement, DELIMITERS, true);
        parseToPolishLine(tokenizingStatement);
        if (stackPolishLine.empty()) {
            return null;
        }
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
                        if (operand1 == 0) {
                            return null;
                        }
                        stackCalcExpr.push(String.valueOf(operand2/operand1));
                        break;
                }
            } else {
                return null;
            }
        }
        return stackCalcExpr.pop();
    }

    private void parseToPolishLine(StringTokenizer tokens) {
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

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (Exception exc) {
            return false;
        }
        return true;
    }

    private static boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    private static int getPriority(String operation) {
        if ("+".equals(operation) || "-".equals(operation)) {
            return 1;
        } else {
            return 2;
        }
    }

    private void clearContext() {
        stackOperations.clear();
        stackPolishLine.clear();
        stackCalcExpr.clear();
    }
}
