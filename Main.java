import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение:");
        String equation = scanner.nextLine();

        String[] parts = equation.split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Ошибка: Введите корректное выражение");
        }

        boolean isRoman1 = isRoman(parts[0]);
        boolean isRoman2 = isRoman(parts[2]);

        if (isRoman1 != isRoman2) {
            throw new IllegalArgumentException("Ошибка: Числа должны быть либо  римские, либо  арабские");
        }

        int num1 = parseOperand(parts[0]);
        int num2 = parseOperand(parts[2]);

        validateOperands(num1, num2);

        int result = performOperation(parts[1], num1, num2);

        if(isRoman1 && isRoman2) {
            for(RomanNumeral romanNumeral: RomanNumeral.values())
                if(romanNumeral.getValue() == result) {
                    System.out.println("Результат: " + romanNumeral);
                }
        } else {
            System.out.println("Результат: " + result);
        }
    }

    private static boolean isRoman(String input) {
        return input.matches("^[IVXLCDM]+$");
    }

    private static int parseOperand(String operand) {
        try {
            return Integer.parseInt(operand);
        } catch (NumberFormatException e) {
            return parseRomanNumeral(operand);
        }
    }

    private static int parseRomanNumeral(String romanNumeral) {
        int result = 0;
        for (RomanNumeral numeral : RomanNumeral.values()) {
            if (numeral.toString().equals(romanNumeral)) {
                result = numeral.getValue();
                break;
            }
        }
        return result;
    }

    private static void validateOperands(int a, int b) {
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Ошибка: В уравнении должны быть только числа от 1 до 10");
        }
    }

    private static int performOperation(String operation, int a, int b) {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b != 0) {
                    return a / b;
                } else {
                    throw new ArithmeticException("Ошибка: Деление на ноль недопустимо");
                }
            default:
                throw new IllegalArgumentException("Ошибка: Неправильная операция: " + operation);
        }
    }
}