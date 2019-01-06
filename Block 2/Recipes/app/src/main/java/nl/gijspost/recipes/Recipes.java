package nl.gijspost.recipes;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipes {

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("recipes")
    @Expose
    private List<Recipe> recipes = new ArrayList<Recipe>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

}