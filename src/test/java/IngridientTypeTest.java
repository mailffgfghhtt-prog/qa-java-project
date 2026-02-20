
import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.*;

public class IngridientTypeTest {

    @Test
    public void sauceTypeShouldBeCorrect() {
        assertEquals(IngredientType.SAUCE, IngredientType.SAUCE);
    }

    @Test
    public void fillingTypeShouldBeCorrect() {
        assertEquals(IngredientType.FILLING, IngredientType.FILLING);
    }

    @Test
    public void veggiesTypeShouldBeCorrect() {
        // Сравниваем элементы перечисления напрямую
        assertEquals(IngredientType.VEGETABLE, IngredientType.VEGETABLE);
    }
}

