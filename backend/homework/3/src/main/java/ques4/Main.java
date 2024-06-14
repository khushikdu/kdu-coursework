package ques4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Logback.class);

    public static void main(String[] args) {
        User staff = new User();

        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();

        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);

        //insurancePlan.computeMonthlyPremium(15000, 56, true);
        double ans = insurancePlan.computeMonthlyPremium(5000, 56, true);
        logger.info("{}",ans);
    }
}