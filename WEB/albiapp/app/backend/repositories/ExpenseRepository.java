package backend.repositories;

import backend.models.Expense;
import play.db.jpa.JPAApi;

import javax.inject.Inject;

public class ExpenseRepository extends GenericRepository<Expense> {

    @Inject
    public ExpenseRepository(JPAApi jpaApi) {
        super(Expense.class, jpaApi);
    }
}
