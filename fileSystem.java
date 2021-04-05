import java.text.DecimalFormat;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import javafx.stage.DirectoryChooser;
import java.nio.charset.StandardCharsets;

public class fileSystem extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        // set title
        primaryStage.setTitle("File Saver v.1.0");

        // Use table function to allow tables
        TableView tableView = new TableView();

        // set window parameters
        Scene scene = new Scene(new Group());
        primaryStage.setWidth(520);
        primaryStage.setHeight(470);

        // set to editable
        tableView.setEditable(true);

        // create a VBox emote to display the table
        VBox vbox = new VBox();

        // set pane variable for each list
        SplitPane listSection = new SplitPane();

        // set list fariables for first and second list
        ListView first = new ListView();
        ListView second = new ListView();

        // set vbox variables for the first and second list
        VBox firstList = new VBox(first);
        VBox secondList = new VBox(second);

        // add all to listsection
        listSection.getItems().addAll(firstList, secondList);

        // create button variables
        Button upload = new Button("Upload");
        Button download = new Button("Download");

        // create box secton
        HBox buttonSection = new HBox(download, upload);

        // add to the vbox
        vbox.getChildren().addAll(buttonSection, listSection);

        // create function for the downlod button
        download.setOnAction(d -> {
            // use trycatch to interacter with the server files
            try {
                // set the server socket
                Socket server = new Socket("127.0. 0.1", 41901);

            } catch (Exception e) {
                ///// TODO Auto-generated catch block
                // output error
                System.out.println("Download error");
            }
        });

        // create function for the upload button
        upload.setOnAction(u -> {

        });

        // use this to allow the program to out put the table
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        // set up the window for the Filter program
        primaryStage.setScene(scene);

        // display the program as a whole
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}