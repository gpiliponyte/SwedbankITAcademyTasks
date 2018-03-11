package lt.swedbank.itacademy.service;

import lt.swedbank.itacademy.domain.Loan;

import java.util.Iterator;

public class LoanIterable implements Iterable<Loan> {

    private Loan[] loans;

    public LoanIterable(Loan[] loans) {

        this.loans = loans;

    }

    //@Override is missing
    //It improves performance. Not much, but every CPU cycle saved helps a little :)
    public Iterator<Loan> iterator() {

        return new Iterator<Loan>() {

            private int iteratorCounter = 0;

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {

                return iteratorCounter < loans.length;

            }

            @Override
            public Loan next() {

                return loans[iteratorCounter++];

            }

        };

    }
}
