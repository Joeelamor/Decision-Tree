import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zhanglizhong on 2/18/17.
 */
public class DataSet{
    private ArrayList<String> attributes;
    private ArrayList<ArrayList<String>> data;

    public DataSet() {
        this.data = null;
        this.attributes = null;
    }


    public DataSet(ArrayList<String> attributes, ArrayList<ArrayList<String>> data) {
        this.attributes = new ArrayList<>();
        this.data = new ArrayList<>();
        for(String it : attributes) {
            String temp = new String(it + "");
            this.attributes.add(temp);
        }
        for (ArrayList<String> it1 : data) {
            ArrayList<String> arrayTemp = new ArrayList<>();
            for (String strIt : it1) {
                String temp = new String(strIt + "");
                arrayTemp.add(temp);
            }
            this.data.add(arrayTemp);
        }
    }

    public DataSet(String testFileName) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(testFileName));
        String eachLine = br.readLine();

        String [] features = eachLine.split(",");
        int numFeatures = features.length - 1;

        this.attributes = new ArrayList<>();
        this.data = new ArrayList<>();

        int i = 0;
        for(String s: features){
            attributes.add(s);
            i++;
        }

        attributes.remove(numFeatures);
        while(eachLine != null){
            eachLine = br.readLine();
            ArrayList<String> record = new ArrayList<String>();
            i = 0;
            if(eachLine != null) {
                for (String t : eachLine.split(",")) {
                    record.add(t);
                    i++;
                }
                this.data.add(record);
            }
        }
        br.close();
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public ArrayList<Integer> getPreData(int a, int b){
        ArrayList<ArrayList<String>> data = this.getData();
        ArrayList<Integer> result = new ArrayList<>(Arrays.asList(0,0,0,0,0,0));

        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).get(a).equals("1")) {
                result.set(0, result.get(0) + 1);
                if(data.get(i).get(b).equals("1"))
                    result.set(1, result.get(1) + 1);
                else
                    result.set(2, result.get(2) + 1);
            }
            else {
                result.set(3, result.get(3) + 1);
                if(data.get(i).get(b).equals("1"))
                    result.set(4, result.get(4) + 1);
                else
                    result.set(5, result.get(5) + 1);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "attributes=" + attributes + "\n" +
                ", data=" + data +  "\n" +
                '}';
    }


}


