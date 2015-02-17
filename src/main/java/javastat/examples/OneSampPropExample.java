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
 * <p>Example: class OneSampProp.</p>
 */

public class OneSampPropExample
{

    public static void main(String arg[])
    {
        DataManager dm = new DataManager();

        OneSampProp testclass1 = new OneSampProp(0.05, 0.5, "less", 30, 100);
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

        OneSampProp testclass2 = new OneSampProp();
        double[] confidenceInterval = testclass2.confidenceInterval(0.05, 30,
                100);
        testStatistic = testclass2.testStatistic(0.5, 30, 100);
        pValue = testclass2.pValue(0.5, "greater", 30, 100);
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
        argument1.put(NULL_VALUE, 0.5);
        argument1.put(SIDE, "less");
        StatisticalAnalysis testclass3 = new OneSampProp(argument1, 30, 100).
                                         statisticalAnalysis;
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
        out.println(CONFIDENCE_INTERVAL.description());

        Hashtable argument2 = new Hashtable();
        OneSampProp testclass4 = new OneSampProp(argument2, null);
        argument2.put(ALPHA, 0.05);
        confidenceInterval = testclass4.confidenceInterval(argument2, 30, 100);
        argument2.put(NULL_VALUE, 0.5);
        testStatistic = testclass4.testStatistic(argument2, 30, 100);
        argument2.put(SIDE, "greater");
        pValue = testclass4.pValue(argument2, 30, 100);
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
