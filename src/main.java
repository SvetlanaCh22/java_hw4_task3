// В калькулятор добавьте возможность отменить последнюю операцию.

// Чубченко Светлана

import java.util.Scanner;

public class main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // вывод в консоль на английском, так как не смогла
        // победить кракозябры в консоли Windows 11 (настройки не помогают)
        int num1 = GetInt(1); // число 1
        char operation = GetOperation(); // операция вычисления
        int num2 = GetInt(2); // число 2
        int num1back; // копия предыдущего вычисления для Undo
        // вычисляем как обычный калькулятор - в каждом следующем
        // выражении первое число - уже имеющийся результат
        while ((num1 > 0) && (num2 > 0)) {
            int result = Calc(num1, num2, operation);
            System.out.println("Result (used next time as first number): " + result);
            num1back = num1; // число 1
            num1 = result; // число 1
            operation = GetOperation(); // операция вычисления
            // если введено "c" - делаем отмену предыдущего вычисления
            String ch = Character.toString(operation);
            ch = ch.toLowerCase();
            // проверяем и русский и английский вариант буквы C
            // на случай неверной раскладки
            if (ch.equals("c") || ch.equals("с")) {
                num1 = num1back;
                System.out.println("number 1 is reset to previous value " + num1);
                operation = GetOperation(); // операция вычисления
            }
            num2 = GetInt(2); // число 2
        }
    }

    public static int GetInt(int n){
        System.out.print("Please enter number " + n + ": ");
        int num;
        if(scanner.hasNextInt()){
            num = scanner.nextInt();
        } else {
            System.out.println("Error. Please enter integer.");
            scanner.next(); // рекурсия
            num = GetInt(n);
        }
        return num;
    }

    public static char GetOperation(){
        System.out.print("Please enter operation: ");
        char operation;
        if(scanner.hasNext()){
            operation = scanner.next().charAt(0);
        } else {
            System.out.println("Error. Please enter a char.");
            scanner.next(); // рекурсия
            operation = GetOperation();
        }
        return operation;
    }

    public static int Calc(int num1, int num2, char operation){
        int result;
        switch (operation) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> result = num1 / num2;
            default -> {
                System.out.println("Unknown operation.");
                result = Calc(num1, num2, GetOperation()); // рекурсия
            }
        }
        return result;
    }
}

