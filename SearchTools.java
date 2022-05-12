import java.util.Random;

public class SearchTools {

    public static void main(String[] args) {
        int[] arraySize = {100_000, 1_000_000,100_000_000,685_154_321};
        int measurements = 500;
        getTimes(arraySize,measurements);
    }

    public enum SearchAlgorithms {
        LinearSearch, BinarySearch, BinarySearchNew
    }
    public enum SearchMode{
        RandomNumber, NotInArray
    }


    //Blatt 04 Aufgabe 1d)
    //Methoden zum Zeiten messen
    public static void getTimes(int[]array, int measurements){
        for(SearchAlgorithms algorithm : SearchAlgorithms.values()){
            switch (algorithm){
                case LinearSearch:
                    System.out.println("LinearSearch, random number");
                    startMeasures(array, measurements, SearchAlgorithms.LinearSearch, SearchMode.RandomNumber);
                    System.out.println("LinearSearch, searching for -5");
                    startMeasures(array, measurements, SearchAlgorithms.LinearSearch, SearchMode.NotInArray);
                    break;
                case BinarySearch:
                    System.out.println("BinarySearch, Random number");
                    startMeasures(array, measurements, SearchAlgorithms.BinarySearch, SearchMode.RandomNumber);
                    System.out.println("BinarySearch, searching for -5");
                    startMeasures(array, measurements, SearchAlgorithms.BinarySearch, SearchMode.NotInArray);
                    break;
                case BinarySearchNew:
                    System.out.println("BinarySearchNew, Random number");
                    startMeasures(array, measurements, SearchAlgorithms.BinarySearchNew, SearchMode.NotInArray);
                    System.out.println("BinarySearchNew, searching for -5");
                    startMeasures(array, measurements, SearchAlgorithms.BinarySearchNew, SearchMode.NotInArray);
                    break;
            }
        }
    }
    
    private static void startMeasures(int[] array, int measurements, SearchAlgorithms algorithm, SearchMode searchMode) {
        int sum = 0;
        long startTime = 0;
        long endTime = 0;

        for(int j = 0; j < array.length; j++){
            int[] testArray = createSequenceInc(array[j]);
            System.out.println("Elements: " + array[j]);
            System.out.print("[");
            Random randomNumber = new Random();
            startTime = System.nanoTime();
            for(int i = 0; i < measurements; i++){
                switch (algorithm){
                    case LinearSearch:
                        switch (searchMode){
                            case RandomNumber -> linSearch(testArray, randomNumber.nextInt(1, array.length));
                            case NotInArray ->   linSearch(testArray, -5);
                        }
                        break;
                    case BinarySearch:
                        switch (searchMode){
                            case RandomNumber -> binSearch(testArray, randomNumber.nextInt(1, array.length),0, array.length - 1);
                            case NotInArray ->   binSearch(testArray, -5,0, array.length - 1);
                        }
                        break;
                    case BinarySearchNew:
                        switch (searchMode){
                            case RandomNumber -> binSearchNew(testArray, randomNumber.nextInt(1, array.length), 0, array.length - 1);
                            case NotInArray ->   binSearchNew(testArray,-5, 0, array.length - 1);
                        }
                        break;

                }
            }
            endTime = System.nanoTime();
            System.out.print("Measured Time: " + (endTime - startTime));
            System.out.println("]");
            System.out.println(" ");
        }
    }


    //Blatt 01 Aufgabe 1a)
    public static int[] createSequenceInc(int n){
        int[] incArray = new int[n];
        for(int i = 0; i < incArray.length; i++ ){
            incArray[i] = i + 1;
        }
        return incArray;
    }


    //Blatt 04 Aufgabe 1a)
    public static int linSearch(int[] A , int x){
        for(int i = 0; i < A.length; i++){
            if(A[i] == x){
                return i;
            }
        }
        return -1;
    }

    //Blatt 04 Aufgabe 1b)
    public static int binSearch(int[] A, int x, int l, int r)
    {
        if(l > r){
            return -1;
        }
        else {
            int mid = (l + r) / 2;
            if (A[mid] == x) {
                return mid;
            } else if (A[mid] > x) {
                return binSearch(A, x, l, mid - 1);
            } else {
                return binSearch(A, x, mid + 1, r);
            }
        }
    }

    //Blatt 04 Aufgabe 1c)
    public static int binSearchNew(int[] A, int x, int l, int r){
        if(l > r){
            return -1;
        }
        else {
            int mid = (l + r) / 2;
            int partSize = (r - l) / 3;
            int q1 = l + partSize;
            int q2 = r - partSize - 1;

            if (A[mid] == x) {
                return mid;
            } else if (x < A[q1]) {
                return binSearchNew(A, x, l, q1 - 1);
            } else if (x > A[q2]){
                return binSearchNew(A, x, q2 + 1, r);
            } else {
                return binSearchNew(A, x, q1, q2);
            }
        }
    }
}


/*
Gemessene Zeiten:

LinearSearch, random number

Elements: 100000
    [Measured Time: 265300]
Elements: 1000000
    [Measured Time: 708800]
Elements: 100000000
    [Measured Time: 62700]
Elements: 685154321
    [Measured Time: 97700]


LinearSearch, searching for -5

Elements: 100000
    [Measured Time: 15184100]
Elements: 1000000
    [Measured Time: 181684600]
Elements: 100000000
    [Measured Time: 13730217500]
Elements: 685154321
    [Measured Time: 92763231300]



BinarySearch, Random number

Elements: 100000
    [Measured Time: 102300]
Elements: 1000000
    [Measured Time: 60000]
Elements: 100000000
    [Measured Time: 50700]
Elements: 685154321
    [Measured Time: 52400]


BinarySearch, searching for -5

Elements: 100000
    [Measured Time: 30000]
Elements: 1000000
    [Measured Time: 56400]
Elements: 100000000
    [Measured Time: 37800]
Elements: 685154321
    [Measured Time: 25200]



BinarySearchNew, Random number

Elements: 100000
    [Measured Time: 180200]
Elements: 1000000
    [Measured Time: 34800]
Elements: 100000000
    [Measured Time: 33400]
Elements: 685154321
    [Measured Time: 36400]


BinarySearchNew, searching for -5

Elements: 100000
    [Measured Time: 45400]
Elements: 1000000
    [Measured Time: 33800]
Elements: 100000000
    [Measured Time: 34800]
Elements: 685154321
    [Measured Time: 24500]

*/
