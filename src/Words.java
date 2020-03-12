import java.io.FileNotFoundException;
import java.util.ArrayList;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException; 
public class Words {
    ArrayList<Definitions> definitions = new ArrayList<Definitions>(); 
	ArrayList<String> synonyms = new ArrayList<String>();
	ArrayList<String> antonyms = new ArrayList<String>();
	
	public Words(String word, ArrayList<Definitions> definitions, ArrayList<String> synonyms, ArrayList<String> antonyms) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		this.word = word.substring(0, 1).toUpperCase() + word.substring(1, word.length()).toLowerCase();
		this.definitions = definitions;
		this.synonyms = synonyms;
		this.antonyms = antonyms;
	}


}
