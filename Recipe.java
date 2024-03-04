import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recipe {
    private String recipeName;
    private List<Ingredient> ingredient;
    public Set<Taste> tasteSet = new HashSet<>();
    public String recipeRate;

    public Recipe(String recipeName, List<Ingredient> ingredient) {
        this.recipeName = recipeName;
        this.ingredient = ingredient;
        for (Ingredient ing: ingredient) {
            tasteSet.add(ing.getTaste());
        }
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public String autoRate(){
        int sweet = 0;
        int bitter = 0;
        int neutral = 0;
        int sour = 0;
        int salty = 0;

        for (Ingredient ing : this.getIngredient()) {
            if(ing.getTaste().equals(Taste.SWEET)) sweet++;
            if(ing.getTaste().equals(Taste.BITTER)) bitter++;
            if(ing.getTaste().equals(Taste.NEUTRAL)) neutral++;
            if(ing.getTaste().equals(Taste.SOUR)) sour++;
            if(ing.getTaste().equals(Taste.SALTY)) salty++;
        }
        if(tasteSet.size() == 5) recipeRate = "To danie jest tak złe że aż ciężko na nie patrzeć";
        else if(sweet > bitter && sweet > neutral && sweet > sour && sweet > salty)
            recipeRate = "To danie jest tak słodkie, że nadaje się na deser";
        else if(bitter > sweet && bitter > neutral && bitter > sour && bitter > salty)
            recipeRate = "To danie jest dość gorzkie, dzieci mogą nie polubić!";
        else if(neutral > sweet && neutral > bitter && neutral > sour && neutral > salty)
            recipeRate = "To danie jest neutralne, posmakuje wszystkim! ";
        else if(sour > sweet && sour > neutral && sour > bitter && sour > salty)
            recipeRate = "To danie jest dla fanów kwaśnych smaków!";
        else if(salty > sweet && salty > neutral && salty > sour && salty > bitter)
            recipeRate = "Uwaga słone danie!";
        else recipeRate = "To danie ma różne smaki, nie przeważa żaden konkretny";
        return recipeRate;
    }

    @Override
    public String toString() {
        return recipeName;
    }
}
