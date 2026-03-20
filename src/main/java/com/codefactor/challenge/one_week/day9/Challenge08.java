package com.codefactor.challenge.one_week.day9;

/**
 * Challenge 08 - Decorator Pattern
 *
 * Attach additional responsibilities to objects dynamically.
 * Provides a flexible alternative to subclassing.
 *
 * Example: Adding features to a coffee order (toppings/extras).
 */
public class Challenge08 {

    interface Coffee {
        double cost();
        String description();
    }

    static class SimpleCoffee implements Coffee {
        @Override public double cost() { return 2.00; }
        @Override public String description() { return "Simple Coffee"; }
    }

    /** Abstract decorator */
    static abstract class CoffeeDecorator implements Coffee {
        protected final Coffee decoratedCoffee;
        CoffeeDecorator(Coffee coffee) { this.decoratedCoffee = coffee; }
    }

    static class MilkDecorator extends CoffeeDecorator {
        MilkDecorator(Coffee coffee) { super(coffee); }
        @Override public double cost() { return decoratedCoffee.cost() + 0.50; }
        @Override public String description() { return decoratedCoffee.description() + " + Milk"; }
    }

    static class SugarDecorator extends CoffeeDecorator {
        SugarDecorator(Coffee coffee) { super(coffee); }
        @Override public double cost() { return decoratedCoffee.cost() + 0.25; }
        @Override public String description() { return decoratedCoffee.description() + " + Sugar"; }
    }

    static class WhipCreamDecorator extends CoffeeDecorator {
        WhipCreamDecorator(Coffee coffee) { super(coffee); }
        @Override public double cost() { return decoratedCoffee.cost() + 0.75; }
        @Override public String description() { return decoratedCoffee.description() + " + Whip Cream"; }
    }

    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.printf("%-40s $%.2f%n", coffee.description(), coffee.cost());

        coffee = new MilkDecorator(coffee);
        System.out.printf("%-40s $%.2f%n", coffee.description(), coffee.cost());

        coffee = new SugarDecorator(coffee);
        System.out.printf("%-40s $%.2f%n", coffee.description(), coffee.cost());

        coffee = new WhipCreamDecorator(coffee);
        System.out.printf("%-40s $%.2f%n", coffee.description(), coffee.cost());

        // Build a different order in one line
        System.out.println();
        Coffee latte = new MilkDecorator(new MilkDecorator(new SugarDecorator(new SimpleCoffee())));
        System.out.printf("%-40s $%.2f%n", latte.description(), latte.cost());
    }
}
