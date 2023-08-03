package com.ltp.globalsuperstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  // can't name a clas name like controller( annotaiton)
public class StoreController {

    private List<Item> items = new ArrayList<Item>();

    @GetMapping("/")
    public String getForm( Model model, @RequestParam(required=false) String id) {

        Item item;

        // check if item already exist;
         if ( checkItem(id) == -1) {
            item = new Item();
         } else {
            item = items.get(checkItem(id));
         }

         // add the item data to the model;
        model.addAttribute("categories",Constants.CATEGORIES);
        model.addAttribute("item",item);

        return "form";
    }


    @PostMapping("/submitItem")
    public String handleSubmit(Item item) {     


        if( checkItem(item.getId()) == -1) {
            items.add(item);
        }else {
          //  items.add(checkItem(item.getId()), item);
            items.set(checkItem(item.getId()), item);
        }
        
        return "redirect:/inventory";
    }

    @GetMapping("/inventory")
    public String getInventory( Model model) {

        model.addAttribute("items", items);
        return "inventory";
    }


    public Integer checkItem(String id) {

        for (int i = 0; i < items.size(); i++) {
            if ( items.get(i).getId().equals(id)) {
                return i;
            }
        }

        return -1;
    }
}
