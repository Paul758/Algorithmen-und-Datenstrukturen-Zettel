import java.util.Random;

public class SortTools {

        public static void main(String[] args) {

            int[] elementsToSort = {100,1_000,10_000, 100_000, 200_000};
            int measurements = 10;
            getTimes(elementsToSort, measurements);
        }

        public enum SortAlgorithms{
            QuickSort, QuickSortRandom, QuickSortNewRandom, QuickSortTriRandom, QuickSortTriNewRandom
        }

        public enum ArrayType{
            Incline,Decline,Random
        }

        //Methoden zum Zeiten messen
        public static void getTimes(int[]elementsToSort, int measurements){
            for(SortAlgorithms algorithm : SortTools.SortAlgorithms.values()){
                switch (algorithm){
                    case QuickSort:
                        System.out.println("QuickSort:");
                        startMeasures(elementsToSort, measurements, SortTools.SortAlgorithms.QuickSort);
                        break;
                    case QuickSortRandom:
                        System.out.println("QuickSortRandom:");
                        startMeasures(elementsToSort, measurements, SortTools.SortAlgorithms.QuickSortRandom);
                        break;
                    case QuickSortNewRandom:
                        System.out.println("QuickSortNewRandom:");
                        startMeasures(elementsToSort, measurements, SortTools.SortAlgorithms.QuickSortNewRandom);
                        break;
                    case QuickSortTriRandom:
                        System.out.println("QuickSortTriRandom:");
                        startMeasures(elementsToSort, measurements, SortTools.SortAlgorithms.QuickSortTriRandom);
                        break;
                    case QuickSortTriNewRandom:
                        System.out.println("QuickSortTriNewRandom:");
                        startMeasures(elementsToSort, measurements, SortTools.SortAlgorithms.QuickSortTriNewRandom);
                        break;
                }
            }
        }
        private static void startMeasures(int[] elementsToSort, int measurements, SortTools.SortAlgorithms algorithm) {
            long sum = 0;
            long startTime = 0;
            long endTime = 0;
            int[] testArray = new int[0];
            for(SortTools.ArrayType arrayType : SortTools.ArrayType.values()){
                System.out.println("ArrayType: " + arrayType);
                for(int j = 0; j < elementsToSort.length; j++){
                    System.out.println("Elements: " + elementsToSort[j]);
                    System.out.print("[");
                    sum = 0;
                    for(int i = 0; i < measurements; i++){
                        switch (arrayType){
                            case Incline ->  testArray = createSequenceInc(elementsToSort[j]);
                            case Decline ->  testArray = createSequenceDec(elementsToSort[j]);
                            case Random ->   testArray = createSequenceRand(elementsToSort[j]);
                        }
                        //int[] testArray = createSequenceDec(elementsToSort[j]);
                        startTime = System.nanoTime();
                        switch (algorithm){
                            case QuickSort -> quickSort(testArray, 0, testArray.length - 1);
                            case QuickSortRandom ->  quickSortRandom(testArray, 0, testArray.length - 1);
                            case QuickSortNewRandom ->   quickSortNewRandom(testArray,0, testArray.length - 1);
                            case QuickSortTriRandom ->   quickSortTriRandom(testArray,0, testArray.length - 1);
                            case QuickSortTriNewRandom ->   quickSortTriNewRandom(testArray,0, testArray.length - 1);
                        }
                        endTime = System.nanoTime();
                        sum += endTime - startTime;
                        System.out.print((endTime - startTime) + ", ");
                    }
                    System.out.println("]");
                    System.out.println("Average time: " + (sum / measurements));
                    System.out.println(" ");
                }
            }

        }


        //Aufgabe 1a)
        public static int[] quickSort(int[] array, int left, int right){
            if(left < right){
                int pivot = partitionLeft(array, left, right, left);
                quickSort(array, left, pivot - 1);
                quickSort(array, pivot + 1, right);
            }
            return array;
        }

        public static int[] quickSortRandom(int[] array, int left, int right){
            if(left < right){
                Random randomNumber = new Random();
                int n = randomNumber.nextInt(right - left) + left;
                int middle = partitionLeft(array,left,right,n);
                quickSortRandom(array, left, middle - 1);
                quickSortRandom(array, middle + 1, right);
            }
            return array;
        }

        public static int[] quickSortNewRandom(int[] array, int left, int right){
            if(left < right){
                //3 Zufallszahlen bestimmen
                Random randomNumber = new Random();
                int n = randomNumber.nextInt(right - left) + left;
                int m = randomNumber.nextInt(right - left) + left;
                int p = randomNumber.nextInt(right - left) + left;
                //Mittleren Wert berechnen
                int middleValue = checkForMiddle(n,m,p);

                int pivot = partitionLeft(array,left,right, middleValue);
                quickSortNewRandom(array, left, pivot - 1);
                quickSortNewRandom(array, pivot + 1, right);
            }
            return array;
        }

        //Hilfsmethoden
        private static int partitionLeft(int[] array, int left, int right, int pivot){
            swap(array,left,pivot);
            int x = array[left];
            int i = right + 1;
            for(int j = right; j > left; j--){
                if(array[j] > x){
                    i--;
                    swap(array, i, j);
                }
            }
            swap(array, i - 1, left);
            i = i - 1;
            return i;
        }
        private static void swap(int[] array, int i, int j) {
            int buffer = array[i];
            array[i] = array[j];
            array[j] = buffer;

        }
        private static int checkForMiddle(int n, int m, int p) {
            if(n >= m && n <= p){
                return n;
            } else if(n >= m) {
                return checkForMiddle(m,p,n);
            } else {
                return checkForMiddle(m,n,p);
            }

        }

        //Aufgabe 1b)
        public static int[] quickSortTriRandom(int[] array, int left, int right){
            if(left < right){
                //Zufallszahlen bestimmen
                Random randomNumber = new Random();
                int[] pivots = new int[2];
                pivots[0] = randomNumber.nextInt(left, right + 1);
                pivots[1] = randomNumber.nextInt(left, right + 1);
                //Partitionierung
                int[] q = partitionTriRandom(array, left, right, pivots);
                quickSortTriRandom(array, left, q[0] - 1);
                quickSortTriRandom(array, q[0] + 1, q[1] - 1);
                quickSortTriRandom(array, q[1] + 1, right);
            }
            return array;
        }
        public static int[] partitionTriRandom(int[] array, int left, int right, int[] pivots){
            int[] q = new int[2];
            //Fallunterscheidung, um zu gewährleisten, dass Pivot noch an der richtigen Stelle steht
            if(array[pivots[0]] == array[pivots[1]]){
                swap(array, right, pivots[0]);
                q[0] = partition(array, left, right);
                q[1] = q[0];
                return q;
            } else if(array[pivots[0]] < array[pivots[1]]) {
                swap(array, right - 1, pivots[0]);
                swap(array, right, pivots[1]);
            } else {
                swap(array, right - 1, pivots[1]);
                swap(array, right, pivots[0]);
            }
            //Größeres Pivotelement steht ganz rechts, Sortierung daher zuerst nur von left bis zum kleineren Pivotelement
            //Index von größerem Pivotelement ist dann unverändert und kann sortiert werden
            q[0] = partition(array, left, right - 1);
            q[1] = partition(array, left , right);
            return q;
        }

        //QuicksortTriNewRandom
        public static int[] quickSortTriNewRandom(int[] array, int left, int right){
            if(left < right){

                //Zufallszahlen bestimmen
                Random randomNumber = new Random();
                int[] pivots = new int[5];
                for(int i = 0; i < 5; i++){
                    pivots[i] = array[randomNumber.nextInt(left, right + 1)];
                }

                //selectRank Methode zum bestimmen des Rangs
                int[] rankElements = new int[2];
                //Bestimme Rang 2
                rankElements[0] = selectRank(2,pivots,0,pivots.length - 1);
                //Bestimme Rang 4
                rankElements[1] = selectRank(4, pivots,0,pivots.length - 1);

                //Index der Rangelemente ermitteln
                int[] indexOfRankElements = new int[2];
                indexOfRankElements[0] = getIndex(array,left,right,rankElements[0]);
                indexOfRankElements[1] = getIndex(array,left,right,rankElements[0]);

                //Partitionierung
                int[] q;
                q = partitionTriRandom(array,left,right, indexOfRankElements);

                quickSortTriNewRandom(array, left, q[0] - 1);
                quickSortTriNewRandom(array, q[0] + 1, q[1] - 1);
                quickSortTriNewRandom(array, q[1] + 1, right);
            }
            return array;
        }

        public static int partition(int[] array, int left, int right){
            int x = array[right];
            int i = left - 1;
            for(int j = left; j < right; j++){
                if(array[j] <= x){
                    i++;
                    swap(array, i, j);
                }
            }
            swap(array, i + 1, right);
            i = i + 1;
            return i;
        }

        private static int selectRank(int rank, int[] pivots, int left, int right) {
            int currentPivot = partition(pivots,left,right);

            if(currentPivot == rank - 1){
                return pivots[currentPivot];
            } else if (currentPivot < rank - 1){
                return selectRank(rank, pivots, currentPivot + 1, right);
            } else {
                return selectRank(rank, pivots, left, currentPivot - 1);
            }
        }

        private static int getIndex(int[] array, int left, int right, int rankElement) {
            for(int i = left; i <= right;i++){
                if(array[i] == rankElement){
                    return i;
                }
            }
            return -1;
        }


    //Methoden zum Erstellen von Testarrays
    public static int[] createSequenceDec(int n){
        int[] decArray = new int[n];
        for(int i = 0; i < decArray.length; i++){
            decArray[i] = decArray.length - i;
        }
        return decArray;
    }

    public static int[] createSequenceInc(int n){
        int[] incArray = new int[n];
        for(int i = 0; i < incArray.length; i++ ){
            incArray[i] = i + 1;
        }
        return incArray;
    }

    public static int[] createSequenceRand(int n){
        int[] randArray = new int[n];
        Random randomNumber = new Random();
        for(int i = 0; i < randArray.length; i++){
            randArray[i] = randomNumber.nextInt(1, n + 1);
        }
        return randArray;
    }

}
/*      QuickSort:
        ArrayType: Incline
        Elements: 100
        [200300, 162800, 72900, 35400, 38000, 35300, 29200, 28700, 38300, 26400, ]
        Average time: 66730

        Elements: 1000
        [2442900, 166800, 158700, 158300, 156900, 157300, 156500, 155700, 155500, 157800, ]
        Average time: 386640

        Elements: 10000
        [16093900, 17231900, 17230100, 17158400, 18202700, 20902300, 17430700, 17337100, 17336400, 17013500, ]
        Average time: 17593700

        Elements: 100000
        [1713269000, 1695021900, 1685342100, 1685215100, 1684200500, 1684913800, 1684574200, 1684530200, 1683998000, 1683495100, ]
        Average time: 1688455990

        Elements: 200000
        [6780414100, 6771268000, 6766288400, 6767244600, 6770939300, 6772700900, 6771620600, 6773091100, 6774231900, 6772610500, ]
        Average time: 6772040940


        ArrayType: Decline
        Elements: 100
        [650000, 90700, 90000, 90100, 89400, 101200, 96900, 106400, 14100, 42300, ]
        Average time: 137110

        Elements: 1000
        [1716000, 1738900, 1841800, 602900, 359900, 360100, 362700, 359100, 362700, 359200, ]
        Average time: 806330

        Elements: 10000
        [38881900, 39387000, 39271200, 39343300, 39260500, 39761900, 39401000, 39488900, 39412700, 39436200, ]
        Average time: 39364460

        Elements: 100000
        [3919731900, 3921343900, 3919791400, 3919079000, 3920072900, 3918798500, 3919640700, 3922202400, 3919525700, 3919136800, ]
        Average time: 3919932320

        Elements: 200000
        [15762649100, 15710013300, 15690877900, 15692980800, 15697745600, 15697962300, 15702376400, 15746580000, 15695154300, 15696493600, ]
        Average time: 15709283330


        ArrayType: Random
        Elements: 100
        [12100, 3800, 3600, 3800, 3500, 3400, 3500, 3400, 3400, 3600, ]
        Average time: 4410

        Elements: 1000
        [44000, 42400, 49600, 42500, 56300, 42200, 42400, 40800, 41500, 42000, ]
        Average time: 44370

        Elements: 10000
        [531300, 537400, 542000, 534500, 531200, 535900, 528600, 536500, 531000, 527400, ]
        Average time: 533580

        Elements: 100000
        [6454200, 6517500, 6487000, 6431200, 6447800, 6490200, 6471100, 6474600, 6432000, 6482000, ]
        Average time: 6468760

        Elements: 200000
        [13614400, 13627000, 13988700, 13670500, 13945300, 13762000, 13746300, 13754500, 14653500, 13743600, ]
        Average time: 13850580



        QuickSortRandom:
        ArrayType: Incline
        Elements: 100
        [43200, 39000, 31000, 35200, 30100, 68600, 25400, 32900, 28600, 31900, ]
        Average time: 36590

        Elements: 1000
        [223600, 134700, 101400, 77400, 73000, 70500, 73500, 73400, 73200, 72200, ]
        Average time: 97290

        Elements: 10000
        [741800, 809900, 875900, 734900, 684200, 677800, 665200, 653300, 741600, 699200, ]
        Average time: 728380

        Elements: 100000
        [7126200, 6482400, 6105000, 5895500, 6187400, 5981300, 6071500, 5973600, 6039100, 6218000, ]
        Average time: 6208000

        Elements: 200000
        [12334200, 12222300, 12638800, 12604800, 12808900, 12351800, 12123300, 12391400, 12383600, 12659400, ]
        Average time: 12451850


        ArrayType: Decline
        Elements: 100
        [6400, 6600, 5500, 5300, 5400, 5500, 7100, 4900, 6700, 5000, ]
        Average time: 5840

        Elements: 1000
        [63000, 59800, 60800, 60500, 59300, 57600, 61500, 62400, 59700, 59600, ]
        Average time: 60420

        Elements: 10000
        [632300, 630200, 629700, 654500, 638400, 634300, 639400, 633300, 628300, 620900, ]
        Average time: 634130

        Elements: 100000
        [6947200, 6781400, 6997700, 6982800, 6900100, 6680400, 7086000, 7044900, 6864700, 6708800, ]
        Average time: 6899400

        Elements: 200000
        [13665000, 13959500, 13710700, 13835100, 13609000, 13669100, 13684800, 14081300, 13872100, 13950100, ]
        Average time: 13803670


        ArrayType: Random
        Elements: 100
        [8400, 6200, 7700, 5800, 7600, 5800, 7600, 5700, 7800, 5700, ]
        Average time: 6830

        Elements: 1000
        [78600, 76900, 81300, 79300, 77100, 78600, 77900, 82200, 85600, 79000, ]
        Average time: 79650

        Elements: 10000
        [901500, 889100, 895000, 901000, 910100, 908000, 917300, 901300, 902200, 896700, ]
        Average time: 902220

        Elements: 100000
        [10379600, 10341900, 10391800, 10429200, 9609300, 9531500, 9624000, 9604700, 9623200, 9553100, ]
        Average time: 9908830

        Elements: 200000
        [20141300, 20657100, 20070500, 20084700, 19872200, 19675000, 20087900, 20004100, 20313900, 19801400, ]
        Average time: 20070810



        QuickSortNewRandom:
        ArrayType: Incline
        Elements: 100
        [25400, 16900, 24600, 16400, 16300, 16600, 18500, 18300, 17700, 16600, ]
        Average time: 18730

        Elements: 1000
        [165100, 103600, 69600, 72600, 64200, 64200, 67200, 64300, 64300, 63300, ]
        Average time: 79840

        Elements: 10000
        [671700, 665000, 666200, 665000, 669100, 750400, 669200, 694500, 674200, 666200, ]
        Average time: 679150

        Elements: 100000
        [6969800, 7078100, 6924100, 6573500, 6549100, 6523500, 6627900, 6572200, 6570700, 6475400, ]
        Average time: 6686430

        Elements: 200000
        [13297200, 13301200, 13255600, 13259900, 13384500, 13274100, 13447900, 13354100, 13355100, 13340300, ]
        Average time: 13326990


        ArrayType: Decline
        Elements: 100
        [7400, 6600, 6700, 6700, 6800, 6600, 6500, 6200, 6800, 6100, ]
        Average time: 6640

        Elements: 1000
        [67900, 67400, 66900, 65600, 67600, 66700, 66300, 66200, 66400, 67000, ]
        Average time: 66800

        Elements: 10000
        [699700, 721100, 702900, 703200, 715400, 703100, 700600, 706300, 694600, 702800, ]
        Average time: 704970

        Elements: 100000
        [7500800, 7453300, 7484200, 7389700, 7397600, 7403900, 7475500, 7422600, 7378500, 7460400, ]
        Average time: 7436650

        Elements: 200000
        [15022400, 15249100, 15233700, 16374200, 15148500, 15166400, 15174000, 15112600, 15103900, 15070300, ]
        Average time: 15265510


        ArrayType: Random
        Elements: 100
        [7900, 7500, 7300, 7900, 7300, 7600, 7400, 7700, 7200, 7500, ]
        Average time: 7530

        Elements: 1000
        [87600, 89500, 86900, 85000, 85900, 85200, 85300, 84800, 85400, 85500, ]
        Average time: 86110

        Elements: 10000
        [984500, 1047300, 980900, 986600, 997900, 987500, 988700, 1048500, 990300, 1015500, ]
        Average time: 1002770

        Elements: 100000
        [11182000, 11100300, 11390100, 11337900, 11126800, 11165100, 11331700, 11470500, 11275900, 11103000, ]
        Average time: 11248330

        Elements: 200000
        [23385100, 23410400, 23070700, 23068400, 22897800, 23244500, 23322300, 23247600, 23093500, 23004900, ]
        Average time: 23174520



        QuickSortTriRandom:
        ArrayType: Incline
        Elements: 100
        [151400, 62100, 55000, 60800, 55200, 44300, 57600, 39100, 59300, 40200, ]
        Average time: 62500

        Elements: 1000
        [328000, 246400, 148300, 125000, 132400, 117200, 119000, 121900, 122900, 131500, ]
        Average time: 159260

        Elements: 10000
        [1423300, 889400, 834200, 755800, 764300, 825500, 790900, 751700, 790900, 795800, ]
        Average time: 862180

        Elements: 100000
        [7966000, 7794300, 7859900, 7826000, 7566200, 7228700, 7227400, 7246400, 7081700, 8648200, ]
        Average time: 7644480

        Elements: 200000
        [14374800, 14601800, 14720700, 14896900, 14661600, 14805100, 15049200, 14701300, 15006400, 14767400, ]
        Average time: 14758520


        ArrayType: Decline
        Elements: 100
        [7000, 6400, 6100, 5800, 6400, 5900, 5900, 6500, 5900, 6100, ]
        Average time: 6200

        Elements: 1000
        [64900, 58400, 62300, 59300, 72500, 63900, 85200, 61200, 60900, 61400, ]
        Average time: 65000

        Elements: 10000
        [636600, 655700, 644900, 641300, 620800, 640000, 645000, 635700, 682000, 636800, ]
        Average time: 643880

        Elements: 100000
        [6846800, 6886600, 6836800, 7500000, 6792300, 6748700, 6684700, 6802100, 6699900, 6773800, ]
        Average time: 6857170

        Elements: 200000
        [14234800, 13688500, 13758900, 13860100, 16039400, 13852000, 13993600, 13822100, 13708300, 14079000, ]
        Average time: 14103670


        ArrayType: Random
        Elements: 100
        [7000, 6600, 6400, 6600, 7100, 6700, 6300, 6500, 6900, 6100, ]
        Average time: 6620

        Elements: 1000
        [75500, 79900, 79000, 76700, 74500, 75000, 74600, 75500, 72500, 77300, ]
        Average time: 76050

        Elements: 10000
        [877200, 966100, 880600, 880100, 864800, 862300, 872100, 875500, 948600, 977500, ]
        Average time: 900480

        Elements: 100000
        [10227000, 11624900, 10057600, 9956400, 9999400, 10012800, 10014300, 10068200, 10039500, 10026100, ]
        Average time: 10202620

        Elements: 200000
        [20955200, 20921200, 20631400, 21215800, 20439700, 20799000, 20763300, 20609900, 20783700, 22147300, ]
        Average time: 20926650



        QuickSortTriNewRandom:
        ArrayType: Incline
        Elements: 100
        [76500, 57300, 54200, 56600, 55600, 61300, 59900, 54600, 58200, 55800, ]
        Average time: 59000

        Elements: 1000
        [347600, 174200, 159900, 161600, 155000, 154900, 157000, 162900, 155500, 153300, ]
        Average time: 178190

        Elements: 10000
        [1622100, 1642700, 1800800, 1662600, 1656500, 1747900, 1696500, 1725800, 1720400, 1710200, ]
        Average time: 1698550

        Elements: 100000
        [17774200, 17318700, 17585100, 17560900, 17455800, 30685700, 43249300, 42817600, 17564200, 12405800, ]
        Average time: 23441730

        Elements: 200000
        [25081200, 26291000, 24418900, 24464300, 24520200, 24795500, 24568400, 24569700, 24509000, 25955800, ]
        Average time: 24917400


        ArrayType: Decline
        Elements: 100
        [13300, 12700, 12200, 12200, 12300, 11700, 12300, 12400, 12500, 11600, ]
        Average time: 12320

        Elements: 1000
        [128300, 127600, 125100, 127100, 126900, 126200, 125400, 125800, 126100, 124300, ]
        Average time: 126280

        Elements: 10000
        [1289100, 1294000, 1287600, 1286200, 1306300, 1294000, 1287100, 1292900, 1299800, 1305200, ]
        Average time: 1294220

        Elements: 100000
        [13335200, 13299200, 13322500, 13342100, 13395400, 13323800, 13293600, 13395700, 13239400, 13393900, ]
        Average time: 13334080

        Elements: 200000
        [26979100, 27016500, 28172400, 26817700, 26889900, 26904600, 26858200, 26996800, 26861400, 26952800, ]
        Average time: 27044940


        ArrayType: Random
        Elements: 100
        [13700, 12500, 13400, 12800, 12800, 12600, 12800, 13000, 12800, 13200, ]
        Average time: 12960

        Elements: 1000
        [144800, 140000, 137200, 137600, 139100, 136200, 140000, 138800, 138700, 136800, ]
        Average time: 138920

        Elements: 10000
        [1478800, 1488400, 1495600, 1484600, 1496000, 1482600, 1488200, 1479200, 1478100, 2703700, ]
        Average time: 1607520

        Elements: 100000
        [15915200, 16066900, 16024800, 16144600, 16153400, 16392200, 16588000, 16149100, 16242000, 16035000, ]
        Average time: 16171120

        Elements: 200000
        [32753000, 32708200, 33053600, 32970900, 32781700, 32835900, 33124100, 32929600, 32995000, 32858700, ]
        Average time: 32901070
*/