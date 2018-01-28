package javastat.examples;

import static java.lang.System.out;

import javastat.probability.*;

public class NegativeBinomialDistributionExample
{
    
    public static void main(String[] args)
    {
        int r1 = 3;
        double p1 = 0.7;
        int r2 = 5;
        double p2 = 0.3;
        int r3 = 4;
        double p3 = 0.5;
        double negativeBinomialPercentiles = 2.0;
        double negativeBinomialX = 3.0;
        double negativeBinomialProb = 0.95;

        NegativeBinomialDistribution testclass1 = 
            new NegativeBinomialDistribution();
        double cumulative = testclass1.
                            cumulative(r1, p1, negativeBinomialPercentiles);
        double probability = testclass1.probability(r2, p2, negativeBinomialX);
        double inverse = testclass1.inverse(r3, p3, negativeBinomialProb);
        out.println("Negative-Binomial Probability            : " + cumulative);
        out.println("Negative-Binomial Density                : " + 
                    probability);
        out.println("Negative-Binomial Percentile             : " + inverse);
    }
    
}