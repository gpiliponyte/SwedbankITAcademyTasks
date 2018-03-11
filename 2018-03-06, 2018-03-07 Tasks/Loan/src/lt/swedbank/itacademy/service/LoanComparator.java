package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.Loan;
import lt.swedbank.itacademy.domain.LoanRiskType;

import java.util.Comparator;

public class LoanComparator implements Comparator<Loan> {

    @Override
    public int compare(Loan loan1, Loan loan2) {

        int compareResult;
        Comparator<LoanRiskType> loanRiskTypeComparator = new LoanRiskTypeComparator();

        if ((compareResult = loanRiskTypeComparator.compare(loan2.getRiskType(), loan1.getRiskType())) != 0) {
            return compareResult;
        }

        if ((compareResult = loan2.getTotalLoanCost().compareTo(loan1.getTotalLoanCost())) != 0) {
            return compareResult;
        }

        return loan1.getCreationDate().compareTo(loan2.getCreationDate());
    }

}
