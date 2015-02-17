package javastat.examples;

import static java.lang.System.*;
import java.util.*;

import javastat.*;
import javastat.regression.nonparametric.*;
import static javastat.util.Argument.*;
import static javastat.util.Output.*;

/**
 *
 * Example: class BSplineBasis.
 * <p>Data Source: Brinkman, N. D., 1981. Ethanol fuel-A single-cylinder
 * engine study of efficiency and exhaust emissions. SAE Transactions 90,
 * No. 810345, 1410-1424. </p>
 */

public class BSplineBasisExample
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

        double[][] basis = new BSplineBasis(10, ethanolx).basis;
        basis = new BSplineBasis().basis(10, ethanolx);

        Hashtable argument1 = new Hashtable();
        argument1.put(DIVISIONS, 10);
        StatisticalAnalysis testclass1 = new BSplineBasis(argument1, ethanolx).
                                         statisticalAnalysis;
        basis = (double[][]) testclass1.output.get(BASIS);
        out.println(testclass1.output.toString());

        Hashtable argument2 = new Hashtable();
        BSplineBasis testclass2 = new BSplineBasis(argument2, null);
        argument2.put(DIVISIONS, 10);
        basis = testclass2.basis(argument2, ethanolx);
        out.println(testclass2.output.toString());
    }

}
