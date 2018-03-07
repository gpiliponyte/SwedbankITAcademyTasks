package lt.swedbank.itacademy.domain;

public class PaymentSystem implements PaymentSystemInterface {

    public String name;

//    public final int age;

    public PaymentSystem() {
        /**/
    }
//
    public PaymentSystem(String name) {
        this.name = name;
//        this();
    }

//    public PaymentSystem(String name, Date birthDate) {
////        this(name);
//        this.age = (int) ( DateUtil.differenceInDays(new Date(), birthDate) / 365 );
//    }
    @Override
    public TransferResult transferPayment(Payment payment) {
        return transferPayment(payment, false);
    }

    @Override
    public TransferResult transferPayment(Payment payment, boolean immediate) {
        // transaction processing implementation omitted
        setName(BANK_NAME);

        return new TransferResult();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}