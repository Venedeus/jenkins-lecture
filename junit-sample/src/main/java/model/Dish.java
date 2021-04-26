package model;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Dish {
    private java.lang.String title;
    private Map<java.lang.String, Integer> recipe;

    public Dish(String title, Map<java.lang.String, Integer> recipe) {
        this.title = title;
        this.recipe = recipe;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public java.lang.String printRecipe() {
        return title + getFormattedRecipe();
    }

    public void addIngredient(java.lang.String title, Integer weight) {
        if(weight <= 0) {
            throw new IllegalArgumentException();
        }

        recipe.put(title, weight);
    }

    public void deleteIngredient(java.lang.String title){
        recipe.remove(title);
    }

    public Set<java.lang.String> getIngredients() {
        return recipe.entrySet().stream().map(e -> e.getKey()).collect(Collectors.toSet());
    }


    public Integer getIngredientWeight(java.lang.String title) {
        return recipe.get(title);
    }

    private java.lang.String getFormattedRecipe() {
        return "\nRecipe:\n" + recipe
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining("\n"));
    }
}
