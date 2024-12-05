package sfu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        Scanner scanner = new Scanner(System.in);
        InputProcessor processor = new InputProcessor();

        System.out.println("Введите описание объекта.");
        System.out.println("Пример: 2023.09.15 \"Кофе\" 5");
        System.out.println("Введите 'exit', чтобы выйти из программы.");

        while (true) {
            System.out.print("Введите данные: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Программа завершена.");
                break;
            }

            try {
                GoodsReceiptModel model = processor.processInput(input);
                System.out.println("Созданный объект: " + model);
            } catch (InvalidInputException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
