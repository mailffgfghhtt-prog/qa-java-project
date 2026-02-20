

import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;

public class IngridientTest {

    @Test
    public void ingredientShouldHaveCorrectName() {
        Ingredient ingredient = new Ingredient("hot sauce", 50.0F, IngredientType.SAUCE);
        assertEquals("Название должно быть hot sauce", "hot sauce", ingredient.getName());
    }

    @Test
    public void ingredientShouldHaveCorrectPrice() {
        Ingredient ingredient = new Ingredient("chili sauce", 60.0F, IngredientType.SAUCE);
        assertEquals("Цена должна быть 60.0", 60.0F, ingredient.getPrice(), 0);
    }

    @Test
    public void ingredientShouldHaveCorrectType() {
        Ingredient ingredient = new Ingredient("cutlet", 100.0F, IngredientType.FILLING);
        assertEquals("Тип должен быть FILLING", IngredientType.FILLING, ingredient.getType());
    }

    @Test
    public void ingredientTypeShouldBeCorrectForSauce() {
        Ingredient sauce = new Ingredient("special sauce", 45.0F, IngredientType.SAUCE);
        assertEquals(IngredientType.SAUCE, sauce.getType());
    }

    @Test
    public void ingredientTypeShouldBeCorrectForFilling() {
        Ingredient filling = new Ingredient("beef patty", 80.0F, IngredientType.FILLING);
        assertEquals(IngredientType.FILLING, filling.getType());
    }

    @Test
    public void ingredientTypeShouldBeCorrectForVegetable() {
        Ingredient vegetable = new Ingredient("tomato", 15.0F, IngredientType.VEGETABLE);
        assertEquals(IngredientType.VEGETABLE, vegetable.getType());
    }

    @Test
    public void ingredientTypeShouldBeCorrectForCheese() {
        Ingredient cheese = new Ingredient("swiss cheese", 35.0F, IngredientType.CHEESE);
        assertEquals(IngredientType.CHEESE, cheese.getType());
    }
}
