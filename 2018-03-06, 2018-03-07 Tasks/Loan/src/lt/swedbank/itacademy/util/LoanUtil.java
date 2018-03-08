package lt.swedbank.itacademy.util;

import lt.swedbank.itacademy.domain.Loan;
import lt.swedbank.itacademy.domain.VehicleLoan;

import java.math.BigDecimal;
import java.util.Date;

public class LoanUtil {

    public static BigDecimal calculateVehicleDepreciation(VehicleLoan loan) {

        int yearsInUse = (int) (DateUtil.differenceInDays(new Date(), loan.getManufactured())) / 365;

        BigDecimal vehicleDepreciation =

                loan.getPrice().multiply(new BigDecimal(yearsInUse)).

                        divide(new BigDecimal(loan.getMaximumAge()), 2, BigDecimal.ROUND_UP);

        return vehicleDepreciation;
    }

    public static boolean isValid(Loan loan){
        return DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears()).after(new Date());
    }

    public static BigDecimal calculateInterests(Loan loan){
        return loan.getPrice().multiply(loan.getInterestRate()).divide(new BigDecimal(100));
    }

    public static BigDecimal calculateTotalLoanCost(Loan loan){
        return loan.getPrice().add(calculateInterests(loan));
    }
}