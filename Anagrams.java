package hw6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Anagrams {
    private final Integer[] primes = {2 , 3 , 5 , 7, 11 , 13 , 17 , 19 , 23 , 29 , 31 , 37 , 41 , 43 , 47 , 53 , 59 , 61 ,
            67 , 71 , 73 , 79 , 83 , 89 , 97 , 101};
//the letters required
    private final Character[] letters = {'a', 'b', 'c','d','e','f','g','h','i','j','k','l','m','n','o', 'p','q', 'r', 's', 't', 'u', 'v','w', 'x', 'y', 'z'};

    private Map<Character,Integer> letterTable = new HashMap<>();

    private Map<Long, ArrayList<String>> anagramTable = new HashMap<>();

    public  Anagrams(){
        buildLetterTable();
    }
//builds the letter table
    public void buildLetterTable(){
        for(int i=0; i <26; i++){
        letterTable.put(letters[i], primes[i]);
    }
    }

    public void addWord(String s){
        Long hash = myHashCode(s);
        if (anagramTable.containsKey(hash)){
            ArrayList temp = anagramTable.get(hash);
            temp.add(s);
            anagramTable.put(hash,temp);
        }
        else{
            ArrayList array = new ArrayList<String>();
            array.add(s);
            anagramTable.put(hash, array);
        }}

    private Long myHashCode(String s){
        Long temp = 1L;
            for (int i=0; i<s.length(); i++){
            temp *= letterTable.get(s.charAt(i));
        }
        return temp;
        }

    private void processFile ( String s ) throws IOException {
        FileInputStream fstream = new FileInputStream (s);
        BufferedReader br = new BufferedReader ( new InputStreamReader( fstream ));
        String strLine ;
        while (( strLine = br . readLine ()) != null ) {
             this.addWord(strLine);
        }
        br . close ();
    }

    private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
        ArrayList<Map.Entry<Long,ArrayList<String>>> res = new ArrayList<>();
        int max = 0;
        for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
            if ( entry.getValue().size()==max){
                res.add(entry);
            } else if(entry.getValue().size()>max){
                max = entry.getValue().size();
                res.clear();
                res.add(entry);
            }
        }
        return res;
    }

    public static void main ( String [] args ) {
        Anagrams a = new Anagrams ();
        final long startTime = System . nanoTime ();
        try {
            a.processFile ("words_alpha.txt");
        } catch ( IOException e1 ) {
            e1.printStackTrace ();
        }
        ArrayList < Map . Entry < Long , ArrayList < String > >> maxEntries = a. getMaxEntries ();
        final long estimatedTime = System . nanoTime () - startTime ;
        final double seconds = (( double ) estimatedTime /1000000000);
        System.out.println ("Time : "+ seconds );
        System.out.println ("List of max anagrams : "+ maxEntries );
    }



}