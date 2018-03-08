package lt.swedbank.itacademy.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Loan {

    private Date creationDate;
    private int termInYears;
    private String name;
    private BigDecimal price;
    private BigDecimal interestRate;
    private LoanRiskType riskType;
    private BigDecimal totalLoanCost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan)) return false;
        Loan loan = (Loan) o;
        return getTermInYears() == loan.getTermInYears() &&
                Objects.equals(getCreationDate(), loan.getCreationDate()) &&
                Objects.equals(getName(), loan.getName()) &&
                Objects.equals(getPrice(), loan.getPrice()) &&
                Objects.equals(getInterestRate(), loan.getInterestRate()) &&
                getRiskType() == loan.getRiskType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreationDate(), getTermInYears(), getName(), getPrice(), getInterestRate(), getRiskType());
    }

    public BigDecimal getTotalLoanCost() {
        return totalLoanCost;
    }

    public void setTotalLoanCost(BigDecimal totalLoanCost) {
        this.totalLoanCost = totalLoanCost;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LoanRiskType getRiskType() {
        return riskType;
    }

    public void setRiskType(LoanRiskType riskType) {
        this.riskType = riskType;
    }

    public BigDecimal getInterestrate() {
        return interestRate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getTermInYears() {
        return termInYears;
    }

    public void setTermInYears(int termInYears) {
        this.termInYears = termInYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
