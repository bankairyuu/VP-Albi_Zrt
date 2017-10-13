package backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class MonthlyFee {

    public enum FeeType implements NamedEnum {
        GAS("Gas"),
        ELECTRICITY("Electricity"),
        INTERNET("Internet"),
        COMMON_EXPENSE("Common expense"),
        RENTAL_FEE("Rental fee");

        private final String name;

        FeeType(String name) {
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
    public FeeType feeType;
    public Date creationDate = new Date();
    public int amount;
}
