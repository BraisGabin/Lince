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
 * <p>Example: class TwoSampProps.</p>
 */

public class TwoSampPropsExample
{

    public static void main(String arg[])
    {
        DataManager dm = new DataManager();
        TwoSampProps testclass1 = new TwoSampProps(0.05, 0, "less", 36, 150, 30,
                100);
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

        TwoSampProps testclass2 = new TwoSampProps();
        double[] confidenceInterval = testclass2.confidenceInterval(0.05, 36,
                150, 30, 100);
        testStatistic = testclass2.testStatistic(0, 36, 150, 30, 100);
        pValue = testclass2.pValue(0.5, "greater", 36, 150, 30, 100);
        out.println("\n\nThe test statistic based on null constructor" +
                    "          =  " + dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on null constructor" +
                    "                 =  " + dm.roundDigits(pValue, 3.0));
        out.println("The confidence interval based on null constructor" +
                    "     = [" +
                    dm.roundDigits(confidenceInterval[0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1], 3.0) + "]");

        Hashtable argument1 = new Hashtable();
        argument1.put(NULL_VALUE, 0);
        argument1.put(SIDE, "equal");
        argument1.put(ALPHA, 0.05);
        StatisticalAnalysis testclass3 = new TwoSampProps(argument1, 36, 150,
                30, 100).statisticalAnalysis;
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
        TwoSampProps testclass4 = new TwoSampProps(argument2, null);
        argument2.put(ALPHA, 0.05);
        confidenceInterval = testclass4.confidenceInterval(argument2, 36, 150,
                30, 100);
        argument2.put(NULL_VALUE, 0);
        testStatistic = testclass4.testStatistic(argument2, 36, 150, 30, 100);
        argument2.put(SIDE, "greater");
        pValue = testclass4.pValue(argument2, 36, 150, 30, 100);
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
