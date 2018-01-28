package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.regression.glm.*;
import static javastat.util.Argument.*;
import static javastat.util.Output.*;
import javastat.util.*;

/**
 *
 * <p>Example: class LogisticRegression.</p>
 */

public class LogisticRegressionExample
{

    public static void main(String[] args)
    {
        String[][] nominalCovariate = { {"a", "a", "b", "b", "c", "c", "a", "a",
                                         "b", "c", "c", "c"},
                                        {"T", "T", "T", "F", "T", "F", "T", "F",
                                         "F", "T", "F", "F"} };
        String[] stringBinaryResponse = {"s", "d", "s", "d", "s", "d", "d",
                                         "s", "d", "s", "d", "d"};
        DataManager dm = new DataManager();

        LogisticRegression testclass1 = new LogisticRegression(
                stringBinaryResponse, nominalCovariate);
        double[] coefficients = testclass1.coefficients;
        double[][] confidenceInterval = testclass1.confidenceInterval;
        double[] testStatistic = testclass1.testStatistic;
        double[] pValue = testclass1.pValue;
        double[][] devianceTable = testclass1.devianceTable;

        out.println("The estimated coefficients based on non-null constructor" +
                    "                    = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + " , " +
                    dm.roundDigits(coefficients[2], 3.0) + " , " +
                    dm.roundDigits(coefficients[3], 3.0) + "]");
        out.println("The z statistics based on non-null constructor" +
                    "                              = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + " , " +
                    dm.roundDigits(testStatistic[2], 3.0) + " , " +
                    dm.roundDigits(testStatistic[3], 3.0) + "]");
        out.println("The p-values for the t statistics based on non-null" +
                    " constructor             = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + " , " +
                    dm.roundDigits(pValue[2], 3.0) + " , " +
                    dm.roundDigits(pValue[3], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 1 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 2 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 3 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[2][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[2][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 4 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[3][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[3][1], 3.0) + "]");
        out.println("              D.F. Difference        Deviance Difference" +
                    "        D.F.        Deviance");
        out.println("Null              " +
                    dm.roundDigits(devianceTable[0][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[0][1], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[0][2], 3.0) + "         " +
                    dm.roundDigits(devianceTable[0][3], 3.0));
        out.println("Factor A          " +
                    dm.roundDigits(devianceTable[1][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[1][1], 3.0) +
                    "                   " +
                    dm.roundDigits(devianceTable[1][2], 3.0) + "         " +
                    dm.roundDigits(devianceTable[1][3], 3.0));
        out.println("Factor A+B        " +
                    dm.roundDigits(devianceTable[2][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[2][1], 3.0) +
                    "                   " +
                    dm.roundDigits(devianceTable[2][2], 3.0) + "          " +
                    dm.roundDigits(devianceTable[2][3], 3.0));

        LogisticRegression testclass2 = new LogisticRegression();
        coefficients =
                testclass2.coefficients(stringBinaryResponse, nominalCovariate);
        confidenceInterval = testclass2.confidenceInterval(
                0.1, stringBinaryResponse, nominalCovariate);
        testStatistic = testclass2.testStatistic(stringBinaryResponse,
                                                 nominalCovariate);
        pValue = testclass2.pValue(stringBinaryResponse, nominalCovariate);
        devianceTable = testclass2.devianceTable(stringBinaryResponse,
                                                 nominalCovariate);

        out.println("\n\nThe estimated coefficients based on null constructor" +
                    "                    = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + " , " +
                    dm.roundDigits(coefficients[2], 3.0) + " , " +
                    dm.roundDigits(coefficients[3], 3.0) + "]");
        out.println("The z statistics based on null constructor" +
                    "                              = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + " , " +
                    dm.roundDigits(testStatistic[2], 3.0) + " , " +
                    dm.roundDigits(testStatistic[3], 3.0) + "]");
        out.println("The p-values for the t statistics based on null" +
                    " constructor             = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + " , " +
                    dm.roundDigits(pValue[2], 3.0) + " , " +
                    dm.roundDigits(pValue[3], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 1 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 2 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 3 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[2][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[2][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 4 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[3][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[3][1], 3.0) + "]");
        out.println("              D.F. Difference        Deviance Difference" +
                    "        D.F.        Deviance");
        out.println("Null              " +
                    dm.roundDigits(devianceTable[0][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[0][1], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[0][2], 3.0) + "         " +
                    dm.roundDigits(devianceTable[0][3], 3.0));
        out.println("Factor A          " +
                    dm.roundDigits(devianceTable[1][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[1][1], 3.0) +
                    "                   " +
                    dm.roundDigits(devianceTable[1][2], 3.0) + "         " +
                    dm.roundDigits(devianceTable[1][3], 3.0));
        out.println("Factor A+B        " +
                    dm.roundDigits(devianceTable[2][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[2][1], 3.0) +
                    "                   " +
                    dm.roundDigits(devianceTable[2][2], 3.0) + "          " +
                    dm.roundDigits(devianceTable[2][3], 3.0));

        Hashtable argument1 = new Hashtable();
        StatisticalAnalysis testclass3 =
                new LogisticRegression(argument1, stringBinaryResponse,
                                       nominalCovariate).statisticalAnalysis;
        coefficients = (double[]) testclass3.output.get(COEFFICIENTS);
        confidenceInterval = (double[][]) testclass3.output.get(
                CONFIDENCE_INTERVAL);
        testStatistic = (double[]) testclass3.output.get(TEST_STATISTIC);
        pValue = (double[]) testclass3.output.get(PVALUE);
        devianceTable = (double[][]) testclass3.output.get(DEVIANCE_TABLE);

        out.println("\n\nThe estimated coefficients based on non-null " +
                    "constructor                    = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + " , " +
                    dm.roundDigits(coefficients[2], 3.0) + " , " +
                    dm.roundDigits(coefficients[3], 3.0) + "]");
        out.println("The z statistics based on non-null constructor" +
                    "                              = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + " , " +
                    dm.roundDigits(testStatistic[2], 3.0) + " , " +
                    dm.roundDigits(testStatistic[3], 3.0) + "]");
        out.println("The p-values for the t statistics based on non-null" +
                    " constructor             = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + " , " +
                    dm.roundDigits(pValue[2], 3.0) + " , " +
                    dm.roundDigits(pValue[3], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 1 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 2 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 3 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[2][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[2][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 4 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[3][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[3][1], 3.0) + "]");
        out.println("              D.F. Difference        Deviance Difference" +
                    "        D.F.        Deviance");
        out.println("Null              " +
                    dm.roundDigits(devianceTable[0][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[0][1], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[0][2], 3.0) + "         " +
                    dm.roundDigits(devianceTable[0][3], 3.0));
        out.println("Factor A          " +
                    dm.roundDigits(devianceTable[1][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[1][1], 3.0) +
                    "                   " +
                    dm.roundDigits(devianceTable[1][2], 3.0) + "         " +
                    dm.roundDigits(devianceTable[1][3], 3.0));
        out.println("Factor A+B        " +
                    dm.roundDigits(devianceTable[2][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[2][1], 3.0) +
                    "                   " +
                    dm.roundDigits(devianceTable[2][2], 3.0) + "          " +
                    dm.roundDigits(devianceTable[2][3], 3.0));
        out.println("\n\n" + testclass3.output.toString());

        Hashtable argument2 = new Hashtable();
        LogisticRegression testclass4 = new LogisticRegression(argument2, null);
        coefficients = testclass4.coefficients(argument2, stringBinaryResponse,
                                               nominalCovariate);
        argument2.put(ALPHA, 0.1);
        confidenceInterval = testclass4.confidenceInterval(argument2,
                stringBinaryResponse, nominalCovariate);
        testStatistic = testclass4.testStatistic(
                argument2, stringBinaryResponse, nominalCovariate);
        pValue = testclass4.
                 pValue(argument2, stringBinaryResponse, nominalCovariate);
        devianceTable = testclass4.devianceTable(
                argument2, stringBinaryResponse, nominalCovariate);

        out.println("\n\nThe estimated coefficients based on null constructor" +
                    "                    = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + " , " +
                    dm.roundDigits(coefficients[2], 3.0) + " , " +
                    dm.roundDigits(coefficients[3], 3.0) + "]");
        out.println("The z statistics based on null constructor" +
                    "                              = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + " , " +
                    dm.roundDigits(testStatistic[2], 3.0) + " , " +
                    dm.roundDigits(testStatistic[3], 3.0) + "]");
        out.println("The p-values for the t statistics based on null" +
                    " constructor             = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + " , " +
                    dm.roundDigits(pValue[2], 3.0) + " , " +
                    dm.roundDigits(pValue[3], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 1 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 2 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 3 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[2][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[2][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 4 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[3][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[3][1], 3.0) + "]");
        out.println("              D.F. Difference        Deviance Difference" +
                    "        D.F.        Deviance");
        out.println("Null              " +
                    dm.roundDigits(devianceTable[0][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[0][1], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[0][2], 3.0) + "         " +
                    dm.roundDigits(devianceTable[0][3], 3.0));
        out.println("Factor A          " +
                    dm.roundDigits(devianceTable[1][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[1][1], 3.0) +
                    "                   " +
                    dm.roundDigits(devianceTable[1][2], 3.0) + "         " +
                    dm.roundDigits(devianceTable[1][3], 3.0));
        out.println("Factor A+B        " +
                    dm.roundDigits(devianceTable[2][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[2][1], 3.0) +
                    "                   " +
                    dm.roundDigits(devianceTable[2][2], 3.0) + "          " +
                    dm.roundDigits(devianceTable[2][3], 3.0));
    }

}
