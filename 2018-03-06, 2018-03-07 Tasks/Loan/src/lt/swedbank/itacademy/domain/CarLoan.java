package lt.swedbank.itacademy.domain;

public class CarLoan extends VehicleLoan implements Comparable {
    private float enginePower;

    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    public int compareTo(Object o) {

        CarLoan carLoan = (CarLoan) o;

        if (carLoan.getPrice().compareTo(getPrice()) != 0) {
            return carLoan.getPrice().compareTo(getPrice());
        }


        if(((Float)carLoan.getEnginePower()).compareTo((Float)getEnginePower()) != 0) {
            return ((Float)carLoan.getEnginePower()).compareTo((Float)getEnginePower());
        }


        return getInterestRate().compareTo(carLoan.getInterestRate());
    }
}
