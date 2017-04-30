import java.util.ArrayList;

/**
 * Created by zhanglizhong on 2/19/17.
 */
public class VarianceTrainingSet extends DataSet implements Gain {

    private Double variance;

    public double getJudgement() {
        if(this.variance == null)
            this.calcVariance();
        return this.variance;
    }

    public VarianceTrainingSet(String testFileName) throws Exception {
        super(testFileName);
        this.variance = null;
    }

    public VarianceTrainingSet(ArrayList<String> attributes, ArrayList<ArrayList<String>> data) {
        super(attributes, data);
        this.variance = null;
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
        if(max == 0)
            return -1;
        else
            return maxIndex;
    }


    private double getGain(int i) {
        return this.variance - getSumVariance(i);
    }


    private void calcVariance(){
        ArrayList<ArrayList<String>> data = this.getData();
        int length = data.get(0).size();
        int posCount = this.getPreData(length-1, length-1).get(0);
        int negCount = data.size() - posCount;
        this.variance = new Double((double)(posCount * negCount) / (double)(data.size() * data.size()));
    }

    private double getSumVariance(int index) {
        ArrayList<Integer> preData = getPreData(index, this.getData().get(0).size() - 1);

        if(preData.get(0) == 0 )
            return this.variance;
        else if (preData.get(3) == 0)
            return this.variance;
        else {
            double a = (double)(preData.get(1) * preData.get(2)) / (double)(preData.get(0) * preData.get(0));

            double d = (double)(preData.get(4) * preData.get(5)) / (double)(preData.get(3) * preData.get(3));

            return (a * (double)preData.get(0) / (double)this.getData().size()) +
                    (d * (double)preData.get(3) / (double)this.getData().size());
        }
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
        ArrayList<String> attributes = this.getAttributes();
        ArrayList<Gain> subDataSet = new ArrayList<>();
        ArrayList<ArrayList<String>> subData1 = new ArrayList<>();
        ArrayList<ArrayList<String>> subData2 = new ArrayList<>();
        for(ArrayList<String> record: data) {
            if(record.get(index).equals("0"))
                subData1.add(record);
            else
                subData2.add(record);
        }

        VarianceTrainingSet subDataSet1 = new VarianceTrainingSet(attributes,subData1);
        VarianceTrainingSet subDataSet2 = new VarianceTrainingSet(attributes,subData2);
        subDataSet1.removeColumn(index);
        subDataSet2.removeColumn(index);
        subDataSet.add(subDataSet1);
        subDataSet.add(subDataSet2);

        return subDataSet;
    }
}
