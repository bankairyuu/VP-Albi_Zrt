package backend.repositories;

import backend.models.MonthlyFee;
import play.db.jpa.JPAApi;

import javax.inject.Inject;


public class MonthlyFeeRepository extends GenericRepository<MonthlyFee> {
    @Inject
    public MonthlyFeeRepository(JPAApi jpaApi) {
        super(MonthlyFee.class, jpaApi);
    }
}
