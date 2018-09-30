package net.cloudcentrik.myevent.db.participant;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("participant")
public class Participant {

    @Id
    private ObjectId _id;

    private String type;

    private String email;

    private String name;

    private int noOfChild;

    private int noOfAdult;

    private int total;

    private boolean confirm;

    public Participant() {
    }

    public Participant(ObjectId _id, String type, String email, String name, int noOfChild, int noOfAdult, int total, boolean confirm) {
        this._id = _id;
        this.type = type;
        this.email = email;
        this.name = name;
        this.noOfChild = noOfChild;
        this.noOfAdult = noOfAdult;
        this.total = total;
        this.confirm = confirm;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfChild() {
        return noOfChild;
    }

    public void setNoOfChild(int noOfChild) {
        this.noOfChild = noOfChild;
    }

    public int getNoOfAdult() {
        return noOfAdult;
    }

    public void setNoOfAdult(int noOfAdult) {
        this.noOfAdult = noOfAdult;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "_id=" + _id +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", noOfChild=" + noOfChild +
                ", noOfAdult=" + noOfAdult +
                ", total=" + total +
                ", confirm=" + confirm +
                '}';
    }
}
