package com.codegym.model;

public class Product {
    private int id;
    private String name;
    private String typeOfProduct;
    private String producerOfProduct;

    public Product() {
    }

    public Product(int id, String name, String typeOfProduct, String producerOfProduct) {
        this.id = id;
        this.name = name;
        this.typeOfProduct = typeOfProduct;
        this.producerOfProduct = producerOfProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public String getProducerOfProduct() {
        return producerOfProduct;
    }

    public void setProducerOfProduct(String producerOfProduct) {
        this.producerOfProduct = producerOfProduct;
    }
}
