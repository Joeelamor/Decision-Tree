
import java.util.ArrayList;

/**
 * Created by zhanglizhong on 2/18/17.
 */
public class EntropyTrainingSet extends DataSet implements Gain {

    private Double entropy;

    public double getJudgement() {
        if(this.entropy == null)
            this.calcEntropy();
        return this.entropy;
    }

    public EntropyTrainingSet(String testFileName) throws Exception {
        super(testFileName);
        this.entropy = null;
    }

    public EntropyTrainingSet(ArrayList<String> attributes, ArrayList<ArrayList<String>> data) {
        super(attributes, data);
        this.entropy = null;
    }

    public int getMaxGain() {
        double max = getGain(0);

        int maxIndex = 0;
        for(int i = 1; i < this.getData().get(0).size() - 1; i++) {
            double tmp = getGain(i);

            if(tmp > max) {
                max = tmp;
                maxIndex = i;
            }
        }
        if(max == 0) {
            return -1;
        }
        else
            return maxIndex;
    }

     private double getGain(int i) {
        return this.entropy - getSumEntropy(i);
    }

    private double getSumEntropy(int index) {
        ArrayList<Integer> preEntropy = getPreData(index, this.getData().get(0).size() - 1);

        if(preEntropy.get(0) == 0 )
            return this.entropy;
        else if (preEntropy.get(3) == 0)
            return this.entropy;
        else {
            double a = -plogp((double)preEntropy.get(1) / (double)preEntropy.get(0)) -
                    plogp((double)preEntropy.get(2) / (double)preEntropy.get(0));

            double d = -plogp((double)preEntropy.get(4) / (double)preEntropy.get(3)) -
                    plogp((double)preEntropy.get(5) / (double)preEntropy.get(3));

            return (a * (double)preEntropy.get(0) / (double)this.getData().size()) +
                    (d * (double)preEntropy.get(3) / (double)this.getData().size());
        }
    }

    private void calcEntropy(){

        ArrayList<ArrayList<String>> data = this.getData();
        int length = data.get(0).size();
        int posCount = this.getPreData(length-1, length-1).get(0);
        int negCount = data.size() - posCount;
        this.entropy = -plogp((double)posCount/(double)data.size()) -
                plogp((double)negCount/(double)data.size());

    }

    private double plogp(double value){
        if(value == 0)
            return 0;
        double a = value * (Math.log(value) / Math.log(2));
        if(!Double.isNaN(a))
            return a;
        else
            return 0;
    }

    public void removeColumn(int index){
        ArrayList<ArrayList<String>> data = this.getData();
        ArrayList<String> attributes = this.getAttributes();
        for(ArrayList<String> record: data) {
            record.remove(index);
        }

        attributes.remove(index);
    }

    public ArrayList<Gain> splitDataSet(int index) {
        ArrayList<ArrayList<String>> data = new ArrayList<>(this.getData());
        ArrayList<String> attributes = new ArrayList<String>(this.getAttributes());
        ArrayList<Gain> subDataSet = new ArrayList<>();
        ArrayList<ArrayList<String>> subData1 = new ArrayList<>();
        ArrayList<ArrayList<String>> subData2 = new ArrayList<>();
        for(ArrayList<String> record: data) {
            if(record.get(index).equals("0"))
                subData1.add(record);
            else
                subData2.add(record);
        }

        EntropyTrainingSet subDataSet1 = new EntropyTrainingSet(attributes,subData1);
        EntropyTrainingSet subDataSet2 = new EntropyTrainingSet(attributes,subData2);
        subDataSet1.removeColumn(index);
        subDataSet2.removeColumn(index);
        subDataSet.add(subDataSet1);
        subDataSet.add(subDataSet2);


        return subDataSet;
    }


}
