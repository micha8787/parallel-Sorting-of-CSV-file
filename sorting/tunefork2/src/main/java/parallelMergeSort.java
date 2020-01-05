import java.util.concurrent.RecursiveAction;

public class  parallelMergeSort extends RecursiveAction {
    private String[][] arr;
    private String [][] temp;
    private  int leftStart;
    private int rightEnd;
    private int featureIndex;
    public parallelMergeSort(String[][] Arr,String [][] Temp,int LeftStart,int RightEnd,int FeatureIndex){
        this.arr = Arr;
        this.temp = Temp;
        leftStart = LeftStart;
        rightEnd = RightEnd;
        featureIndex = FeatureIndex;
    }
    public void compute(){
        if(leftStart >= rightEnd){
            return;
        }
        int mid = (leftStart + rightEnd)/2;

        invokeAll(new parallelMergeSort(arr,temp,leftStart, mid,featureIndex), new parallelMergeSort(arr,temp,mid + 1, rightEnd,featureIndex));
        mergeHalves(arr,temp,leftStart,mid,rightEnd,featureIndex);
    }

    public static void mergeHalves(String[][] array,
                                   String[][] aux,
                                   int start, int middle,
                                   int end,
                                   int index) {

        if (start >= end) return;

        int ls = start, le = middle, rs = middle + 1, re = end, size = end - start + 1;

        for (int i = 0; i < size; i++) {
            if (rs > re) {
                aux[i] = array[ls++];
            } else if (ls > le) {
                aux[i] = array[rs++];
            } else if (array[ls][index].compareTo(array[rs][index]) <= 0) {
                aux[i] = array[ls++];
            } else {
                aux[i] = array[rs++];
            }
        }

        System.arraycopy(aux, 0, array, start, size);


    }

}
