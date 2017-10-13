package backend.services;

import backend.models.MonthlyFee;
import backend.repositories.MonthlyFeeRepository;

import javax.inject.Inject;

public class MonthlyFeeService {
    private final MonthlyFeeRepository monthlyFeeRepository;

    @Inject
    public MonthlyFeeService(MonthlyFeeRepository monthlyFeeRepository) {
        this.monthlyFeeRepository = monthlyFeeRepository;
    }

    public MonthlyFee createOrUpdateMonthlyFee(MonthlyFee monthlyFee) {
        return monthlyFeeRepository.save(monthlyFee);
    }
}
