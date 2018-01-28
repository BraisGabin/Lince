package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.eda.*;
import static javastat.util.Argument.*;
import static javastat.util.Output.*;

/**
 *
 * <p>Example: class QuantitativeDataAnalysis.</p>
 * <p>Data Source: Anderson, D. R., Sweeney, D. J. and Williams, T. A. (2001).
 *    Contemporary Business Statistics with Microsoft Excel. South-Western,
 *    p. 81. </p>
 */

public class QuantitativeDataAnalysisExample
{

    public static void main(String arg[])
    {
        double[] testdata1 = {2350, 2450, 2550, 2380, 2255, 2210,
                              2390, 2630, 2440, 2825, 2420, 2380};
        String[] printOut = {"Min.", "Q1  ", "Q2  ", "Q3  ", "Max."};

        QuantitativeDataAnalysis testclass1 = new QuantitativeDataAnalysis(3,
                testdata1);
        double[] fiveNumberSummary = testclass1.fiveNumberSummary;
        double[][] frequencyTable = testclass1.frequencyTable;
        out.println(" Based on non-null constructor:");
        out.println("Five Number Summary:");
        for (int i = 0; i < fiveNumberSummary.length; i++)
        {
            System.out.println(printOut[i] + ": " + fiveNumberSummary[i]);
        }
        out.println("Frequency Distribution:");
        for (int j = 0; j < frequencyTable[0].length; j++)
        {
            out.println("<=" + frequencyTable[0][j] + ": " +
                        frequencyTable[1][j]);
        }

        QuantitativeDataAnalysis testclass2 = new QuantitativeDataAnalysis(
                testdata1);
        fiveNumberSummary = testclass2.fiveNumberSummary;
        frequencyTable = testclass2.frequencyTable;
        out.println(
                "\n\n Based on non-null constructor with default bin number:");
        out.println("Five Number Summary:");
        for (int i = 0; i < fiveNumberSummary.length; i++)
        {
            out.println(printOut[i] + ": " + fiveNumberSummary[i]);
        }
        out.println("Frequency Distribution:");
        for (int j = 0; j < frequencyTable[0].length; j++)
        {
            out.println("<=" + frequencyTable[0][j] + ": " +
                        frequencyTable[1][j]);
        }

        Hashtable argument = new Hashtable();
        argument.put(NUMBER_OF_CLASS, 3);
        StatisticalAnalysis testclass3 = new QuantitativeDataAnalysis(argument,
                testdata1).statisticalAnalysis;
        fiveNumberSummary = (double[]) testclass3.output.get(
                FIVE_NUMBER_SUMMARY);
        frequencyTable = (double[][]) testclass3.output.get(FREQUENCY_TABLE);
        out.println("\n\n Based on non-null constructor:");
        out.println("Five Number Summary:");
        for (int i = 0; i < fiveNumberSummary.length; i++)
        {
            out.println(printOut[i] + ": " + fiveNumberSummary[i]);
        }
        out.println("Frequency Distribution:");
        for (int j = 0; j < frequencyTable[0].length; j++)
        {
            out.println("<=" + frequencyTable[0][j] + ": " +
                        frequencyTable[1][j]);
        }
        out.println("\n\n" + testclass3.output);

        QuantitativeDataAnalysis testclass4 = new QuantitativeDataAnalysis(
                argument, null);
        fiveNumberSummary = testclass4.fiveNumberSummary(argument, testdata1);
        frequencyTable = testclass4.frequencyTable(argument, testdata1);
        out.println("\n\n Based on null constructor:");
        out.println("Five Number Summary:");
        for (int i = 0; i < fiveNumberSummary.length; i++)
        {
            out.println(printOut[i] + ": " + fiveNumberSummary[i]);
        }
        out.println("Frequency Distribution:");
        for (int j = 0; j < frequencyTable[0].length; j++)
        {
            out.println("<=" + frequencyTable[0][j] + ": " +
                        frequencyTable[1][j]);
        }
        out.println("\n\n" + testclass4.output);
    }

}
