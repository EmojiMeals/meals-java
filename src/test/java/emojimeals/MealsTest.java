package emojimeals;

import emojimeals.Meals;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class MealsTest {

    private static Meals MEALS;

    static {
        try {
            MEALS = new Meals();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestItWorks() throws IOException {
        String result = MEALS.mealify("🐟🍚");
        Assert.assertEquals(result,"🍣");
    }

    @Test
    public void TestItWorksTheOtherWayAround() throws IOException {
        String result = MEALS.mealify("🍚🐟");
        Assert.assertEquals(result,"🍣");
    }

    @Test
    public void TestItWorksWithCustomPath() throws IOException {
        Meals newMeals = new Meals("src/main/resources/recipes.json");
        String result = newMeals.mealify("🍚🐟");
        Assert.assertEquals(result, "🍣");
    }

    @Test
    public void TestItWorksWithCustomRecipes() throws IOException {
        HashMap<String,String> customRecipes = new HashMap<>();
        customRecipes.put("🥕⛏🤮","🦅");
        customRecipes.put("🎄","💻");
        customRecipes.put("🎅🏻","🍚");

        Meals newMeals = new Meals(customRecipes);
        String result = newMeals.mealify("🎅🏻");
        Assert.assertEquals(result, "🍚");
    }
}
