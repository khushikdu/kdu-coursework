package ques2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class SentimentAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(SentimentAnalyzer.class);
    /**
     *
     * @param review
     * @param featureSet
     * @param posOpinionWords
     * @param negOpinionWords
     * @return
     */
    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords,String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length];
        review = review.toLowerCase();

        outerLoop:
        for (int i = 0; i < featureSet.length; i++) {
            String[] synonyms = featureSet[i];

            for (int j = 0; j < synonyms.length; j++) {
                String feature = synonyms[j];
                int opinion = getOpinionOnFeature(review, feature, posOpinionWords, negOpinionWords);

                if (opinion != 0) {
                    featureOpinions[i] = opinion;
                    continue outerLoop;
                }
            }
        }

        return featureOpinions;
    }

    /**
     *
     * @param review
     * @param feature
     * @param posOpinionWords
     * @param negOpinionWords
     * @return
     */
    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords,String[] negOpinionWords) {
        int opinion = 0;
        opinion = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
        if (opinion == 0)
            opinion = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);

        return opinion;
    }

    /**
     *
     * @param review
     * @param feature
     * @param posOpinionWords
     * @param negOpinionWords
     * @return
     */
    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinion = 0;
        String pattern = feature + " was ";

        for (String posWord : posOpinionWords) {
            if (review.contains(pattern + posWord)) {
                opinion = 1;
                break;
            }
        }

        if (opinion == 0) {
            for (String negWord : negOpinionWords) {
                if (review.contains(pattern + negWord)) {
                    opinion = -1;
                    break;
                }
            }
        }

        return opinion;
    }

    /**
     *
     * @param review
     * @param feature
     * @param posOpinionWords
     * @param negOpinionWords
     * @return
     */
    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords,
                                                   String[] negOpinionWords) {
        String[] sentences = review.split("\\.");
        int opinion = 0;

        for (String sentence : sentences) {
            for (String posWord : posOpinionWords) {
                if (sentence.contains(posWord + " " + feature)) {
                    opinion = 1;
                    return opinion;
                }
            }

            for (String negWord : negOpinionWords) {
                if (sentence.contains(negWord + " " + feature)) {
                    opinion = -1;
                    return opinion;
                }
            }
        }

        return opinion;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
        String[][] featureSet = {
                {"ambiance", "ambience", "atmosphere", "decor"},
                {"dessert", "ice cream", "desert"},
                {"food"},
                {"soup"},
                {"service", "management", "waiter", "waitress", "bartender", "staff", "server"}};

        String[] posOpinionWords = {"good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome", "delicious"};
        String[] negOpinionWords = {"slow", "bad", "horrible", "awful", "unprofessional", "poor"};

        int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
        logger.info("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}
