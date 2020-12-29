package emojimeals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

public class Meals {

    private HashMap<Integer,String> recipes = new HashMap<Integer,String>();

    public Meals(HashMap<String,String> customRecipes) {
        processRecipes(customRecipes);
    }

    public Meals(String recipePath) throws IOException {
        processRecipes(loadRecipes(recipePath));
    }

    public Meals() throws IOException {
        processRecipes(
            loadRecipes(
                "src/main/resources/recipes.json"
            )
        );
    }

    public String mealify(String ingredients) throws IOException {
        Integer ingredientHash = new HashSet<String>(
                Arrays.asList(
                        ingredients.split(
                                ""
                        )
                )
        ).hashCode();
        return recipes.get(ingredientHash);
    }

    private HashMap<String, String> loadRecipes(String filename) throws IOException {
        String recipesJson = new String(Files.readAllBytes(Paths.get(filename).toAbsolutePath()));
        ObjectMapper mapper = new ObjectMapper();
        MapType type = mapper.getTypeFactory().constructMapType(
                HashMap.class, String.class, String.class);
        HashMap<String, String> data = mapper.readValue(recipesJson, type);
        return data;
    }

    private void processRecipes(HashMap<String, String> unprocessedRecipes) {
        unprocessedRecipes.forEach((ingredients,recipe)-> {
            recipes.put(new HashSet<String>(
                    Arrays.asList(
                            ingredients.split(
                                    ""
                            )
                    )
            ).hashCode(),recipe);
        });
    }
}
