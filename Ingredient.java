import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private String ingredientName;
    private Taste taste;
    public static List<Ingredient> ingredientList = new ArrayList<>();

    public Ingredient(String ingredientName, Taste taste) {
        this.ingredientName = ingredientName;
        this.taste = taste;
        ingredientList.add(this);
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Taste getTaste() {
        return taste;
    }

    public void setTaste(Taste taste) {
        this.taste = taste;
    }

    @Override
    public String toString() {
        return ingredientName;
    }
}
