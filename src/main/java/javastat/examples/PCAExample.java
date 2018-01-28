package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.multivariate.*;
import static javastat.util.Argument.*;
import static javastat.util.Output.*;
import javastat.util.*;

/**
 *
 * <p>Example: class PCA.</p>
 * <p>Data Source: S-PLUS 6 for Windows Guide to Statistics, Volume 2,
 *    Insightful Corporation, Seattle, WA.,  pp. 40-41.</p>
 */

public class PCAExample
{

    public static void main(String arg[])
    {
        double[][] testscores = { {36, 62, 31, 76, 46, 12, 39, 30, 22, 9, 32,
                                   40, 64, 36, 24, 50, 42, 2, 56, 59, 28, 19,
                                   36, 54, 14},
                                  {58, 54, 42, 78, 56, 42, 46, 51, 32, 40, 49,
                                   62, 75, 38, 46, 50, 42, 35, 53, 72, 50, 46,
                                   56, 57, 35},
                                  {43, 50, 41, 69, 52, 38, 51, 54, 43, 47, 54,
                                   51, 70, 58, 44, 54, 52, 32, 42, 70, 50, 49,
                                   56, 59, 38},
                                  {36, 46, 40, 66, 56, 38, 54, 52, 28, 30, 37,
                                   40, 66, 62, 55, 52, 38, 22, 40, 66, 42, 40,
                                   54, 62, 29},
                                  {37, 52, 29, 81, 40, 28, 41, 32, 22, 24, 52,
                                   49, 63, 62, 49, 51, 50, 16, 32, 62, 63, 30,
                                   52, 58, 20} };
        DataManager dm = new DataManager();

        PCA testclass1 = new PCA(0.95, "covariance", testscores);
        out.println("1st component vector     = " +
                    dm.roundDigits(testclass1.principalComponents[0][0], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[0][1], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[0][2], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[0][3], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[0][4], 3.0));
        out.println("2nd component vector     = " +
                    dm.roundDigits(testclass1.principalComponents[1][0], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[1][1], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[1][2], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[1][3], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[1][4], 3.0));
        out.println("3rd component vector     = " +
                    dm.roundDigits(testclass1.principalComponents[2][0], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[2][1], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[2][2], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[2][3], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[2][4], 3.0));
        out.println("4th component vector     = " +
                    dm.roundDigits(testclass1.principalComponents[3][0], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[3][1], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[3][2], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[3][3], 3.0) +
                    "  " +
                    dm.roundDigits(testclass1.principalComponents[3][4], 3.0));
        out.println("variances of components  = " +
                    dm.roundDigits(testclass1.variance[0], 3.0) + "  "
                    + dm.roundDigits(testclass1.variance[1], 3.0) + "  "
                    + dm.roundDigits(testclass1.variance[2], 3.0) + "  "
                    + dm.roundDigits(testclass1.variance[3], 3.0));

        PCA testclass2 = new PCA();
        double[][] principalComponents = testclass2.principalComponents(
                testscores);
        double[] variance = testclass2.variance(testscores);
        out.println("\n\n1st component vector     = " +
                    dm.roundDigits(principalComponents[0][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[0][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[0][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[0][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[0][4], 3.0));
        out.println("2nd component vector     = " +
                    dm.roundDigits(principalComponents[1][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[1][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[1][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[1][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[1][4], 3.0));
        out.println("3rd component vector     = " +
                    dm.roundDigits(principalComponents[2][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[2][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[2][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[2][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[2][4], 3.0));
        out.println("4th component vector     = " +
                    dm.roundDigits(principalComponents[3][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[3][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[3][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[3][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[3][4], 3.0));
        out.println("5th component vector     = " +
                    dm.roundDigits(principalComponents[4][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[4][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[4][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[4][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[4][4], 3.0));
        out.println("variances of components  = " +
                    dm.roundDigits(variance[0], 3.0) + "  "
                    + dm.roundDigits(variance[1], 3.0) + "  "
                    + dm.roundDigits(variance[2], 3.0) + "  "
                    + dm.roundDigits(variance[3], 3.0) + "  "
                    + dm.roundDigits(variance[4], 3.0));

        Hashtable argument1 = new Hashtable();
        argument1.put(LEVEL, 0.95);
        argument1.put(COVARIANCE_CHOICE, "covariance");
        StatisticalAnalysis testclass3 = new PCA(argument1, testscores).
                                         statisticalAnalysis;
        principalComponents = (double[][]) testclass3.output.get(
                PRINCIPAL_COMPONENTS);
        variance = (double[]) testclass3.output.get(COMPONENT_VARIANCE);
        out.println("\n\n1st component vector     = " +
                    dm.roundDigits(principalComponents[0][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[0][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[0][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[0][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[0][4], 3.0));
        out.println("2nd component vector     = " +
                    dm.roundDigits(principalComponents[1][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[1][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[1][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[1][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[1][4], 3.0));
        out.println("3rd component vector     = " +
                    dm.roundDigits(principalComponents[2][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[2][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[2][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[2][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[2][4], 3.0));
        out.println("4th component vector     = " +
                    dm.roundDigits(principalComponents[3][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[3][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[3][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[3][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[3][4], 3.0));
        out.println("variances of components  = " +
                    dm.roundDigits(variance[0], 3.0) + "  "
                    + dm.roundDigits(variance[1], 3.0) + "  "
                    + dm.roundDigits(variance[2], 3.0) + "  "
                    + dm.roundDigits(variance[3], 3.0));
        out.println("\n\n" + testclass3.output.toString());

        Hashtable argument2 = new Hashtable();
        PCA testclass4 = new PCA(argument2, null);
        principalComponents = testclass4.principalComponents(argument2,
                testscores);
        variance = testclass4.variance(argument2, testscores);
        out.println("\n\n1st component vector     = " +
                    dm.roundDigits(principalComponents[0][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[0][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[0][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[0][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[0][4], 3.0));
        out.println("2nd component vector     = " +
                    dm.roundDigits(principalComponents[1][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[1][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[1][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[1][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[1][4], 3.0));
        out.println("3rd component vector     = " +
                    dm.roundDigits(principalComponents[2][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[2][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[2][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[2][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[2][4], 3.0));
        out.println("4th component vector     = " +
                    dm.roundDigits(principalComponents[3][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[3][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[3][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[3][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[3][4], 3.0));
        out.println("5th component vector     = " +
                    dm.roundDigits(principalComponents[4][0], 3.0) + "  "
                    + dm.roundDigits(principalComponents[4][1], 3.0) + "  "
                    + dm.roundDigits(principalComponents[4][2], 3.0) + "  "
                    + dm.roundDigits(principalComponents[4][3], 3.0) + "  "
                    + dm.roundDigits(principalComponents[4][4], 3.0));
        out.println("variances of components  = " +
                    dm.roundDigits(variance[0], 3.0) + "  "
                    + dm.roundDigits(variance[1], 3.0) + "  "
                    + dm.roundDigits(variance[2], 3.0) + "  "
                    + dm.roundDigits(variance[3], 3.0) + "  "
                    + dm.roundDigits(variance[4], 3.0));
        out.println("\n\n" + testclass4.output.toString());
    }

}
