import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException; 

public class Dictionary {
    public static Words[] wordList;
	
	public static Words[] addAllWords() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		wordList = new Gson().fromJson(new FileReader(".\\JSON\\words.json"), Words[].class);
		return wordList;
    }
    
    public static ArrayList<String> listSpellings(Boolean ascending) {
		ArrayList<String> listOfWords = new ArrayList<String>();
		
		for (Words word : wordList)  {	
			listOfWords.add(word.getSpelling());
		}
		
		if (ascending) {
			Collections.sort(listOfWords, Collections.reverseOrder());	
		} else {
			Collections.sort(listOfWords);
		}
		return listOfWords;
    }
    
    public static ArrayList<Words> sortObj(Boolean ascending, List<String> sortedSpellings) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		
		wordList = addAllWords();
		ArrayList<Words> sortedObj = new ArrayList<Words>();
		for (int i = 0; sortedObj.size() < sortedSpellings.size(); i++) {
		
			for (Words word : wordList) {
				if (sortedSpellings.get(i).equals(word.getSpelling())) {
					sortedObj.add(word);
					break;
				}
				
            }
        }
         return sortedObj;	
    }
    public static Words[] addToList(int n, Words wordList[], Words word) { 
        Words newWordList[] = new Words[n + 1]; 

        for (int i = 0; i < n; i++) {
            newWordList[i] = wordList[i]; 
        }
        newWordList[n] = word; 

        return newWordList; 
    } 

    public static void addWord(Words word) {
		
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(addToList(wordList.length, wordList, word));
	  try {
	   FileWriter writer = new FileWriter(".\\JSON\\words.json");
	   wordList = addToList(wordList.length, wordList, word);
	   writer.write(json);
	   writer.close();
	  
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	  

    }
    
    public static void delWord(Words[] wordsToDel) { 
        Words newWordList[] = new Words[wordList.length - wordsToDel.length]; 
        Boolean kill = false;
        for (int i = 0; i < newWordList.length; i++) {
        	kill = false;
        	for (Words deadWord : wordsToDel) {
        		if (wordList[i] == deadWord) {
        			kill = true;
        		}
        		if (!kill) {
            		newWordList[i] = wordList[i];
            	} else {
            		newWordList[i] = wordList[i + 1];
            	}
        	}
        	
        }

        wordList = newWordList;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(wordList);
		try {
			
			   FileWriter writer = new FileWriter(".\\JSON\\words.json");
			  
			   writer.write(json);
			   writer.close();
			  
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
		
    } 

}