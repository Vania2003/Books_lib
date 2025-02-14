
package com.me.shop;

import java.util.HashMap;

public class BookStoreGUI {
    
    protected static BookStoreGUI instance;
    protected final HashMap<Integer, Product> products = new HashMap();
    protected final HashMap<Integer, Product> cart = new HashMap();
    
    
    //Funkcja która inicializuje tworzenie spisu artykułuw oraz wiświetla program
    public static void initializeBookStore(){
        instance = new BookStoreGUI();
        ProductsList productsList = new ProductsList();
        
        productsList.initializeProducts(); 
        
        productsList.showProducts();
    }
}
