package ReceiptGenerator;

import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.InputMismatchException;
import java.text.DecimalFormat;

abstract class AbstractionOfQueuesMethods {
    public abstract void addPurchase(Purchase purchase);
    public abstract void generateReceipt();
    public abstract void displayReceiptQueue();
}

class Purchase {
    String item;
    double price;

    public Purchase(String item, double price) {
        this.item = item;
        this.price = price;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return item + " - P" + df.format(price);
    }
}

class ReceiptQueue extends AbstractionOfQueuesMethods {
    private Deque<Purchase> queue = new ArrayDeque<>();
    private double totalBill = 0.00;
    private DecimalFormat df = new DecimalFormat("#.##");

    public void addPurchase(Purchase purchase) {
        queue.add(purchase);
        totalBill += purchase.price;
    }

    public void generateReceipt() {
        if (!queue.isEmpty()) {
            Purchase nextPurchase = queue.poll();
            System.out.println("Receipt: " + nextPurchase);
            totalBill -= nextPurchase.price;
        } else {
            System.out.println("There are no purchases in the queue.");
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void displayReceiptQueue() {
        if (queue.isEmpty()) {
            System.out.println("The Receipt Queue is empty.");
        } else {
            System.out.println("Here's the current Receipt Queue: ");
            for (Purchase purchase : queue) {
                System.out.println(purchase);
            }
            System.out.println("Total bill: " + df.format(totalBill));
        }
    }
}

public class myReceiptGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReceiptQueue rq = new ReceiptQueue();

        System.out.println("Welcome to the My Receipt Generation Simulator!");

        while (true) {
            try {
                System.out.println("+--------- MY BIKE SHOP --------+");
                System.out.println("|   1. Display Receipt Queue.   |");
                System.out.println("|   2. Add Purchase.            |");
                System.out.println("|   3. Generate Receipt.        |");
                System.out.println("|   4. Exit.                    |");
                System.out.println("+-------------------------------+");
                System.out.println("Choose from the option:");

                int select = scanner.nextInt();

                if (select == 1) {
                    rq.displayReceiptQueue();
                } else if (select == 2) {
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter the item name (1-15 letters): ");
                    String itemName = scanner.nextLine();

                    if (itemName.length() > 15) {
                        System.out.println("Out of bounds! You must enter 1-15 letters. Try Again.");
                        continue; // Restart the loop
                    }

                    System.out.print("Enter the item price: ");
                    double itemPrice = scanner.nextDouble();
                    Purchase purchase = new Purchase(itemName, itemPrice);
                    rq.addPurchase(purchase);
                } else if (select == 3) {
                    rq.generateReceipt();
                } else if (select == 4) {
                	 System.out.println("Thank you for using My Receipt Generator!");
                     System.out.println("Good Bye!");
                     scanner.close();
                     System.exit(0);
                } else {
                   System.out.println("Out of bounds! Please enter a valid choice between 1 and 4. Try Again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Numbers only from 1-4. Try again.");
                scanner.nextLine();
            }
        }
    }
}
