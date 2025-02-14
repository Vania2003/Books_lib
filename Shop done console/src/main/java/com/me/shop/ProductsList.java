
package com.me.shop;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ProductsList {
    //Lista artykułuw w sklepie
    protected final HashMap<Integer, Product> products;
    
    //Lista artykułuw w koszyku
    protected final HashMap<Integer, Product> cart;

    //Konstruktor klasy
    public ProductsList() {
        this.products = BookStoreGUI.instance.products;
        this.cart = BookStoreGUI.instance.cart;
        this.initializeProducts();
    }
    
    //Funkcja, która daje pocątkowe wartości liście products
    public void initializeProducts(){
        Product product = new Product("Math", 1, 7.9, true);
        product.book = new Book("Math", 7.9,"James Bond", "Be a math wizard", "Philip");
        products.put(1, product);
        
        product = new Product("English", 1, 8.5, true);
        product.book = new Book("English", 8.5,"Dale Cooper", "Be an expert in english", "Sarah");
        products.put(2, product);
        
        product = new Product("Science", 1, 8.2, true);
        product.book = new Book("Science", 8.2,"Will Smith", "Get extra knowledge in science", "Jacob");
        products.put(3, product);
        
        product = new Product("Pencil", 1, 0.58, false);
        product.pencil = new Pencil("Pencil",0.58, true, 8, "Purple");
        products.put(4, product);
    }
    
    //Funkcja, która drukuje spis artykułuw w sklepie
    public void showProducts(){
        int i = 0;
        System.out.println("------------------------------------------");
        System.out.println("BookStore");
        System.out.println("------------------------------------------");
        for(Map.Entry<Integer, Product> entry : products.entrySet()){
            int count = entry.getKey();
            Product product = entry.getValue();
            product.printProductInfo(count, false);
            if(product.isBook){
                Book book = product.book;
                product.book.printBookInfo();
//                System.out.println("Type: Book");
//                System.out.println("Author: " + book.author);
//                System.out.println("Description: " + book.description);
//                System.out.println("Publisher: " + book.publisher);
            }else{
                Pencil pencil = product.pencil;
                product.pencil.printPencilInfo();
//                System.out.println("Type: Pencil");
//                System.out.println("Eraser: " + pencil.eraser);
//                System.out.println("Length: " + pencil.length);
//                System.out.println("Color: " + pencil.color);
            }
            System.out.println("------------------------------------------");
            i++;
        }
        System.out.println("(1-" + i +") Add to cart (c) View Cart (r) Register Product (0) Exit");
        System.out.println("------------------------------------------");
        menuChoice();
    }
    
    //Funkcja, która drukuje listę artykułuw w koszyku
    public void showCart(){
        int i = 0;
        System.out.println("");
        System.out.println("------------------------------------------");
        System.out.println("Your Cart");
        System.out.println("------------------------------------------");
        if(cart.isEmpty()){
                System.out.println("Empty Cart!");
                System.out.println("------------------------------------------");
            }
        for(Map.Entry<Integer, Product> entry : cart.entrySet()){
            
            int count = entry.getKey();
            Product product = entry.getValue();
            product.printProductInfo(count, true);
//            System.out.println("(" + count + ") " + product.name + " $" + product.price);
//            System.out.println("Quantity: " + product.quantity);
            if(product.isBook){
                Book book = product.book;
                product.book.printBookInfo();
//                System.out.println("Author: " + book.author);
//                System.out.println("Description: " + book.description);
//                System.out.println("Publisher: " + book.publisher);
            }else{
                Pencil pencil = product.pencil;
                product.pencil.printPencilInfo();
//                System.out.println("Eraser: " + pencil.eraser);
//                System.out.println("Length: " + pencil.length);
//                System.out.println("Color: " + pencil.color);
            }
            i = count;
            System.out.println("------------------------------------------");
        }
        System.out.println("(1-" + i + ") Remove from cart (b) Menu (0) Checkout");
        System.out.println("------------------------------------------");
        cartChoice();
    }
    
    //Funkcja, która działa jako MENU w koszyku
    public void cartChoice(){
        System.out.print("Choice: ");
        
        Scanner scanner = new Scanner(System.in);
        try{
            int choice = scanner.nextInt();
            if(choice == 0){
                checkout();
            }else if(cart.containsKey(choice) && choice > 0){
               
                removeProduct(choice);
                System.out.println("Item has been removed from cart!");
                showCart();
                cartChoice();
            } else if(cart.isEmpty() && choice >0) {
                System.out.println("Cart is empty");
                cartChoice();
            } else{
                System.out.println("Invalid Choice!");
                cartChoice();
            }
        }catch(InputMismatchException e){
            String choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("b")){
                showProducts();
            }else{
                System.out.println("Invalid Choice!");
            }
            cartChoice();
        }
    }
    
    //Funkcja, która oblicza i wyświetlia wartość artykułuw w koszyku, za tym usuwa wszystkie artykuły z koszyku
    public void checkout(){
        double total = 0;
        for(Product product : cart.values()){
            total += product.price * product.quantity;
        }
        System.out.println("------------------------------------------");
        System.out.println("Total Price: $" + total);
        System.out.println("------------------------------------------");
        
        cart.clear();
        this.showCart();
    }
    
    //Funkcja, która działa jako MENU sklepu
    public void menuChoice(){
        System.out.print("Choice: ");
        Scanner scanner = new Scanner(System.in);
        
        try{
            int choice = scanner.nextInt();
            if(choice == 0){
                System.out.close();
            }else if(choice >= 0 && products.containsKey(choice)){
                addProduct(choice);
                System.out.println("Item has been added to cart!");
                menuChoice();
            }
        }catch(InputMismatchException e){
            String choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("c")){
                showCart();
            }else if (choice.equalsIgnoreCase("r")){
//                RegisterProduct register = new RegisterProduct();
//                register.registerMenu();
                  registerMenu();
            }else {
                System.out.println("Invalid Choice!");
            }
            menuChoice();
        }
        
    }
    
    //Funckja, która zwiększa znaczenie zmiennej quantity o 1 więcej
//    public void updateQuantity(int key){
//        Product product = products.get(key);
//        product.quantity += 1;
//    }
    
    //Funkcja, która dodaje artykuł do koszyku. Jezeli artykuł już jest w koszyku, zwiększa go ilośc (quantity)
    public void addProduct(int key){
        if(cart.containsKey(key)){
//            updateQuantity(key);
            Product product = products.get(key);
            product.quantity++;
        }else{
            cart.put(key, products.get(key));
        }
    }
    
    //Funkcja, zmniejsza ilość artykulu w koszyku. Jezeli ilość (quantity) artykulu rowna 1, to funkcja usuwa artykul z koszyku
    public void removeProduct(int key){
        if(cart.get(key).quantity > 1) {
            cart.get(key).quantity--;
        } else {
            cart.remove(key);
        }
    }
    
    //Funkcja, ktora wyświtla menu rejestacji nowego artykulu, i odpowiednio  do wpisanego parametry tworzy nową książkę lub ołowek
    public void registerMenu(){
        System.out.println("------------------------------------------");
        System.out.println("(1) Book (2) Pencil");
        System.out.println("------------------------------------------");
        System.out.print("Choice: ");
        Scanner scanner = new Scanner(System.in);
        try{
            int choice = scanner.nextInt();
            switch(choice){
                case 1 -> createBook();
                case 2 -> createPencil();
            }
        }catch(InputMismatchException e){
            
        }
    }
    
    //Funkcja, ktora wyświetla formę do wypelniena uzytkownikiem i odpowidnio do wpisanych parametrow tworzy nową ksiazke
    public void createBook(){
        System.out.println("Book Product");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Author: ");
        String author = scanner.next();
        System.out.print("Description: ");
        String description = scanner.next();
        System.out.print("Publisher: ");
        String publisher = scanner.next();
        
        Product product = new Product(name, 1, price, true);
        product.book = new Book(name, price ,author, description, publisher);
        products.put(products.size() + 1, product);
        
        System.out.println("(Book) " + name + " has been created!");
        ProductsList list = new ProductsList();
        list.showProducts();
    }
    
    //Funkcja, ktora wyświetla formę do wypelniena uzytkownikiem i odpowidnio do wpisanych parametrow tworzy nowy olowek
    public void createPencil(){
        System.out.println("Pencil Product");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Eraser: ");
        boolean eraser = scanner.nextBoolean();
        System.out.print("Length: ");
        int length = scanner.nextInt();
        System.out.print("Color: ");
        String color = scanner.next();
        
        Product product = new Product(name, 1, price, false);
        product.pencil = new Pencil(name, price, eraser, length, color);
        products.put(products.size() + 1, product);
        
        System.out.println("(Pencil) " + name + " has been created!");
        ProductsList list = new ProductsList();
        list.showProducts();
    }
}
