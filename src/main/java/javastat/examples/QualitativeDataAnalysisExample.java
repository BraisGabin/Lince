package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.eda.*;
import static javastat.util.Output.*;

/**
 *
 * <p>Example: class QualitativeDataAnalysis.</p>
 */

public class QualitativeDataAnalysisExample
{

    public static void main(String arg[])
    {
        String[] testdata1 = {"IBM", "Compaq", "Apple", "Packard Bell",
                              "Gateway 2000", "IBM", "Apple", "Packard Bell",
                              "Packard Bell", "Gateway 2000", "Compaq",
                              "Compaq", "Apple", "Compaq", "Apple",
                              "Apple", "Apple", "IBM", "IBM", "Apple",
                              "Compaq", "Packard Bell", "IBM", "Compaq",
                              "Apple", "Apple", "Compaq", "Gateway 2000",
                              "Packard Bell", "IBM", "IBM", "Gateway 2000",
                              "Apple", "IBM", "Packard Bell", "Packard Bell",
                              "Gateway 2000", "Compaq", "Compaq", "Apple",
                              "IBM", "Packard Bell", "Compaq", "Packard Bell",
                              "Compaq", "Packard Bell", "Compaq", "Apple",
                              "Packard Bell", "Apple"};

        QualitativeDataAnalysis testclass1 = new QualitativeDataAnalysis(
                testdata1);
        String[] dataValues = testclass1.dataValues;
        String[] frequency = testclass1.frequency;
        out.println(" Based on non-null constructor:");
        for (int i = 0; i < dataValues.length; i++)
        {
            out.print(dataValues[i] + ": " + frequency[i] + " ");
        }

        QualitativeDataAnalysis testclass2 = new QualitativeDataAnalysis();
        dataValues = testclass2.frequencyTable(testdata1)[0];
        frequency = testclass2.frequencyTable(testdata1)[1];
        out.println("\n\n Based on null constructor:");
        for (int i = 0; i < dataValues.length; i++)
        {
            out.print(dataValues[i] + ": " + frequency[i] + " ");
        }

        Hashtable argument = new Hashtable();
        StatisticalAnalysis testclass3 =
                new QualitativeDataAnalysis(argument, testdata1).
                statisticalAnalysis;
        dataValues = (String[]) testclass3.output.get(DATA_VALUES);
        frequency = (String[]) testclass3.output.get(FREQUENCY);
        out.println("\n\n Based on non-null constructor:");
        for (int i = 0; i < dataValues.length; i++)
        {
            out.print(dataValues[i] + ": " + frequency[i] + " ");
        }
        out.println("\n\n" + testclass3.output.toString());

        QualitativeDataAnalysis testclass4 = new QualitativeDataAnalysis(
                argument, null);
        String[] category = new String[] {"Apple", "IBM", "OTHER"};
        dataValues = testclass4.frequencyTable(
                argument, category, testdata1)[0];
        frequency = testclass4.frequencyTable(argument, category, testdata1)[1];
        out.println("\n\n Based on null constructor:");
        for (int i = 0; i < dataValues.length; i++)
        {
            out.print(dataValues[i] + ": " + frequency[i] + " ");
        }
        out.println("\n\n" + testclass4.output.toString());
    }

}
