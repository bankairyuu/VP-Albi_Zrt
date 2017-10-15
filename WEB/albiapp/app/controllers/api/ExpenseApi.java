package controllers.api;

import backend.models.Expense;
import backend.repositories.ExpenseRepository;
import backend.repositories.UserRepository;
import backend.services.ExpenseService;
import controllers.api.models.ApiExpense;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Api(value = "/expenses", description = "Expenses", consumes="application/json, application/xml")
public class ExpenseApi extends Controller {
    @Inject
    private ExpenseRepository expenseRepository;

    @Inject
    private ExpenseService expenseService;

    @Inject
    private UserRepository userRepository;

    @ApiOperation(value = "List expenses", response = ApiExpense.class, responseContainer = "List")
    public Result list() {
        return ok(Json.toJson(expenseRepository.findAll().stream().map(ApiExpense::new).collect(Collectors.toList())));
    }

    @ApiOperation(value = "Create expense")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", allowMultiple = true, dataType = "controllers.api.models.ApiExpense", value = "Api expense", required = true)
    })
    //@Secured
    public Result create() {

        ApiExpense apiExpense = Json.fromJson(request().body().asJson(), ApiExpense.class);

        Expense expense = new Expense(userRepository.findById(apiExpense.From.ID), userRepository.findById(apiExpense.To.ID),
                apiExpense.Name, apiExpense.Description, Expense.SharingType.valueOf(apiExpense.SharingType), apiExpense.Amount,
                apiExpense.ApartmentCost, apiExpense.ExpenseMonth);

        expenseRepository.save(expense);

        return ok();
    }


    @ApiOperation(value = "Update expense")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", allowMultiple = true, dataType = "controllers.api.models.ApiExpense", value = "Api expense", required = true)
    })
    //@Secured
    public Result update() {
        ApiExpense apiExpense = Json.fromJson(request().body().asJson(), ApiExpense.class);

        Expense expense = new Expense(userRepository.findById(apiExpense.From.ID), userRepository.findById(apiExpense.To.ID),
                apiExpense.Name, apiExpense.Description, Expense.SharingType.valueOf(apiExpense.SharingType), apiExpense.Amount,
                apiExpense.ApartmentCost, apiExpense.ExpenseMonth);

        expense.id = apiExpense.ID;

        expenseRepository.save(expense);

        return ok();
    }

    @ApiOperation(value = "Delete expense")
    //@Secured
    public Result delete(long expenseId) {
        Expense expense = expenseRepository.findById(expenseId);
        expenseRepository.delete(expense);

        return ok();
    }

    @ApiOperation(value = "Mark expense as paid")
    public Result markAsPaid(long expenseId) {
        Expense expense = expenseService.markAsPaid(expenseRepository.findById(expenseId));

        return ok();
    }
}
