package javastat.examples;

import static java.lang.System.*;
import java.util.*;

import javastat.*;
import static javastat.regression.PsiFunction.*;
import static javastat.regression.SelectionCriterion.*;
import javastat.regression.*;
import static javastat.util.Argument.*;
import javastat.util.*;

/**
 *
 * <p>Example: class WeightedSelectionCriterion. </p>
 */

public class WeightedSelectionCriterionExample
{

    public static void main(String[] args)
    {
        DataManager dm = new DataManager();
        BasicStatistics bs = new BasicStatistics();
        double[][] covariate =
            { {0.907, 0.761, 1.108, 1.016, 1.189, 1.001, 1.231,
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
               0.584, 0.562, 0.535, 0.655} };
        double[] response = {3.741, 2.295, 1.498, 2.881, 0.760, 3.120, 0.638,
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
        double[][] weightMatrix = dm.inverse(bs.covarianceAR1(response.length,
                0.2, 1));

        double aic = new WeightedSelectionCriterion(weightMatrix, response,
                covariate).weightedSelectionCriterion;
        double gcv = new WeightedSelectionCriterion(GCV, weightMatrix, response,
                covariate).weightedSelectionCriterion;
        double t = new WeightedSelectionCriterion(
                T, weightMatrix, response, covariate).
                   weightedSelectionCriterion;
        double fpe = new WeightedSelectionCriterion(FPE, weightMatrix, response,
                covariate).weightedSelectionCriterion;
        double ns = new WeightedSelectionCriterion(nS, weightMatrix, response,
                covariate).weightedSelectionCriterion;
        double u = new WeightedSelectionCriterion(U, weightMatrix, response,
                                                  covariate).
                   weightedSelectionCriterion;
        out.println("AIC: " + dm.roundDigits(aic, 3.0) +
                    "\nGCV: " + dm.roundDigits(gcv, 3.0) +
                    "\nT  : " + dm.roundDigits(t, 3.0) +
                    "\nFPE: " + dm.roundDigits(fpe, 3.0) +
                    "\nnS : " + dm.roundDigits(ns, 3.0) +
                    "\nU  : " + dm.roundDigits(u, 3.0));

        WeightedSelectionCriterion criterion = new WeightedSelectionCriterion();
        aic = criterion.weightedSelectionCriterion(weightMatrix, response,
                covariate);
        gcv = criterion.weightedSelectionCriterion(GCV, weightMatrix, response,
                covariate);
        t = criterion.weightedSelectionCriterion(T, weightMatrix, response,
                                                 covariate);
        fpe = criterion.weightedSelectionCriterion(FPE, weightMatrix, response,
                covariate);
        ns = criterion.weightedSelectionCriterion(nS, weightMatrix, response,
                                                  covariate);
        u = criterion.weightedSelectionCriterion(U, weightMatrix, response,
                                                 covariate);
        out.println("AIC: " + dm.roundDigits(aic, 3.0) +
                    "\nGCV: " + dm.roundDigits(gcv, 3.0) +
                    "\nT  : " + dm.roundDigits(t, 3.0) +
                    "\nFPE: " + dm.roundDigits(fpe, 3.0) +
                    "\nnS : " + dm.roundDigits(ns, 3.0) +
                    "\nU  : " + dm.roundDigits(u, 3.0));

        double aicPenalty = criterion.
                            penalty(weightMatrix, response, covariate);
        double gcvPenalty = criterion.penalty(GCV, weightMatrix, response,
                                              covariate);
        double tPenalty = criterion.penalty(T, weightMatrix, response,
                                            covariate);
        double fpePenalty = criterion.penalty(FPE, weightMatrix, response,
                                              covariate);
        double nsPenalty = criterion.penalty(nS, weightMatrix, response,
                                             covariate);
        double uPenalty = criterion.penalty(U, weightMatrix, response,
                                            covariate);
        out.println("\n Penalty: ");
        out.println("AIC: " + dm.roundDigits(aicPenalty, 3.0) +
                    "\nGCV: " + dm.roundDigits(gcvPenalty, 3.0) +
                    "\nT  : " + dm.roundDigits(tPenalty, 3.0) +
                    "\nFPE: " + dm.roundDigits(fpePenalty, 3.0) +
                    "\nnS : " + dm.roundDigits(nsPenalty, 3.0) +
                    "\nU  : " + dm.roundDigits(uPenalty, 3.0));

        double wRSS = criterion.weightedRSS(weightMatrix, response, covariate);
        out.println("\n Weighted Residual Sum of Squares: " +
                    dm.roundDigits(wRSS, 3.0));

        Hashtable argument1 = new Hashtable();
        StatisticalAnalysis testclass1 = new WeightedSelectionCriterion(
                argument1, weightMatrix, response, covariate).
                                         statisticalAnalysis;
        aic = (Double) testclass1.output.get(Output.SELECTION_CRITERION);
        aicPenalty = (Double) testclass1.output.get(Output.PENALTY);
        argument1.put(SELECTION_CRITERION, GCV);
        testclass1 = new WeightedSelectionCriterion(
                argument1, weightMatrix, response, covariate).
                     statisticalAnalysis;
        gcv = (Double) testclass1.output.get(Output.SELECTION_CRITERION);
        gcvPenalty = (Double) testclass1.output.get(Output.PENALTY);
        argument1.put(SELECTION_CRITERION, T);
        testclass1 = new WeightedSelectionCriterion(
                argument1, weightMatrix, response, covariate).
                     statisticalAnalysis;
        t = (Double) testclass1.output.get(Output.SELECTION_CRITERION);
        tPenalty = (Double) testclass1.output.get(Output.PENALTY);
        argument1.put(SELECTION_CRITERION, FPE);
        testclass1 = new WeightedSelectionCriterion(
                argument1, weightMatrix, response, covariate).
                     statisticalAnalysis;
        fpe = (Double) testclass1.output.get(Output.SELECTION_CRITERION);
        fpePenalty = (Double) testclass1.output.get(Output.PENALTY);
        argument1.put(SELECTION_CRITERION, nS);
        testclass1 = new WeightedSelectionCriterion(
                argument1, weightMatrix, response, covariate).
                     statisticalAnalysis;
        ns = (Double) testclass1.output.get(Output.SELECTION_CRITERION);
        nsPenalty = (Double) testclass1.output.get(Output.PENALTY);
        argument1.put(SELECTION_CRITERION, U);
        testclass1 = new WeightedSelectionCriterion(
                argument1, weightMatrix, response, covariate).
                     statisticalAnalysis;
        u = (Double) testclass1.output.get(Output.SELECTION_CRITERION);
        uPenalty = (Double) testclass1.output.get(Output.PENALTY);

        wRSS = (Double) testclass1.output.get(Output.RSS);

        out.println("\n AIC: " + dm.roundDigits(aic, 3.0) +
                    "\nGCV: " + dm.roundDigits(gcv, 3.0) +
                    "\nT  : " + dm.roundDigits(t, 3.0) +
                    "\nFPE: " + dm.roundDigits(fpe, 3.0) +
                    "\nnS : " + dm.roundDigits(ns, 3.0) +
                    "\nU  : " + dm.roundDigits(u, 3.0));
        out.println("\n Penalty: ");
        out.println("AIC: " + dm.roundDigits(aicPenalty, 3.0) +
                    "\nGCV: " + dm.roundDigits(gcvPenalty, 3.0) +
                    "\nT  : " + dm.roundDigits(tPenalty, 3.0) +
                    "\nFPE: " + dm.roundDigits(fpePenalty, 3.0) +
                    "\nnS : " + dm.roundDigits(nsPenalty, 3.0) +
                    "\nU  : " + dm.roundDigits(uPenalty, 3.0));
        out.println("\n Weighted Residual Sum of Squares: " +
                    dm.roundDigits(wRSS, 3.0));
        out.println("\n\n" + testclass1.output.toString());

        Hashtable argument2 = new Hashtable();
        WeightedSelectionCriterion testclass2 = new WeightedSelectionCriterion(
                argument2, null);
        aic = (Double) testclass2.weightedSelectionCriterion(argument2,
                weightMatrix, response, covariate);
        aicPenalty = (Double) testclass2.penalty(argument2, weightMatrix,
                                                 response, covariate);
        argument2.put(SELECTION_CRITERION, GCV);
        gcv = (Double) testclass2.weightedSelectionCriterion(argument2,
                weightMatrix, response, covariate);
        gcvPenalty = (Double) testclass2.penalty(argument2, weightMatrix,
                                                 response, covariate);
        argument2.put(SELECTION_CRITERION, T);
        t = (Double) testclass2.weightedSelectionCriterion(argument2,
                weightMatrix, response, covariate);
        tPenalty = (Double) testclass2.penalty(argument2, weightMatrix,
                                               response, covariate);
        argument2.put(SELECTION_CRITERION, FPE);
        fpe = (Double) testclass2.weightedSelectionCriterion(argument2,
                weightMatrix, response, covariate);
        fpePenalty = (Double) testclass2.penalty(argument2, weightMatrix,
                                                 response, covariate);
        argument2.put(SELECTION_CRITERION, nS);
        ns = (Double) testclass2.weightedSelectionCriterion(argument2,
                weightMatrix, response, covariate);
        nsPenalty = (Double) testclass2.penalty(argument2, weightMatrix,
                                                response, covariate);
        argument2.put(SELECTION_CRITERION, U);
        u = (Double) testclass2.weightedSelectionCriterion(argument2,
                weightMatrix, response, covariate);
        uPenalty = (Double) testclass2.penalty(argument2, weightMatrix,
                                               response, covariate);

        wRSS = (Double) testclass2.weightedRSS(argument2, weightMatrix,
                                               response, covariate);

        out.println("AIC: " + dm.roundDigits(aic, 3.0) +
                    "\nGCV: " + dm.roundDigits(gcv, 3.0) +
                    "\nT  : " + dm.roundDigits(t, 3.0) +
                    "\nFPE: " + dm.roundDigits(fpe, 3.0) +
                    "\nnS : " + dm.roundDigits(ns, 3.0) +
                    "\nU  : " + dm.roundDigits(u, 3.0));
        out.println("\n Penalty: ");
        out.println("AIC: " + dm.roundDigits(aicPenalty, 3.0) +
                    "\nGCV: " + dm.roundDigits(gcvPenalty, 3.0) +
                    "\nT  : " + dm.roundDigits(tPenalty, 3.0) +
                    "\nFPE: " + dm.roundDigits(fpePenalty, 3.0) +
                    "\nnS : " + dm.roundDigits(nsPenalty, 3.0) +
                    "\nU  : " + dm.roundDigits(uPenalty, 3.0));
        out.println("\n Weighted Residual Sum of Squares: " +
                    dm.roundDigits(wRSS, 3.0));
        out.println("\n\n" + testclass2.output.toString());
    }

}
