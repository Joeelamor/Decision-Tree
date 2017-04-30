import java.util.ArrayList;

/**
 * Created by zhanglizhong on 2/19/17.
 */

public class DecisionTreeNode {
    private String result; // Currently 0 or 1, indicating leaf content.
    private String attribute; // attribute name.
    private int depth;
    private DecisionTreeNode left;
    private DecisionTreeNode right;
    private DecisionTreeNode parent;
    private Gain dataSet;

    public DecisionTreeNode(Gain dataSet, DecisionTreeNode parent) {
        this.dataSet = dataSet;
        if(dataSet.getAttributes().size() == 0) {
            this.attribute = null;
            this.left = null;
            this.right = null;
            this.parent = parent;

            ArrayList<Integer> preEntropy = dataSet.getPreData(0, 0);

            this.result = preEntropy.get(0) > preEntropy.get(3) ? "1" : "0";

            this.depth = parent == null ? 0 : parent.depth + 1;
        }
        else {
            this.result = null;

            double gain;
            int currentSize = dataSet.getData().get(0).size();
            double judgement = dataSet.getJudgement();

            if (judgement == 0) {
                this.result = dataSet.getData().get(0).get(currentSize - 1);
                this.attribute = null;
                this.left = null;
                this.right = null;
                this.depth = parent == null ? 0 : parent.depth + 1;
                this.parent = parent;
            }
            else {
                int bestAttrIndex = dataSet.getMaxGain();
                if(bestAttrIndex == -1){
                    this.attribute = null;
                    this.left = null;
                    this.right = null;

                    ArrayList<Integer> preEntropy = dataSet.getPreData(currentSize - 1, currentSize - 1);

                    this.result = preEntropy.get(0) > preEntropy.get(3) ? "1" : "0";
                    this.depth = parent == null ? 0 : parent.depth + 1;
                    this.parent = parent;
                } else {
                    ArrayList<Gain> subDataSets = dataSet.splitDataSet(bestAttrIndex);

                    this.depth = parent == null ? 0 : parent.depth + 1;
                    this.parent = parent;
                    this.result = null;
                    this.attribute = dataSet.getAttributes().get(bestAttrIndex);
                    this.left = new DecisionTreeNode(subDataSets.get(0), this);
                    this.right = new DecisionTreeNode(subDataSets.get(1), this);

                }
            }
        }
    }

    public DecisionTreeNode getNext(String result){
        if( result.equals("0") )
            return left;
        else
            return right;
    }

    public String getResult() {
        return result;
    }

    public String getAttribute() {
        return attribute;
    }

    public int getDepth() {
        return depth;
    }

    public DecisionTreeNode getLeft() {
        return left;
    }

    public DecisionTreeNode getRight() {
        return right;
    }

    public DecisionTreeNode getParent() {
        return parent;
    }

    public Gain getDataSet() {
        return dataSet;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setLeft(DecisionTreeNode left) {
        this.left = left;
    }

    public void setRight(DecisionTreeNode right) {
        this.right = right;
    }

    public void setParent(DecisionTreeNode parent) {
        this.parent = parent;
    }

    public void setDataSet(Gain dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public String toString() {
        String vert = new String();
        for(int i = 1; i <= depth; i++)
            vert = vert + "| ";
        String result = new String();

        if(this.attribute == null) {
            result = result + this.result;
        } else {
            if(this.left.attribute == null) {
                result = result + vert + attribute + "= 0 : " + left + "\n";
            } else {
                result = result + vert + attribute + "= 0 : \n" + left + "\n";
            }
            if(this.right.attribute == null) {
                result = result + vert + attribute + "= 1 : " + right;
            } else {
                result = result + vert + attribute + "= 1 : \n" + right ;
            }
        }
        return result;
    }
}