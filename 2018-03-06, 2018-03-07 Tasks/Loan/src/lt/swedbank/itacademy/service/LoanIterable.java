package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.Loan;

import java.util.Iterator;
import java.util.List;

public class LoanIterable implements Iterable {

    private List<Loan> loans;
    public Iterator<Loan> iterator(){
        Iterator<Loan> loanIterator = loans.iterator();
        return loanIterator;


    }
}

