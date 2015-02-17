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
 * <p>Example: class LogLinearRegression.</p>
 * <p>Data Source: McCullagh, P. and Nelder, J. A. (1989).
 *    Generalized Linear Models. New York: Chapman and Hall, p. 205. </p>
 */

public class LogLinearRegressionExample
{

    public static void main(String[] args)
    {
        /*
        40 elementos es la combinacion de 5 elementos de tipo a, 4 de tipo b y 2 de tipo c
        que forman el sistema combinacional
         */

        String[][] shipData = { {"a", "a", "a", "a", "a", "a", "a", "a",
                                 "b", "b", "b", "b", "b", "b", "b", "b",
                                 "c", "c", "c", "c", "c", "c", "c", "c",
                                 "d", "d", "d", "d", "d", "d", "d", "d",
                                 "e", "e", "e", "e", "e", "e", "e", "e"},
                                {"1960-64", "1960-64", "1965-69", "1965-69",
                                 "1970-74", "1970-74", "1975-79", "1975-79",
                                 "1960-64", "1960-64", "1965-69", "1965-69",
                                 "1970-74", "1970-74", "1975-79", "1975-79",
                                 "1960-64", "1960-64", "1965-69", "1965-69",
                                 "1970-74", "1970-74", "1975-79", "1975-79",
                                 "1960-64", "1960-64", "1965-69", "1965-69",
                                 "1970-74", "1970-74", "1975-79", "1975-79",
                                 "1960-64", "1960-64", "1965-69", "1965-69",
                                 "1970-74", "1970-74", "1975-79", "1975-79"},
                                {"1960-74", "1975-79", "1960-74", "1975-79",
                                 "1960-74", "1975-79", "1960-74", "1975-79",
                                 "1960-74", "1975-79", "1960-74", "1975-79",
                                 "1960-74", "1975-79", "1960-74", "1975-79",
                                 "1960-74", "1975-79", "1960-74", "1975-79",
                                 "1960-74", "1975-79", "1960-74", "1975-79",
                                 "1960-74", "1975-79", "1960-74", "1975-79",
                                 "1960-74", "1975-79", "1960-74", "1975-79",
                                 "1960-74", "1975-79", "1960-74", "1975-79",
                                 "1960-74", "1975-79", "1960-74", "1975-79"} };

        double[] offset = {127, 63, 1095, 1095, 1512, 3353, 0, 2244, 44882,
                           17176, 28609, 20370, 7064, 13099, 0, 7177, 1179,
                           552, 781, 676, 783, 1948, 0, 274, 251, 105, 288,
                           192, 349, 1208, 0, 2051, 45, 0, 789, 437, 1157,
                           2161, 0, 542};

        /*
        Este numero coincide con el numero de apariciones de cada combinacion anterior
         */

        double[] damageNumber = {0, 0, 3, 4, 6, 18, 0, 11, 39, 29, 58, 53, 12,
                                 44, 0, 18, 1, 1, 0, 1, 6, 2, 0, 1, 0, 0, 0, 0,
                                 2, 11, 0, 4, 0, 0, 7, 7, 5, 12, 0, 1};
        DataManager dm = new DataManager();
        LogLinearRegression testclass1 = new LogLinearRegression(damageNumber,
                offset, shipData);
        double[] coefficients = testclass1.coefficients;
        double[][] confidenceInterval = testclass1.confidenceInterval;
        double[] testStatistic = testclass1.testStatistic;
        double[] pValue = testclass1.pValue;
        double[][] devianceTable = testclass1.devianceTable;

        out.println("The estimated coefficients based on non-null " +
                    "constructor                    = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + " , " +
                    dm.roundDigits(coefficients[2], 3.0) + " , " +
                    dm.roundDigits(coefficients[3], 3.0) +
                    " , " + dm.roundDigits(coefficients[4], 3.0) + " , " +
                    dm.roundDigits(coefficients[5], 3.0) + " , " +
                    dm.roundDigits(coefficients[6], 3.0) + " , " +
                    dm.roundDigits(coefficients[7], 3.0) + " , " +
                    dm.roundDigits(coefficients[8], 3.0) + "]");
        out.println("The z statistics based on non-null constructor" +
                    "                              = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + " , " +
                    dm.roundDigits(testStatistic[2], 3.0) + " , " +
                    dm.roundDigits(testStatistic[3], 3.0) +
                    " , " + dm.roundDigits(testStatistic[4], 3.0) + " , " +
                    dm.roundDigits(testStatistic[5], 3.0) + " , " +
                    dm.roundDigits(testStatistic[6], 3.0) + " , " +
                    dm.roundDigits(testStatistic[7], 3.0) + " , " +
                    dm.roundDigits(testStatistic[8], 3.0) + "]");
        out.println("The p-values for the t statistics based on non-null" +
                    " constructor             = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + " , " +
                    dm.roundDigits(pValue[2], 3.0) + " , " +
                    dm.roundDigits(pValue[3], 3.0) +
                    " , " + dm.roundDigits(pValue[4], 3.0) + " , " +
                    dm.roundDigits(pValue[5], 3.0) + " , " +
                    dm.roundDigits(pValue[6], 3.0) + " , " +
                    dm.roundDigits(pValue[7], 3.0) + " , " +
                    dm.roundDigits(pValue[8], 3.0) + "]");
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
        out.println("The 95% confidence interval for parameter 5 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[4][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[4][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 6 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[5][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[5][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 7 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[6][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[6][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 8 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[7][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[7][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 9 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[8][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[8][1], 3.0) + "]");
        out.println("              D.F. Difference       Deviance Difference" +
                    "     D.F.        Deviance");
        out.println("Null              " +
                    dm.roundDigits(devianceTable[0][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[0][1], 3.0) +
                    "                " +
                    dm.roundDigits(devianceTable[0][2], 3.0) + "         " +
                    dm.roundDigits(devianceTable[0][3], 3.0));
        out.println("Factor A          " +
                    dm.roundDigits(devianceTable[1][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[1][1], 3.0) +
                    "             " + dm.roundDigits(devianceTable[1][2], 3.0) +
                    "          " + dm.roundDigits(devianceTable[1][3], 3.0));
        out.println("Factor A+B        " +
                    dm.roundDigits(devianceTable[2][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[2][1], 3.0) +
                    "              " +
                    dm.roundDigits(devianceTable[2][2], 3.0) +
                    "          " + dm.roundDigits(devianceTable[2][3], 3.0));
        out.println("Factor A+B+C      " +
                    dm.roundDigits(devianceTable[3][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[3][1], 3.0) +
                    "             " + dm.roundDigits(devianceTable[3][2], 3.0) +
                    "          " + dm.roundDigits(devianceTable[3][3], 3.0));
        out.println("\nFactor A: Ship Type;  Factor B: Year of Construction;" +
                    "  Factor C: Period of Operation");

        LogLinearRegression testclass2 = new LogLinearRegression();
        coefficients = testclass2.coefficients(damageNumber, offset, shipData);
        confidenceInterval = testclass2.confidenceInterval(0.1, damageNumber,
                offset, shipData);
        testStatistic =
                testclass2.testStatistic(damageNumber, offset, shipData);
        pValue = testclass2.pValue(damageNumber, offset, shipData);
        devianceTable =
                testclass2.devianceTable(damageNumber, offset, shipData);

        out.println("\n\nThe estimated coefficients based on null constructor" +
                    "                    = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + " , " +
                    dm.roundDigits(coefficients[2], 3.0) + " , " +
                    dm.roundDigits(coefficients[3], 3.0) +
                    " , " + dm.roundDigits(coefficients[4], 3.0) + " , " +
                    dm.roundDigits(coefficients[5], 3.0) + " , " +
                    dm.roundDigits(coefficients[6], 3.0) + " , " +
                    dm.roundDigits(coefficients[7], 3.0) + " , " +
                    dm.roundDigits(coefficients[8], 3.0) + "]");
        out.println("The z statistics based on null constructor" +
                    "                              = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + " , " +
                    dm.roundDigits(testStatistic[2], 3.0) + " , " +
                    dm.roundDigits(testStatistic[3], 3.0) +
                    " , " + dm.roundDigits(testStatistic[4], 3.0) + " , " +
                    dm.roundDigits(testStatistic[5], 3.0) + " , " +
                    dm.roundDigits(testStatistic[6], 3.0) + " , " +
                    dm.roundDigits(testStatistic[7], 3.0) + " , " +
                    dm.roundDigits(testStatistic[8], 3.0) + "]");
        out.println("The p-values for the t statistics based on null" +
                    " constructor             = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + " , " +
                    dm.roundDigits(pValue[2], 3.0) + " , " +
                    dm.roundDigits(pValue[3], 3.0) +
                    " , " + dm.roundDigits(pValue[4], 3.0) + " , " +
                    dm.roundDigits(pValue[5], 3.0) + " , " +
                    dm.roundDigits(pValue[6], 3.0) + " , " +
                    dm.roundDigits(pValue[7], 3.0) + " , " +
                    dm.roundDigits(pValue[8], 3.0) + "]");
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
        out.println("The 90% confidence interval for parameter 5 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[4][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[4][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 6 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[5][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[5][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 7 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[6][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[6][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 8 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[7][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[7][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 9 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[8][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[8][1], 3.0) + "]");
        out.println("              D.F. Difference       Deviance Difference" +
                    "     D.F.        Deviance");
        out.println("Null              " +
                    dm.roundDigits(devianceTable[0][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[0][1], 3.0) +
                    "                " +
                    dm.roundDigits(devianceTable[0][2], 3.0) + "         " +
                    dm.roundDigits(devianceTable[0][3], 3.0));
        out.println("Factor A          " +
                    dm.roundDigits(devianceTable[1][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[1][1], 3.0) +
                    "             " + dm.roundDigits(devianceTable[1][2], 3.0) +
                    "          " + dm.roundDigits(devianceTable[1][3], 3.0));
        out.println("Factor A+B        " +
                    dm.roundDigits(devianceTable[2][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[2][1], 3.0) +
                    "              " +
                    dm.roundDigits(devianceTable[2][2], 3.0) +
                    "          " + dm.roundDigits(devianceTable[2][3], 3.0));
        out.println("Factor A+B+C      " +
                    dm.roundDigits(devianceTable[3][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[3][1], 3.0) +
                    "             " + dm.roundDigits(devianceTable[3][2], 3.0) +
                    "          " + dm.roundDigits(devianceTable[3][3], 3.0));

        Hashtable argument1 = new Hashtable();
        StatisticalAnalysis testclass3 =
                new LogLinearRegression(argument1, damageNumber, offset,
                                        shipData).statisticalAnalysis;
        coefficients = (double[]) testclass3.output.get(COEFFICIENTS);
        confidenceInterval = (double[][]) testclass3.output.get(
                CONFIDENCE_INTERVAL);
        testStatistic = (double[]) testclass3.output.get(TEST_STATISTIC);
        pValue = (double[]) testclass3.output.get(PVALUE);
        devianceTable = (double[][]) testclass3.output.get(DEVIANCE_TABLE);

        out.println("\n\nThe estimated coefficients based on non-null" +
                    " constructor                    = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + " , " +
                    dm.roundDigits(coefficients[2], 3.0) + " , " +
                    dm.roundDigits(coefficients[3], 3.0) +
                    " , " + dm.roundDigits(coefficients[4], 3.0) + " , " +
                    dm.roundDigits(coefficients[5], 3.0) + " , " +
                    dm.roundDigits(coefficients[6], 3.0) + " , " +
                    dm.roundDigits(coefficients[7], 3.0) + " , " +
                    dm.roundDigits(coefficients[8], 3.0) + "]");
        out.println("The z statistics based on non-null constructor" +
                    "                              = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + " , " +
                    dm.roundDigits(testStatistic[2], 3.0) + " , " +
                    dm.roundDigits(testStatistic[3], 3.0) +
                    " , " + dm.roundDigits(testStatistic[4], 3.0) + " , " +
                    dm.roundDigits(testStatistic[5], 3.0) + " , " +
                    dm.roundDigits(testStatistic[6], 3.0) + " , " +
                    dm.roundDigits(testStatistic[7], 3.0) + " , " +
                    dm.roundDigits(testStatistic[8], 3.0) + "]");
        out.println("The p-values for the t statistics based on non-null" +
                    " constructor             = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + " , " +
                    dm.roundDigits(pValue[2], 3.0) + " , " +
                    dm.roundDigits(pValue[3], 3.0) +
                    " , " + dm.roundDigits(pValue[4], 3.0) + " , " +
                    dm.roundDigits(pValue[5], 3.0) + " , " +
                    dm.roundDigits(pValue[6], 3.0) + " , " +
                    dm.roundDigits(pValue[7], 3.0) + " , " +
                    dm.roundDigits(pValue[8], 3.0) + "]");
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
        out.println("The 95% confidence interval for parameter 5 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[4][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[4][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 6 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[5][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[5][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 7 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[6][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[6][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 8 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[7][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[7][1], 3.0) + "]");
        out.println("The 95% confidence interval for parameter 9 based on " +
                    "non-null constructor   = [" +
                    dm.roundDigits(confidenceInterval[8][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[8][1], 3.0) + "]");
        out.println("              D.F. Difference       Deviance Difference" +
                    "     D.F.        Deviance");
        out.println("Null              " +
                    dm.roundDigits(devianceTable[0][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[0][1], 3.0) +
                    "                " +
                    dm.roundDigits(devianceTable[0][2], 3.0) + "         " +
                    dm.roundDigits(devianceTable[0][3], 3.0));
        out.println("Factor A          " +
                    dm.roundDigits(devianceTable[1][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[1][1], 3.0) +
                    "             " + dm.roundDigits(devianceTable[1][2], 3.0) +
                    "          " + dm.roundDigits(devianceTable[1][3], 3.0));
        out.println("Factor A+B        " +
                    dm.roundDigits(devianceTable[2][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[2][1], 3.0) +
                    "              " +
                    dm.roundDigits(devianceTable[2][2], 3.0) +
                    "          " + dm.roundDigits(devianceTable[2][3], 3.0));
        out.println("Factor A+B+C      " +
                    dm.roundDigits(devianceTable[3][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[3][1], 3.0) +
                    "             " + dm.roundDigits(devianceTable[3][2], 3.0) +
                    "          " + dm.roundDigits(devianceTable[3][3], 3.0));
        out.println("\n\n" + testclass3.output.toString());

        Hashtable argument2 = new Hashtable();
        LogLinearRegression testclass4 =
                new LogLinearRegression(argument2, null);
        coefficients = testclass4.coefficients(argument2, damageNumber, offset,
                                               shipData);
        argument2.put(ALPHA, 0.1);
        confidenceInterval = testclass4.confidenceInterval(argument2,
                damageNumber, offset, shipData);
        testStatistic = testclass4.testStatistic(argument2, damageNumber,
                                                 offset, shipData);
        pValue = testclass4.pValue(argument2, damageNumber, offset, shipData);
        devianceTable = testclass4.devianceTable(argument2, damageNumber,
                                                 offset, shipData);

        out.println("\n\nThe estimated coefficients based on null constructor" +
                    "                    = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + " , " +
                    dm.roundDigits(coefficients[2], 3.0) + " , " +
                    dm.roundDigits(coefficients[3], 3.0) +
                    " , " + dm.roundDigits(coefficients[4], 3.0) + " , " +
                    dm.roundDigits(coefficients[5], 3.0) + " , " +
                    dm.roundDigits(coefficients[6], 3.0) + " , " +
                    dm.roundDigits(coefficients[7], 3.0) + " , " +
                    dm.roundDigits(coefficients[8], 3.0) + "]");
        out.println("The z statistics based on null constructor" +
                    "                              = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + " , " +
                    dm.roundDigits(testStatistic[2], 3.0) + " , " +
                    dm.roundDigits(testStatistic[3], 3.0) +
                    " , " + dm.roundDigits(testStatistic[4], 3.0) + " , " +
                    dm.roundDigits(testStatistic[5], 3.0) + " , " +
                    dm.roundDigits(testStatistic[6], 3.0) + " , " +
                    dm.roundDigits(testStatistic[7], 3.0) + " , " +
                    dm.roundDigits(testStatistic[8], 3.0) + "]");
        out.println("The p-values for the t statistics based on null " +
                    "constructor             = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + " , " +
                    dm.roundDigits(pValue[2], 3.0) + " , " +
                    dm.roundDigits(pValue[3], 3.0) +
                    " , " + dm.roundDigits(pValue[4], 3.0) + " , " +
                    dm.roundDigits(pValue[5], 3.0) + " , " +
                    dm.roundDigits(pValue[6], 3.0) + " , " +
                    dm.roundDigits(pValue[7], 3.0) + " , " +
                    dm.roundDigits(pValue[8], 3.0) + "]");
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
        out.println("The 90% confidence interval for parameter 5 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[4][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[4][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 6 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[5][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[5][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 7 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[6][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[6][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 8 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[7][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[7][1], 3.0) + "]");
        out.println("The 90% confidence interval for parameter 9 based on " +
                    "null constructor   = [" +
                    dm.roundDigits(confidenceInterval[8][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[8][1], 3.0) + "]");
        out.println("              D.F. Difference       Deviance Difference" +
                    "     D.F.        Deviance");
        out.println("Null              " +
                    dm.roundDigits(devianceTable[0][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[0][1], 3.0) +
                    "                " +
                    dm.roundDigits(devianceTable[0][2], 3.0) + "         " +
                    dm.roundDigits(devianceTable[0][3], 3.0));
        out.println("Factor A          " +
                    dm.roundDigits(devianceTable[1][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[1][1], 3.0) +
                    "             " + dm.roundDigits(devianceTable[1][2], 3.0) +
                    "          " + dm.roundDigits(devianceTable[1][3], 3.0));
        out.println("Factor A+B        " +
                    dm.roundDigits(devianceTable[2][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[2][1], 3.0) +
                    "              " +
                    dm.roundDigits(devianceTable[2][2], 3.0) +
                    "          " + dm.roundDigits(devianceTable[2][3], 3.0));
        out.println("Factor A+B+C      " +
                    dm.roundDigits(devianceTable[3][0], 3.0) +
                    "                    " +
                    dm.roundDigits(devianceTable[3][1], 3.0) +
                    "             " + dm.roundDigits(devianceTable[3][2], 3.0) +
                    "          " + dm.roundDigits(devianceTable[3][3], 3.0));
        out.println("\n\n" + testclass4.output.toString());
    }

}
