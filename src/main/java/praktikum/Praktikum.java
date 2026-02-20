package praktikum;

import java.util.List;

public class Praktikum {
    /**
     * Приватный конструктор для предотвращения создания экземпляров класса.
     * Класс содержит только статические методы.
     */
    private Praktikum() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static void main(String[] args) {
        Database database = new Database();
        List<Ingredient> ingredients = database.availableIngredients();
        List<Bun> buns = database.availableBuns();

        printIngredients(ingredients);
        printBuns(buns);
    }

    /**
     * Выводит список ингредиентов в консоль
     */
    public static void printIngredients(List<Ingredient> ingredients) {
        System.out.println("Доступные ингредиенты:");
        if (ingredients.isEmpty()) {
            System.out.println("- Нет доступных ингредиентов");
            return;
        }
        for (Ingredient ingredient : ingredients) {
            System.out.println("- " + ingredient.getName() + " (" + ingredient.getType() + ")");
        }
    }

    /**
     * Выводит список булочек в консоль
     */
    public static void printBuns(List<Bun> buns) {
        System.out.println("\nДоступные булочки:");
        if (buns.isEmpty()) {
            System.out.println("- Нет доступных булочек");
            return;
        }
        for (Bun bun : buns) {
            System.out.println("- " + bun.getName() + " (цена: " + bun.getPrice() + ")");
        }
    }
}
