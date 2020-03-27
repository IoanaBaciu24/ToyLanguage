package sample;

import ExceptionThing.SomeException;
import Model.Containers.*;
import Model.ProgState;
import Model.Stmt.IStmt;
import Model.Type.type;
import Model.Value.value;
import RepoStuff.Repo;
import Service.Service;
import View.View;
import com.sun.glass.ui.CommonDialogs;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    private Button myButton;

    @FXML
    private Label myTextField;

    @FXML
    private ListView<String> listView ;
    private serviceTest service = new serviceTest();
    private Stage startWin = null;

    private Integer index = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        populateList();



    }



    public void populateList()
    {
        /*
        ArrayList<String> list = service.getList();
        //ObservableList<String> observableList = FXCollections.observableArrayList(list);
        //listView.setItems(observableList);
        System.out.println(list);
        for(String s:list)
        {
            listView.getItems().add(s);
        }*/

        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<IStmt> stmts = View.getExamples();
        list.addAll(stmts.stream().map(Objects::toString).collect(Collectors.toList()));
        listView.setItems(list);


    }

    public void showDateTime(ActionEvent event) {
        System.out.println("Button Clicked!");

        Date now= new Date();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");


        // Model Data
        String dateTimeString = df.format(now);

        // Show in VIEW
        myTextField.setText(dateTimeString);

    }
    public void setStartWin(Stage primaryStage) { startWin = primaryStage;}
    public void openNew(ActionEvent event) throws IOException
    {
        if(index ==null)
        {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText("bre");
            alert.showAndWait();
            return;
        }

        IStmt stmt = View.getExamples().get(index);
        try {
            MapInter<String, type> tblEvl = new MonMap<>();
            tblEvl = stmt.typecheck(tblEvl);
        }
        catch (SomeException e){

            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            return;
        }
        Repo rep = new Repo("log"+index+".in");
        ProgState prg = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), stmt);
        rep.add(prg);
        Service ctrl = new Service(rep);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("window2.fxml"));
        Scene scene = new Scene(loader.load());
        ControllerW2 controllerW2 = loader.getController();
        controllerW2.setService(ctrl);
        controllerW2.setController(this);
        controllerW2.initialize(null,null);
        Stage stage = new Stage();
        stage.setTitle("miau" + index.toString());
        stage.setScene(scene);
        stage.initOwner(startWin);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
        index = null;
    }

    public void itemClicked(javafx.scene.input.MouseEvent mouseEvent) {

        index = listView.getSelectionModel().getSelectedIndex();
        System.out.println("macar a intrat in functie");
    }
}
