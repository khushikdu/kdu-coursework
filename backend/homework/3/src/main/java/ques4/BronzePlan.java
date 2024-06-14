package ques4;

public abstract class  BronzePlan extends HealthInsurancePlan {
    public BronzePlan() {

        setCoverage(0.7);
    }

    @Override
    public double computeMonthlyPremium(double salary,int age, boolean smokes) {
        return 0.05*salary+getOfferedBy().computeMonthlyPremium(this, age, smokes);
    }
}
