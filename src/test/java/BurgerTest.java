import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient0;
    private Ingredient ingredient1;

    @Before
    public void setUp() {
        burger = new Burger();
        bun = mock(Bun.class);
        ingredient0 = mock(Ingredient.class);
        ingredient1 = mock(Ingredient.class);
    }

    @Test
    public void complexFunctionalityTest() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient0);
        burger.addIngredient(null);
        burger.addIngredient(ingredient1);

        when(bun.getName()).thenReturn("deluxe bun");
        when(bun.getPrice()).thenReturn(120.0F);
        when(ingredient0.getName()).thenReturn("beef patty");
        when(ingredient0.getPrice()).thenReturn(80.0F);
        when(ingredient0.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient1.getName()).thenReturn("lettuce");
        when(ingredient1.getPrice()).thenReturn(20.0F);
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);

        String receipt = burger.getReceipt().toLowerCase();
        assertTrue("Чек должен содержать lettuce", receipt.contains("lettuce"));
    }


    @Test
    public void getPriceWithSingleBunTest() {
        burger.setBuns(bun);
        burger.setBunCount(1); // Устанавливаем одну булочку
        when(bun.getName()).thenReturn("single bun");
        when(bun.getPrice()).thenReturn(60.0F);

        float price = burger.getPrice();
        assertEquals("Цена с одной булочкой должна быть 60.0", 60.0F, price, 0);
    }

    @Test
    public void getReceiptWithEmptyIngredientsTest() {
        burger.setBuns(bun);
        when(bun.getName()).thenReturn("test bun");
        when(bun.getPrice()).thenReturn(50.0F);

        String receipt = burger.getReceipt().toLowerCase();
        assertNotNull("Чек не должен быть null", receipt);
        assertTrue("Чек должен содержать название булочки", receipt.contains("test bun"));
        assertTrue("Чек должен указывать на отсутствие ингредиентов", receipt.contains("no ingredients"));
        assertTrue("Цена должна быть 100.0", receipt.contains("price: 100.0"));
    }

    @Test
    public void emptyBurgerReceiptTest() {
        String receipt = burger.getReceipt().toLowerCase();
        assertNotNull("Чек не должен быть null", receipt);
        assertTrue("Чек должен указывать на отсутствующую булочку", receipt.contains("bun missing"));
        assertTrue("Чек должен указывать на отсутствие ингредиентов", receipt.contains("no ingredients"));
        assertTrue("Цена должна быть 0.0", receipt.contains("price: 0.0"));
    }

    @Test
    public void addAndRemoveIngredientTest() {
        burger.setBuns(bun);
        when(bun.getName()).thenReturn("test bun");
        when(bun.getPrice()).thenReturn(50.0F);
        when(ingredient0.getName()).thenReturn("cheese");
        when(ingredient0.getPrice()).thenReturn(30.0F);
        when(ingredient0.getType()).thenReturn(IngredientType.FILLING);

        // Добавляем ингредиент
        burger.addIngredient(ingredient0);

        // Проверяем цену после добавления
        float priceAfterAdd = burger.getPrice();
        assertEquals("Цена после добавления ингредиента должна быть 130.0 (2×50 + 30)",
                130.0F, priceAfterAdd, 0);

        // Получаем чек после добавления
        String receiptAfterAdd = burger.getReceipt().toLowerCase();
        assertTrue("Чек после добавления должен содержать cheese", receiptAfterAdd.contains("cheese"));
        assertTrue("Чек после добавления должен содержать test bun", receiptAfterAdd.contains("test bun"));
        assertTrue("Цена в чеке после добавления должна быть 130.0",
                receiptAfterAdd.contains("price: 130.0"));

        // Удаляем ингредиент
        burger.removeIngredient(0);

        // Проверяем цену после удаления
        float priceAfterRemove = burger.getPrice();
        assertEquals("Цена после удаления ингредиента должна быть 100.0 (только булочки)",
                100.0F, priceAfterRemove, 0);

        // Получаем чек после удаления
        String receiptAfterRemove = burger.getReceipt().toLowerCase();
        assertFalse("Чек после удаления не должен содержать cheese",
                receiptAfterRemove.contains("cheese"));
        assertTrue("Чек после удаления должен содержать test bun", receiptAfterRemove.contains("test bun"));
        assertTrue("Цена в чеке после удаления должна быть 100.0",
                receiptAfterRemove.contains("price: 100.0"));
        assertTrue("Чек после удаления должен указывать на отсутствие ингредиентов",
                receiptAfterRemove.contains("no ingredients"));
    }

    @Test
    public void moveIngredientTest() {
        burger.setBuns(bun);
        when(bun.getName()).thenReturn("classic bun");
        when(bun.getPrice()).thenReturn(40.0F);

        Ingredient lettuce = mock(Ingredient.class);
        Ingredient patty = mock(Ingredient.class);

        when(lettuce.getName()).thenReturn("lettuce");
        when(lettuce.getPrice()).thenReturn(15.0F);
        when(lettuce.getType()).thenReturn(IngredientType.SAUCE);

        when(patty.getName()).thenReturn("beef patty");
        when(patty.getPrice()).thenReturn(70.0F);
        when(patty.getType()).thenReturn(IngredientType.FILLING);

        // Добавляем ингредиенты в определённом порядке
        burger.addIngredient(lettuce);
        burger.addIngredient(patty);

        // Проверяем начальную цену
        float initialPrice = burger.getPrice();
        assertEquals("Начальная цена должна быть 165.0 (2×40 + 15 + 70)", 165.0F, initialPrice, 0);

        // Перемещаем lettuce с позиции 0 на позицию 1
        burger.moveIngredient(0, 1);

        // Проверяем, что цена не изменилась
        float movedPrice = burger.getPrice();
        assertEquals("Цена после перемещения не должна измениться и должна быть 165.0", 165.0F, movedPrice, 0);

        // Проверяем порядок ингредиентов в чеке
        String receipt = burger.getReceipt().toLowerCase();

        int lettuceIndex = receipt.indexOf("lettuce");
        int pattyIndex = receipt.indexOf("beef patty");

        assertTrue("lettuce должен идти после beef patty после перемещения", lettuceIndex > pattyIndex);
    }


    @Test
    public void removeIngredientWithInvalidIndexTest() {
        // Пытаемся удалить ингредиент из пустого списка
        burger.removeIngredient(0);
        burger.removeIngredient(-1);
        burger.removeIngredient(10);

        // Проверяем, что это не вызывает исключений и не меняет состояние
        String receipt = burger.getReceipt();
        assertNotNull("Чек должен быть доступен даже при пустых данных", receipt);

        float price = burger.getPrice();
        assertEquals("Цена должна остаться 0.0 при удалении из пустого списка",
                0.0F, price, 0);
    }
}
