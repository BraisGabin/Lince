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
 * <p>Example: class OneSampMeanZTest.</p>
 * <p>Data Source: Anderson, D. R., Sweeney, D. J. and Williams, T. A. (2001).
 *    Contemporary Business Statistics with Microsoft Excel. South-Western,
 *    p. 340. </p>
 */

public class OneSampMeanZTestExample
{

    public static void main(String arg[])
    {
        double[] testdata = {86, 97, 159, 92, 146, 138, 101, 87, 151, 69,
                             97, 79, 103, 104, 104, 105, 105, 102, 112, 116,
                             118, 124, 125, 129, 132, 98, 88, 68, 135, 100};
        DataManager dm = new DataManager();

        OneSampMeanZTest testclass1 = new OneSampMeanZTest(0.05, 100, "equal",
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

        OneSampMeanZTest testclass2 = new OneSampMeanZTest();
        double[] confidenceInterval = testclass2.confidenceInterval(0.05,
                testdata);
        testStatistic = testclass2.testStatistic(100, testdata);
        pValue = testclass2.pValue(100, "greater", testdata);
        out.println("\n\nThe test statistic based on null constructor" +
                    "          =  " + dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on null constructor" +
                    "                 =  " + dm.roundDigits(pValue, 3.0));
        out.println("The confidence interval based on null constructor" +
                    "     = [" +
                    dm.roundDigits(confidenceInterval[0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1], 3.0) + "]");

        Hashtable argument1 = new Hashtable();
        argument1.put(NULL_VALUE, 100.0);
        argument1.put(SIDE, "equal");
        argument1.put(ALPHA, 0.05);
        StatisticalAnalysis testclass3 = new OneSampMeanZTest(argument1,
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
        out.println(PVALUE.description());

        Hashtable argument2 = new Hashtable();
        OneSampMeanZTest testclass4 = new OneSampMeanZTest(argument2, null);
        argument2.put(ALPHA, 0.05);
        confidenceInterval = testclass4.confidenceInterval(argument2, testdata);
        argument2.put(NULL_VALUE, 100.0);
        testStatistic = testclass4.testStatistic(argument2, testdata);
        argument2.put(SIDE, "greater");
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
