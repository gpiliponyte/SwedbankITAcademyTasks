package lt.swedbank.itacademy.domain;

public class LandLoan extends RealEstateLoan {

    private boolean inReservation;

    public void setInReservation(boolean inReservation){
        this.inReservation = inReservation;
    }

    public boolean isInReservation(){
        return inReservation;
    }
}
