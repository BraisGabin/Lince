package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.inference.twosamples.*;
import static javastat.util.Argument.*;
import static javastat.util.Output.*;
import javastat.util.*;

/**
 *
 * <p>Example: class TwoSampMeansTTest.</p>
 * <p>Data Source: Anderson, D. R., Sweeney, D. J. and Williams, T. A. (2001).
 *    Contemporary Business Statistics with Microsoft Excel. South-Western,
 *    p. 395. </p>
 */

public class TwoSampMeansTTestExample
{

    public static void main(String arg[])
    {
        double[] testdata1 = {300, 280, 344, 385, 372, 360, 288, 321, 376, 290,
                              301, 283};
        double[] testdata2 = {276, 222, 310, 338, 200, 302, 317, 260, 320, 312,
                              334, 265};
        DataManager dm = new DataManager();

        TwoSampMeansTTest testclass1 = new TwoSampMeansTTest(0.05, 0, "equal",
                testdata1, testdata2);
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

        TwoSampMeansTTest testclass2 = new TwoSampMeansTTest();
        double[] confidenceInterval = testclass2.confidenceInterval(0.05,
                testdata1, testdata2);
        testStatistic = testclass2.testStatistic(0, testdata1, testdata2);
        pValue = testclass2.pValue(0, "greater", testdata1, testdata2);
        out.println("\n\nThe test statistic based on null constructor" +
                    "          =  " + dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on null constructor" +
                    "                 =  " + dm.roundDigits(pValue, 3.0));
        out.println("The confidence interval based on null constructor" +
                    "     = [" +
                    dm.roundDigits(confidenceInterval[0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1], 3.0) + "]");

        Hashtable argument1 = new Hashtable();
        argument1.put(ALPHA, 0.05);
        argument1.put(NULL_VALUE, 0);
        argument1.put(SIDE, "equal");
        StatisticalAnalysis testclass3 = new TwoSampMeansTTest(argument1,
                testdata1, testdata2).statisticalAnalysis;
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

        Hashtable argument2 = new Hashtable();
        TwoSampMeansTTest testclass4 = new TwoSampMeansTTest(argument2, null);
        argument2.put(ALPHA, 0.05);
        confidenceInterval = testclass4.confidenceInterval(argument2, testdata1,
                testdata2);
        argument2.put(NULL_VALUE, 0);
        testStatistic = testclass4.testStatistic(argument2, testdata1,
                                                 testdata2);
        argument2.put(SIDE, "greater");
        pValue = testclass4.pValue(argument2, testdata1, testdata2);
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
