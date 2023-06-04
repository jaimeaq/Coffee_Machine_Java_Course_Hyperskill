package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int money;
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;

    public static void main(String[] args) {
        int startMoney = 550;
        int startWater = 400;
        int startMilk = 540;
        int startCoffeeBeans = 120;
        int startCups = 9;

        Scanner input = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine(startMoney, startWater, startMilk, startCoffeeBeans, startCups);

        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = input.nextLine();
            switch (action) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                    String coffeeType = input.nextLine();
                    if(coffeeType.equals("back")) {
                        break;
                    }
                    coffeeMachine.buy(Integer.parseInt(coffeeType));
                    break;

                case "fill":
                    System.out.println("Write how many ml of water do you want to add:");
                    int addWater = input.nextInt();
                    System.out.println("Write how many ml of milk do you want to add:");
                    int addMilk = input.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    int addCoffee = input.nextInt();
                    System.out.println("Write how many disposable cups do you want to add:");
                    int addCups = input.nextInt();
                    input.nextLine();

                    coffeeMachine.fill(addWater, addMilk, addCoffee, addCups);
                    break;

                case "take":
                    coffeeMachine.take();
                    break;

                case "remaining":
                    System.out.println(coffeeMachine);
                    break;

                case "exit":
                    return;
            }
        }
    }

    public CoffeeMachine (int money, int water, int milk, int coffeeBeans, int cups) {
        this.money = money;
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
    }

    public void buy(int coffeeType) {
        boolean enoughSupplies = supplyCalc(coffeeType);
        if (!enoughSupplies) {
            return;
        }
        System.out.println("I have enough resources, making you a coffee!");
        switch (coffeeType) {
            case 1: //ESPRESSO
                this.water -= 250;
                this.coffeeBeans -= 16;
                this.money += 4;
                break;
            case 2: //LATTE
                this.water -= 350;
                this.milk -= 75;
                this.coffeeBeans -= 20;
                this.money += 7;
                break;
            case 3: //CAPPUCCINO
                this.water -= 200;
                this.milk -= 100;
                this.coffeeBeans -= 12;
                this.money += 6;
                break;
        }
        this.cups -= 1;
    }

    public boolean supplyCalc(int coffeeType) {
        boolean enoughSupplies = true;
        int waterNeed = 0;
        int milkNeed = 0;
        int coffeBeansNeed = 0;
        int cupsNeed = 1;

        String[] suppliesNames = {"water", "milk", "coffee beans", "cups"};
        int[] suppliesStock = {this.water, this.milk, this.coffeeBeans, this.cups};

        switch (coffeeType) {
            case 1: //ESPRESSO
                waterNeed = 250;
                coffeBeansNeed = 16;
                break;
            case 2: //LATTE
                waterNeed = 350;
                milkNeed = 75;
                coffeBeansNeed = 20;
                break;
            case 3: //CAPPUCCINO
                waterNeed = 200;
                milkNeed = 100;
                coffeBeansNeed = 12;
                break;
        }

        int[] suppliesNeed = {waterNeed, milkNeed, coffeBeansNeed, cupsNeed};

        for (int i = 0; i < suppliesStock.length; i++) {
            if (suppliesStock[i] - suppliesNeed[i] <= 0) {
                enoughSupplies = false;
                System.out.println("Sorry, not enough " + suppliesNames[i]);
            }
        }
        return enoughSupplies;
    }

    public void fill(int addWater, int addMilk, int addCoffeeBeans, int addCups) {
        this.water += addWater;
        this.milk += addMilk;
        this.coffeeBeans += addCoffeeBeans;
        this.cups += addCups;
    }

    public void take() {
        System.out.println("I gave you $" + this.money + "\n");
        this.money = 0;
    }

    public String toString() {
        return "\nThe coffee machine has:\n" +
                water + " ml of water\n" +
                milk + " ml of milk\n" +
                coffeeBeans + " g of coffee beans\n" +
                cups + " disposable cups\n" +
                "$" + money + " of money\n";
    }
}