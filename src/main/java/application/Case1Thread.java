package application;
import dto.DTOToy;
import models.TypeToy;
import service.ToyService;
import service.impl.ServiceImpl;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class Case1Thread {
    private static final ToyService toyService = new ServiceImpl();
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome back to Kira's Toy Store!");
            System.out.println("Enter 1 to add a new toy");
            System.out.println("Enter 0 to exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
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
                    Future<?> future = executor.submit(() -> {
                        try {
                            toyService.addToy(newToy);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    });
                    // Esperar a que la operación asíncrona se complete si es necesario
                    waitForCompletion(future);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        executor.shutdown();
        scanner.close();
    }
    public static Future<?> addToyAsync(DTOToy toy) {
        return executor.submit(() -> {
            try {
                toyService.addToy(toy);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        });
    }
    static void waitForCompletion(Future<?> future) {
        try {
            future.get();
            System.out.println("Toy added successfully.");
        } catch (Exception e) {
            System.out.println("Error waiting for completion: " + e.getMessage());
        }
    }
}


