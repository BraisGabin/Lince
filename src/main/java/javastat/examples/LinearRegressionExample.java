package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.regression.lm.*;
import static javastat.util.Argument.*;
import static javastat.util.Output.*;
import javastat.util.*;

/**
 *
 * <p>Example: class LinearRegression.</p>
 * <p>Data Source: Anderson, D. R., Sweeney, D. J. and Williams, T. A. (2001).
 *    Contemporary Business Statistics with Microsoft Excel.
 *    South-Western, p. 468, p.530.
 </p>
 */

public class LinearRegressionExample
{

    public static void main(String arg[])
    {
        double[] pizzaResponse = {58, 105, 88, 118, 117, 137, 157, 169, 149,
                                  202};
        double[] pizzaCovariate = {2, 6, 8, 8, 12, 16, 20, 20, 22, 26};
        double[] hellerResponse = {102, 100, 120, 77, 46, 93, 26, 69, 65, 85};
        double[] hellerCovariate1 = {120, 140, 190, 130, 155, 175, 125, 145,
                                     180, 150};
        double[] hellerCovariate2 = {100, 110, 90, 150, 210, 150, 250, 270, 300,
                                     250};
        DataManager dm = new DataManager();
        LinearRegression myClass1 = new LinearRegression(pizzaResponse,
                pizzaCovariate);
        double[] coefficients = myClass1.coefficients;
        double[] fittedValues = myClass1.fittedValues;
        double[] residuals = myClass1.residuals;
        double[] testStatistic = myClass1.testStatistic;
        double[] pValue = myClass1.pValue;
        double[][] confidenceInterval = {myClass1.confidenceInterval[0],
                                         myClass1.confidenceInterval[1]};
        double rSquare = myClass1.rSquare;
        double testFStatistic = myClass1.testFStatistic;
        double fPValue = myClass1.fPValue;
        out.println("The estimated coefficients based on non-null" +
                    " constructor                = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + "]");
        out.println("The t statistics based on non-null constructor" +
                    "                          = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + "]");
        out.println("The p-values for the t statistics based on non-null " +
                    "constructor         = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + "]");
        out.println("The confidence interval for the intercept based on" +
                    " non-null constructor = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The confidence interval for the slope based on non-null" +
                    " constructor     = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");
        out.println("The r-square based on non-null constructor" +
                    "                              =  " +
                    dm.roundDigits(rSquare, 3.0));
        out.println("The f statistic based on non-null constructor" +
                    "                           =  " +
                    dm.roundDigits(testFStatistic, 3.0));
        out.println("The p-value for the F statistic based on non-null" +
                    " constructor           =  " +
                    dm.roundDigits(fPValue, 3.0));

        LinearRegression myClass2 = new LinearRegression();
        coefficients = myClass2.coefficients(hellerResponse, hellerCovariate1,
                                             hellerCovariate2);
        confidenceInterval = myClass2.confidenceInterval(0.05, hellerResponse,
                hellerCovariate1, hellerCovariate2);
        testStatistic = myClass2.testStatistic(hellerResponse, hellerCovariate1,
                                               hellerCovariate2);
        pValue = myClass2.pValue(hellerResponse, hellerCovariate1,
                                 hellerCovariate2);
        testFStatistic = myClass2.testFStatistic(hellerResponse,
                                                 hellerCovariate1,
                                                 hellerCovariate2);
        fPValue = myClass2.fPValue(hellerResponse, hellerCovariate1,
                                   hellerCovariate2);
        out.println("\n\nThe estimated coefficients based on null constructor" +
                    "                    = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + " , " +
                    dm.roundDigits(coefficients[2], 3.0) + "]");
        out.println("The t statistics based on null constructor" +
                    "                              = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + " , " +
                    dm.roundDigits(testStatistic[2], 3.0) + "]");
        out.println("The p-values for the t statistics based on null" +
                    " constructor             = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + " , " +
                    dm.roundDigits(pValue[2], 3.0) + "]");
        out.println("The confidence interval for parameter 1 based on null" +
                    " constructor       = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The confidence interval for parameter 2 based on null" +
                    " constructor       = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");
        out.println("The confidence interval for parameter 3 based on null" +
                    " constructor       = [" +
                    dm.roundDigits(confidenceInterval[2][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[2][1], 3.0) + "]");
        out.println("The f statistic based on null constructor" +
                    "                               =  " +
                    dm.roundDigits(testFStatistic, 3.0));
        out.println("The p-value for the f statistic based on null" +
                    " constructor               =  " +
                    dm.roundDigits(fPValue, 3.0));
        confidenceInterval = myClass2.confidenceInterval(hellerResponse,
                hellerCovariate1, hellerCovariate2);
        out.println("The confidence interval for parameter 1 based on " +
                    "null constructor       = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The confidence interval for parameter 2 based on null" +
                    " constructor       = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");
        out.println("The confidence interval for parameter 3 based on " +
                    "null constructor       = [" +
                    dm.roundDigits(confidenceInterval[2][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[2][1], 3.0) + "]");

        Hashtable argument1 = new Hashtable();
        argument1.put(ALPHA, 0.05);
        StatisticalAnalysis myClass3 = new LinearRegression(argument1,
                pizzaResponse, pizzaCovariate).statisticalAnalysis;
        confidenceInterval =
                (double[][]) myClass3.output.get(CONFIDENCE_INTERVAL);
        testStatistic = (double[]) myClass3.output.get(TEST_STATISTIC);
        pValue = (double[]) myClass3.output.get(PVALUE);
        rSquare = (Double) myClass3.output.get(R_SQUARE);
        testFStatistic = (Double) myClass3.output.get(F_STATISTIC);
        fPValue = (Double) myClass3.output.get(F_PVALUE);
        residuals = (double[]) myClass3.output.get(RESIDUALS);
        fittedValues = (double[]) myClass3.output.get(FITTED_VALUES);
        out.println("\n\nThe estimated coefficients based on non-null " +
                    "constructor                = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + "]");
        out.println("The t statistics based on non-null constructor" +
                    "                          = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + "]");
        out.println("The p-values for the t statistics based on non-null" +
                    " constructor         = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + "]");
        out.println("The confidence interval for the intercept based on " +
                    "non-null constructor = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The confidence interval for the slope based on " +
                    "non-null constructor     = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");
        out.println("The r-square based on non-null constructor" +
                    "                              =  " +
                    dm.roundDigits(rSquare, 3.0));
        out.println("The f statistic based on non-null constructor" +
                    "                           =  " +
                    dm.roundDigits(testFStatistic, 3.0));
        out.println("The p-value for the F statistic based on non-null " +
                    "constructor           =  " +
                    dm.roundDigits(fPValue, 3.0));
        out.println("\n\n" + myClass3.output.toString());

        Hashtable argument2 = new Hashtable();
        LinearRegression myClass4 = new LinearRegression(argument2, null);
        coefficients = myClass4.coefficients(
                argument2, hellerResponse, hellerCovariate1, hellerCovariate2);
        argument2.put(ALPHA, 0.05);
        confidenceInterval = myClass4.confidenceInterval(argument2,
                hellerResponse, hellerCovariate1, hellerCovariate2);
        testStatistic = myClass4.testStatistic(argument2, hellerResponse,
                                               hellerCovariate1,
                                               hellerCovariate2);
        pValue = myClass4.pValue(argument2, hellerResponse, hellerCovariate1,
                                 hellerCovariate2);
        testFStatistic = myClass4.testFStatistic(argument2, hellerResponse,
                                                 hellerCovariate1,
                                                 hellerCovariate2);
        fPValue = myClass4.fPValue(argument2, hellerResponse, hellerCovariate1,
                                   hellerCovariate2);
        out.println("\n\nThe estimated coefficients based on null constructor" +
                    "                    = [" +
                    dm.roundDigits(coefficients[0], 3.0) + " , " +
                    dm.roundDigits(coefficients[1], 3.0) + " , " +
                    dm.roundDigits(coefficients[2], 3.0) + "]");
        out.println("The t statistics based on null constructor" +
                    "                              = [" +
                    dm.roundDigits(testStatistic[0], 3.0) + " , " +
                    dm.roundDigits(testStatistic[1], 3.0) + " , " +
                    dm.roundDigits(testStatistic[2], 3.0) + "]");
        out.println("The p-values for the t statistics based on null " +
                    "constructor             = [" +
                    dm.roundDigits(pValue[0], 3.0) + " , " +
                    dm.roundDigits(pValue[1], 3.0) + " , " +
                    dm.roundDigits(pValue[2], 3.0) + "]");
        out.println("The confidence interval for parameter 1 based on null" +
                    " constructor       = [" +
                    dm.roundDigits(confidenceInterval[0][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[0][1], 3.0) + "]");
        out.println("The confidence interval for parameter 2 based on null" +
                    " constructor       = [" +
                    dm.roundDigits(confidenceInterval[1][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[1][1], 3.0) + "]");
        out.println("The confidence interval for parameter 3 based on null" +
                    " constructor       = [" +
                    dm.roundDigits(confidenceInterval[2][0], 3.0) + " , " +
                    dm.roundDigits(confidenceInterval[2][1], 3.0) + "]");
        out.println("The f statistic based on null constructor" +
                    "                               =  " +
                    dm.roundDigits(testFStatistic, 3.0));
        out.println("The p-value for the f statistic based on null" +
                    " constructor               =  " +
                    dm.roundDigits(fPValue, 3.0));
        out.println("\n\n" + myClass4.output.toString());
    }

}
