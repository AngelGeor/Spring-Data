package com.example.springdataadvancedq;

import com.example.springdataadvancedq.services.IngredientService;
import com.example.springdataadvancedq.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class LineRunnerMain implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public LineRunnerMain(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //1.
        //String inputSize = scanner.nextLine();

        //this.shampooService.findBySizeOrderByIdDesc(inputSize).
        //     forEach(shampoo -> System.out.println(shampoo));

        // List<String> ingredients = new ArrayList<>();

//        String ingredient = scanner.nextLine();
//        while (!ingredient.isBlank()){
//            ingredients.add(ingredient);
//
//            ingredient = scanner.nextLine();
//        }

        //     this.shampooService.findByIngredient(ingredients).
        //    forEach(shampoo -> System.out.println(shampoo));

        //2.Select Shampoos by Size or Label

        // String size = scanner.nextLine();
        // long labelId = Long.parseLong(scanner.nextLine());

        //  this.shampooService.findBySizeOrLabelId(size,labelId).
        //   forEach(shampoo -> System.out.println(shampoo));

        //3. Select Shampoos by Price

//        String price = scanner.nextLine();
//        this.shampooService.findByPriceGreaterThanOrderByPriceDesc(price).
//        forEach(shampoo -> System.out.println(shampoo));

        //4.	Select Ingredients by Name
//        String name = scanner.nextLine();
//        this.ingredientService.selectByName(name).
//          forEach(ingredient -> System.out.println(ingredient));

        //5. Select Ingredients by Names
//        List<String> ingredients = new ArrayList<>();
//
//        String input = scanner.nextLine();
//        while (!input.isBlank()) {
//            ingredients.add(input);
//
//            input = scanner.nextLine();
//        }
//             this.ingredientService.findAllByNameInOrderByPrice(ingredients).
//            forEach(ingredient -> System.out.println(ingredient));

        //6.Count Shampoos by Price
//        String price = scanner.nextLine();
//        System.out.println(this.shampooService.countByPriceLessThan(price));

        //8. Select Shampoos by Ingredients Count
//        int count = Integer.parseInt(scanner.nextLine());
//
//        this.shampooService.findWithIngredientCountLessThan(count).
//            forEach(shampoo -> System.out.println(shampoo));

        //9. Delete Ingredients by Name
//        String ingredientName = scanner.nextLine();
//        this.ingredientService.deleteByName(ingredientName);

        //10.	Update Ingredients by Price
        this.ingredientService.updatePriceAll();
    }
}
