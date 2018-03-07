package lt.swedbank.itacademy.util;

import lt.swedbank.itacademy.domain.VehicleLoan;

import java.math.BigDecimal;
import java.util.Date;

public class LoanUtil {

    public static BigDecimal calculateVehicleDepreciation(VehicleLoan loan) {
        BigDecimal vehicleDepreciation;

        vehicleDepreciation = loan.getPrice().multiply(new BigDecimal(
                ((int) DateUtil.differenceInDays(new Date(), loan.getManufactured()))
                        / 365 / loan.getMaximumAge()));

        return vehicleDepreciation;
    }
}