package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.inference.*;
import static javastat.util.Output.*;
import javastat.util.*;

/**
 *
 * <p>Example: class OneWayANOVA.</p>
 * <p>Data Source: Anderson, D. R., Sweeney, D. J. and Williams, T. A. (2001).
 *    Contemporary Business Statistics with Microsoft Excel. South-Western,
 * p. 408. </p>
 */

public class OneWayANOVAExample
{

    public static void main(String arg[])
    {
        double[][] anovadata1 = { {6.0, 7.0, 6.0, 8.0}, {8.0, 9.0, 8.0, 10.0},
                                  {13.0, 14.0, 15.0} };
        double[] anovadata21 = {85, 75, 82, 76, 71, 85};
        double[] anovadata22 = {71, 75, 73, 74, 69, 82};
        double[] anovadata23 = {59, 64, 62, 69, 75, 67};

        DataManager dm = new DataManager();

        OneWayANOVA testclass1 = new OneWayANOVA(anovadata1);
        double testStatistic = testclass1.testStatistic;
        double pValue = testclass1.pValue;
        out.println("Covariance Matrix:");
        out.println("The test statistic based on non-null constructor " +
                    "for data set I = " + dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on non-null constructor " +
                    "for data set I        = " + dm.roundDigits(pValue, 3.0));

        OneWayANOVA testclass2 = new OneWayANOVA();
        testStatistic = testclass2.testStatistic(anovadata21, anovadata22,
                                                 anovadata23);
        pValue = testclass2.pValue(anovadata21, anovadata22, anovadata23);
        out.println("\n\nCorrelation Matrix:");
        out.println("The test statistic based on null constructor " +
                    "for data set II    = " +
                    dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on null constructor " +
                    "for data set II           = " +
                    dm.roundDigits(pValue, 5.0));

        Hashtable argument = new Hashtable();
        StatisticalAnalysis testclass3 =
                new OneWayANOVA(argument, anovadata21, anovadata22, anovadata23).
                statisticalAnalysis;
        testStatistic = (Double) testclass3.output.get(TEST_STATISTIC);
        pValue = (Double) testclass3.output.get(PVALUE);
        out.println("\n\nCovariance Matrix:");
        out.println("The test statistic based on non-null constructor " +
                    "for data set II= " + dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on non-null constructor " +
                    "for data set II       = " + dm.roundDigits(pValue, 5.0));
        out.println("\n\n" + testclass3.output.toString());

        OneWayANOVA testclass4 = new OneWayANOVA(argument, null);
        testStatistic = testclass4.testStatistic(argument, anovadata1);
        pValue = testclass4.pValue(argument, anovadata1);
        out.println("\n\nCorrelation Matrix:");
        out.println("The test statistic based on null constructor " +
                    "for data set I     = " +
                    dm.roundDigits(testStatistic, 3.0));
        out.println("The p-value based on null constructor " +
                    "for data set I            = " +
                    dm.roundDigits(pValue, 3.0));
        out.println("\n\n" + testclass4.output.toString());
    }

}
