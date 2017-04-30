import java.util.ArrayList;

/**
 * Created by zhanglizhong on 2/19/17.
 */
public class TestingSet extends DataSet {
    private DecisionTree decisionTree;
    private Double result;

    public TestingSet(String testFileName, DecisionTree decisionTree) throws Exception {
        super(testFileName);
        this.decisionTree = decisionTree;
        this.result = null;
    }

    public double getResult() {
        if(result == null)
            this.runTest();
        return result;
    }

    private void runTest(){
        ArrayList<String> attributes = this.getAttributes();
        ArrayList<ArrayList<String>> data = this.getData();

        int pos = 0;
        for(int i = 0; i < data.size(); i++) {
            ArrayList<String> record = data.get(i);
            DecisionTreeNode curNode = this.decisionTree.getDTreeRoot();
            while(curNode.getResult() == null) {
                String curAttribute = new String(curNode.getAttribute());
                int targetIndex = 0;
                boolean found = false;
                for(int j = 0; j < attributes.size(); j++) {
                    if(attributes.get(j).equals(curAttribute)) {
                        targetIndex = j;
                        found = true;
                        break;
                    }
                }
                if(found == true) {
                    curNode = curNode.getNext(record.get(targetIndex));
                } else
                    System.err.println("Not found attribute in decision tree!.");
            }
            if(curNode.getResult().equals(record.get(record.size() - 1)))
                pos++;
        }
        this.result = new Double((double)pos/(double)data.size());
    }

    @Override
    public String toString() {
        return "TestingSet{" +
                "decisionTree=" + decisionTree +
                ", result=" + result +
                '}';
    }
}
