
import org.junit.Test;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;
import praktikum.Praktikum;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class PraktikumTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @org.junit.Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @org.junit.After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
public void constructorShouldThrowException() {
        try {
            java.lang.reflect.Constructor<Praktikum> constructor =
                    Praktikum.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
            fail("Конструктор должен выбрасывать исключение");
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            assertTrue("Исключение должно быть UnsupportedOperationException",
                    cause instanceof UnsupportedOperationException);
        } catch (Exception e) {
            fail("Должно быть выброшено InvocationTargetException с причиной UnsupportedOperationException, но получено: " + e.getClass().getName());
        }
    }

    @Test
    public void mainMethodShouldPrintIngredientsAndBuns() {
        Praktikum.main(new String[]{});
        String output = outContent.toString();

        assertTrue("Вывод должен содержать заголовок ингредиентов",
                output.contains("Доступные ингредиенты:"));
        assertTrue("Вывод должен содержать заголовок булочек",
                output.contains("Доступные булочки:"));
    }

    @Test
    public void printIngredientsShouldHandleEmptyList() {
        List<Ingredient> emptyIngredients = new ArrayList<>();
        Praktikum.printIngredients(emptyIngredients);
        String output = outContent.toString();
        assertTrue("Должен выводить сообщение для пустого списка ингредиентов",
                output.contains("- Нет доступных ингредиентов"));
    }

    @Test
    public void printBunsShouldHandleEmptyList() {
        List<Bun> emptyBuns = new ArrayList<>();
        Praktikum.printBuns(emptyBuns);
        String output = outContent.toString();
        assertTrue("Должен выводить сообщение для пустого списка булочек",
                output.contains("- Нет доступных булочек"));
    }

    @Test
    public void printIngredientsShouldPrintAllItems() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("hot sauce", 50.0F, IngredientType.SAUCE));
        ingredients.add(new Ingredient("lettuce", 20.0F, IngredientType.VEGETABLE));

        Praktikum.printIngredients(ingredients);
        String output = outContent.toString();

        assertTrue("Должен вывести hot sauce", output.contains("hot sauce"));
        assertTrue("Должен вывести lettuce", output.contains("lettuce"));
    }

    @Test
    public void printBunsShouldPrintAllItems() {
        List<Bun> buns = new ArrayList<>();
        buns.add(new Bun("black bun", 200.0F));
        buns.add(new Bun("white bun", 150.0F));

        Praktikum.printBuns(buns);
        String output = outContent.toString();

        assertTrue("Должен вывести black bun", output.contains("black bun"));
        assertTrue("Должен вывести white bun", output.contains("white bun"));
    }
}
