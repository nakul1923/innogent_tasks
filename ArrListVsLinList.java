import java.util.*;

public class ArrListVsLinList {

    public static void main(String[] args){

        int[] sizes = {10000,50000,100000};

        for(int size:sizes){

            System.out.println("testing with size " + size);

            List<Integer> arrayList = new ArrayList<Integer>();

            testPerfomance(arrayList,size,"ArrayList");

            List<Integer> linkedList = new LinkedList<Integer>();
            testPerfomance(linkedList,size,"LinkedList");

        }
    }

    public static void testPerfomance(List<Integer> list, int size, String listType){

        long startTime = System.currentTimeMillis();                  // it gives the current time in milliseconds from 1 jan 1970

        for(int i=0;i<size;i++){

            list.add(i);
        }

        long endTime = System.currentTimeMillis();

        long insertionTime = endTime-startTime;


        Iterator itr = list.iterator();

        long startDelete = System.currentTimeMillis();

        while(itr.hasNext()){

            itr.next();
            itr.remove();

        }

        long endDelete = System.currentTimeMillis();

        long deletionTime = endDelete - startDelete;

        System.out.println("ListType: " + listType + "         Insertion Time :  " + insertionTime + "          Deletion Time:  " + deletionTime);
    }
}