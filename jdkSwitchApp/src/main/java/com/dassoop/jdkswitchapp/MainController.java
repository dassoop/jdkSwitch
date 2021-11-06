package com.dassoop.jdkswitchapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    Label welcomeText;
    @FXML
    ListView<String> listView = new ListView<String>();

    public MainController()
    {

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        //Test Data
        String[] testList = new String[10];
        testList[0] = "Test";
        testList[1] = "Test";
        testList[2] = "Test";
        testList[3] = "Test";
        testList[4] = "Test";
        testList[5] = "Test";
        testList[6] = "Test";
        testList[7] = "Test";
        testList[8] = "Test";
        testList[9] = "Test";
        listView.getItems().addAll(testList);
    }

}