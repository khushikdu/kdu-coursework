package ques4;

public class BlueCrossBlueShield implements InsuranceBrand{
    /**
     *
     * @param plan
     * @param age
     * @param smokes
     * @return
     */
    public double computeMonthlyPremium(HealthInsurancePlan plan,int age, boolean smokes){

        double premiuim=0;
        if(plan instanceof PlatinumPlan){
            if(age>55)
                premiuim+=200;
            if(smokes)
                premiuim+=100;
        }
        else if(plan instanceof GoldenPlan){
            if(age>55)
                premiuim+=150;
            if(smokes)
                premiuim+=90;
        }
        else if(plan instanceof SilverPlan){
            if(age>55)
                premiuim+=100;
            if(smokes)
                premiuim+=80;
        }
        else if(plan instanceof BronzePlan){
            if(age>55)
                premiuim+=50;
            if(smokes)
                premiuim+=70;
        }
        return premiuim;
    }
}
