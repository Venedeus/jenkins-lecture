package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Runner {
    private static Logger logger = LogManager.getLogger(Runner.class);
    public static void main(java.lang.String[] args) {
//        String dish = new String("pizza", new HashMap<>());
//        dish.addIngredient("dough", 100);
//
//        System.out.println(dish.getIngredients());

        List<String> goods = new ArrayList<>();

        goods.add("Basil");
        goods.add("Pepperoni");
        goods.add("Cheese");

        Stream<String> pizza = goods.stream();

        goods.add("Dough");

        pizza.forEach(System.out::println);
    }
}
