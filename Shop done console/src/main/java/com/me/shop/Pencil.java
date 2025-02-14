
package com.me.shop;

public class Pencil extends Product{
    
    protected boolean eraser;
    protected int length;
    protected String color;

    public Pencil(String name, double price, boolean eraser, int length, String color) {
        super( name, 1, price, false);
        this.eraser = eraser;
        this.length = length;
        this.color = color;
    }
  
    //Funkcja ktora drukuje informacje o ołowku
    public void printPencilInfo() {
        System.out.println("Type: Pencil");
        System.out.println("Has eraser: " + this.eraser);
        System.out.println("Length: " + this.length);
        System.out.println("Color: " + this.color);
    }
}
