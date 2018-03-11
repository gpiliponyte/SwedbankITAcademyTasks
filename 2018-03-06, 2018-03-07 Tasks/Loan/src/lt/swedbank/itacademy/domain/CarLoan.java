package lt.swedbank.itacademy.domain;

public class CarLoan extends VehicleLoan implements Comparable<CarLoan> {
    private float enginePower;

    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    public int compareTo(CarLoan carLoan) {

        int compareResult;


        if ((compareResult = carLoan.getPrice().compareTo(getPrice())) != 0) {
            return compareResult;
        }


        if((compareResult = ((Float)carLoan.getEnginePower()).compareTo((Float)getEnginePower())) != 0) {
            return compareResult;
        }


        return getInterestRate().compareTo(carLoan.getInterestRate());
    }
}
