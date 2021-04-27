/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import dtos.ItemDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author SE140066
 */
public class Validation {

    private static final String CHARACTER = "^[_A-z]*((-|\\s)*[_A-z])*$";

    public static String checkValidation(String itemName, String description, float price, String createDate, String category, int quantity) {
        if (!itemName.matches(CHARACTER)) {
            return "Item Name only contains words";
        } else if (price <= 0) {
            return "Price must be greater than zero";
        } else if (!isValidDate(createDate)) {
            return "Incorrect date format (yyyy-MM-dd)";
        } else if (!category.matches(CHARACTER)) {
            return "Category only contains words";
        } else if (quantity <= 0) {
            return "Quantity must be greater than zero";
        }

        return null;
    }

    static boolean isValidDate(String input) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            format.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String checkItem(ItemDTO dto) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateFormat = formatter.format(dto.getCreateDate());
        
        if (!dto.getItemID().matches(CHARACTER)) {
            return "Item ID only contains words";
        } else if (!dto.getItemName().matches(CHARACTER)) {
            return "Item Name only contains words";
        } else if (dto.getPrice() <= 0) {
            return "Price must be greater than zero";
        } else if (!isValidDate(dateFormat)) {
            return "Incorrect date format (yyyy-MM-dd)";
        } else if (!dto.getCategory().matches(CHARACTER)) {
            return "Category only contains words";
        } else if (dto.getQuantity() <= 0) {
            return "Quantity must be greater than zero";
        }

        return null;
    }
}
