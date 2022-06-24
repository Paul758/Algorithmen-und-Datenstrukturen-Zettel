import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenHashmapForStrings extends Hashmap<String> {

    public OpenHashmapForStrings(int m, CollisionHandling mode){
        super(new String[m], m, mode);
    }

    @Override
    public void delete(String key){
       int j = search(key);
       if(j != -1){
           hashmap[j] = DELETED;
       }
    }
    @Override
    public long hashFunction(String key, int index, CollisionHandling mode){
        long n = 0;
        int exponent = key.length() - 1;
        for (char element: key.toCharArray()){
            int normalizedCharValue = element - 'A' + 1;
            n += normalizedCharValue * Math.pow(26, exponent);
            exponent--;
        }
        if(n < 0){
            n += Long.MAX_VALUE;
        }
       switch (mode){
            case LinearProbing ->{
                return (n + index) % m;
            }
            case QuadraticProbing -> {
                return (long) (((n % m) + c1 * index + c2 * Math.pow(index,2)) % m);
            }
            case DoubleHashing -> {
                long h1 = n % m;
                long h2 = 1 + (n % (m - 1));
                return (h1 + index * h2) % m;
            }
        }
        throw new RuntimeException("Missing hash mode");
    }

    public void insertWords(String fileName) throws IOException {
        String currentWord;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while((currentWord = reader.readLine()) != null){
            insert(currentWord);
        }
    }
    public int searchForCheaters(String fileName) throws IOException {
        int hits = 0;
        String currentWord;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while((currentWord = reader.readLine()) != null){
            if(search(currentWord) == -1){
                hits++;
            }
        }
        return hits;
    }
}

/*
Hashmode: LinearProbing

Hashmapsize: 200000
Time to insert words: 4.646038E8
Time to search for cheat words: 5.66711E7
Erfolglose Suchen: 1814

Hashmapsize: 1000000
Time to insert words: 5.47103E7
Time to search for cheat words: 1552600.0
Erfolglose Suchen: 1814


Hashmode: QuadraticProbing

Hashmapsize: 200000
Time to insert words: 1.642548E8
Time to search for cheat words: 9956000.0
Erfolglose Suchen: 1814

Hashmapsize: 1000000
Time to insert words: 7.39273E7
Time to search for cheat words: 1569500.0
Erfolglose Suchen: 1814


Hashmode: DoubleHashing

Hashmapsize: 200000
Time to insert words: 1.41712E8
Time to search for cheat words: 9.50444E7
Erfolglose Suchen: 1814

Hashmapsize: 1000000
Time to insert words: 5.60153E7
Time to search for cheat words: 1650000.0
Erfolglose Suchen: 1814


 */