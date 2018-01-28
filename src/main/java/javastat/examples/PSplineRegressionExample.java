package javastat.examples;

import static java.lang.System.*;
import java.util.*;

import javastat.*;
import javastat.regression.nonparametric.*;
import static javastat.util.Argument.*;
import static javastat.util.Output.*;

/**
 *
 * Example: class PSplineRegression.
 * <p>Data Source: Brinkman, N. D., 1981. Ethanol fuel-A single-cylinder
 * engine study of efficiency and exhaust emissions. SAE Transactions 90,
 * No. 810345, 1410-1424. </p>
 */

public class PSplineRegressionExample
{

    public static void main(String[] args)
    {
        double[] ethanolx = {0.907, 0.761, 1.108, 1.016, 1.189, 1.001, 1.231,
                             1.123, 1.042, 1.215, 0.930, 1.152, 1.138, 0.601,
                             0.696, 0.686, 1.072, 1.074, 0.934, 0.808, 1.071,
                             1.009, 1.142, 1.229, 1.175, 0.568, 0.977, 0.767,
                             1.006, 0.893, 1.152, 0.693, 1.232, 1.036, 1.125,
                             1.081, 0.868, 0.762, 1.144, 1.045, 0.797, 1.115,
                             1.070, 1.219, 0.637, 0.733, 0.715, 0.872, 0.765,
                             0.878, 0.811, 0.676, 1.045, 0.968, 0.846, 0.684,
                             0.729, 0.911, 0.808, 1.168, 0.749, 0.892, 1.002,
                             0.812, 1.230, 0.804, 0.813, 1.002, 0.696, 1.199,
                             1.030, 0.602, 0.694, 0.816, 1.037, 1.181, 0.899,
                             1.227, 1.180, 0.795, 0.990, 1.201, 0.629, 0.608,
                             0.584, 0.562, 0.535, 0.655};

        double[] ethanoly = {3.741, 2.295, 1.498, 2.881, 0.760, 3.120, 0.638,
                             1.170, 2.358, 0.606, 3.669, 1.000, 0.981, 1.192,
                             0.926, 1.590, 1.806, 1.962, 4.028, 3.148, 1.836,
                             2.845, 1.013, 0.414, 0.812, 0.374, 3.623, 1.869,
                             2.836, 3.567, 0.866, 1.369, 0.542, 2.739, 1.200,
                             1.719, 3.423, 1.634, 1.021, 2.157, 3.361, 1.390,
                             1.947, 0.962, 0.571, 2.219, 1.419, 3.519, 1.732,
                             3.206, 2.471, 1.777, 2.571, 3.952, 3.931, 1.587,
                             1.397, 3.536, 2.202, 0.756, 1.620, 3.656, 2.964,
                             3.760, 0.672, 3.677, 3.517, 3.290, 1.139, 0.727,
                             2.581, 0.923, 1.527, 3.388, 2.085, 0.966, 3.488,
                             0.754, 0.797, 2.064, 3.732, 0.586, 0.561, 0.563,
                             0.678, 0.370, 0.530, 1.900};

        double[] fittedValues = new PSplineRegression(0.013, 10, 3, 2, ethanoly,
                ethanolx).fittedValues;
        double[] coefficients = new PSplineRegression().coefficients(0.013, 10,
                3, 2, ethanoly, ethanolx);
        double minimizer = new PSplineRegression().minimizer(10, ethanoly,
                ethanolx);
        System.out.println(" The minimizer is " + minimizer);

        Hashtable argument1 = new Hashtable();
        argument1.put(SMOOTHING_PARAMETER, 10);
        argument1.put(DIVISIONS, 10);
        StatisticalAnalysis testclass1 = new PSplineRegression(argument1,
                ethanoly, ethanolx).statisticalAnalysis;
        fittedValues = (double[]) testclass1.output.get(FITTED_VALUES);
        out.println(testclass1.output.toString());

        Hashtable argument2 = new Hashtable();
        PSplineRegression testclass2 = new PSplineRegression(argument2, null);
        argument2.put(SMOOTHING_PARAMETER, 1);
        argument2.put(DIVISIONS, 20);
        coefficients = testclass2.coefficients(argument2, ethanoly, ethanolx);
        out.println(testclass2.output.toString());
    }

}
