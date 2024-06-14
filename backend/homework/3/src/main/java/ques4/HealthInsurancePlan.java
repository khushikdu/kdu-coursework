package ques4;

public abstract class HealthInsurancePlan {
    private double coverage;
    private InsuranceBrand offeredBy;
    public abstract double computeMonthlyPremium(double Salary,int age, boolean smokes);
    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }


    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }

}