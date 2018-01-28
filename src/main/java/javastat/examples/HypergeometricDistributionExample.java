package javastat.examples;

import static java.lang.System.out;

import javastat.probability.*;

public class HypergeometricDistributionExample
{
    
    public static void main(String[] args)
    {
        int N = 50;
        int r = 10;
        int n = 20;
        double hypergeometricPercentiles = 4.0;
        double hypergeometricX = 1.0;
        double hypergeometricProb = 0.90;

        HypergeometricDistribution testclass1 =  
            new HypergeometricDistribution(N, r, n);
        double cumulative = testclass1.cumulative(hypergeometricPercentiles);
        double probability = testclass1.probability(hypergeometricX);
        double inverse = testclass1.inverse(hypergeometricProb);
        out.println("Hypergeometric Probability            : " + cumulative);
        out.println("Hypergeometric Density                : " + probability);
        out.println("Hypergeometric Percentile             : " + inverse);
    }
    
}