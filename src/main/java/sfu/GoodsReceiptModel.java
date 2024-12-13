package sfu;

import java.time.LocalDate;

/**
 * Represents a goods receipt model with a date, product name, and quantity.
 *
 * @param date        The date of the goods receipt.
 * @param productName The name of the product.
 * @param quantity    The quantity of the product.
 */
public record GoodsReceiptModel(LocalDate date,
                                String productName,
                                int quantity) {

    /**
     * Constructs a new GoodsReceiptModel.
     *
     * @param date        the date of the goods receipt
     * @param productName the name of the product
     * @param quantity    the quantity of the product
     */
    public GoodsReceiptModel {
        if (date == null) {
            throw new IllegalArgumentException(
                    "Дата не может быть null."
            );
        }
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException(
                    "Название товара не может быть пустым."
            );
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Количество должно быть положительным числом."
            );
        }
    }

    /**
     * Gets the date of the goods receipt.
     *
     * @return the date
     */
    @Override
    public LocalDate date() {
        return date;
    }

    /**
     * Gets the quantity of the product.
     *
     * @return the quantity
     */
    @Override
    public int quantity() {
        return quantity;
    }

    /**
     * Gets the name of the product.
     *
     * @return the product name
     */
    @Override
    public String productName() {
        return productName;
    }

    /**
     * Returns a string representation of the goods receipt model.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "GoodsReceiptModel{"
                + "date=" + date
                + ", productName='" + productName + '\''
                + ", quantity=" + quantity
                + '}';
    }
}
