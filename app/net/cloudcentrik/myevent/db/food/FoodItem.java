package net.cloudcentrik.myevent.db.food;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("fooditem")
public class FoodItem {
    @Id
    private ObjectId id;
    private String type;
    private String name;
    private String amount;
    private String email;

    public FoodItem() {
    }

    public FoodItem(String type, String name, String amount, String email) {
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.email = email;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
