/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author laptop
 */
public class Cart {

    private Map<String, Product> cart;

    public Cart() {
    }

    public Cart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public Map<String, Product> getCart() {
        return cart;
    }

    public void setCart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public boolean add(String id, Product pro) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(id)) {
                int currentQuantity = this.cart.get(id).getQuantity();
                pro.setQuantity(currentQuantity + pro.getQuantity());
            }
            this.cart.put(id, pro);
            check = true;
        } catch (Exception e) {

        }
        return check;
    }

    public boolean remove(String proID) {
        boolean check=false;
        try{
            if(this.cart==null){
                this.cart=new HashMap<>();
            }
            if(this.cart.containsKey(proID)){
                this.cart.remove(proID);
                check=true;
            }
        } catch(Exception e){
            
        } 
        return check;
    }

    public boolean edit(String proID, Product pro) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(proID)) {
                    this.cart.replace(proID, pro);
                    check = true;
                }
            }

        } catch (Exception e) {

        }
        return check;
    }

}
