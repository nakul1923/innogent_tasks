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

//        long startTime = System.currentTimeMillis();                  // it gives the current time in milliseconds from 1 jan 1970
          long startTime = System.nanoTime();
//        System.out.println(startTime);
        for(int i=0;i<size;i++){

            list.add(i);
        }

//        long endTime = System.currentTimeMillis();
          long endTime = System.nanoTime();
//        System.out.println(endTime);
        long insertionTime = (endTime-startTime) / 1000000;


        Iterator itr = list.iterator();

//        long startDelete = System.currentTimeMillis();
          long startDelete = System.nanoTime();
        System.out.println(startDelete);
        while(itr.hasNext()){

            itr.next();
            itr.remove();

        }

//        for(int i=0; i<size;i++){
//
//            list.remove(0);
////        }

//        long endDelete = System.currentTimeMillis();
          long endDelete = System.nanoTime();
        System.out.println(endDelete);
        long deletionTime = (endDelete - startDelete) / 1000000;

        System.out.println("ListType: " + listType + "         Insertion Time :  " + insertionTime + "ms          Deletion Time:  " + deletionTime + "ms");
    }
}