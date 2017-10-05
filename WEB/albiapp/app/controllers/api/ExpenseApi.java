package controllers.api;

import backend.models.Expense;
import backend.repositories.ExpenseRepository;
import backend.repositories.UserRepository;
import backend.services.ExpenseService;
import controllers.api.models.ApiExpense;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.stream.Collectors;

@Transactional
public class ExpenseApi extends Controller {
    @Inject
    private ExpenseRepository expenseRepository;

    @Inject
    private ExpenseService expenseService;

    @Inject
    private UserRepository userRepository;

    public Result list() {
        return ok(Json.toJson(expenseRepository.findAll().stream().map(ApiExpense::new).collect(Collectors.toList())));
    }

    //@Secured
    public Result create() {

        ApiExpense apiExpense = Json.fromJson(request().body().asJson(), ApiExpense.class);

        Expense expense = new Expense(userRepository.findById(apiExpense.From.id), userRepository.findById(apiExpense.To.id),
                apiExpense.Name, apiExpense.Description, Expense.SharingType.valueOf(apiExpense.SharingType), apiExpense.Amount,
                apiExpense.ApartmentCost, apiExpense.ExpenseMonth);

        expenseRepository.save(expense);

        return ok();
    }

    //@Secured
    public Result update() {
        ApiExpense apiExpense = Json.fromJson(request().body().asJson(), ApiExpense.class);

        Expense expense = new Expense(userRepository.findById(apiExpense.From.id), userRepository.findById(apiExpense.To.id),
                apiExpense.Name, apiExpense.Description, Expense.SharingType.valueOf(apiExpense.SharingType), apiExpense.Amount,
                apiExpense.ApartmentCost, apiExpense.ExpenseMonth);

        expense.id = apiExpense.ID;

        expenseRepository.save(expense);

        return ok();
    }

    //@Secured
    public Result delete(long expenseId) {
        Expense expense = expenseRepository.findById(expenseId);
        expenseRepository.delete(expense);

        return ok();
    }

    public Result markAsPaid(long expenseId) {
        Expense expense = expenseService.markAsPaid(expenseRepository.findById(expenseId));

        return ok();
    }
}
