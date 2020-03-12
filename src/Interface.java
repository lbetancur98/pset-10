
 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;
 
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

    public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        Dictionary.addAllWords();
        firstWord = true;
        scroll.setPrefWidth(900);
        scroll.setPadding(new Insets(20, 10, 10, 20));
        asc.setSelected(true);
        launch(args);
      
       }
      
}