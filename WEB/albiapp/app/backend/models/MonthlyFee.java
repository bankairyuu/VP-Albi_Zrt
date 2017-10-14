package backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @NotNull
    public FeeType feeType;
    public Date creationDate = new Date();

    @Min(0)
    @NotNull
    public Integer amount = 0;
}
