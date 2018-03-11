package lt.swedbank.itacademy;

import lt.swedbank.itacademy.domain.Loan;
import lt.swedbank.itacademy.domain.VehicleLoan;
import lt.swedbank.itacademy.service.LoanIterable;
import lt.swedbank.itacademy.service.LoanService;

public class Task2Test {
    public static void main(String[] args) {
        LoanService loanService = new LoanService(
                new LoanIterable(
                createLoans(
                        "Scout Traveler",
                        "Audi A3",
                        "Alfa Romeo Spider",
                        "Audi A3",
                        "BMW i8",
                        "Scout Traveler"
                )
                )
        );

        for (String model : loanService.findVehicleModels()) {
            System.out.println(model);
        }
    }

    private static Loan[] createLoans(String... vehicleModels) {
        Loan[] loans = new Loan[vehicleModels.length];
        for (int i = 0; i < vehicleModels.length; i++) {
            loans[i] = createVehicleLoan(vehicleModels[i]);
        }
        return loans;
    }

    private static VehicleLoan createVehicleLoan(String model) {
        VehicleLoan vehicleLoan = new VehicleLoan();
        vehicleLoan.setModel(model);
        return vehicleLoan;
    }
}
