package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.Loan;
import lt.swedbank.itacademy.domain.LoanRiskType;
import lt.swedbank.itacademy.domain.RealEstatePurpose;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LoanServiceInterface {

    List<Loan> getLowRiskHarvesterLoans();

    List<Loan> getExpiredLandLoansInReservation();

    List<Loan> getLoansOfHigherThanAverageDepreciation();

    Set<Loan> prioritizeLoans();

    int getMaximumAgeOfLowRiskLoanedVehicles();

    int calculateMaximumAgeOfLowRiskVehicles();

    void setMaximumAgeOfLowRiskLoanedVehicles(int maximumAgeOfLowRiskLoanedVehicles);

    List<Loan> getPersonalRealEstateLoans();

    List<Loan> findRealEstateLoansByPurpose(RealEstatePurpose purpose);

    void setPersonalRealEstateLoans(List<Loan> personalRealEstateLoans);

    List<Loan> getExpiredHighRiskVehicleLoansOfHighestDuration();

    List<Loan> findExpiredVehicleLoansOfHighestDurationByRiskType(LoanRiskType loanRiskType);

    void setExpiredHighRiskVehicleLoansOfHighestDuration(List<Loan> expiredHighRiskVehicleLoansOfHighestDuration);

    void setHighRiskLoans(List<Loan> highRiskLoans);

    List<Loan> getHighRiskLoans();

    Loan[] getLoans();

    void setLoans(Loan[] loans);

    List<Loan> getNormalRiskVehicleLoans();

    List<Loan> findVehicleLoansByRiskType(LoanRiskType loanRiskType);

    void setNormalRiskVehicleLoans(List<Loan> normalRiskVehicleLoans);

    BigDecimal getAverageHighRiskLoanCost();

    List<Loan> findLoansByLoanRiskType(LoanRiskType loanRiskType);

    void setAverageLoanCost(BigDecimal averageLoanCost);

    BigDecimal calculateAverageLoanCost(List<Loan> loans);

    BigDecimal getAverageLoanCost();

    BigDecimal calculateAverageLoanCost(LoanRiskType loanRiskType);

    void setAverageHighRiskLoanCost(BigDecimal averageHighRiskLoanCost);

    BigDecimal getAverageCostOfHighRiskLoans();

    BigDecimal getMaximumPriceOfNonExpiredLoans();

    BigDecimal calculateMaximumPriceOfNonExpiredLoans();

    void setMaximumPriceOfNonExpiredLoans(BigDecimal maximumPriceOfNonExpiredLoans);

    Set<String> findVehicleModels();

    Map<LoanRiskType, List<Loan>> groupLoansByRiskType();
}
