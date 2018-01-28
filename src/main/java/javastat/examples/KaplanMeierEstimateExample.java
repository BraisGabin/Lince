package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.survival.*;
import static javastat.util.Argument.*;
import static javastat.util.Output.*;
import javastat.util.*;

/**
 *
 * <p>Example: class KaplanMeierEstimate.</p>
 * <p>Data Source: Collett, D. (1994). Modelling Survival Data in Medical
 *    Research. New York: Chapman and Hall, pp. 290-291. </p>
 */

public class KaplanMeierEstimateExample
{

    public static void main(String arg[])
    {
        double[] time1 = {156, 1040, 59, 421, 329, 769, 365, 770, 1227, 268,
                          475, 1129, 464, 1206, 638, 563, 1106, 431, 855, 803,
                          115, 744, 477, 448, 353, 377};
        double[] censor1 = {1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0,
                            1, 0, 0, 1, 0, 0, 0, 1, 0};
        double[][] covariate1 = { {1, 1, 1, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1,
                                   2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2},
                                  {66, 38, 72, 53, 43, 59, 64, 57, 59, 74, 59,
                                   53, 56, 44, 56, 55, 44, 50, 43, 39, 74, 50,
                                   64, 56, 63, 58} };
        DataManager dm = new DataManager();

        KaplanMeierEstimate testclass1 =
                new KaplanMeierEstimate(0.05, time1, censor1);
        double[] estimate1 = testclass1.estimate;
        double[] variance = testclass1.variance;
        double[][] confidenceInterval = new double[time1.length][2];
        for (int i = 0; i < confidenceInterval.length; i++) 
		{
            confidenceInterval[i] = testclass1.confidenceInterval[i];
        }
        for (int i = 0; i < testclass1.estimate.length; i++)
        {
            out.println("The estimate for the " + (i + 1) +
                    "'th observation based on non-null constructor" +
                    "                         =  " +
                    dm.roundDigits(estimate1[i], 3.0));
            out.println("The variance of the estimate of the " + (i + 1) +
                    "'th observation based on non-null constructor" +
                    "          =  " +
                    dm.roundDigits(variance[i], 3.0));
            out.println("The confidence interval corresponding to the " +
                        (i + 1) +
                        "'th observation based on non-null constructor = [" +
                        dm.roundDigits(confidenceInterval[i][0], 3.0) + " , " +
                        dm.roundDigits(confidenceInterval[i][1], 3.0) + "]");
        }
        out.println("\n\n");

        KaplanMeierEstimate testclass2 = new KaplanMeierEstimate();
        confidenceInterval =
                testclass2.confidenceInterval(0.05, time1, censor1);
        estimate1 = testclass2.estimate(time1, censor1);
        variance = testclass2.variance(time1, censor1);
        for (int i = 0; i < estimate1.length; i++)
        {
            out.println("The estimate for the " + (i + 1) +
                    "'th observation based on null constructor" +
                    "                             =  " +
                    dm.roundDigits(estimate1[i], 3.0));
            out.println("The variance of the estimate of the " + (i + 1) +
                    "'th observation based on null constructor              =" +
                    "  " + dm.roundDigits(variance[i], 3.0));
            out.println("The confidence interval corresponding to the " +
                        (i + 1) +
                        "'th observation based on null constructor     = [" +
                        dm.roundDigits(confidenceInterval[i][0], 3.0) + " , " +
                        dm.roundDigits(confidenceInterval[i][1], 3.0) + "]");
        }
        out.println("\n\n");

        double[] estimate2 = testclass2.estimate(time1, censor1, covariate1);
        double[] mv = new BasicStatistics().meanVector(covariate1);
        double power = 1.0;
        for (int i = 0; i < covariate1.length; i++)
        {
            power *= Math.exp(mv[i] * testclass2.coefficients[i]);
        }
        double[] estimate2Prop = new double[estimate2.length];
        for (int i = 0; i < estimate2.length; i++)
        {
            estimate2Prop[i] = Math.pow(estimate2[i], power);
            out.println("The estimate for the " + (i + 1) +
                        "'th observation based on null constructor" +
                        "                    =  " +
                        dm.roundDigits(estimate2Prop[i], 3.0));
        }
        confidenceInterval = testclass2.confidenceInterval(time1, censor1);
        for (int i = 0; i < estimate1.length; i++)
        {
            out.println("The confidence interval corresponding to the " +
                        (i + 1) +
                        "'th observation based on null constructor     = [" +
                        dm.roundDigits(confidenceInterval[i][0], 3.0) + " , " +
                        dm.roundDigits(confidenceInterval[i][1], 3.0) + "]");
        }
        out.println("\n\n");

        KaplanMeierEstimate testclass3 =
                new KaplanMeierEstimate(time1, censor1, covariate1);
        double[] estimate3 = testclass3.estimate;
        power = 1.0;
        for (int i = 0; i < covariate1.length; i++)
        {
            power *= Math.exp(covariate1[i][5] * testclass3.coefficients[i]);
        }
        double[] estimate3Prop = new double[testclass3.estimate.length];
        for (int i = 0; i < estimate3Prop.length; i++)
        {
            estimate3Prop[i] = Math.pow(estimate3[i], power);
            out.println("The estimate for the " + (i + 1) +
                        "'th observation under proportional hazards assumption"
                        + "                 =  " +
                        dm.roundDigits(estimate3Prop[i], 3.0));
        }
        out.println("\n\n");

        Hashtable argument1 = new Hashtable();
        argument1.put(ALPHA, 0.05);
        StatisticalAnalysis testclass4 = new KaplanMeierEstimate(argument1,
                time1, censor1).statisticalAnalysis;
        estimate1 = (double[]) testclass4.output.get(SURVIVAL_ESTIMATE);
        variance = (double[]) testclass4.output.get(SURVIVAL_ESTIMATE_VARIANCE);
        confidenceInterval =
                (double[][]) testclass4.output.get(CONFIDENCE_INTERVAL);
        for (int i = 0; i < estimate1.length; i++)
        {
            out.println("The estimate for the " + (i + 1) +
                        "'th observation based on non-null constructor" +
                        "                         =  " +
                        dm.roundDigits(estimate1[i], 3.0));
            out.println("The variance of the estimate of the " + (i + 1) +
                        "'th observation based on non-null constructor" +
                        "          =  " + dm.roundDigits(variance[i], 3.0));
            out.println("The confidence interval corresponding to the " +
                        (i + 1) +
                        "'th observation based on non-null constructor = [" +
                        dm.roundDigits(confidenceInterval[i][0], 3.0) + " , " +
                        dm.roundDigits(confidenceInterval[i][1], 3.0) + "]");
        }
        out.println("\n\n" + testclass4.output.toString());
        out.println("\n\n");

        Hashtable argument2 = new Hashtable();
        KaplanMeierEstimate testclass5 =
                new KaplanMeierEstimate(argument2, null);
        argument2.put(ALPHA, 0.05);
        confidenceInterval =
                testclass5.confidenceInterval(argument2, time1, censor1);
        estimate1 = testclass5.estimate(argument2, time1, censor1);
        variance = testclass5.variance(argument2, time1, censor1);
        for (int i = 0; i < estimate1.length; i++)
        {
            out.println("The estimate for the " + (i + 1) +
                        "'th observation based on null constructor" +
                        "                             =  " +
                        dm.roundDigits(estimate1[i], 3.0));
            out.println("The variance of the estimate of the " + (i + 1) +
                        "'th observation based on null constructor" +
                        "              =  " + dm.roundDigits(variance[i], 3.0));
            out.println("The confidence interval corresponding to the " +
                        (i + 1) +
                        "'th observation based on null constructor     = [" +
                        dm.roundDigits(confidenceInterval[i][0], 3.0) + " , " +
                        dm.roundDigits(confidenceInterval[i][1], 3.0) + "]");
        }
        estimate2 = testclass5.estimate(argument2, time1, censor1, covariate1);
        mv = new BasicStatistics().meanVector(covariate1);
        power = 1.0;
        for (int i = 0; i < covariate1.length; i++)
        {
            power *= Math.exp(mv[i] * testclass5.coefficients[i]);
        }
        estimate2Prop = new double[estimate2.length];
        for (int i = 0; i < estimate2.length; i++)
        {
            estimate2Prop[i] = Math.pow(estimate2[i], power);
            out.println("The estimate for the " + (i + 1) +
                        "'th observation based on null constructor" +
                        "                    =  " +
                        dm.roundDigits(estimate2Prop[i], 3.0));
        }
        out.println("\n\n" + testclass5.output.toString());
        out.println("\n\n");

        Hashtable argument3 = new Hashtable();
        StatisticalAnalysis testclass6 = new KaplanMeierEstimate(argument3,
                time1, censor1, covariate1).statisticalAnalysis;
        estimate3 = (double[]) testclass6.output.get(SURVIVAL_ESTIMATE);
        double[] coefficients = (double[]) testclass6.output.get(COEFFICIENTS);
        power = 1.0;
        for (int i = 0; i < covariate1.length; i++)
        {
            power *= Math.exp(covariate1[i][5] * coefficients[i]);
        }
        estimate3Prop = new double[estimate3.length];
        for (int i = 0; i < estimate3Prop.length; i++)
        {
            estimate3Prop[i] = Math.pow(estimate3[i], power);
            out.println("The estimate for the " + (i + 1) +
                        "'th observation under proportional hazards " +
                        "assumption                 =  " +
                        dm.roundDigits(estimate3Prop[i], 3.0));
        }
        out.println("\n\n" + testclass6.output.toString());
    }

}
