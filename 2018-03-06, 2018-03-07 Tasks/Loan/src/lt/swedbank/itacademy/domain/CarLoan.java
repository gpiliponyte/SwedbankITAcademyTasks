package lt.swedbank.itacademy.domain;

import java.util.Objects;

/**
 * Created by p998gpy on 2018.03.07.
 */
public class CarLoan extends VehicleLoan implements Comparable {
    private float enginePower;

    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    public int compareTo(Object o){

        CarLoan carLoan = (CarLoan) o;


            if(getPrice().compareTo(carLoan.getPrice()) == -1) return 1;
            if(getPrice().compareTo(carLoan.getPrice()) == 1) return -1;
            if(getEnginePower() > carLoan.getEnginePower()) return -1;
            if(getEnginePower() < carLoan.getEnginePower()) return 1;


        return getInterestRate().compareTo(carLoan.getInterestRate());
    }
}
