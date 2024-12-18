package sfu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class for processing input data and creating a GoodsReceiptModel object.
 */
public class InputProcessor {

    /**
     * A formatter for converting strings to dates in the format "yyyy.MM.dd".
     */
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy.MM.dd");

    /**
     * Processes the input data and creates a GoodsReceiptModel object.
     *
     * @param input is a string with data to be processed.
     * @return GoodsReceiptModel object.
     * @throws InvalidInputException if the input data is incorrect.
     */
    public GoodsReceiptModel processInput(final String input)
            throws InvalidInputException {
        if (input == null || input.isBlank()) {
            throw new InvalidInputException("Входная строка пуста.");
        }

        String[] parts = input.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        // Using a constant with the correct naming
        final int expectedPartsLength = 3;
        if (parts.length < expectedPartsLength) {
            throw new InvalidInputException(
                    "Недостаточно аргументов. Пример: 2023.09.15 \"Кофе\" 5");
        }

        LocalDate date = parseDate(parts[0]);
        String productName = parseProductName(parts[1]);
        int quantity = parseQuantity(parts[2]);

        return new GoodsReceiptModel(date, productName, quantity);
    }

    /**
     * Parses a string into a date.
     *
     * @param dateStr date string.
     * @return LocalDate object.
     * @throws InvalidInputException if the date is incorrect.
     */
    private LocalDate parseDate(final String dateStr)
            throws InvalidInputException {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(
                    "Некорректный формат даты. Ожидается формат: " + dateStr);
        }
    }

    /**
     * Parses the product name.
     *
     * @param productName a string with the product name.
     * @return product name without quotes.
     */
    private String parseProductName(final String productName) {
        return productName.replaceAll("\"", "").trim();
    }

    /**
     * Parses the string into a quantity.
     *
     * @param quantityStr is a string with a quantity.
     * @return quantity.
     * @throws InvalidInputException if the quantity is incorrect.
     */
    private int parseQuantity(
            final String quantityStr) throws InvalidInputException {
        if (quantityStr == null || quantityStr.isEmpty()) {
            throw new InvalidInputException(
                    "Количество не может быть пустым."
            );
        }
        try {
            return Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(
                    "Количество должно быть целым числом: "
                            + quantityStr);
        }
    }
}
