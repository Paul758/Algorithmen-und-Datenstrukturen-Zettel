import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HashmapForStrings {
    private final int m;
    private final LinkedList<String>[] hashmap;


    public HashmapForStrings(int m){
        this.m = m;
        this.hashmap = new LinkedList[m];
        for(int i = 0; i < hashmap.length; i++){
            hashmap[i] = new LinkedList<>();
        }
    }

    public void insert(String key){
        int index = hashFunction(key);
        LinkedList<String> listAtIndex = hashmap[index];
        listAtIndex.insertAtHead(key);
    }

    public void delete(String key){
        int index = hashFunction(key);
        LinkedList<String> listAtIndex = hashmap[index];
        listAtIndex.remove(key);
    }

    public int search(String key){
        int index = hashFunction(key);
        LinkedList<String> listAtIndex = hashmap[index];
        if(listAtIndex.search(key)){
            return index;
        }
        return -1;
    }

    public int hashFunction(String key){
        char[] keyCharacters = new char[key.length()];
        for(int i = 0; i < key.length(); i++){
            keyCharacters[i] = key.charAt(i);
        }
        int charSum = 0;
        for (char keyCharacter : keyCharacters) {
            charSum += keyCharacter;
        }
        return charSum % m;
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

//Zeiten:
/*
Testing for Hashmapsize: 1000
Time needed to insert words:6.79945E7
Time needed to search for words:6.67867E7

Testing for Hashmapsize: 10000
Time needed to insert words:2.49972E7
Time needed to search for words:1.185933E8

Erfolglose Suchen: 1814
 */