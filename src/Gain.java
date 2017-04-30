import java.util.ArrayList;

/**
 * Created by zhanglizhong on 2/19/17.
 */
public interface Gain {
    abstract public ArrayList<String> getAttributes();
    abstract public int getMaxGain();
    abstract public ArrayList<Gain> splitDataSet(int index);
    abstract public ArrayList<Integer> getPreData(int a, int b);
    abstract public ArrayList<ArrayList<String>> getData();
    abstract public double getJudgement();


}
