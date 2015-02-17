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
 * <p>Example: class MatchedSampMeansZTest.</p>
 */

public class MatchedSampMeansZTestExample
{

    public static void main(String arg[])
    {
        double[] testdata1 = {30, 40, 28, 5, 28, 29, 38, 36, 23, 22, 51, 33, 35,
                              42, 21, 31, 44, 32, 30, 32, 30, 30, 45, 29, 35,
                              33, 40, 31, 37, 41, 34, 40, 29, 42, 17, 29, 34,
                              39};
        double[] testdata2 = {60, 68, 63, 37, 57, 52, 63, 68, 47, 44, 74, 71,
                              83, 92, 66, 64, 62, 73, 62, 51, 77, 77, 71, 53,
                              72, 58, 70, 58, 72, 81, 64, 60, 60, 72, 40, 61,
                              58, 69};
        DataManager dm = new DataManager();

        MatchedSampMeansZTest testclass1 = new MatchedSampMeansZTest(0.05, 0,
                "equal", testdata1, testdata2);
        double testStatistic = testclass1.testStatistic;
        double pValue = testclass1.pValue;
        double lowerBound = testclass1.confidenceInterval[0];
        double upperBound = testclass1.confidenceInterval[1];
        out.println("The test statistic based on non-null constructor" +
                    "      =  " + dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on non-null constructor" +
                    "             =  " + dm.roundDigits(pValue, 3.0));
        out.println("The confidence interval based on non-null " +
                    "constructor = [" +
                    dm.roundDigits(lowerBound, 3.0) + " , " +
                    dm.roundDigits(upperBound, 3.0) + "]");

        MatchedSampMeansZTest testclass2 = new MatchedSampMeansZTest();
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
        StatisticalAnalysis testclass3 = new MatchedSampMeansZTest(argument1,
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
        MatchedSampMeansZTest testclass4 =
                new MatchedSampMeansZTest(argument2, null);
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
