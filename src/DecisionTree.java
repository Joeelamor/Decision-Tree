/**
 * Created by zhanglizhong on 2/17/17.
 */
public class DecisionTree {
    private DecisionTreeNode DTreeRoot;
    private Gain trainingDataSet;

    public DecisionTree(DecisionTreeNode DTreeRoot, Gain trainingDataSet) {
        this.DTreeRoot = DTreeRoot;
        this.trainingDataSet = trainingDataSet;
    }

    public DecisionTree(Gain trainingDataSet) {
        this.trainingDataSet = trainingDataSet;
        this.getOriRoot(trainingDataSet);
    }

    private void getOriRoot(Gain trainingDataSet) {
        this.DTreeRoot = new DecisionTreeNode(trainingDataSet, null);
    }

    public DecisionTreeNode getDTreeRoot() {
        return DTreeRoot;
    }

    public Gain getTrainingDataSet() {
        return trainingDataSet;
    }

    @Override
    public String toString() {
        return "" + DTreeRoot;
    }
}
