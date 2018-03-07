package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.*;
import lt.swedbank.itacademy.util.DateUtil;
import lt.swedbank.itacademy.util.LoanUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class LoanService implements LoanServiceInterface {

    private Loan[] loans;
    private List<Loan> highRiskLoans;
    private List<Loan> normalRiskVehicleLoans;

    private BigDecimal averageLoanCost;
    private BigDecimal averageHighRiskLoanCost;

    private BigDecimal  maximumPriceOfNonExpiredLoans;

    private List<Loan> expiredLandLoansInReservation;
    private List<Loan> loansOfHigherThanAverageDepreciation;
    private List<Loan> lowRiskHarvesterLoans;


    private int maximumAgeOfLowRiskLoanedVehicles;
    private List<Loan> personalRealEstateLoans;
    private List<Loan> expiredHighRiskVehicleLoansOfHighestDuration;

    public LoanService(Loan[] loans){
        this.loans = loans;
    }

    public List<Loan> getExpiredLandLoansInReservation(){
        return  expiredLandLoansInReservation;
    }

    public void setExpiredLandLoansInReservation(List<Loan> expiredLandLoansInReservation){
        this.expiredLandLoansInReservation = expiredLandLoansInReservation;
    }

    public List<Loan> findExpiredLandLoansInReservation(){

        List<Loan> expiredLandLoansInReservation = new ArrayList<>();

        for(Loan loan : loans){
            if(loan instanceof LandLoan && !loan.isValid() && ((LandLoan) loan).isInReservation()){
                expiredLandLoansInReservation.add(loan);
            }
        }

        return expiredLandLoansInReservation;
    }

    @Override
    public List<Loan> getLoansOfHigherThanAverageDepreciation() {
        return loansOfHigherThanAverageDepreciation;
    }

    public List<Loan> findLoansOfHigherThanAverageDepreciation() {

        List<Loan> loansOfHigherThanAverageDepreciation = new ArrayList<>();

        BigDecimal averageDepreciation = calculateAverageDepreciation();


        for(Loan loan : loans){
            if(loan instanceof  VehicleLoan && LoanUtil.calculateVehicleDepreciation((VehicleLoan)loan).compareTo(averageDepreciation) == 1) {
                loansOfHigherThanAverageDepreciation.add(loan);
            }
        }

        return loansOfHigherThanAverageDepreciation;
    }

    public BigDecimal calculateAverageDepreciation() {
        BigDecimal sum = new BigDecimal(0);
        BigDecimal counter = new BigDecimal(0);

        for (Loan loan : loans) {

            if (loan instanceof VehicleLoan) {
                sum = sum.add(LoanUtil.calculateVehicleDepreciation((VehicleLoan) loan));
                counter = counter.add(new BigDecimal(1));

            }

        }

        return sum.divide(counter, 5, RoundingMode.CEILING);

    }

        public void setLoansOfHigherThanAverageDepreciation (List < Loan > loansOfHigherThanAverageDepreciation) {
            this.loansOfHigherThanAverageDepreciation = loansOfHigherThanAverageDepreciation;
        }

        @Override
        public List<Loan> getLowRiskHarvesterLoans () {
            return lowRiskHarvesterLoans;
        }

        public List<Loan> findLowRiskHarvesterLoans () {

            List<Loan> lowRiskHarvesterLoans = new ArrayList<>();

            for (Loan loan : loans) {
                if (loan.getRiskType() == LoanRiskType.LOW_RISK && loan instanceof HarvesterLoan) {
                    lowRiskHarvesterLoans.add(loan);
                }
            }

            return lowRiskHarvesterLoans;
        }

        public void setLowRiskHarvesterLoans (List < Loan > lowRiskHarvesterLoans) {
            this.lowRiskHarvesterLoans = lowRiskHarvesterLoans;
        }

        @Override
        public Set<Loan> prioritizeLoans () {


            return new HashSet<>();
        }

        @Override
        public int getMaximumAgeOfLowRiskLoanedVehicles () {
            return maximumAgeOfLowRiskLoanedVehicles;
        }

        @Override
        public int calculateMaximumAgeOfLowRiskVehicles () {

            int maximumAgeOfLowRiskVehicles = 0;
            int vehicleAge;

            for (Loan loan : this.loans) {


                if (loan.getRiskType() == LoanRiskType.LOW_RISK && loan instanceof VehicleLoan) {

                    vehicleAge = (int) DateUtil.differenceInDays(new Date(), ((VehicleLoan) loan).getManufactured()) / 365;
                    if (vehicleAge > maximumAgeOfLowRiskVehicles) maximumAgeOfLowRiskVehicles = vehicleAge;

                }

            }

            return maximumAgeOfLowRiskVehicles;
        }

        @Override
        public void setMaximumAgeOfLowRiskLoanedVehicles ( int maximumAgeOfLowRiskLoanedVehicles){
            this.maximumAgeOfLowRiskLoanedVehicles = maximumAgeOfLowRiskLoanedVehicles;
        }

        @Override
        public List<Loan> getPersonalRealEstateLoans () {
            return personalRealEstateLoans;
        }

        @Override
        public List<Loan> findRealEstateLoansByPurpose (RealEstatePurpose purpose){


            List<Loan> realEstateLoans = new ArrayList<>();

            for (Loan loan : loans) {
                if (loan instanceof RealEstateLoan && ((RealEstateLoan) loan).getPurpose() == purpose) {
                    realEstateLoans.add(loan);
                }
            }


            return realEstateLoans;

        }

        @Override
        public void setPersonalRealEstateLoans (List < Loan > personalRealEstateLoans) {
            this.personalRealEstateLoans = personalRealEstateLoans;
        }

        @Override
        public List<Loan> getExpiredHighRiskVehicleLoansOfHighestDuration () {
            return expiredHighRiskVehicleLoansOfHighestDuration;
        }

        @Override
        public List<Loan> findExpiredVehicleLoansOfHighestDurationByRiskType (LoanRiskType loanRiskType){

            int highestDuration = 0;
            List<Loan> list = new ArrayList();
            for (Loan loan : loans) {
                if (loan.getRiskType() == loanRiskType && !loan.isValid() && loan instanceof VehicleLoan && loan.getTermInYears() > highestDuration) {
                    highestDuration = loan.getTermInYears();
                }
            }


            for (Loan loan : loans) {
                if (loan.getRiskType() == loanRiskType && !loan.isValid() && loan instanceof VehicleLoan && loan.getTermInYears() == highestDuration) {
                    list.add(loan);
                }
            }


            return list;

        }

        @Override
        public void setExpiredHighRiskVehicleLoansOfHighestDuration
        (List < Loan > expiredHighRiskVehicleLoansOfHighestDuration) {
            this.expiredHighRiskVehicleLoansOfHighestDuration = expiredHighRiskVehicleLoansOfHighestDuration;
        }

        @Override
        public void setHighRiskLoans (List < Loan > highRiskLoans) {
            this.highRiskLoans = highRiskLoans;

        }
        @Override
        public List<Loan> getHighRiskLoans () {

            return highRiskLoans;
        }

        @Override
        public Loan[] getLoans () {
            return loans;
        }

        @Override
        public void setLoans (Loan[]loans){
            this.loans = loans;
        }

        @Override
        public List<Loan> getNormalRiskVehicleLoans () {
            return normalRiskVehicleLoans;
        }

        @Override
        public List<Loan> findVehicleLoansByRiskType (LoanRiskType loanRiskType){

            List<Loan> vehicleLoans = new ArrayList<>();

            for (Loan loan : loans) {
                if (loan instanceof VehicleLoan && loan.getRiskType() == loanRiskType) {
                    vehicleLoans.add(loan);
                }
            }

            return vehicleLoans;
        }

        @Override
        public void setNormalRiskVehicleLoans (List < Loan > normalRiskVehicleLoans) {
            this.normalRiskVehicleLoans = normalRiskVehicleLoans;
        }

        @Override
        public BigDecimal getAverageHighRiskLoanCost () {
            return averageHighRiskLoanCost;
        }

        @Override
        public List<Loan> findLoansByLoanRiskType (LoanRiskType loanRiskType){

            List<Loan> loans = new ArrayList<>();

            for (Loan loan : this.loans) {
                if (loan.getRiskType() == loanRiskType) {
                    loans.add(loan);
                }
            }

            return loans;

        }

        @Override
        public void setAverageLoanCost (BigDecimal averageLoanCost){
            this.averageLoanCost = averageLoanCost;
        }

        @Override
        public BigDecimal calculateAverageLoanCost (List < Loan > loans) {
            BigDecimal totalCost = new BigDecimal(0);
            for (Loan loan : loans) {
                totalCost = totalCost.add(loan.getTotalLoanCost());
            }

            return totalCost.divide(new BigDecimal(loans.size()), 5, RoundingMode.CEILING);
        }

        @Override
        public BigDecimal getAverageLoanCost () {
            return averageLoanCost;
        }

        @Override
        public BigDecimal calculateAverageLoanCost (LoanRiskType loanRiskType){
            List<Loan> loanRiskTypeArray = findLoansByLoanRiskType(loanRiskType);
            return calculateAverageLoanCost(loanRiskTypeArray);
        }

        @Override
        public void setAverageHighRiskLoanCost (BigDecimal averageHighRiskLoanCost){
            this.averageHighRiskLoanCost = averageHighRiskLoanCost;
        }

        @Override
        public BigDecimal getAverageCostOfHighRiskLoans () {
            return averageHighRiskLoanCost;
        }

        @Override
        public BigDecimal getMaximumPriceOfNonExpiredLoans () {
            return maximumPriceOfNonExpiredLoans;
        }

        @Override
        public BigDecimal calculateMaximumPriceOfNonExpiredLoans () {
            BigDecimal maximumPriceOfNonExpiredLoans = new BigDecimal(0);
            for (Loan loan : loans) {
                if (loan.isValid() && maximumPriceOfNonExpiredLoans.compareTo(loan.getPrice()) == -1) {
                    maximumPriceOfNonExpiredLoans = loan.getPrice();
                }
            }
            return maximumPriceOfNonExpiredLoans;
        }


        @Override
        public void setMaximumPriceOfNonExpiredLoans (BigDecimal maximumPriceOfNonExpiredLoans){
            this.maximumPriceOfNonExpiredLoans = maximumPriceOfNonExpiredLoans;
        }

        @Override
        public Set<String> findVehicleModels () {

            Set<String> vehicleModels = new HashSet<>();

            for (Loan loan : loans) {
                if (loan instanceof VehicleLoan) vehicleModels.add(((VehicleLoan) loan).getModel());
            }
            return vehicleModels;
        }

        @Override
        public Map<LoanRiskType, List<Loan>> groupLoansByRiskType () {

            Map<LoanRiskType, List<Loan>> map = new TreeMap<>();

            for (Loan loan : loans) {
                if (!map.containsKey(loan.getRiskType())) {
                    map.put(loan.getRiskType(), new ArrayList<>());
                }

                map.get(loan.getRiskType()).add(loan);
            }
            return map;
        }


}
