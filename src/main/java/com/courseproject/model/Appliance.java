package com.courseproject.model;

public abstract class Appliance {
    private final String id;
    private final String name;
    private final double price;

    protected Appliance(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s (id=%s) - $%.2f", name, id, price);
    }
}
