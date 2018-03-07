package lt.swedbank.itacademy.domain;

public class HarvesterLoan extends VehicleLoan{
    private int capacity;
    private float enginePower;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }
}
