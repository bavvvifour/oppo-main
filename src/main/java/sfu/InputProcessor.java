package sfu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputProcessor {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public GoodsReceiptModel processInput(String input) throws InvalidInputException {
        if (input == null || input.isBlank()) {
            throw new InvalidInputException("Входная строка пуста.");
        }

        String[] parts = input.split("\\s+");

        if (parts.length < 3) {
            throw new InvalidInputException("Недостаточно аргументов. Пример: 2023.09.15 \"Кофе\" 5");
        }

        LocalDate date = parseDate(parts[0]);
        String productName = parseProductName(parts[1]);
        int quantity = parseQuantity(parts[2]);

        return new GoodsReceiptModel(date, productName, quantity);
    }

    private LocalDate parseDate(String dateStr) throws InvalidInputException {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Некорректный формат даты. Ожидается формат: " + dateStr);
        }
    }

    private String parseProductName(String productName) {
        return productName.replaceAll("\"", "").trim();
    }

    private int parseQuantity(String quantityStr) throws InvalidInputException {
        try {
            return Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Количество должно быть целым числом: " + quantityStr);
        }
    }
}