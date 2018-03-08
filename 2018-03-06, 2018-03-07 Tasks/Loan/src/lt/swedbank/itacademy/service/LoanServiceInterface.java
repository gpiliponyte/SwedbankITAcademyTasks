package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.Loan;
import lt.swedbank.itacademy.domain.LoanRiskType;
import lt.swedbank.itacademy.domain.RealEstatePurpose;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LoanServiceInterface {
    List<Loan> findExpiredLandLoansInReservation();

    List<Loan> findLoansOfHigherThanAverageDepreciation();

    BigDecimal calculateAverageDepreciation();

    List<Loan> findLowRiskHarvesterLoans();

    Set<Loan> prioritizeLoans();

    int calculateMaximumAgeOfLowRiskVehicles();

    List<Loan> findRealEstateLoansByPurpose(RealEstatePurpose purpose);

    List<Loan> findExpiredVehicleLoansOfHighestDurationByRiskType(LoanRiskType loanRiskType);

    List<Loan> findVehicleLoansByRiskType(LoanRiskType loanRiskType);

    List<Loan> findLoansByLoanRiskType(LoanRiskType loanRiskType);

    BigDecimal calculateAverageLoanCost(List<Loan> loans);

    BigDecimal calculateAverageLoanCost(LoanRiskType loanRiskType);

    BigDecimal calculateMaximumPriceOfNonExpiredLoans();

    Set<String> findVehicleModels();

    Map<LoanRiskType, List<Loan>> groupLoansByRiskType();
}
