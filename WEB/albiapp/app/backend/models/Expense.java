package backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Expense {
    public enum SharingType {
        PERSONAL,
        COMMON;
    }

    @Id
    @GeneratedValue
    public Long id;

    public Date creationTime = new Date();

    @ManyToOne
    public FlatUser from;

    @ManyToOne
    public FlatUser to;

    public String name;

    public String description;

    public SharingType sharingType;

    public int amount;

    public boolean apartmentCost;

    public Date expenseMonth = new Date();

    public boolean paid = false;

    public Expense(FlatUser from, FlatUser to, String name, String description, SharingType sharingType, int amount,
                   boolean apartmentCost, Date expenseMonth) {
        this.from = from;
        this.to = to;
        this.name = name;
        this.description = description;
        this.sharingType = sharingType;
        this.amount = amount;
        this.apartmentCost = apartmentCost;
        this.expenseMonth = expenseMonth;
    }

    public Expense() {

    }
}
