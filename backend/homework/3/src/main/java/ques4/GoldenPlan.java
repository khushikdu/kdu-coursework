package ques4;

public abstract class GoldenPlan extends HealthInsurancePlan {
    public GoldenPlan() {

        setCoverage(0.8);
    }
    @Override
    public double computeMonthlyPremium(double salary,int age, boolean smokes) {
        return 0.07*salary+getOfferedBy().computeMonthlyPremium(this, age, smokes);
    }
}
