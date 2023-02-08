package kata.calc;

import kata.calc.Exception.DifferentNumberSystemsException;

import java.util.Scanner;

/**
 * @author Byzov A.A.
 */
public class Main {
    static Converter converter = new Converter();
    static String[] arithmeticSigns = {"+", "-", "/", "*"};
    static String[] regexArithmeticSigns = {"\\+", "-", "/", "\\*"};

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        System.out.println("--------------------------------");
        System.out.println("Введите выражение форматом \"a+b\" (принимаются только римские (I, II, III...X) и арабские (1,2,3...10).");
        System.out.println("цифры от 1 до 10 (от I до X), а также операторы \"+\", \"-\", \"*\", \"/\".");
        System.out.println("Нельзя использовать разные типы чисел в одном выражении.");
        System.out.println("--------------------------------");
        System.out.println("Введите числовое выражение:");
        String input = console.nextLine();
        try {
            System.out.println(calc(input));
        } catch (DifferentNumberSystemsException e) {
            throw new RuntimeException(e);
        }
    }

    public static String calc(String input) throws DifferentNumberSystemsException {

        int actionIndex = -1;
        int a;
        int b;

        for (int i = 0; i < arithmeticSigns.length; i++) {
            if (input.contains(arithmeticSigns[i])) {
                actionIndex = i;
                break;
            }
        }
        if (actionIndex == -1) {
            throw new DifferentNumberSystemsException("Cтрока не является математической операцией!");
        }
        String[] data = input.split(regexArithmeticSigns[actionIndex]);
        if (data.length != 2) {
            throw new DifferentNumberSystemsException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                a = converter.romanConverter(data[0]);
                b = converter.romanConverter(data[1]);
                if (a > 10 || a < 1 && b < 1 || b > 10) {
                    throw new DifferentNumberSystemsException("Числа не удовлетворяют условию - от I до X!");
                }
            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            if (a > 10 || a < 1 && b < 1 || b > 10) {
                throw new DifferentNumberSystemsException("Числа не удовлетворяют условию - от 1 до 10!");
            }
/*
Арифметические действия
 */
            if (isRoman) {
                int calcResult = arithmeticExpression(arithmeticSigns, actionIndex, a, b);
                if (calcResult > 0) {
                    System.out.println("Результат: " + converter.arabicConverter(calcResult));
                } else {
                    throw new DifferentNumberSystemsException("В римской системе нет отрицательных чисел!");
                }
            }
            if (!isRoman) {
                int calcResult = arithmeticExpression(arithmeticSigns, actionIndex, a, b);
                System.out.println("Результат: " + calcResult);
            }

        } else {
            throw new DifferentNumberSystemsException("Используются одновременно разные системы счисления!");
        }
        return input;
    }

    private static int arithmeticExpression(String[] arithmeticSigns, int actionIndex, int a, int b) {
        int calcResult = 0;
        switch (arithmeticSigns[actionIndex]) {
            case "+" -> calcResult = a + b;
            case "-" -> calcResult = a - b;
            case "*" -> calcResult = a * b;
            case "/" -> calcResult = a / b;
        }
        return calcResult;
    }
}


