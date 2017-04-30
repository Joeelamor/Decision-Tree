import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zhanglizhong on 2/19/17.
 */
public class PrunDecisionTree{

    private DecisionTreeNode postPruningDtreeRoot;
    private String validationFileName;
    private int L;
    private int K;
    private double validationResult;

    public PrunDecisionTree(Gain trainingDataSet, String validationFileName, int L, int K) throws Exception {
        this.validationFileName = validationFileName;
        this.L = L;
        this.K = K;
        this.validationResult = 0;
        this.getPruningRoot(trainingDataSet);
    }

    private void getPruningRoot(Gain trainingDataSet) throws Exception {
        Gain g = new EntropyTrainingSet(trainingDataSet.getAttributes(), trainingDataSet.getData());
        DecisionTreeNode D = new DecisionTreeNode(g, null);

        this.postPruningDtreeRoot = D;

        for (int i = 1; i <= this.L; i++) {
            Gain gp = new EntropyTrainingSet(trainingDataSet.getAttributes(), trainingDataSet.getData());
            DecisionTreeNode Dprime = new DecisionTreeNode(gp, null);
            Random rand = new Random();
            int  m = rand.nextInt(K) + 1;

            for (int j = 1; j <= m; j++) {
                ArrayDeque<DecisionTreeNode> queue = new ArrayDeque<>();
                ArrayList<DecisionTreeNode> nonLeafNodes = new ArrayList<>();
                DecisionTreeNode curNode = Dprime;

                queue.add(curNode);

                while(queue.size() != 0) {
                    curNode = queue.pop();
                    if (curNode.getResult() == null) {
                        queue.add(curNode.getLeft());
                        queue.add(curNode.getRight());
                        nonLeafNodes.add(curNode);
                    }
                }
                int n = nonLeafNodes.size();
                if( n == 0) {
                    continue;
                }
                int p = rand.nextInt(n);

                curNode = nonLeafNodes.get(p);
                Gain curDataSet = curNode.getDataSet();
                int a, b;
                a = b = curDataSet.getData().get(0).size() - 1;

                ArrayList<Integer> preData = curDataSet.getPreData(a, b);
                curNode.setResult(preData.get(0) > preData.get(3) ? "1" : "0");
                curNode.setAttribute(null);
                curNode.setLeft(null);
                curNode.setRight(null);
            }
            DecisionTree pre = new DecisionTree(this.postPruningDtreeRoot, trainingDataSet);
            DecisionTree post = new DecisionTree(Dprime, trainingDataSet);

            TestingSet preTest = new TestingSet(this.validationFileName, pre);

            TestingSet postTest = new TestingSet(this.validationFileName, post);

            double preResult = preTest.getResult();
            double postResult = postTest.getResult();
            if(preResult < postResult) {
                this.postPruningDtreeRoot = Dprime;
                this.validationResult = postResult;
            }
        }
    }

    public double getValidationResult() {
        return validationResult;
    }

    public DecisionTreeNode getPostPruningDtreeRoot() {
        return postPruningDtreeRoot;
    }

    @Override
    public String toString() {
        return "" + postPruningDtreeRoot;
    }
}
