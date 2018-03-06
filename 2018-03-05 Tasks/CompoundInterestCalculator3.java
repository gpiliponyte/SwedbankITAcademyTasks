package lt.swedbank.itacademy.interestcalculator;

import java.util.Arrays;
import java.util.Scanner;


public class CompoundInterestCalculator3 {

    private static final int PERCENT = 100;

    public static void main(String[] args) {

        double originalAmount;
        int annualInterestRate;
        int periodLengthInYears;
        int frequency;
        String compoundFrequency;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Amount: ");
        originalAmount = scanner.nextDouble();

        System.out.print("Interest rate (%): ");
        annualInterestRate = scanner.nextInt();

        System.out.print("Period length (years): ");
        periodLengthInYears = scanner.nextInt();

        System.out.print("Compound frequency: ");
        compoundFrequency = scanner.next();

        scanner.close();

        frequency = findFrequency(compoundFrequency);

        double[] intermediateAmounts = new double[frequency * periodLengthInYears];

        for (int i = 1; i <= periodLengthInYears * frequency; i++) {

            intermediateAmounts[i - 1] = calculateNewInterestAmount(annualInterestRate, originalAmount, frequency);
            originalAmount += intermediateAmounts[i - 1];

            if (i % frequency == 0) {

                System.out.printf("Interest amount after year %d : %.2f \n", i / frequency, calculateGatheredAmountAtYear(intermediateAmounts, i / frequency, frequency));
            }

        }
        System.out.printf("Total amount: %.2f \n", originalAmount);

        System.out.println(Arrays.toString(intermediateAmounts));

    }

    private static double calculateNewInterestAmount(int annualInterestRate, double originalAmount, int frequency) {

        return (originalAmount * Math.pow(1 + ((double) annualInterestRate / PERCENT / frequency), frequency)) - originalAmount;
    }


    private static double calculateGatheredAmountAtYear(double[] intermediateAmounts, int year, int frequency) {

        double gatheredAmountOfInterest = 0;

        for (int i = 0; i < year * frequency; i++) {
            gatheredAmountOfInterest += intermediateAmounts[i];
        }

        return gatheredAmountOfInterest;
    }


    private static int findFrequency(String frequency) {

        switch (frequency) {
            case "D":
                return 365;
            case "W":
                return 52;
            case "M":
                return 12;
            case "Q":
                return 4;
            case "H":
                return 2;
            default:
                return 1;
        }
    }
}