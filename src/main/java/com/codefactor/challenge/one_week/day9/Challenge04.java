package com.codefactor.challenge.one_week.day9;

import java.util.ArrayList;
import java.util.List;

/**
 * Challenge 04 - Observer Pattern
 *
 * Implement the Observer (Pub/Sub) pattern.
 * Subjects notify observers of state changes.
 *
 * Real-world example: Event listeners, message brokers, stock tickers.
 */
public class Challenge04 {

    interface Observer {
        void update(String event, Object data);
    }

    interface Subject {
        void subscribe(String event, Observer observer);
        void unsubscribe(String event, Observer observer);
        void notify(String event, Object data);
    }

    /** Event manager that supports multiple event types */
    static class EventManager implements Subject {
        private final java.util.Map<String, List<Observer>> listeners = new java.util.HashMap<>();

        @Override
        public void subscribe(String event, Observer observer) {
            listeners.computeIfAbsent(event, k -> new ArrayList<>()).add(observer);
        }

        @Override
        public void unsubscribe(String event, Observer observer) {
            List<Observer> observers = listeners.get(event);
            if (observers != null) observers.remove(observer);
        }

        @Override
        public void notify(String event, Object data) {
            List<Observer> observers = listeners.get(event);
            if (observers != null) {
                for (Observer obs : observers) {
                    obs.update(event, data);
                }
            }
        }
    }

    /** Concrete subject: Stock Ticker */
    static class StockTicker {
        private final EventManager events = new EventManager();
        private double price;

        public void subscribe(String event, Observer obs) { events.subscribe(event, obs); }

        public void setPrice(double price) {
            double oldPrice = this.price;
            this.price = price;
            events.notify("priceChange", price);
            if (price > oldPrice) events.notify("priceUp", price);
            if (price < oldPrice) events.notify("priceDown", price);
        }
    }

    /** Concrete observers */
    static class ConsoleLogger implements Observer {
        @Override
        public void update(String event, Object data) {
            System.out.println("  [LOG] Event: " + event + ", Data: " + data);
        }
    }

    static class AlertSystem implements Observer {
        private final double threshold;

        AlertSystem(double threshold) { this.threshold = threshold; }

        @Override
        public void update(String event, Object data) {
            if (data instanceof Double && (Double) data > threshold) {
                System.out.println("  [ALERT] Price exceeded $" + threshold + "! Current: $" + data);
            }
        }
    }

    public static void main(String[] args) {
        StockTicker ticker = new StockTicker();
        ticker.subscribe("priceChange", new ConsoleLogger());
        ticker.subscribe("priceUp", new AlertSystem(150.0));

        System.out.println("Setting price to $100:");
        ticker.setPrice(100.0);
        System.out.println("Setting price to $155:");
        ticker.setPrice(155.0);
        System.out.println("Setting price to $140:");
        ticker.setPrice(140.0);
    }
}
