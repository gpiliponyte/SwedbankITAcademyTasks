package lt.swedbank.itacademy.domain;

public class CarLoan extends VehicleLoan implements Comparable { //Comparable interface has a generic type. You should always specify it (ex.: Comparable<CarLoan>)
    private float enginePower;

    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    //Order is correct
    public int compareTo(Object o) {
        //This cast won't be needed if "CarLoan" would implement "Comparable" correctly. See comment above.
        CarLoan carLoan = (CarLoan) o;

        //I you are calling same method twice (or more) with the same parameters, you should save it's result into a
        // variable and use it instead of calling a method for the second time.
        if (carLoan.getPrice().compareTo(getPrice()) != 0) {
            return carLoan.getPrice().compareTo(getPrice());
        }


        if(((Float)carLoan.getEnginePower()).compareTo((Float)getEnginePower()) != 0) {
            return ((Float)carLoan.getEnginePower()).compareTo((Float)getEnginePower());
        }


        return getInterestRate().compareTo(carLoan.getInterestRate());
    }
}
