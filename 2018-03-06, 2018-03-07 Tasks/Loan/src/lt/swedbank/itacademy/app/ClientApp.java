package lt.swedbank.itacademy.app;


        import lt.swedbank.itacademy.domain.Loan;
        import lt.swedbank.itacademy.domain.LoanRiskType;
        import lt.swedbank.itacademy.domain.RealEstatePurpose;
        import lt.swedbank.itacademy.service.LoanService;

        import java.util.Arrays;

public class ClientApp {

    public static void main(String[] args) {

        Loan[] loans = getInitializer1().initializeLoans();
        LoanService service = new LoanService(loans);

        //high risk loans

        service.setHighRiskLoans(service.findLoansByLoanRiskType(LoanRiskType.HIGH_RISK));

        System.out.println(service.getHighRiskLoans().size());

        //get average loan cost

        service.setAverageLoanCost(service.calculateAverageLoanCost(Arrays.asList(loans)));

        System.out.println(service.getAverageLoanCost());

        //NORMAL_RISK

        System.out.println(service.calculateAverageLoanCost(LoanRiskType.NORMAL_RISK));

        //HIGH_RISK

        System.out.println(service.calculateAverageLoanCost(LoanRiskType.HIGH_RISK));

        //LOW

        System.out.println(service.calculateAverageLoanCost(LoanRiskType.LOW_RISK));

        //GET AVERAGE OF HIGH RISK LOANS

        service.setAverageHighRiskLoanCost(service.calculateAverageLoanCost(LoanRiskType.HIGH_RISK));

        System.out.println(service.getAverageCostOfHighRiskLoans());

        //maximum

        service.setMaximumPriceOfNonExpiredLoans(service.calculateMaximumPriceOfNonExpiredLoans());

        System.out.println(service.getMaximumPriceOfNonExpiredLoans());

        System.out.println("--------------------------------------");

        loans = getInitializer2().initializeLoans();
        service = new LoanService(loans);

        service.setNormalRiskVehicleLoans(service.findVehicleLoansByRiskType(LoanRiskType.NORMAL_RISK));
        System.out.println("1: " + service.getNormalRiskVehicleLoans().size());

        service.setMaximumAgeOfLowRiskLoanedVehicles(service.calculateMaximumAgeOfLowRiskVehicles());
        System.out.println("2: " + service.getMaximumAgeOfLowRiskLoanedVehicles());

        service.setPersonalRealEstateLoans(service.findRealEstateLoansByPurpose(RealEstatePurpose.PERSONAL));
        System.out.println("3: " + service.getPersonalRealEstateLoans().size());

        service.setExpiredHighRiskVehicleLoansOfHighestDuration(service.findExpiredVehicleLoansOfHighestDurationByRiskType(LoanRiskType.HIGH_RISK));
        System.out.println("4: " + service.getExpiredHighRiskVehicleLoansOfHighestDuration().size() + " " +
                service.getExpiredHighRiskVehicleLoansOfHighestDuration().get(0).getTermInYears() );


        System.out.println("--------------------------------------------");

        loans = getInitializer3().initializeLoans();
        service = new LoanService(loans);

        service.setLowRiskHarvesterLoans(service.findLowRiskHarvesterLoans());
        System.out.println(service.getLowRiskHarvesterLoans().size());

        service.setExpiredLandLoansInReservation(service.findExpiredLandLoansInReservation());
        System.out.println(service.getExpiredLandLoansInReservation().size());

        service.setLoansOfHigherThanAverageDepreciation(service.findLoansOfHigherThanAverageDepreciation());
        System.out.println(service.getLoansOfHigherThanAverageDepreciation().size());
    }


    public static DomainInitializer getInitializer1() {

        return new Task1DomainInitializer();

    }

    public static DomainInitializer getInitializer2() {

        return new Task2DomainInitializer();

    }

    public static DomainInitializer getInitializer3() {

        return new Task3DomainInitializer();

    }

}