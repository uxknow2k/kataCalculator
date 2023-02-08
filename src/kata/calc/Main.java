package kata.calc;

import kata.calc.Exception.DifferentNumberSystemsException;

import java.util.Scanner;

/**
 * @author Byzov A.A.
 */
public class Main {
    public static void main(String[] args) throws DifferentNumberSystemsException {
        Converter converter = new Converter();
        String[] arithmeticSigns = {"+", "-", "/", "*"};
        String[] regexArithmeticSigns = {"\\+", "-", "/", "\\*"};

        Scanner calc = new Scanner(System.in);
        System.out.println("Введите числовое выражение:");
        String exp = calc.nextLine();
        int actionIndex = -1;
        for (int i = 0; i < arithmeticSigns.length; i++) {
            if (exp.contains(arithmeticSigns[i])) {
                actionIndex = i;
                break;
            }
        }
        if (actionIndex == -1) {
            throw new DifferentNumberSystemsException("Cтрока не является математической операцией!");
        }
        String[] data = exp.split(regexArithmeticSigns[actionIndex]);
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
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
            int result = 0;
            switch (arithmeticSigns[actionIndex]) {
                case "+" -> result = a + b;
                case "-" -> result = a - b;
                case "*" -> result = a * b;
                case "/" -> result = a / b;
            }

            if (isRoman && result > 0) {
                System.out.println("Результат: " + converter.arabicConverter(result));
            } else {
                throw new DifferentNumberSystemsException("Римские числа не могут быть отрицательными!");
            }
        } else {
            throw new DifferentNumberSystemsException("Используются одновременно разные системы счисления!");
        }
    }
}


