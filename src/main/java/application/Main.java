package application;

import dto.DTOToy;
import models.TypeToy;
import service.ToyService;
import service.impl.ServiceImpl;

import java.util.Scanner;
import java.util.concurrent.Future;

import static application.Case1Thread.waitForCompletion;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToyService toyService = new ServiceImpl();
        boolean ext = false;
        while (!ext) {
            System.out.println("\nWelcome back to kira's Toy Store!");
            System.out.println("Enter 1 to add new toy");
            System.out.println("Enter 2 to show the toys by type");
            System.out.println("Enter 3 to show the total count");
            System.out.println("Enter 4 to show the total value");
            System.out.println("Enter 5 to decrease the quantity amount of a toy ");
            System.out.println("Enter 6 to increase the quantity amount of a toy");
            System.out.println("Enter 7 to show the major quantity toys");
            System.out.println("Enter 8 to show the less quantity toys.");
            System.out.println("Enter 9 to filter toys by price.");
            System.out.println("Enter 10 to sort toys by price.");
            System.out.println("Enter 0 to exit ");

            int interact = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (interact){
                    case 1:

                        System.out.println("Toy Name: ");
                        String name = scanner.nextLine();
                        System.out.println("Toy Type: 1.Female, 2.Male, 3.Unisex");
                        TypeToy type = TypeToy.fromName(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Toy Price: ");
                        double price = scanner.nextDouble();
                        System.out.println("Toy Quantity: ");
                        int quantity = scanner.nextInt();
                        DTOToy newToy = new DTOToy(name, type, price, quantity);
                        Future<?> future = toyService.addToy(newToy);
                        waitForCompletion(future);
                        break;
                    case 2:
                        System.out.println("\nToys by type: ");
                        System.out.println(toyService.showByType());
                        break;
                    case 3:
                        System.out.println("\n Total count: " + toyService.totalCount());
                        break;
                    case 4:
                        System.out.println("\n Total Value: " + toyService.totalValue());
                        break;
                    case 5:
                        System.out.println("\n Name of the toy: ");
                        String decrName= scanner.nextLine();
                        System.out.println("Quantity to decrease: ");
                        int decrQuantity = scanner.nextInt();
                        toyService.decreaseTotal(decrName, decrQuantity);
                        break;
                    case 6:
                        System.out.println("\n Name of the toy: ");
                        String incrName = scanner.nextLine();
                        System.out.println("Quantity to increase: ");
                        int incrQuantity = scanner.nextInt();
                        toyService.increaseTotal(incrName, incrQuantity);
                        break;
                    case 7:
                        System.out.println("\n Major quantity: " + toyService.mxToy());
                        break;
                    case 8:
                        System.out.println("\n Less quantity: " + toyService.minToy());
                        break;
                    case 9:
                        System.out.println("\n Min Price: ");
                        double minPrice = scanner.nextDouble();
                        toyService.filterByPrice(minPrice).forEach(System.out::println);
                        break;
                    case 10:
                        System.out.println("Sorted by quantity: ");
                        toyService.sortByQuantity().forEach(System.out::println);
                        break;
                    case 0:
                        ext = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error :(( " + e.getMessage());
            }

        }
        scanner.close();
    }
}