// Jacob Fonger
// A02316298

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WritePoetry {
    public String writePoem(String file, String startWord, int length, boolean printHashtable){
        HashTable<String, WordFreqInfo> hashTable = genHashTable(file);
        startWord = startWord.toLowerCase();
        if (printHashtable) {
            System.out.println("-- Hash Table for \"" + file + "\" --\n" + hashTable.toString(hashTable.size()));
        }
        if (!hashTable.contains(startWord)) {
            return "Error:\nCould not build poem.\n\"" + startWord + "\" was not found in the file \"" + file + "\"";
        }
        Random rnd = new Random();
        String poem = startWord;
        String currentWordStr = startWord;
        boolean newLine = false;

        // if start word is punctuation
        if (!Character.isAlphabetic(currentWordStr.charAt(0)) || currentWordStr.charAt(0) == '\''){
            poem += "\n";
            newLine = true;
        }
        // for loop builds poem
        for (int i = 0; i < length - 1; i++) {
            WordFreqInfo wordObj = hashTable.find(currentWordStr);
            int number = rnd.nextInt(wordObj.getOccurCount());
            currentWordStr = wordObj.getFollowWord(number);
            // punctuation
            if (!Character.isAlphabetic(currentWordStr.charAt(0)) || currentWordStr.charAt(0) == '\''){
                poem += currentWordStr + "\n";
                newLine = true;
            }
            // not punctuation
            else {
                if (newLine) poem = poem + currentWordStr;
                else poem = poem + " " + currentWordStr;
                newLine = false;
            }
        }
        // check to see if last word added was punctuation
        if (Character.isAlphabetic(currentWordStr.charAt(0)) || currentWordStr.charAt(0) == '\'') {
            poem += ".";
        }
    return poem;
    }


    // genHashTable reads file and adds words to dictionary, returns the dictionary
    private HashTable<String, WordFreqInfo> genHashTable(String fileName){

        HashTable<String, WordFreqInfo> hashTable = new HashTable<>();
        ArrayList<String> allWords = new ArrayList<>();
        File file = new File(fileName);
        // read file
        try (Scanner input = new Scanner(file)) {
            // Start by reading all the words into memory.
            while (input.hasNextLine()) {
                String line = input.nextLine().toLowerCase();
                String[] individualWords = line.split(" "); // still has punctuation with words
                // add all words to arrayList allWords
                for (String word : individualWords) {
                    // clean word (line 59-69 from cleanWord() in HashingPoetry.java)
                    String newWord = "";
                    for (int j = 0; j < word.length(); j++) {
                        if (Character.isAlphabetic(word.charAt(j)) || word.charAt(j) == '\'') {
                            newWord += word.charAt(j);
                        }
                    }
                    if (newWord.length() > 0 && !Character.isAlphabetic(newWord.charAt(0))) {
                        newWord = newWord.substring(1);
                    }
                    if (newWord.compareTo("") != 0) allWords.add(newWord);

                    // add any punctuation to arrayList as separate String
                    if (newWord.compareTo(word) != 0) {
                        char punctuation = word.charAt(word.length() - 1);
                        allWords.add(Character.toString(punctuation));
                    }
                }
            }
            // add each word to dictionary
                for (int i = 0; i < allWords.size(); i++) {
                    String word = allWords.get(i);
                    WordFreqInfo newValue;

                    // if word is already in dict
                    if (hashTable.contains(word)) {
                         newValue = hashTable.find(word);
                         hashTable.remove(word);
                    }
                    // if not in dict
                    else {
                        newValue = new WordFreqInfo(word, 0);
                    }
                    // add follow word as long as it's not the last word
                    if (i < allWords.size() - 1) {
                        newValue.updateFollows(allWords.get(i + 1));
                    }
                    hashTable.insert(word, newValue);
                }
    }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary: " + ex);
        }
    return hashTable;
    }


}
