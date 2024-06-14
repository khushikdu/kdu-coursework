package ques4;

class Billing  {
    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        if(patientInsurancePlan!=null)
            payments=getAmount(amount,patientInsurancePlan);
        else {
            payments[1]=amount-20;
            payments[0]=0;
        }

        return payments;
    }
    public static double[] getAmount(double amount, HealthInsurancePlan patientPlan){

        double insuranceCoverage=patientPlan.getCoverage();
        double insuranceAmount=amount*insuranceCoverage;
        double remainingAmount=amount-insuranceAmount;

        if(patientPlan instanceof PlatinumPlan)
            remainingAmount-=50;
        else if(patientPlan instanceof GoldenPlan)
            remainingAmount-=40;
        else if(patientPlan instanceof BronzePlan)
            remainingAmount-=30;
        else if(patientPlan instanceof SilverPlan)
            remainingAmount-=25;
        else
            remainingAmount-=20;

        double []billSplit={insuranceAmount,remainingAmount};
        return billSplit;
    }
}