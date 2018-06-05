/**
 * A Bunch of test methods and code to test out our printer on
 */

import java.util.LinkedList;

public class PrinterTestMethods {
    static String name = "my name is PrinterTestMethods";
    static Integer[] array;
    static {
        array = new Integer[32];
    }
    public int forTest(int a, int b){
        int total = 0;
        for (int i = a; i < b; ++i){
            total += i;
        }
        return total;
    }

    public void populateArray(){
        for (int i = 0; i < array.length; ++i){
            array[i] = i;
        }
    }

    public void doWhileTest(){
        int i = 0;
        int total = 0;
        do {
            int x = 1;
            total += x;
            ++i;
        } while (i < 10);
    }

    public void linkedListTest(){
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(1);
    }
}