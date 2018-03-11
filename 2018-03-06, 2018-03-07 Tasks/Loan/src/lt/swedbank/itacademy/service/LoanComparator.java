package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.Loan;

import java.util.Comparator;

public class LoanComparator implements Comparator<Loan> {

    @Override
    public int compare(Loan loan1, Loan loan2) {

        //It works and test passes, but, in general, this is not a most appropriate way to compare 2 enum values.
        //I've created an example with some explanations on a few different strategies you can use while sorting enums.
        //If you'll feel interested, please, check it out - https://github.com/andrewmic/enum-comparator-example
        if (loan1.getRiskType().compareTo(loan2.getRiskType()) != 0) {
            return loan1.getRiskType().compareTo(loan2.getRiskType());
        }

        if (loan2.getTotalLoanCost().compareTo(loan1.getTotalLoanCost()) != 0) {
            return loan2.getTotalLoanCost().compareTo(loan1.getTotalLoanCost());
        }

        //You don't need to check the last compare result. This can be simplified to:
        //return loan1.getCreationDate().compareTo(loan2.getCreationDate())
        return loan1.getCreationDate().compareTo(loan2.getCreationDate()) != 0 ? loan1.getCreationDate().compareTo(loan2.getCreationDate()) : 0;
    }

}
