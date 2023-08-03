package com.ltp.globalsuperstore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller  // can't name a clas name like controller( annotaiton)
public class StoreController {

    private List<Item> items = new ArrayList<Item>();

    @GetMapping("/")
    public String getForm( Model model) throws ParseException {

        Item testItem = new Item();
        
        // Set values for the test item
        testItem.setCategory("Furniture");
        testItem.setName("lol");
        testItem.setPrice(699.99);
        testItem.setDiscount(10.0); // 10% discount


        long timestamp = 1627992000000L; // Represents August 3, 2021, 00:00:00 GMT
        Date date = new Date(timestamp);

        testItem.setDate(date);
        
        model.addAttribute("categories",Constants.CATEGORIES);
        model.addAttribute("item",testItem);

        return "form";
    }


    @PostMapping("/submitItem")
    public String handleSubmit(Item item) {

        System.out.println(item.getCategory());
        System.out.println(item.getName());

        return "redirect:/inventory";
    }

    @GetMapping("/inventory")
    public String getInventory( Model model) {

        return "inventory";
    }
}
