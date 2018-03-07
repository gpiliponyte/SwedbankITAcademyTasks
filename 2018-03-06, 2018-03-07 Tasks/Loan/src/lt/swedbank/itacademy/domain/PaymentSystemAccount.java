package lt.swedbank.itacademy.domain;

public class PaymentSystemAccount {

    private String number;

    PaymentSystemAccount(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

}
