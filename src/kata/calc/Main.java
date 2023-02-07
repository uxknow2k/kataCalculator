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
            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
/*
Арифметические действия
 */
            int result = switch (arithmeticSigns[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> a / b;
                default -> 0;
            };

            System.out.println("Результат: " + result);
        }
    }
}

