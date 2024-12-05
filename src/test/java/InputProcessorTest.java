import org.junit.Test;
import sfu.GoodsReceiptModel;
import sfu.InputProcessor;
import sfu.InvalidInputException;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class InputProcessorTest {
    private final InputProcessor processor = new InputProcessor();

    @Test
    public void testValidInput() throws InvalidInputException {
        String input = "2023.09.15 \"Кофе\" 5";
        GoodsReceiptModel model = processor.processInput(input);

        assertEquals(LocalDate.of(2023, 9, 15), model.getDate());
        assertEquals("Кофе", model.getProductName());
        assertEquals(5, model.getQuantity());
    }

    @Test
    public void testInvalidDateFormat() {
        String input = "15.09.2023 \"Кофе\" 5";
        Exception exception = assertThrows(InvalidInputException.class, () -> processor.processInput(input));

        assertEquals("Некорректный формат даты. Ожидается формат: 15.09.2023", exception.getMessage());
    }

    @Test
    public void testMissingArguments() {
        String input = "2023.09.15 \"Кофе\"";
        Exception exception = assertThrows(InvalidInputException.class, () -> processor.processInput(input));

        assertEquals("Недостаточно аргументов. Пример: 2023.09.15 \"Кофе\" 5", exception.getMessage());
    }

    @Test
    public void testInvalidQuantity() {
        String input = "2023.09.15 \"Кофе\" пять";
        Exception exception = assertThrows(InvalidInputException.class, () -> processor.processInput(input));

        assertEquals("Количество должно быть целым числом: пять", exception.getMessage());
    }

    @Test
    public void testInvalidInput() {
        String input = "";
        Exception exception = assertThrows(InvalidInputException.class, () -> processor.processInput(input));

        assertEquals("Входная строка пуста.", exception.getMessage());
    }
}
