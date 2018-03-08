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

        System.out.println(service.findLoansByLoanRiskType(LoanRiskType.HIGH_RISK));

        //get average loan cost


        System.out.println(service.calculateAverageLoanCost(Arrays.asList(loans)));

        //NORMAL_RISK

        System.out.println(service.calculateAverageLoanCost(LoanRiskType.NORMAL_RISK));

        //HIGH_RISK

        System.out.println(service.calculateAverageLoanCost(LoanRiskType.HIGH_RISK));

        //LOW

        System.out.println(service.calculateAverageLoanCost(LoanRiskType.LOW_RISK));

        //GET AVERAGE OF HIGH RISK LOANS

        System.out.println(service.calculateAverageLoanCost(LoanRiskType.HIGH_RISK));

        //maximum

        System.out.println(service.calculateMaximumPriceOfNonExpiredLoans());

        System.out.println("--------------------------------------");

        loans = getInitializer2().initializeLoans();
        service = new LoanService(loans);

        System.out.println("1: " + service.findVehicleLoansByRiskType(LoanRiskType.NORMAL_RISK).size());

        System.out.println("2: " + service.calculateMaximumAgeOfLowRiskVehicles());

        System.out.println("3: " + service.findRealEstateLoansByPurpose(RealEstatePurpose.PERSONAL).size());

        System.out.println("4: " + service.findExpiredVehicleLoansOfHighestDurationByRiskType(LoanRiskType.HIGH_RISK).size() + " " +
                service.findExpiredVehicleLoansOfHighestDurationByRiskType(LoanRiskType.HIGH_RISK).get(0).getTermInYears() );


        System.out.println("--------------------------------------------");

        loans = getInitializer3().initializeLoans();
        service = new LoanService(loans);

        System.out.println(service.findLowRiskHarvesterLoans().size());

        System.out.println(service.findExpiredLandLoansInReservation().size());

        System.out.println(service.findLoansOfHigherThanAverageDepreciation().size());
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