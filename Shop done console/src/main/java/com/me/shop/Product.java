
package com.me.shop;
/**
 *
 * @author miroslav
*/
public class Product {
    
    //Zmienna, odpowiadająca za imie artukułu
    protected String name;
    
    //Zmienna, odpowiadająca za ilośc takich artukułów w koszyku
    protected int quantity;
    
    //Zmienna, odpowiadająca za cenę artukułu
    protected double price;
    
    //Zmienna, odpowiadająca za książkę artykułu
    protected Book book;
    
    //Zmienna, odpowiadająca za ołowek artukułu
    protected Pencil pencil;
    
    //Zmienna, sprawdzająca czy artykuł jest książką
    protected boolean isBook; 

    public Product(String name, int quantity, double price, boolean isBook) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.isBook = isBook;
    }
    
    
    
    //Funkcja ktora drukuje informacje o artukule 
    public void printProductInfo(int count, boolean inCart) {
        System.out.println("(" + count + ") " + this.name + " $" + this.price);
        if(inCart) {
            System.out.println("Quantity: " + this.quantity);
        }
    }
}
