package lt.swedbank.itacademy.domain;

public class InternationalPaymentSystem extends PaymentSystem {

    private String sourceCountry;

    private String destinationCountry;

    public InternationalPaymentSystem(String name) {
        super();
        super.setName(name);
    }

    @Override
    public String getName() {
        return "International Payment System: " + super.getName();
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getSourceCountry() {
        return sourceCountry;
    }

    public void setSourceCountry(String sourceCountry) {
        this.sourceCountry = sourceCountry;
    }

}
