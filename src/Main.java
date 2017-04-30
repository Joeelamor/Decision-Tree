/**
 * Created by zhanglizhong on 2/18/17.
 */
public class Main {
    public static void main(String[] args) throws Exception{

        if(args.length!= 6){
            System.out.println("Enter input as .\\program <L> <K> <training-set> <validation-set> <test-set> <to-print>");
            return;
        }

        int l = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        String trainingfile = args[2];
        String validationfile = args[3];
        String testfile = args[4];
        String toprint = args[5];

        Gain entropyTrainingSet = new EntropyTrainingSet(trainingfile);
        DecisionTree decisionTreeEntropy1 = new DecisionTree(entropyTrainingSet);
        TestingSet testSetEntropy = new TestingSet(testfile, decisionTreeEntropy1);
        System.out.print("Accuracy for Information Gain Heuristic: ");
        System.out.println(testSetEntropy.getResult());
        System.out.println();

        Gain varianceTrainingSet = new VarianceTrainingSet(trainingfile);
        DecisionTree decisionTreeVariance1 = new DecisionTree(varianceTrainingSet);
        TestingSet testSetVariance = new TestingSet(testfile, decisionTreeVariance1);
        System.out.print("Accuracy for Variance Impurity Heuristic: ");
        System.out.println(testSetVariance.getResult());
        System.out.println();

        PrunDecisionTree prunDecisionTree1 = new PrunDecisionTree(entropyTrainingSet, validationfile, l, k);
        System.out.print("Accuracy for Information Gain Heuristic after Post-Pruning: ");
        System.out.println(prunDecisionTree1.getValidationResult());
        System.out.println();

        PrunDecisionTree prunDecisionTree2 = new PrunDecisionTree(varianceTrainingSet, validationfile, l, k);
        System.out.print("Accuracy for Variance Impurity Heuristic after Post-Pruning: ");
        System.out.println(prunDecisionTree2.getValidationResult());
        System.out.println();

        if(toprint.equals("yes")) {
            System.out.println("Decision Tree for Information Gain Heuristic: ");
            System.out.println(decisionTreeEntropy1);
            System.out.println();
            System.out.println("Decision Tree for Variance Impurity Heuristic: ");
            System.out.println(decisionTreeVariance1);
            System.out.println();
            System.out.println("Decision Tree for Information Gain Heuristic after Post-Pruning:: ");
            System.out.println(prunDecisionTree1);
            System.out.println();
            System.out.println("Decision Tree for Variance Impurity Heuristic after Post-Pruning:: ");
            System.out.println(prunDecisionTree2);
        }
    }
}
