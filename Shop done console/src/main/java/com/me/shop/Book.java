
package com.me.shop;

public class Book extends Product{
    
    protected String author;
    protected String description;
    protected String publisher;

    public Book(String name, double price, String author, String description, String publisher) {
        super(name, 1, price, true);
        this.author = author;
        this.description = description;
        this.publisher = publisher;
    }
    
    //Funkcja ktora drukuje informacje o ksiazce
    public void printBookInfo() {
        System.out.println("Type: Book");
        System.out.println("Author: " + this.author);
        System.out.println("Description: " + this.description);
        System.out.println("Publisher: " + this.publisher);
    }
}
