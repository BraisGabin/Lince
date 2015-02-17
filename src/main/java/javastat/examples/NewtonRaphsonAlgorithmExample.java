package javastat.examples;

import static java.lang.System.out;

import javastat.algorithm.*;
import javastat.util.*;

/**
 *
 * Example: class NewtonRaphsonAlgorithm.
 * Object equations:
 *     7x^3-10x-y-1=0
 *     8y^3-11y+x-1=0
 */

public class NewtonRaphsonAlgorithmExample extends NewtonRaphsonAlgorithm
{
    public NewtonRaphsonAlgorithmExample(){}

    public double[] objectFunctionVector(double[] s)
    {
        return new double[]{
                7 * Math.pow(s[0], 3.0) - 10 * s[0] - s[1] - 1.0,
                8 * Math.pow(s[1], 3.0) - 11 * s[1] + s[0] - 1.0};
    }

    public double[][] firstDerivativeMatrix(double[] s)
    {
        return new double[][]{
                {21 * Math.pow(s[0], 2.0) - 10.0, -1.0},
                {1.0, 24 * Math.pow(s[1], 2.0) - 11.0}};
    }

    public static void main(String arg[])
    {
        NewtonRaphsonAlgorithmExample example =
            new NewtonRaphsonAlgorithmExample();
        DataManager dm = new DataManager();

        double[][] start = {{1.0, 0.0}, {1.0, 1.0}, {1.0, -1.0}};
        double[] solution1 = example.getSolution(start[0]);
        double[] solution2 = example.getSolution(start[1]);
        double[] solution3 = example.getSolution(start[2]);
        out.println("Solution: " + dm.roundDigits(solution1[0], 5.0) + "  " +
                    dm.roundDigits(solution1[1], 5.0));
        out.println("Solution: " + dm.roundDigits(solution2[0], 5.0) + "  " +
                    dm.roundDigits(solution2[1], 5.0));
        out.println("Solution: " + dm.roundDigits(solution3[0], 5.0) + "  " +
                    dm.roundDigits(solution3[1], 5.0));
    }

}
