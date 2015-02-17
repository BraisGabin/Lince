package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.inference.nonparametric.*;
import static javastat.util.Argument.*;
import static javastat.util.Output.*;
import javastat.util.*;

/**
 *
 * <p>Example: class RankSumTest.</p>
 * <p>Data Source: Hollander, M. and Wolfe, D. A. (1999).
 *    Nonparametric Statistical Methods. John Wiley and Sons, INC., p. 110. </p>
 */

public class RankSumTestExample
{

    public static void main(String arg[])
    {
        double[] testdata1 = {1.15, 0.88, 0.9, 0.74, 1.21};
        double[] testdata2 = {0.8, 0.83, 1.89, 1.04, 1.45, 1.38, 1.91, 1.64,
                              0.73, 1.46};
        DataManager dm = new DataManager();

        RankSumTest testclass1 = new RankSumTest(0.05, "equal", testdata1,
                                                 testdata2);
        double wAlpha = testclass1.wAlpha;
        double testStatistic = testclass1.testStatistic;
        double pValue = testclass1.pValue;
        out.println("The selected constant based on non-null constructor" +
                    " from the table = " +
                    dm.roundDigits(testclass1.wAlpha, 3.0));
        out.println("The test statistic based on non-null constructor" +
                    "                   = " +
                    dm.roundDigits(testclass1.testStatistic, 3.0));
        out.println("The p-value based on non-null constructor" +
                    "                          = " +
                    dm.roundDigits(testclass1.pValue, 3.0));

        RankSumTest testclass2 = new RankSumTest();
        testStatistic = testclass2.testStatistic(testdata1, testdata2);
        wAlpha = testclass2.wAlpha(0.05, testdata1, testdata2);
        pValue = testclass2.pValue("equal", testdata1, testdata2);
        out.println("\n\nThe selected constant based on null constructor" +
                    " from the table     = " + dm.roundDigits(wAlpha, 3.0));
        out.println("The test statistic based on null constructor" +
                    "                       = " +
                    dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on null constructor" +
                    "                              = " +
                    dm.roundDigits(pValue, 3.0));

        Hashtable argument1 = new Hashtable();
        argument1.put(ALPHA, 0.05);
        argument1.put(SIDE, "equal");
        StatisticalAnalysis testclass3 = new RankSumTest(argument1, testdata1,
                testdata2).statisticalAnalysis;
        wAlpha = (Double) testclass3.output.get(WALPHA);
        testStatistic = (Double) testclass3.output.get(TEST_STATISTIC);
        pValue = (Double) testclass3.output.get(PVALUE);
        out.println("\n\nThe selected constant based on non-null constructor" +
                    " from the table = " + dm.roundDigits(wAlpha, 3.0));
        out.println("The test statistic based on non-null constructor" +
                    "                   = " +
                    dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on non-null constructor" +
                    "                          = " +
                    dm.roundDigits(pValue, 3.0));
        out.println("\n\n" + testclass3.output.toString());

        Hashtable argument2 = new Hashtable();
        RankSumTest testclass4 = new RankSumTest(argument2, null);
        testStatistic = testclass4.testStatistic(argument2, testdata1,
                                                 testdata2);
        wAlpha = testclass4.wAlpha(argument2, testdata1, testdata2);
        argument2.put(SIDE, "greater");
        pValue = testclass4.pValue(argument2, testdata1, testdata2);
        out.println("\n\nThe selected constant based on null constructor" +
                    " from the table     = " + dm.roundDigits(wAlpha, 3.0));
        out.println("The test statistic based on null constructor" +
                    "                       = " +
                    dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on null constructor" +
                    "                              = " +
                    dm.roundDigits(pValue, 3.0));
        out.println("\n\n" + testclass4.output.toString());
    }

}
