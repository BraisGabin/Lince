package javastat.examples;

import static java.lang.System.out;

import javastat.probability.*;

public class GammaDistributionExample
{
    
    public static void main(String[] args)
    {
        double alpha1 = 2.0;
        double beta1 = 3.0;
        double alpha2 = 3.0;
        double beta2 = 4.0;
        double alpha3 = 5.0;
        double beta3 = 7.0;
        double gammaPercentiles = 2.0;
        double gammaX = 1.0;
        double gammaProb = 0.95;

        GammaDistribution testclass1 = new GammaDistribution();
        double cumulative = testclass1.
                            cumulative(alpha1, beta1, gammaPercentiles);
        double probability = testclass1.probability(alpha2, beta2, gammaX);
        double inverse = testclass1.inverse(alpha3, beta3, gammaProb);
        out.println("Gamma Probability            : " + cumulative);
        out.println("Gamma Density                : " + probability);
        out.println("Gamma Percentile             : " + inverse);
    }
    
}