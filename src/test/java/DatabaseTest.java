import org.junit.Test;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;

import static org.junit.Assert.*;
import java.util.List;

public class DatabaseTest {

    @Test
    public void constructorInitializesIngredientsCorrectly() {
        Database db = new Database();
        List<Ingredient> ingredients = db.availableIngredients();

        System.out.println("Количество ингредиентов в базе: " + ingredients.size());
        for (Ingredient i : ingredients) {
            System.out.println("- " + i.getName() + " (" + i.getType() + ")");
        }

        assertEquals("База данных должна содержать ровно 6 ингредиентов", 6, ingredients.size());
    }

    @Test
    public void availableIngredientsReturnsCorrectData() {
        Database db = new Database();
        List<Ingredient> ingredients = db.availableIngredients();

        assertEquals("Должно быть 6 ингредиентов", 6, ingredients.size());

        boolean hasHotSauce = ingredients.stream()
                .anyMatch(i -> i.getName().equals("hot sauce"));
        assertTrue("База данных должна содержать hot sauce", hasHotSauce);

        boolean hasCutlet = ingredients.stream()
                .anyMatch(i -> i.getName().equals("cutlet"));
        assertTrue("База данных должна содержать cutlet", hasCutlet);
    }

    @Test
    public void databaseShouldNotContainNullIngredients() {
        Database db = new Database();
        List<Ingredient> ingredients = db.availableIngredients();

        boolean hasNull = ingredients.contains(null);
        assertFalse("База данных не должна содержать null‑ингредиенты", hasNull);
    }

    @Test
    public void ingredientsShouldHaveCorrectPrices() {
        Database db = new Database();
        List<Ingredient> ingredients = db.availableIngredients();

        Ingredient hotSauce = ingredients.stream()
                .filter(i -> i.getName().equals("hot sauce"))
                .findFirst()
                .orElse(null);

        assertNotNull("hot sauce должен существовать", hotSauce);
        assertEquals("Цена hot sauce должна быть 50.0", 50.0F, hotSauce.getPrice(), 0);
    }

    @Test
    public void availableBunsReturnsCorrectData() {
        Database db = new Database();
        List<Bun> buns = db.availableBuns();

        assertEquals("Должно быть 2 булочки", 2, buns.size());

        boolean hasBlackBun = buns.stream()
                .anyMatch(b -> b.getName().equals("black bun"));
        assertTrue("Должна быть black bun", hasBlackBun);
    }
}
