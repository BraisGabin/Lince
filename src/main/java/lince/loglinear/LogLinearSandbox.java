package lince.loglinear;

import javastat.regression.glm.LogLinearRegression;
import javastat.util.DataManager;
import org.apache.log4j.Logger;

import static java.lang.System.out;

/**
 * Created by deicos on 20/01/2015.
 */
public class LogLinearSandbox {
    private Logger log = Logger.getLogger(LogLinearSandbox.class);

    public static void main(final String[] args) {
        String[][] shipData = {{"A1", "A1", "A1", "A1", "A2", "A2", "A2", "A2"}
                              ,{"C1", "C1", "C2", "C2", "C1", "C1", "C2", "C2"}
                              ,{"B1", "B2", "B1", "B2", "B1", "B2", "B1", "B2"}
                              };

        double[] damageNumber = {7,3,4,6,18,12,24,26};
        DataManager dm = new DataManager();
        LogLinearRegression testClass1 = new LogLinearRegression(damageNumber, shipData);

        double[] coefficients = testClass1.coefficients;
        double[][] confidenceInterval = testClass1.confidenceInterval;
        double[] testStatistic = testClass1.testStatistic;
        double[] pValue = testClass1.pValue;
        double[][] devianceTable = testClass1.devianceTable;

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
    }
}
