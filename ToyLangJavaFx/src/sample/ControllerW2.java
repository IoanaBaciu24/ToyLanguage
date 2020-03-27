package sample;

import ExceptionThing.SomeException;
import Model.Containers.*;
import Model.ProgState;
import Model.Stmt.IStmt;
import Model.Value.value;
import Service.Service;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

//import javax.swing.text.TableView;
//import javax.swing.text.html.ListView;
//import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerW2 {

    private Service service;
    private Controller controller;
    private ProgState currentProgState;
    @FXML
    private TableView heapTable;
    @FXML
    private TextField noPrgStates;
    @FXML
    private ListView out;
    @FXML
    private ListView fileTbl;
    @FXML
    private ListView prgStatesIds;
    @FXML
    private TableView symTbl;
    @FXML
    private ListView exeStack;
    @FXML
    private Button oneStepButton;
    @FXML
    private TableColumn<Map.Entry<Integer, value>, String> htblC1;
    @FXML
    private TableColumn<Map.Entry<Integer,value>, String> htblC2;
    @FXML
    private TableColumn<Map.Entry<String, value>, String> stblC1;
    @FXML
    private TableColumn<Map.Entry<String, value>, String> stblC2 ;




   @FXML
   public void initialize(URL url, ResourceBundle resourceBundle) {
       setUpAtOpen();
       //System.out.println("a intrat aici");
       stblC1.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getKey()+ ""));
       stblC2.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getValue().toString()));

       htblC1.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getKey()+ ""));
       htblC2.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getValue().toString()));



   }
    public void setService(Service s)
    {
        service = s;
    }
    public void setController(Controller c) {controller = c;}

    @FXML
    private void setUpAtOpen()
    {
        //set the list of prg states
        ArrayList<ProgState> prglist = (ArrayList<ProgState>) service.getProgStates();
        System.out.println(prglist);
        ObservableList<String> list = FXCollections.observableArrayList();
        //list.addAll(prglist.stream().map(Objects::toString).collect(Collectors.toList()));
        list.addAll(prglist.stream().map(p->p.getId().toString()).collect(Collectors.toList()));

        prgStatesIds.setItems(list);
        noPrgStates.setEditable(false);
        noPrgStates.setText(Integer.toString(prglist.size()));


    }

    @FXML
   public void initialize2(MouseEvent mouseEvent) {
        setUpAtOpen();
   }
    public void itemClicked(javafx.scene.input.MouseEvent mouseEvent) {

       int index;
       String s;
        s = (String) prgStatesIds.getSelectionModel().getSelectedItem();
        index = Integer.parseInt(s);
        //System.out.println(index);
        //System.out.println("macar a intrat in functie");

        for(ProgState prg:service.getProgStates())
        {
            if(prg.getExeStack().isEmpty())
            {
                   /* Alert alert =new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("tis stack empty, yeet");
                    alert.showAndWait();
                    return;*/
                ObservableList<String> list = FXCollections.observableArrayList();
                //java.util.List<String> l = new ArrayList<String>();
                //l = Arrays.asList();
                //list.addAll(l.stream().collect(Collectors.toList()));
                exeStack.setItems(list);
                exeStack.refresh();

            }
            if(prg.getId() == index) {
                {
                    String staque = prg.getExeStack().toString();
                    ObservableList<String> list = FXCollections.observableArrayList();
                    String[] tokens = staque.split(";");
                    java.util.List<String> l = new ArrayList<String>();
                    l = Arrays.asList(tokens);
                    list.addAll(l.stream().collect(Collectors.toList()));
                    exeStack.setItems(list);
                    exeStack.refresh();

                    //symTbl.getColumns().addAll(stblC1, stblC2);

                /*MonMap<String, value> stbl = (MonMap<String, value>) prg.getSymTable();
                ArrayList<Pair<String, value>> aux = new ArrayList<>();
                for(String str: stbl.keySet())
                {
                    aux.add(new Pair<>(str, stbl.get(str)));
                }
                ObservableList<Pair<String,value>> list1 = FXCollections.observableArrayList(aux);
                stblC1.setCellValueFactory(new PropertyValueFactory<Pair, String>("K"));
                stblC2.setCellValueFactory(new PropertyValueFactory<Pair, String>("V"));

                symTbl.getColumns().addAll(stblC1,stblC2);
                symTbl.setItems(list1);*/


                    MapInter<String, value> symbolTable = prg.getSymTable();

                    List<Map.Entry<String, value>> symbolTableList = new ArrayList<>();
                    for (Map.Entry<String, value> entry : symbolTable.getAll())
                        symbolTableList.add(entry);
                    symTbl.setItems(FXCollections.observableList(symbolTableList));
                    symTbl.refresh();


                    break;
                }
            }}
    }




    public void oneStepForAll(MouseEvent mouseEvent) {

        try {
            //service.oneStepForAllPrg(service.getProgStates());
            service.thatOneStepForGUI(service.getProgStates());

        } catch (SomeException e) {

            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            return;

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
            return;
        }

        ArrayList<ProgState> progs = (ArrayList<ProgState>) service.getProgStates();
        MonQ<value> ot = (MonQ<value>) progs.get(0).getOut();
        List<value> ott = ot.getLelist();
        ArrayList<String> outList = new ArrayList<String>();
        for(value v:ott)
        {
            outList.add(v.toString());
        }

        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(outList.stream().collect(Collectors.toList()));
        String str = service.getProgStates().get(0).getFileTable().toString();

        out.setItems(list);
        ObservableList<String> list2 = FXCollections.observableArrayList();
        list2.addAll(str);


        fileTbl.setItems(list2);

        Map<Integer, value> htbl = progs.get(0).getHeap().getContent();
        List<Map.Entry<Integer,value>> htbllist = new ArrayList<>();

        for(Map.Entry<Integer,value> elem: htbl.entrySet())
        {
            htbllist.add(elem);
        }
        heapTable.setItems(FXCollections.observableArrayList(htbllist));
        heapTable.refresh();


        setUpAtOpen();
    }
}


