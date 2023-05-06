package Model;

import java.io.Serializable;

public class Product implements Serializable {
    private String ID;
    private String name;
    private String unitPrice;
    private boolean size;
    private int amount;
    private String Type;


    public Product(String ID, String name, String type, int amount,String unitPrice, boolean size ) {
        this.ID = ID;
        this.name = name;
        Type = type;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.size = size;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }


    public boolean getSize() {
        return size;
    }

    public void setSize(boolean size) {
        this.size = size;
    }
}
