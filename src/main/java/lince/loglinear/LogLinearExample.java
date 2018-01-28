package lince.loglinear;

import de.bwaldvogel.liblinear.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by deicos on 20/01/2015.
 */
public class LogLinearExample {

    public static void main(final String[] args) {
        Logger.getLogger(LogLinearExample.class).warn("llego");
        Problem prob = new Problem();
        prob.bias = -1;
        prob.l = 4;// number of training examples
        prob.n = 4; // number of features
        prob.x = new FeatureNode[4][];// feature nodes
        prob.x[0] = new FeatureNode[2];
        prob.x[1] = new FeatureNode[1];
        prob.x[2] = new FeatureNode[1];
        prob.x[3] = new FeatureNode[3];

        prob.x[0][0] = new FeatureNode(1, 1);
        prob.x[0][1] = new FeatureNode(2, 1);

        prob.x[1][0] = new FeatureNode(3, 1);
        prob.x[2][0] = new FeatureNode(3, 1);

        prob.x[3][0] = new FeatureNode(1, 2);
        prob.x[3][1] = new FeatureNode(2, 1);
        prob.x[3][2] = new FeatureNode(4, 1);

        prob.y = new double[4];// target values
        prob.y[0] = 0;
        prob.y[1] = 1;
        prob.y[2] = 1;
        prob.y[3] = 0;

        SolverType solver = SolverType.L2R_LR; // -s 0
        double C = 1.0;    // cost of constraints violation
        double eps = 0.01; // stopping criteria

        Parameter parameter = new Parameter(solver, C, eps);
        Model model = Linear.train(prob, parameter);
        File modelFile = new File("model");

        try {
            model.save(modelFile);
            // load model or use it directly
            model = Model.load(modelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Feature[] instance = { new FeatureNode(1, 4), new FeatureNode(2, 2) };
        double prediction = Linear.predict(model, instance);

    }
}
