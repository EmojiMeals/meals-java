import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

public class Meals {

    private static HashMap<Integer,String> recipes = new HashMap<Integer,String>();

    private static Map<String, String> recipes() throws IOException {
        String fileName = "src/main/resources/recipes.json";
        String recipesJson = new String(Files.readAllBytes(Paths.get(fileName).toAbsolutePath()));
        final ObjectMapper mapper = new ObjectMapper();
        final MapType type = mapper.getTypeFactory().constructMapType(
                HashMap.class, String.class, String.class);
        final HashMap<String, String> data = mapper.readValue(recipesJson, type);
        return data;
    }

    private static HashMap<Integer,String> allCombos() throws IOException {
        recipes().forEach((ingredients,recipe)-> {
             recipes.put(new HashSet<String>(
                     Arrays.asList(
                             ingredients.split(
                                     ""
                             )
                     )
             ).hashCode(),recipe);
        });
        return recipes;
    }

    public static String mealify(String ingredients) throws IOException {
        Integer ingredientHash = new HashSet<String>(
                Arrays.asList(
                        ingredients.split(
                                ""
                        )
                )
        ).hashCode();
        return allCombos().get(ingredientHash);
    }
}
