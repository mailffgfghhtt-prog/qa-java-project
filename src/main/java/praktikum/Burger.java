package praktikum;

import java.util.ArrayList;
import java.util.List;

public class Burger {
    public Bun bun;
    private int bunCount = 2; // По умолчанию — две булочки
    private List<Ingredient> ingredients = new ArrayList<>();

    public void setBuns(Bun bun) {
        this.bun = bun;
    }

    /**
     * Устанавливает количество булочек в бургере.
     * @param count количество булочек (должно быть ≥ 0)
     */
    public void setBunCount(int count) {
        if (count >= 0) {
            this.bunCount = count;
        }
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(int index) {
        if (index >= 0 && index < ingredients.size()) {
            ingredients.remove(index);
        }
    }

    public void moveIngredient(int fromIndex, int toIndex) {
        if (fromIndex >= 0 && fromIndex < ingredients.size() &&
                toIndex >= 0 && toIndex < ingredients.size()) {
            Ingredient ingredient = ingredients.remove(fromIndex);
            ingredients.add(toIndex, ingredient);
        }
    }


    public float getPrice() {
        float totalPrice = 0;

        // Добавляем цену булочек: количество × цена одной
        if (bun != null) {
            totalPrice += bun.getPrice() * bunCount;
        }

        // Добавляем цены ингредиентов (пропускаем null)
        for (Ingredient ingredient : ingredients) {
            if (ingredient != null) {
                totalPrice += ingredient.getPrice();
            }
        }

        return totalPrice;
    }

    public String getReceipt() {
        StringBuilder receipt = new StringBuilder();

        // Булочка (верхняя)
        if (bun != null && bunCount > 0) {
            receipt.append("(==== ").append(bun.getName()).append(" ====)\n");
        } else {
            receipt.append("(==== BUN MISSING ====)\n");
        }

        // Ингредиенты
        boolean hasValidIngredients = false;
        for (Ingredient ingredient : ingredients) {
            if (ingredient != null) {
                hasValidIngredients = true;
                receipt.append("= ").append(ingredient.getName())
                        .append(" (").append(ingredient.getType())
                        .append(")\n");
            }
        }

        if (!hasValidIngredients) {
            receipt.append("= no ingredients =\n");
        }

        // Нижняя булочка (если больше одной)
        if (bun != null && bunCount > 1) {
            receipt.append("(==== ").append(bun.getName()).append(" ====)\n");
        }

        // Цена
        receipt.append("\nPrice: ").append(getPrice());

        return receipt.toString();
    }
}
