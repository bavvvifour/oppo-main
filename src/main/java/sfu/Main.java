package sfu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Main class for processing goods receipt input.
 */
final class Main {

    // Private constructor to prevent instantiation
    private Main() {
        // Utility class, no need for instantiation
    }

    /**
     * Main method to start the program.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        init();
    }

    /**
     * Processes user input for goods receipt.
     */
    public static void init() {
        String filePath = "data.txt";
        InputProcessor processor = new InputProcessor();
        List<GoodsReceiptModel> goodsReceipts = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    GoodsReceiptModel model = processor.processInput(line);
                    goodsReceipts.add(model);
                } catch (InvalidInputException e) {
                    System.out.println("Ошибка обработки строки: " + line);
                    System.out.println("Причина: " + e.getMessage());
                }
            }

            // Sorting by date
            goodsReceipts.sort(Comparator.comparing(GoodsReceiptModel::date));

            // Output of sorted data
            System.out.println("Отсортированные данные:");
            for (GoodsReceiptModel model : goodsReceipts) {
                System.out.println(model);
            }

            // Find the date with the maximum receipt of goods
            Map<LocalDate, Integer> dateToTotalQuantity = new HashMap<>();

            for (GoodsReceiptModel model : goodsReceipts) {
                dateToTotalQuantity.merge(
                        model.date(),
                        model.quantity(),
                        Integer::sum);
            }

            Optional<Map.Entry<LocalDate, Integer>> maxEntry =
                    dateToTotalQuantity.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue());

            if (maxEntry.isPresent()) {
                LocalDate maxDate = maxEntry.get().getKey();
                int maxQuantity = maxEntry.get().getValue();

                System.out.println(
                        "\nДата с максимальным поступлением товаров: " + maxDate
                );
                System.out.println("Общее количество товаров: " + maxQuantity);
                System.out.println("Список товаров в этот день:");

                List<GoodsReceiptModel> maxDayReceipts = goodsReceipts.stream()
                        .filter(model -> model.date().equals(maxDate))
                        .collect(Collectors.toList());

                for (GoodsReceiptModel model : maxDayReceipts) {
                    System.out.println(model);
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
