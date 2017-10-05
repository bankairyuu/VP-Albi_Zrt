package backend.services;

import backend.models.Expense;
import backend.repositories.ExpenseRepository;

import javax.inject.Inject;

public class ExpenseService {
    @Inject
    private ExpenseRepository expenseRepository;

    public Expense markAsPaid(Expense expense) {
        expense.paid = true;

        return expenseRepository.save(expense);
    }
}
