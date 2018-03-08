package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.Loan;
import lt.swedbank.itacademy.domain.LoanRiskType;

import java.util.Comparator;

public class LoanComparator implements Comparator{

    @Override
    public int compare(Object object1, Object object2) {

        Loan loan1 = (Loan) object1;
        Loan loan2 = (Loan) object2;
        if(loan1.getRiskType() == LoanRiskType.HIGH_RISK && loan2.getRiskType() !=LoanRiskType.HIGH_RISK) return 1;
        if(loan2.getRiskType() == LoanRiskType.HIGH_RISK && loan1.getRiskType() !=LoanRiskType.HIGH_RISK) return -1;

        if(loan1.getTotalLoanCost().compareTo(loan2.getTotalLoanCost()) == 1) return 1;
        if(loan1.getTotalLoanCost().compareTo(loan2.getTotalLoanCost()) == -1) return -1;

        if(loan1.getCreationDate().before(loan2.getCreationDate())) return 1;
        if(loan2.getCreationDate().before(loan1.getCreationDate())) return -1;

        return 0;
    }

}
