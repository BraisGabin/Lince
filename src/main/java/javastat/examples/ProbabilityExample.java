package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.probability.*;
import static javastat.util.Argument.*;
import static javastat.util.DistributionType.*;
import static javastat.util.Output.*;

/**
 *
 * <p>Example: class Probability.</p>
 */

public class ProbabilityExample
{

    public static void main(String arg[])
    {
        double[] normalPara = {1.0, 2.0};
        double[] normalPercentiles = {1 -1.96 * 2, 1 + 1.96 * 2};
        double normalX = normalPara[0];
        double normalProb = 0.90;
        Probability normal =
            new Probability(NORMAL, normalPara, normalPercentiles, normalX,
                            normalProb);
        out.println("Normal Probability           : " + normal.cumulative);
        out.println("Normal Density               : " + normal.probability);
        out.println("Normal Percentile            : " + normal.inverse);

        double[] betaPara = {2.0, 3.0};
        double[] betaPercentiles = {0.0, 0.5};
        double betaX = 0.5;
        double betaProb = 0.90;
        Probability beta =
            new Probability(BETA, betaPara, betaPercentiles, betaX, betaProb);
        out.println("Beta Probability             : " + beta.cumulative);
        out.println("Beta Density                 : " + beta.probability);
        out.println("Beta Percentile              : " + beta.inverse);

        double[] binomialPara = {10.0, 0.3};
        double[] binomialPercentiles = {0.0, 5.0};
        double binomialX = 5.0;
        double binomialProb = 0.96;
        Probability binomial =
            new Probability(BINOMIAL, binomialPara, binomialPercentiles,
                            binomialX, binomialProb);
        out.println("Binomial Probability         : " + binomial.cumulative);
        out.println("Binomial Density             : " + binomial.probability);
        out.println("Binomial Percentile          : " + binomial.inverse);

        double[] cauchyPara = {0.0, 1.0};
        double[] cauchyPercentiles = {-2.0, 2.0};
        double cauchyX = 0.0;
        double cauchyProb = 0.90;
        Probability cauchy =
            new Probability(CAUCHY, cauchyPara, cauchyPercentiles, cauchyX,
                            cauchyProb);
        out.println("Cauchy Probability           : " + cauchy.cumulative);
        out.println("Cauchy Density               : " + cauchy.probability);
        out.println("Cauchy Percentile            : " + cauchy.inverse);

        double[] chisquarePara = {1.0};
        double[] chisquarePercentiles = {1.0, 2.0};
        double chisquareX = 1.0;
        double chisquareProb = 0.90;
        Probability chisquare =
            new Probability(CHISQUARE, chisquarePara, chisquarePercentiles,
                            chisquareX, chisquareProb);
        out.println("Chi-Square Probability       : " + chisquare.cumulative);
        out.println("Chi-Square Density           : " + chisquare.probability);
        out.println("Chi-Square Percentile        : " + chisquare.inverse);

        double[] exponentialPara = {2.0};
        double[] exponentialPercentiles = {1.0, 2.0};
        double exponentialX = 1.0;
        double exponentialProb = 0.90;
        Probability exponential =
            new Probability(EXPONENTIAL, exponentialPara,
                            exponentialPercentiles,
                            exponentialX, exponentialProb);
        out.println("Exponential Probability      : " + exponential.cumulative);
        out.println("Exponential Density          : " + exponential.probability);
        out.println("Exponential Percentile       : " + exponential.inverse);

        double[] fPara = {2.0, 3.0};
        double[] fPercentiles = {0.0, 4.0};
        double fX = 7.0;
        double fProb = 0.95;
        Probability f = new Probability(F, fPara, fPercentiles, fX, fProb);
        out.println("F Probability                : " + f.cumulative);
        out.println("F Density                    : " + f.probability);
        out.println("F Percentile                 : " + f.inverse);

        double[] gammaPara = {2.0, 3.0};
        double[] gammaPercentiles = {0.0, 2.0};
        double gammaX = 1.0;
        double gammaProb = 0.95;
        Probability gamma = new Probability(GAMMA, gammaPara, gammaPercentiles,
                                            gammaX, gammaProb);
        out.println("Gamma Probability            : " + gamma.cumulative);
        out.println("Gamma Density                : " + gamma.probability);
        out.println("Gamma Percentile             : " + gamma.inverse);

        double[] geometricPara = {0.6};
        double[] geometricPercentiles = {0.0, 2.0};
        double geometricX = 1.0;
        double geometricProb = 0.92;
        Probability geometric =
            new Probability(GEOMETRIC, geometricPara,
                            geometricPercentiles, geometricX,
                            geometricProb);
        out.println("Geometric Probability        : " + geometric.cumulative);
        out.println("Geometric Density            : " + geometric.probability);
        out.println("Geometric Percentile         : " + geometric.inverse);

        double[] hypergeometricPara = {50, 10, 20};
        double[] hypergeometricPercentiles = {0.0, 4.0};
        double hypergeometricX = 1.0;
        double hypergeometricProb = 0.90;
        Probability hypergeometric =
            new Probability(HYPERGEOMETRIC, hypergeometricPara,
                            hypergeometricPercentiles, hypergeometricX,
                            hypergeometricProb);
        out.println("Hypergeometric Probability   : " +
                    hypergeometric.cumulative);
        out.println("Hypergeometric Density       : " +
                    hypergeometric.probability);
        out.println("Hypergeometric Percentile    : " + hypergeometric.inverse);

        double[] lognormalPara = {0.0, 1.0};
        double[] lognormalPercentiles = {0.0, 2.0};
        double lognormalX = 1.0;
        double lognormalProb = 0.95;
        Probability lognormal =
            new Probability(LOGNORMAL, lognormalPara, lognormalPercentiles,
                            lognormalX, lognormalProb);
        out.println("Log-Normal Probability       : " + lognormal.cumulative);
        out.println("Log-Normal Density           : " + lognormal.probability);
        out.println("Log-Normal Percentile        : " + lognormal.inverse);

        double[] negativeBinomialPara = {3.0, 0.7};
        double[] negativeBinomialPercentiles = {0.0, 2.0};
        double negativeBinomialX = 3.0;
        double negativeBinomialProb = 0.95;
        Probability negativeBinomial =
            new Probability(NEGATIVE_BINOMIAL, negativeBinomialPara,
                            negativeBinomialPercentiles, negativeBinomialX,
                            negativeBinomialProb);
        out.println("Negative-Binomial Probability: " +
                    negativeBinomial.cumulative);
        out.println("Negative-Binomial Density    : " +
                    negativeBinomial.probability);
        out.println("Negative-Binomial Percentile : " +
                    negativeBinomial.inverse);

        double[] paretoPara = {2.0, 3.0};
        double[] paretoPercentiles = {3.0, 7.0};
        double paretoX = 4.0;
        double paretoProb = 0.9;
        Probability pareto =
            new Probability(PARETO, paretoPara, paretoPercentiles,
                            paretoX, paretoProb);
        out.println("Pareto Probability           : " + pareto.cumulative);
        out.println("Pareto Density               : " + pareto.probability);
        out.println("Pareto Percentile            : " + pareto.inverse);

        double[] poissonPara = {5.0};
        double[] poissonPercentiles = {3.0, 7.0};
        double poissonX = 5.0;
        double poissonProb = 0.9;
        Probability poisson =
            new Probability(POISSON, poissonPara, poissonPercentiles,
                            poissonX, poissonProb);
        out.println("Poisson Probability          : " + poisson.cumulative);
        out.println("Poisson Density              : " + poisson.probability);
        out.println("Poisson Percentile           : " + poisson.inverse);

        double[] tPara = {9.0};
        double[] tPercentiles = {-2.0, 2.0};
        double tX = 0.0;
        double tProb = 0.95;
        Probability t =
            new Probability(T, tPara, tPercentiles, tX, tProb);
        out.println("T Probability                : " + t.cumulative);
        out.println("T Density                    : " + t.probability);
        out.println("T Percentile                 : " + t.inverse);

        double[] weibullPara = {3.0};
        double[] weibullPercentiles = {0.0, 1.0};
        double weibullX = 1.0;
        double weibullProb = 0.95;
        Probability weibull =
            new Probability(WEIBULL, weibullPara, weibullPercentiles, weibullX,
                            weibullProb);
        out.println("Weibull Probability          : " + weibull.cumulative);
        out.println("Weibull Density              : " + weibull.probability);
        out.println("Weibull Percentile           : " + weibull.inverse);

        Hashtable argument = new Hashtable();
        argument.put(DISTRIBUTION_TYPE, NORMAL);
        argument.put(DISTRIBUTION_PARAMETER, normalPara);
        StatisticalAnalysis testclass1 =
            new Probability(argument, normalPercentiles, normalX, normalProb).
                                 statisticalAnalysis;
        out.println("\nNormal Distribution        :" +
                    testclass1.output.toString());
    }

}
