package lt.swedbank.itacademy.domain;

import java.util.Date;
import java.math.BigDecimal;

public class Payment {
    private PaymentSystemAccount source, destination;

    private BigDecimal amount;
    private Client payee;
    private Date paymentDate;

    public String getDescription() {
        return "From: " + source.getNumber() +
                " to: " + destination.getNumber() +
                " by: " + payee.getCode() +
                " amount: " + amount.toString();
    }

    @Override
    public String toString() {
        return "Payment{" +
                "source=" + source +
                ", destination=" + destination +
                ", amount=" + amount +
                ", payee=" + payee +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
