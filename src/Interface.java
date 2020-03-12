
 
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
 
public class Interface extends Application {
    static private Boolean validWord = true;
 static private Text spelling = new Text();
 static final Text defHeader = new Text("Definitions");
 static final Text synHeader = new Text("Synonyms");
 static final Text antHeader = new Text("Antonyms");
 static private Words currentWord = null;
 static public Boolean ascending = true;
 static Words lastWord = null;
 static private List < Words > objsDisplayed;
 static private List < String > currentWordList;
 static private VBox antsyn = new VBox();
 static private CheckBox asc = new CheckBox("asc");
 static private CheckBox desc = new CheckBox("desc");
 static private Boolean firstWord;
 static private ArrayList < Definitions > definitions = new ArrayList < Definitions > ();
 private static VBox right = new VBox();
 static private ArrayList < String > synonyms = new ArrayList < String > ();
 static private ArrayList < String > antonyms = new ArrayList < String > ();
 static int index;
 ObservableList < String > data = FXCollections.observableArrayList();
 FilteredList < String > filteredData = new FilteredList < > (data, s -> true);
 ListView < String > list = new ListView < String > (filteredData);
 private GridPane content;
 private TextField filterInput;
 private Scene scene;
 private static int extraDefs = 0;
 private static VBox left = new VBox();
 private static Button addButton = new Button("Add");
 private static Button rmButton = new Button("Remove");
 static ScrollPane scroll = new ScrollPane();

 
}