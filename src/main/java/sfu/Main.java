package sfu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    GoodsReceiptModel model = processor.processInput(line);
                    System.out.println("Созданный объект: " + model);
                } catch (InvalidInputException e) {
                    System.out.println("Ошибка обработки строки: " + line);
                    System.out.println("Причина: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
