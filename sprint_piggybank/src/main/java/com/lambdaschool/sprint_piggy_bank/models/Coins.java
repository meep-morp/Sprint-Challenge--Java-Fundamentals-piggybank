package com.lambdaschool.sprint_piggy_bank.models;


import javax.persistence.*;

@Entity
@Table(name = "coins")

public class Coins {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long coinid;

    String name;
    String nameplural;
    float value;
    int quantity;

    // Constructors

    public Coins(String name, String nameplural, float value, int quantity) {
        this.name = name;
        this.nameplural = nameplural;
        this.value = value;
        this.quantity = quantity;
    }

    public Coins() {
    }

    // Getters and Setters

    public long getCoinid() {
        return coinid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameplural() {
        return nameplural;
    }

    public void setNameplural(String nameplural) {
        this.nameplural = nameplural;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Overrrides


    @Override
    public String toString() {
        return "Coins{" +
                "coinid=" + coinid +
                ", name='" + name + '\'' +
                ", nameplural='" + nameplural + '\'' +
                ", value=" + value +
                ", quantity=" + quantity +
                '}';
    }
}
