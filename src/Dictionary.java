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

}