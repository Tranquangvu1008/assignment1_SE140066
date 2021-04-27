/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SE140066
 */
public class CartDTO {

    private String customName;
    private Map<String, ItemDTO> cart;

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public Map<String, ItemDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ItemDTO> cart) {
        this.cart = cart;
    }

    public CartDTO(String customName, Map<String, ItemDTO> cart) {
        this.customName = customName;
        this.cart = cart;
    }

    public CartDTO() {
    }

    public void add(ItemDTO item) {
        if (this.cart == null) {
            this.cart = new HashMap<>();

        }
        if (this.cart.containsKey(item.getItemID())) {
            int quantity = this.cart.get(item.getItemID()).getQuantity();
            item.setQuantity(quantity + item.getQuantity());
        }
        cart.put(item.getItemID(), item);
    }
    public void delete(String id){
        if(this.cart==null){
            return;
        }
        if(this.cart.containsKey(id)){
            this.cart.remove(id);
        }
    }
    public void update(ItemDTO room){
        if(this.cart!=null){
            if(this.cart.containsKey(room.getItemID())){
                this.cart.replace(room.getItemID(), room);
            }
        }
    } 
}
