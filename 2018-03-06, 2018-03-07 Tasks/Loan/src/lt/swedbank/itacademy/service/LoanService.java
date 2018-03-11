package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.*;
import lt.swedbank.itacademy.util.DateUtil;
import lt.swedbank.itacademy.util.LoanUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class LoanService implements LoanServiceInterface {

    private Iterable<Loan> loans;


    public LoanService(Iterable<Loan> loans) {
        this.loans = loans;
    }


    @Override
    public List<Loan> findExpiredLandLoansInReservation() {

        List<Loan> expiredLandLoansInReservation = new ArrayList<>();

        for (Loan loan : loans) {
            if (loan instanceof LandLoan && !LoanUtil.isValid(loan) && ((LandLoan) loan).isInReservation()) {
                expiredLandLoansInReservation.add(loan);
            }
        }

        return expiredLandLoansInReservation;
    }


    @Override
    public List<Loan> findLoansOfHigherThanAverageDepreciation() {

        List<Loan> loansOfHigherThanAverageDepreciation = new ArrayList<>();

        BigDecimal averageDepreciation = calculateAverageDepreciation();


        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan && LoanUtil.calculateVehicleDepreciation((VehicleLoan) loan).compareTo(averageDepreciation) > 0) {
                loansOfHigherThanAverageDepreciation.add(loan);
            }
        }

        return loansOfHigherThanAverageDepreciation;
    }

    @Override
    public BigDecimal calculateAverageDepreciation() {

        BigDecimal sum =  BigDecimal.ZERO;
        BigDecimal counter = BigDecimal.ZERO;

        for (Loan loan : loans) {

            if (loan instanceof VehicleLoan) {

                sum = sum.add(LoanUtil.calculateVehicleDepreciation((VehicleLoan) loan));
                counter = counter.add(new BigDecimal(1));
            }

        }

        return sum.divide(counter, 5, RoundingMode.CEILING);

    }

    @Override
    public List<Loan> findLowRiskHarvesterLoans() {

        List<Loan> lowRiskHarvesterLoans = new ArrayList<>();

        for (Loan loan : loans) {
            if (loan.getRiskType() == LoanRiskType.LOW_RISK && loan instanceof HarvesterLoan) {
                lowRiskHarvesterLoans.add(loan);
            }
        }

        return lowRiskHarvesterLoans;
    }


    @Override
    public Set<Loan> prioritizeLoans() {
        Set<Loan> loans = new TreeSet<>(new LoanComparator());

        for (Loan loan : this.loans) {
            loans.add(loan);
        }


        return loans;
    }

    @Override
    public int calculateMaximumAgeOfLowRiskVehicles() {

        int maximumAgeOfLowRiskVehicles = 0;
        int vehicleAge;

        for (Loan loan : loans) {


            if (loan.getRiskType() == LoanRiskType.LOW_RISK && loan instanceof VehicleLoan) {

                vehicleAge = (int) DateUtil.differenceInDays(new Date(), ((VehicleLoan) loan).getManufactured()) / 365;
                if (vehicleAge > maximumAgeOfLowRiskVehicles) maximumAgeOfLowRiskVehicles = vehicleAge;

            }

        }

        return maximumAgeOfLowRiskVehicles;
    }


    @Override
    public List<Loan> findRealEstateLoansByPurpose(RealEstatePurpose purpose) {


        List<Loan> realEstateLoans = new ArrayList<>();

        for (Loan loan : loans) {
            if (loan instanceof RealEstateLoan && ((RealEstateLoan) loan).getPurpose() == purpose) {
                realEstateLoans.add(loan);
            }
        }


        return realEstateLoans;

    }

    @Override
    public List<Loan> findExpiredVehicleLoansOfHighestDurationByRiskType(LoanRiskType loanRiskType) {

        int highestDuration = 0;
        List<Loan> expiredVehicleLoansOfHighestDuration = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getRiskType() == loanRiskType && !LoanUtil.isValid(loan) && loan instanceof VehicleLoan && loan.getTermInYears() > highestDuration) {
                highestDuration = loan.getTermInYears();
            }
        }


        for (Loan loan : loans) {
            if (loan.getRiskType() == loanRiskType && !LoanUtil.isValid(loan) && loan instanceof VehicleLoan && loan.getTermInYears() == highestDuration) {
                expiredVehicleLoansOfHighestDuration.add(loan);
            }
        }


        return expiredVehicleLoansOfHighestDuration;

    }

    @Override
    public List<Loan> findVehicleLoansByRiskType(LoanRiskType loanRiskType) {

        List<Loan> vehicleLoans = new ArrayList<>();

        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan && loan.getRiskType() == loanRiskType) {
                vehicleLoans.add(loan);
            }
        }

        return vehicleLoans;
    }

    @Override
    public List<Loan> findLoansByLoanRiskType(LoanRiskType loanRiskType) {

        List<Loan> loans = new ArrayList<>();

        for (Loan loan : this.loans) {
            if (loan.getRiskType() == loanRiskType) {
                loans.add(loan);
            }
        }

        return loans;

    }

    @Override
    public BigDecimal calculateAverageLoanCost(List<Loan> loans) {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Loan loan : loans) {
            totalCost = totalCost.add(LoanUtil.calculateTotalLoanCost(loan));
        }

        return totalCost.divide(new BigDecimal(loans.size()), 5, RoundingMode.CEILING);
    }


    @Override
    public BigDecimal calculateAverageLoanCost(LoanRiskType loanRiskType) {
        List<Loan> loanRiskTypeArray = findLoansByLoanRiskType(loanRiskType);
        return calculateAverageLoanCost(loanRiskTypeArray);
    }


    @Override
    public BigDecimal calculateMaximumPriceOfNonExpiredLoans() {
        BigDecimal maximumPriceOfNonExpiredLoans = BigDecimal.ZERO;
        for (Loan loan : loans) {
            if (LoanUtil.isValid(loan) && maximumPriceOfNonExpiredLoans.compareTo(loan.getPrice()) == -1) {
                maximumPriceOfNonExpiredLoans = loan.getPrice();
            }
        }
        return maximumPriceOfNonExpiredLoans;
    }


    @Override
    public Set<String> findVehicleModels() {

        Set<String> vehicleModels = new HashSet<>();

        for (Loan loan : loans) {
            if (loan instanceof VehicleLoan) vehicleModels.add(((VehicleLoan) loan).getModel());
        }
        return vehicleModels;
    }

    @Override
    public Map<LoanRiskType, List<Loan>> groupLoansByRiskType() {

        Map<LoanRiskType, List<Loan>> loansByRiskType = new TreeMap<>();

        for (Loan loan : loans) {
            if (!loansByRiskType.containsKey(loan.getRiskType())) {
                loansByRiskType.put(loan.getRiskType(), new ArrayList<Loan>());
            }

            loansByRiskType.get(loan.getRiskType()).add(loan);
        }
        return loansByRiskType;
    }


}
