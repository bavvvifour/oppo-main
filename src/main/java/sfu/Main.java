package sfu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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
                String[] parts = input.split("\\s+");

                if (parts.length < 3) {
                    throw new Exception("Некорректное количество аргументов. Пример: 2023.09.15 \"Кофе\" 5");
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                LocalDate date;

                try {
                    date = LocalDate.parse(parts[0], formatter);
                } catch (DateTimeParseException e) {
                    throw new Exception("Некорректный формат даты. Ожидается формат: yyyy.MM.dd");
                }

                int quantity;

                try {
                    quantity = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    throw new Exception("Количество должно быть целым числом.");
                }

                String productName = parts[1].replaceAll("\"", "");

                GoodsReceiptModel model = new GoodsReceiptModel(date, productName, quantity);
                System.out.println("Созданный объект: " + model);

            } catch (Exception e) {
                System.out.println("Ошибка при обработке строки: " + e.getMessage());
            }
        }
    }
}
