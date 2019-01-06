package nl.gijspost.recipes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class RecipeFragment extends Fragment {

    private Recipe recipeObject;

    public RecipeFragment() {
    }

    public static RecipeFragment newInstance(Recipe recipe) {
        RecipeFragment fragment = new RecipeFragment();
        fragment.setRecipe(recipe);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.recipeTitle);

        // Warning: I couldn't find an API call that returned a list with ingredients, so this won't show up unless that got fixed.
        String text = this.recipeObject.getTitle();
        if (this.recipeObject.getIngredients() != null) {
            for (int i = 0; i < this.recipeObject.getIngredients().size(); i++) {
                text += "\n" + this.recipeObject.getIngredients().get(i);
            }
        }
        textView.setText(text);

        ImageView view = rootView.findViewById(R.id.imageView);
        Glide.with(this).load(this.recipeObject.getImageUrl()).into(view);
        return rootView;
    }

    public void setRecipe(Recipe recipe){
        this.recipeObject = recipe;
    }

    public Recipe getRecipe(){
        return this.recipeObject;
    }
}