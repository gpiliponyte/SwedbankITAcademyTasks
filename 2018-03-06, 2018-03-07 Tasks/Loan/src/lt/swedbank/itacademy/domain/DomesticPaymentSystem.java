package lt.swedbank.itacademy.domain;

public class DomesticPaymentSystem extends PaymentSystem {

    public String name;

    public DomesticPaymentSystem(String name) {
        setName(name);
    }

    @Override
    public String getName() {
        return "Domestic Payment System: " + super.getName();
    }

}
