package praktikum;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Ingredient> ingredients = new ArrayList<>();

    public Database() {
        addIngredient(new Ingredient("hot sauce", 50.0F, IngredientType.SAUCE));
        addIngredient(new Ingredient("chili sauce", 60.0F, IngredientType.SAUCE));
        addIngredient(new Ingredient("cutlet", 100.0F, IngredientType.FILLING));
        addIngredient(new Ingredient("lettuce", 20.0F, IngredientType.VEGETABLE));
        addIngredient(new Ingredient("cheese", 30.0F, IngredientType.CHEESE));
        addIngredient(new Ingredient("onion", 10.0F, IngredientType.VEGETABLE));
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredient != null) {
            ingredients.add(ingredient);
        }
    }

    public List<Ingredient> availableIngredients() {
        return new ArrayList<>(ingredients);
    }

    // Дополнительный метод для работы с булочками (решает ошибку в Praktikum.java)
    public List<Bun> availableBuns() {
        List<Bun> buns = new ArrayList<>();
        buns.add(new Bun("black bun", 200.0F));
        buns.add(new Bun("white bun", 150.0F));
        return buns;
    }
}
