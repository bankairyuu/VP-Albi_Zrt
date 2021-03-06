package backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Expense {
    public enum SharingType implements NamedEnum {
        PERSONAL("Personal"),
        COMMON("Common");

        private final String name;

        SharingType(String name) {
            this.name = name;
        }


        @Override
        public String getName() {
            return name;
        }
    }

    @Id
    @GeneratedValue
    public Long id;

    public Date creationTime = new Date();

    @ManyToOne
    @NotNull
    public FlatUser from;

    @ManyToOne
    @NotNull
    public FlatUser to;

    @NotNull
    public String name;

    public String description;

    @NotNull
    public SharingType sharingType;

    @NotNull
    public Integer amount = 0;

    @NotNull
    public Boolean apartmentCost;

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
