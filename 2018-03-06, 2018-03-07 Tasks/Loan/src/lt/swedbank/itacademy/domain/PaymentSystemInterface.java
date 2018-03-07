package lt.swedbank.itacademy.domain;

public interface PaymentSystemInterface {

    public static final String BANK_NAME = "Swedbank, AB";

    public TransferResult transferPayment(Payment payment);
    public TransferResult transferPayment(Payment payment, boolean immediate);

}
