package javastat.examples;

import static java.lang.System.out;

import static javastat.util.DistributionType.*;
import javastat.util.*;

public class StatRandomExample
{

    public StatRandomExample() {}

    public static void main(String arg[])
    {
        StatRandom rng = new StatRandom();
        int n = 3;
        double [] normalData = rng.random(NORMAL, new double[]{0.0, 1.0}, n);
        double [] uniformData = rng.random(UNIFORM, new double[]{0.0, 1.0}, n);
        double [] betaData = rng.random(BETA, new double[]{2.0, 3.0}, n);
        double [] cauchyData = rng.random(CAUCHY, new double[]{0.0, 1.0}, n);
        double [] chisqData = rng.random(CHISQUARE, new double[]{1.0}, n);
        double [] exponData = rng.random(EXPONENTIAL, new double[]{2.0}, n);
        double [] fData = rng.random(F, new double[]{1.0, 20.0}, n);
        double [] gammaData = rng.random(GAMMA, new double[]{1.5, 2.5}, n);
        double [] hyperData = rng.random(HYPERGEOMETRIC,
                                         new double[]{4.0, 6.0, 7.0}, n);
        double [] logisData = rng.random(LOGISTIC, new double[]{0.0, 1.0}, n);
        double [] lognorData = rng.random(LOGNORMAL, new double[]{0.0, 1.0}, n);
        double [] tData = rng.random(T, new double[]{10.0}, n);
        double [] weibullData = rng.random(WEIBULL, new double[]{1.0, 2.0}, n);

        for(int i =0; i < n; i++)
        {
            out.println("The " + (i + 1) + "'th data");
            out.println("Normal         : " + normalData[i]);
            out.println("Uniform        : " + uniformData[i]);
            out.println("Beta           : " + betaData[i]);
            out.println("Cauchy         : " + cauchyData[i]);
            out.println("Chi-square     : " + chisqData[i]);
            out.println("Exponential    : " + exponData[i]);
            out.println("F              : " + fData[i]);
            out.println("Gamma          : " + gammaData[i]);
            out.println("Hypergeometric : " + hyperData[i]);
            out.println("Logistic       : " + logisData[i]);
            out.println("Log-normal     : " + lognorData[i]);
            out.println("T              : " + tData[i]);
            out.println("Weibull        : " + weibullData[i]);
        }
    }

}
