
 
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

       public void start(Stage primaryStage) {

        EventHandler < ActionEvent > removeWord = new EventHandler < ActionEvent > () {
         @Override
         public void handle(ActionEvent event) {
          if (list.getSelectionModel().getSelectedItems().size() != 0) {
           Alert alert = new Alert(AlertType.CONFIRMATION);
           alert.setTitle("Confirmation Dialog");
           alert.setHeaderText("DELETING WORD");
           alert.setContentText("Are you sure you want to delete selected words?");
      
           Optional < ButtonType > result = alert.showAndWait();
           if (result.get() == ButtonType.OK && list.getSelectionModel().getSelectedIndices() != null) {
            try {
             Words[] wordsToDelete = new Words[list.getSelectionModel().getSelectedIndices().size()];
             for (int i = 0; i < list.getSelectionModel().getSelectedItems().size(); i++) {
              for (Words orgWord: Dictionary.wordList) {
               if (orgWord.getSpelling().equals(list.getSelectionModel().getSelectedItems().get(i))) {
                wordsToDelete[i] = orgWord;
               }
              }
             }
             Dictionary.delWord(wordsToDelete);
             data.clear();
             filterInput.textProperty().addListener(obs -> {
      
              String filter = filterInput.getText();
              if (filter == null || filter.length() == 0) {
               filteredData.setPredicate(s -> true);
              } else {
               filteredData.setPredicate(s -> s.matches(filter + ".*"));
              }
             });
      
             currentWordList = filteredData;
      
             display(ascending, primaryStage, scene);
             Alert success = new Alert(AlertType.INFORMATION);
             success.setHeaderText("Successfully deleted word!");
             Optional < ButtonType > done = success.showAndWait();
      
             if (done.isPresent() && done.get() == ButtonType.OK) {
              event.consume();
             }
            } catch (JsonIOException e) {
             e.printStackTrace();
            } catch (JsonSyntaxException e) {
             e.printStackTrace();
            }
           } else {
            event.consume();
           }
           event.consume();
          } else {
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("ERROR");
           alert.setHeaderText("No words to delete...");
           Optional < ButtonType > result = alert.showAndWait();
      
           if (result.isPresent() && result.get() == ButtonType.OK) {
            event.consume();
           }
           event.consume();
          }
         }
        };
      
        right = new VBox();
        Dictionary.listSpellings(ascending).forEach(data::add);
      
        defHeader.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 21));
        synHeader.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 21));
        antHeader.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 21));
        filterInput = new TextField();
        
        filterInput.textProperty().addListener(obs -> {

            String filter = filterInput.getText();
            if (filter == null || filter.length() == 0) {
             filteredData.setPredicate(s -> true);
            } else {
             filteredData.setPredicate(s -> s.matches(filter + ".*"));
            }
         
           });
         
           currentWordList = filteredData;
         
           int maxHeight = 600;
         
           index = -1;
           content = new GridPane();
         
           content.setPadding(new Insets(5, 10, 5, 5));
           list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
           list.getSelectionModel().selectedItemProperty()
            .addListener(new ChangeListener < String > () {
         
         
         
             public void changed(ObservableValue < ? extends String > observable,
              String oldValue, String newValue) {
         
              if (list.getSelectionModel().getSelectedIndices().size() == 1 && (firstWord || currentWordList.contains(currentWord.getSpelling()))) {
               index = currentWordList.indexOf(list.getSelectionModel().getSelectedItem());
         
         
               try {
                objsDisplayed = Dictionary.sortObj(ascending, currentWordList);
               } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
         
                e.printStackTrace();
               }
               if (index != -1) {
         
                currentWord = objsDisplayed.get(index);
         
               }
}