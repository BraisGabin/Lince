package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.survival.regression.*;
import static javastat.util.Argument.*;
import static javastat.util.Output.*;
import javastat.util.*;

/**
 *
 * <p>Example: class CoxRegression.</p>
 * <p>Data Source: Collett, D. (1994). Modelling Survival Data in Medical
 *    Research. New York: Chapman and Hall, pp. 290-291. </p>
 */

public class CoxRegressionExample
{

    public static void main(String arg[])
    {
        double[] time = {156, 1040, 59, 421, 329, 769, 365, 770, 1227, 268, 475,
                         1129, 464, 1206, 638, 563, 1106, 431, 855, 803, 115,
                         744, 477, 448, 353, 377};
        double[] censor = {1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1,
                           0, 0, 1, 0, 0, 0, 1, 0};
        double[][] covariate = { {1, 1, 1, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1,
                                  2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2},
                                 {66, 38, 72, 53, 43, 59, 64, 57, 59, 74, 59,
                                  53, 56, 44, 56, 55, 44, 50, 43, 39, 74, 50,
                                  64, 56, 63, 58} };
        DataManager dm = new DataManager();

        CoxRegression testclass1 = new CoxRegression(0.05, time, censor,
                covariate[0], covariate[1]);
        double[] coefficients = testclass1.coefficients;
        double[][] variance = {testclass1.variance[0], testclass1.variance[1]};
        double[] testStatistic = testclass1.testStatistic;
        double[] pValue = testclass1.pValue;
        double[][] confidenceInterval = {testclass1.confidenceInterval[0],
                                        testclass1.confidenceInterval[1]};
        out.println("The estimated coefficients based on non-null constructor" +
                    "                       = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + "]");
        out.println("The variances of the parameter estimates based on " +
                    "non-null constructor         = [" +
                    dm.roundDigits(variance[0][0], 3.0) + " , " +
                    dm.roundDigits(variance[1][1], 3.0) + "]");
        out.println("The test statistics based on non-null constructor" +
                    "                              = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + "]");
        out.println("The p-values for the test based on non-null constructor" +
                    "                        = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + "]");
        out.println("The confidence interval for the first parameter based " +
                    "on non-null constructor  = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + "  " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The confidence interval for the second parameter based " +
                    "on non-null constructor = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + "  " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");

        CoxRegression testclass2 = new CoxRegression();
        coefficients = testclass2.coefficients(time, censor, covariate);
        testStatistic = testclass2.testStatistic(time, censor, covariate);
        pValue = testclass2.pValue(time, censor, covariate);
        confidenceInterval = testclass2.confidenceInterval(0.1, time, censor,
                covariate);
        out.println("\n\nThe estimated coefficients based on null constructor" +
                    "                           = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + "]");
        out.println("The test statistics based on null constructor" +
                    "                                  = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + "  " +
                    dm.roundDigits(testStatistic[1], 3.0) + "]");
        out.println("The p-values for the test based on null constructor" +
                    "                            = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + "]");
        out.println("The confidence interval for the first parameter based " +
                    "on null constructor      = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The confidence interval for the second parameter based " +
                    "on null constructor     = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + "  " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");
        confidenceInterval = testclass2.confidenceInterval(time, censor,
                covariate);
        out.println("The confidence interval for the first parameter based " +
                    "on null constructor      = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The confidence interval for the second parameter based " +
                    "on null constructor     = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + "  " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");

        Hashtable argument1 = new Hashtable();
        argument1.put(ALPHA, 0.05);
        StatisticalAnalysis testclass3 = new CoxRegression(argument1, time,
                censor, covariate[0], covariate[1]).statisticalAnalysis;
        coefficients = (double[]) testclass3.output.get(COEFFICIENTS);
        variance = (double[][]) testclass3.output.get(COEFFICIENT_VARIANCE);
        testStatistic = (double[]) testclass3.output.get(TEST_STATISTIC);
        pValue = (double[]) testclass3.output.get(PVALUE);
        confidenceInterval = (double[][]) testclass3.output.get(
                CONFIDENCE_INTERVAL);
        out.println("\n\nThe estimated coefficients based on non-null " +
                    "constructor                       = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + "]");
        out.println("The variances of the parameter estimates based on " +
                    "non-null constructor         = [" +
                    dm.roundDigits(variance[0][0], 3.0) + " , " +
                    dm.roundDigits(variance[1][1], 3.0) + "]");
        out.println("The test statistics based on non-null constructor" +
                    "= [" + dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + "]");
        out.println("The p-values for the test based on non-null constructor" +
                    "= [" + dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + "]");
        out.println("The confidence interval for the first parameter based " +
                    "on non-null constructor  = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + "  " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The confidence interval for the second parameter based " +
                    "on non-null constructor = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + "  " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");
        out.println("\n\n" + testclass3.output.toString());

        Hashtable argument2 = new Hashtable();
        CoxRegression testclass4 = new CoxRegression(argument2, null);
        coefficients = testclass4.coefficients(argument2, time, censor,
                                               covariate);
        testStatistic = testclass4.testStatistic(argument2, time, censor,
                                                 covariate);
        pValue = testclass4.pValue(argument2, time, censor, covariate);
        argument2.put(ALPHA, 0.1);
        confidenceInterval = testclass4.confidenceInterval(argument2, time,
                censor, covariate);
        out.println("\n\nThe estimated coefficients based on null constructor" +
                    "= [" + dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + "]");
        out.println("The test statistics based on null constructor" +
                    "= [" + dm.roundDigits(testStatistic[0], 3.0) + "  " +
                    dm.roundDigits(testStatistic[1], 3.0) + "]");
        out.println("The p-values for the test based on null constructor" +
                    "= [" + dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + "]");
        out.println("The confidence interval for the first parameter based " +
                    "on null constructor      = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The confidence interval for the second parameter based " +
                    "on null constructor     = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + "  " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");
        out.println("\n\n" + testclass4.output.toString());
    }

}
