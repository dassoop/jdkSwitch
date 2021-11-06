package com.dassoop.jdkswitchapp;

import com.dassoop.jdkswitchapp.services.FilesService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    Label lblDirectory;
    @FXML
    ListView<String> listView = new ListView<String>();

    String directory = "";

    FilesService filesService = new FilesService();

    public MainController()
    {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        lblDirectory.setText(directory);
    }

    public void chooseDirectory(ActionEvent event)
    {
        DirectoryChooser dirChooser = new DirectoryChooser();

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

        File dir = dirChooser.showDialog(thisStage);
        File[] listOfContents = dir.listFiles();


        Arrays.sort(listOfContents);
        listView.getItems().clear();
        listView.getItems().addAll(filesService.getFileNames(listOfContents));

        lblDirectory.setText(dir.getAbsolutePath());
        event.consume();
    }

}