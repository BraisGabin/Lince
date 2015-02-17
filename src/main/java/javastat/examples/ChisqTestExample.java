package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.inference.*;
import static javastat.util.Output.*;
import javastat.util.*;

/**
 *
 * <p>Example: class ChisqTest.</p>
 */

public class ChisqTestExample
{

    public static void main(String arg[])
    {
        double[][] chisqdata1 = { {29, 22}, {95, 121}, {518, 135} };
        String[] colvar = {"M", "F", "M", "M", "M", "F", "F", "M", "F", "M",
                           "F", "F", "M", "F", "M", "M", "F", "F", "M", "F",
                           "M", "F", "F", "F", "F", "F", "M", "F", "M", "F",
                           "F", "M", "M", "F", "M", "F", "F", "F", "M", "F",
                           "F", "F", "M", "M", "F", "F", "F", "M", "F", "F"};
        String[] rowvar = {"Editor", "AE", "Referee", "Editor", "Editor", "AE",
                           "AE", "AE", "AE", "Editor", "Editor", "AE", "AE",
                           "AE", "Referee", "Referee", "AE", "AE", "AE",
                           "Editor", "Referee", "Referee", "Editor", "AE",
                           "AE", "AE", "Referee", "Editor", "AE", "Referee",
                           "Referee", "Referee", "Referee", "AE", "Referee",
                           "AE", "Editor", "AE", "Referee", "AE", "Editor",
                           "Referee", "Editor", "Referee", "AE", "AE",
                           "Referee", "Editor", "Editor", "AE"};
        DataManager dm = new DataManager();

        ChisqTest testclass1 = new ChisqTest(chisqdata1);
        double testStatistic = testclass1.testStatistic;
        double pValue = testclass1.pValue;
        out.println(
                "The test statistic based on non-null constructor for data " +
                "set I = " + dm.roundDigits(testStatistic, 3.0));
        out.println(
                "The p-value based on non-null constructor for data set I" +
                "        = " + dm.roundDigits(pValue, 3.0));

        ChisqTest testclass2 = new ChisqTest();
        testStatistic = testclass2.testStatistic(colvar, rowvar);
        pValue = testclass2.pValue(colvar, rowvar);
        out.println(
                "\n\nThe test statistic based on null constructor for data" +
                " set II    = " + dm.roundDigits(testStatistic, 3.0));
        out.println(
                "The p-value based on null constructor for data set II" +
                "           = " + dm.roundDigits(pValue, 3.0));

        Hashtable argument = new Hashtable();
        StatisticalAnalysis testclass3 = new ChisqTest(argument, chisqdata1).
                                         statisticalAnalysis;
        testStatistic = (Double) testclass3.output.get(TEST_STATISTIC);
        pValue = (Double) testclass3.output.get(PVALUE);
        out.println(
                "\n\nThe test statistic based on non-null constructor for " +
                "data set I = " + dm.roundDigits(testStatistic, 3.0));
        out.println(
                "The p-value based on non-null constructor for data set I" +
                "        = " + dm.roundDigits(pValue, 3.0));
        out.println("\n\n" + testclass3.output.toString());

        ChisqTest testclass4 = new ChisqTest(argument, null);
        testStatistic = testclass4.testStatistic(argument, colvar, rowvar);
        pValue = testclass4.pValue(argument, colvar, rowvar);
        out.println(
                "\n\nThe test statistic based on null constructor for data" +
                " set II    = " + dm.roundDigits(testStatistic, 3.0));
        out.println(
                "The p-value based on null constructor for data set II" +
                "           = " +
                dm.roundDigits(pValue, 3.0));
        out.println("\n\n" + testclass4.output.toString());
    }

}
