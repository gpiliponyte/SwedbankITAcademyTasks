package lt.swedbank.itacademy.interestcalculator;

import java.util.Arrays;
import java.util.Scanner;


public class CompoundInterestCalculator5 {

    private static final int PERCENT = 100;

    //Some code could be extracted into methods, but, in general, your solution is correct!

    public static void main(String[] args) {

        //Practice declaring your variables as close to a place in code, where they're actually used/needed, as possible.
        String input;
        double originalAmount;
        int numberOfInterestRates = 0;
        double annualInterestRates[] = new double[numberOfInterestRates];
        double interestRate;
        int periodLengthInYears;
        int frequency;
        String compoundFrequency;
        double newAmount;
        double copyOfAnnualInterestAmounts[];
        double yearlyInterestMatrix[][];

        Scanner scanner = new Scanner(System.in);


        while (true) {
            try {
                System.out.print("Amount: ");
                input = scanner.next();
                originalAmount = Double.parseDouble(input);
                if (originalAmount > 0) {
                    break;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Invalid input! Try again!");
        }


        do {
            try {
                System.out.print("Interest rate (%): ");
                input = scanner.next();
                interestRate = Double.parseDouble(input);
                if (interestRate == 0) {
                    break;
                } else if (interestRate > 0 && interestRate <= 100) {
                    numberOfInterestRates++;
                    copyOfAnnualInterestAmounts = new double[numberOfInterestRates];
                    System.arraycopy(annualInterestRates, 0, copyOfAnnualInterestAmounts, 0, annualInterestRates.length);
                    annualInterestRates = copyOfAnnualInterestAmounts;
                    annualInterestRates[numberOfInterestRates - 1] = interestRate;
                    //He he.. Sneaky way to skip "Invalid input! Try again!" printing. Clever.
                    continue;
                }
            } catch (NumberFormatException e) {

            }
            System.out.println("Invalid input! Try again!");
        } while (true);


        while (true) {
            try {
                System.out.print("Period length (years): ");
                input = scanner.next();
                periodLengthInYears = Integer.parseInt(input);
                if (periodLengthInYears > 0) break;
            } catch (NumberFormatException e) {
            }
            System.out.println("Invalid input! Try again!");
        }


        yearlyInterestMatrix = new double[numberOfInterestRates][periodLengthInYears];


        while (true) {
            System.out.print("Compound frequency: ");
            compoundFrequency = scanner.next();
            if (Arrays.asList(new String[]{"D", "W", "M", "Q", "H", "Y"}).contains(compoundFrequency)) {
                break;
            }
            System.out.println("Invalid input! Try again!");
        }


        scanner.close();

        frequency = findFrequency(compoundFrequency);


        for (int i = 0; i < numberOfInterestRates; i++) {
            newAmount = originalAmount;
            for (int j = 0; j < periodLengthInYears; j++) {
                yearlyInterestMatrix[i][j] = calculateNewInterestAmount(annualInterestRates[i], newAmount, frequency);
                newAmount += yearlyInterestMatrix[i][j];
            }
        }


        for (double[] row : yearlyInterestMatrix) {
            for (double element : row) {
                System.out.printf("%.2f ", element);
            }
            System.out.println();
        }
    }


    private static double calculateNewInterestAmount(double annualInterestRate, double originalAmount, int frequency) {
        return (originalAmount * Math.pow(1 + (annualInterestRate / PERCENT / frequency), frequency)) - originalAmount;
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
