package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.Loan;

import java.util.Comparator;

public class LoanComparator implements Comparator<Loan> {

    @Override
    public int compare(Loan loan1, Loan loan2) {


        if (loan1.getRiskType().compareTo(loan2.getRiskType()) != 0) {
            return loan1.getRiskType().compareTo(loan2.getRiskType());
        }

        if (loan2.getTotalLoanCost().compareTo(loan1.getTotalLoanCost()) != 0) {
            return loan2.getTotalLoanCost().compareTo(loan1.getTotalLoanCost());
        }

        return loan1.getCreationDate().compareTo(loan2.getCreationDate()) != 0 ? loan1.getCreationDate().compareTo(loan2.getCreationDate()) : 0;
    }

}
