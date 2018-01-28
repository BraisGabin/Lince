package javastat.examples;

import static java.lang.System.out;
import java.util.*;

import javastat.*;
import javastat.multivariate.*;
import static javastat.util.Output.*;
import javastat.util.*;

/**
 *
 * <p>Example: class DiscriminantAnalysis.</p>
 * <p>Data Source: Fisher's iris data</p>
 */

public class DiscriminantAnalysisExample
{

    public static void main(String arg[])
    {
        DataManager dm = new DataManager();
        double[][] testdata = new double[4][];
        dm.scanFileToMatrix(System.getProperty("user.dir") +
                            System.getProperty("file.separator") + 
					        "examples" + System.getProperty("file.separator") + 
			                "javastat" + System.getProperty("file.separator") + 
                            "iris.txt", testdata, 4);
        double[] testgroup = new double[150];
        for (int j = 0; j < 3; j++)
        {
            for (int i = 0; i < 50; i++)
            {
                testgroup[j * 50 + i] = j + 1;
            }
        }

        DiscriminantAnalysis testclass1 = new DiscriminantAnalysis(testgroup,
                testdata);
        double[][] linearDiscriminants1 = {testclass1.linearDiscriminants[0],
                                          testclass1.linearDiscriminants[1]};
        out.println("The first discriminant based on non-null constructor  = ["
                    + dm.roundDigits(linearDiscriminants1[0][0], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants1[0][1], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants1[0][2], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants1[0][3], 3.0) + "]");
        out.println("The second discriminant based on non-null constructor = ["
                    + dm.roundDigits(linearDiscriminants1[1][0], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants1[1][1], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants1[1][2], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants1[1][3], 3.0) + "]");

        DiscriminantAnalysis testclass2 = new DiscriminantAnalysis();
        double[][] linearDiscriminants2 = testclass2.linearDiscriminants(
                testgroup, testdata);
        out.println(
                "\n\nThe first discriminant based on null constructor      = ["
                + dm.roundDigits(linearDiscriminants2[0][0], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants2[0][1], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants2[0][2], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants2[0][3], 3.0) + "]");
        out.println(
                "The second discriminant based on null constructor     = ["
                + dm.roundDigits(linearDiscriminants2[1][0], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants2[1][1], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants2[1][2], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants2[1][3], 3.0) + "]");
        double[][] predata = new double[4][];
        dm.scanFileToMatrix(System.getProperty("user.dir") +
                            System.getProperty("file.separator") +
					        "examples" + System.getProperty("file.separator") + 
			                "javastat" + System.getProperty("file.separator") + 
                            "iris2.txt", predata, 4);
        int[] predictedGroup2 = testclass2.predictedGroup(testgroup, testdata,
                predata);
        double errorRate = 0.0;
        for (int i = 0; i < predictedGroup2.length; i++)
        {
            if ((int) testgroup[i] != predictedGroup2[i])
            {
                errorRate += 1.0;
            }
        }
        errorRate /= predictedGroup2.length;
        out.println("The error rate based on null constructor              =" +
                    "  " + dm.roundDigits(errorRate, 3.0));

        DiscriminantAnalysis testclass3 = new DiscriminantAnalysis(testgroup,
                testdata, testdata);
        double[][] linearDiscriminants3 = {testclass3.linearDiscriminants[0],
                                          testclass3.linearDiscriminants[1]};
        out.println(
                "\n\nThe first discriminant based on non-null constructor  = ["
                + dm.roundDigits(linearDiscriminants3[0][0], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants3[0][1], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants3[0][2], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants3[0][3], 3.0) + "]");
        out.println("The second discriminant based on non-null constructor = ["
                    + dm.roundDigits(linearDiscriminants3[1][0], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants3[1][1], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants3[1][2], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants3[1][3], 3.0) + "]");
        int[] predictedGroup3 = testclass3.predictedGroup;
        int preg = predictedGroup3[predictedGroup3.length - 1];
        out.println(
                "The last observation of Iris data was assigned to     =  " +
                "group " + dm.roundDigits(preg, 3.0));

        Hashtable argument1 = new Hashtable();
        StatisticalAnalysis testclass4 = new DiscriminantAnalysis(argument1,
                testgroup, testdata).statisticalAnalysis;
        linearDiscriminants1 = (double[][]) testclass4.output.get(
                LINEAR_DISCRIMINANTS);
        out.println(
                "\n\nThe first discriminant based on non-null constructor  = ["
                + dm.roundDigits(linearDiscriminants1[0][0], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants1[0][1], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants1[0][2], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants1[0][3], 3.0) + "]");
        out.println("The second discriminant based on non-null constructor = ["
                    + dm.roundDigits(linearDiscriminants1[1][0], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants1[1][1], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants1[1][2], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants1[1][3], 3.0) + "]");
        out.println("\n\n" + testclass4.output.toString());

        Hashtable argument2 = new Hashtable();
        DiscriminantAnalysis testclass5 =
                new DiscriminantAnalysis(argument2, null);
        linearDiscriminants2 =
                testclass5.linearDiscriminants(argument2, testgroup, testdata);
        out.println(
                "\n\nThe first discriminant based on null constructor" +
                "      = ["
                + dm.roundDigits(linearDiscriminants2[0][0], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants2[0][1], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants2[0][2], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants2[0][3], 3.0) + "]");
        out.println(
                "The second discriminant based on null constructor     = ["
                + dm.roundDigits(linearDiscriminants2[1][0], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants2[1][1], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants2[1][2], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants2[1][3], 3.0) + "]");
        predictedGroup2 = testclass5.predictedGroup(argument2, testgroup,
                testdata, predata);
        errorRate = 0.0;
        for (int i = 0; i < predictedGroup2.length; i++)
        {
            if ((int) testgroup[i] != predictedGroup2[i])
            {
                errorRate += 1.0;
            }
        }
        errorRate /= predictedGroup2.length;
        out.println("The error rate based on null constructor              " +
                    "=  " + dm.roundDigits(errorRate, 3.0));
        out.println("\n\n" + testclass5.output.toString());

        Hashtable argument3 = new Hashtable();
        StatisticalAnalysis testclass6 = new DiscriminantAnalysis(argument3,
                testgroup, testdata, testdata).statisticalAnalysis;
        linearDiscriminants3 = (double[][]) testclass6.output.get(
                LINEAR_DISCRIMINANTS);
        out.println(
                "\n\nThe first discriminant based on non-null constructor  = ["
                + dm.roundDigits(linearDiscriminants3[0][0], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants3[0][1], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants3[0][2], 3.0) + " , "
                + dm.roundDigits(linearDiscriminants3[0][3], 3.0) + "]");
        out.println("The second discriminant based on non-null constructor = ["
                    + dm.roundDigits(linearDiscriminants3[1][0], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants3[1][1], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants3[1][2], 3.0) + " , "
                    + dm.roundDigits(linearDiscriminants3[1][3], 3.0) + "]");
        predictedGroup3 = (int[]) testclass6.output.get(PREDICTED_GROUP);
        preg = predictedGroup3[predictedGroup3.length - 1];
        out.println(
                "The last observation of Iris data was assigned to     =" +
                "  group " + dm.roundDigits(preg, 3.0));
        out.println("\n\n" + testclass6.output.toString());
    }

}
