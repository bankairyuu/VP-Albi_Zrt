package controllers.api.models;

import backend.models.Expense;

import java.util.Date;

public class ApiExpense {
    public long ID;

    public Date CreationTime = new Date();

    public ApiUser From;

    public ApiUser To;

    public String Name;

    public String Description;

    public String SharingType;

    public int Amount;

    public boolean ApartmentCost;

    public Date ExpenseMonth = new Date();

    public boolean Paid = false;

    public ApiExpense(Expense expense) {
        this.ID = expense.id;
        this.CreationTime = expense.creationTime;
        this.From = new ApiUser(expense.from);
        this.To = new ApiUser(expense.to);
        this.Name = expense.name;
        this.Description = expense.description;
        this.SharingType = expense.sharingType.name();
        this.Amount = expense.amount;
        this.ApartmentCost = expense.apartmentCost;
        this.ExpenseMonth = expense.expenseMonth;
        this.Paid = expense.paid;
    }

    public ApiExpense() {
    }
}
