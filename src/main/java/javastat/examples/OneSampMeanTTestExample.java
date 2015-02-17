package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.inference.onesample.*;
import static javastat.util.Argument.*;
import static javastat.util.Output.*;
import javastat.util.*;

/**
 *
 * <p>Example: class OneSampMeanTTest.</p>
 * <p>Data Source: Anderson, D. R., Sweeney, D. J. and Williams, T. A. (2001).
 *    Contemporary Business Statistics with Microsoft Excel. South-Western,
 *    p. 354. </p>
 */

public class OneSampMeanTTestExample
{

    public static void main(String arg[])
    {
        double[] testdata = {7, 8, 10, 8, 6, 9, 6, 7, 7, 8, 9, 8};
        DataManager dm = new DataManager();

        OneSampMeanTTest testclass1 = new OneSampMeanTTest(0.05, 7, "greater",
                testdata);
        double testStatistic = testclass1.testStatistic;
        double pValue = testclass1.pValue;
        double lowerBound = testclass1.confidenceInterval[0];
        double upperBound = testclass1.confidenceInterval[1];
        out.println("The test statistic based on non-null constructor" +
                    "      =  " + dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on non-null constructor" +
                    "             =  " + dm.roundDigits(pValue, 3.0));
        out.println("The confidence interval based on non-null constructor" +
                    " = [" + dm.roundDigits(lowerBound, 3.0) + " , " +
                    dm.roundDigits(upperBound, 3.0) + "]");

        OneSampMeanTTest testclass2 = new OneSampMeanTTest();
        double[] confidenceInterval = testclass2.confidenceInterval(0.05,
                testdata);
        testStatistic = testclass2.testStatistic(7, testdata);
        pValue = testclass2.pValue(7, "equal", testdata);
        out.println("\n\nThe test statistic based on null constructor" +
                    "          =  " + dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on null constructor" +
                    "                 =  " + dm.roundDigits(pValue, 3.0));
        out.println("The confidence interval based on null constructor" +
                    "     = [" +
                    dm.roundDigits(confidenceInterval[0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1], 3.0) + "]");

        Hashtable argument1 = new Hashtable();
        argument1.put(NULL_VALUE, 7);
        argument1.put(SIDE, "greater");
        argument1.put(ALPHA, 0.05);
        StatisticalAnalysis testclass3 = new OneSampMeanTTest(argument1,
                testdata).statisticalAnalysis;
        testStatistic = (Double) testclass3.output.get(TEST_STATISTIC);
        pValue = (Double) testclass3.output.get(PVALUE);
        confidenceInterval = (double[]) testclass3.output.get(
                CONFIDENCE_INTERVAL);
        lowerBound = confidenceInterval[0];
        upperBound = confidenceInterval[1];
        out.println("\n\nThe test statistic based on non-null constructor" +
                    "      =  " + dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on non-null constructor" +
                    "             =  " + dm.roundDigits(pValue, 3.0));
        out.println("The confidence interval based on non-null constructor" +
                    " = [" + dm.roundDigits(lowerBound, 3.0) + " , " +
                    dm.roundDigits(upperBound, 3.0) + "]");
        out.println("\n\n" + testclass3.output.toString());
        out.println(TEST_STATISTIC.description());

        Hashtable argument2 = new Hashtable();
        OneSampMeanTTest testclass4 = new OneSampMeanTTest(argument2, null);
        argument2.put(ALPHA, 0.05);
        confidenceInterval = testclass4.confidenceInterval(argument2, testdata);
        argument2.put(NULL_VALUE, 7);
        testStatistic = testclass4.testStatistic(argument2, testdata);
        argument2.put(SIDE, "equal");
        pValue = testclass4.pValue(argument2, testdata);
        out.println("\n\nThe test statistic based on null constructor" +
                    "          =  " + dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on null constructor" +
                    "                 =  " + dm.roundDigits(pValue, 3.0));
        out.println("The confidence interval based on null constructor" +
                    "     = [" +
                    dm.roundDigits(confidenceInterval[0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1], 3.0) + "]");
        out.println("\n\n" + testclass4.output.toString());
    }

}
