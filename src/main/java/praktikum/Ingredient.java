package praktikum;

public class Ingredient {
    private String name;
    private float price;
    private IngredientType type;

    public Ingredient(String name, float price, IngredientType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public IngredientType getType() {
        return type;
    }
}
