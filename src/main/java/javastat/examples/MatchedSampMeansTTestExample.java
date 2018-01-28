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
 * <p>Example: class MatchedSampMeansTTest.</p>
 */

public class MatchedSampMeansTTestExample
{

    public static void main(String arg[])
    {
        double[] testdata1 = {51, 22, 17, 11, 28, 17, 5, 21, 22, 10};
        double[] testdata2 = {32, 14, 9, 8, 28, 11, 3, 23, 12, 14};
        DataManager dm = new DataManager();

        MatchedSampMeansTTest testclass1 = new MatchedSampMeansTTest(0.05, 0,
                "equal", testdata1, testdata2);
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

        MatchedSampMeansTTest testclass2 = new MatchedSampMeansTTest();
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
        StatisticalAnalysis testclass3 = new MatchedSampMeansTTest(argument1,
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
        out.println("The confidence interval based on non-null " +
                    "constructor = [" +
                    dm.roundDigits(lowerBound, 3.0) + " , " +
                    dm.roundDigits(upperBound, 3.0) + "]");
        out.println("\n\n" + testclass3.output.toString());

        Hashtable argument2 = new Hashtable();
        MatchedSampMeansTTest testclass4 =
                new MatchedSampMeansTTest(argument2, null);
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
