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

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import java.awt.*;

public class MainController implements Initializable
{
    @FXML
    Label lblDirectory;
    @FXML
    Label lblVersion;

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
        File dir = new File("/Library/Java/JavaVirtualMachines");
        directory = dir.getAbsolutePath();
        File[] listOfContents = dir.listFiles();


        Arrays.sort(listOfContents);
        listView.getItems().clear();
        listView.getItems().addAll(filesService.getFileNames(listOfContents));

        lblDirectory.setText(dir.getAbsolutePath());
        lblDirectory.setText(directory);

        try
        {
            lblVersion.setText(this.checkJavaVersion());
        }
        catch(IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void chooseDirectory(ActionEvent event)
    {
        DirectoryChooser dirChooser = new DirectoryChooser();

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

        File dir = dirChooser.showDialog(thisStage);
        directory = dir.getAbsolutePath();
        File[] listOfContents = dir.listFiles();


        Arrays.sort(listOfContents);
        listView.getItems().clear();
        listView.getItems().addAll(filesService.getFileNames(listOfContents));

        lblDirectory.setText(dir.getAbsolutePath());
        event.consume();
    }

    public String checkJavaVersion() throws IOException, InterruptedException
    {
        String cmd = "java --version";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));

        List<String> lines = new LinkedList();

        String line = "";
        while ((line=buf.readLine())!=null)
        {
            lines.add(line);
//            System.out.println(line);
        }
        String version = lines.get(0);
        System.out.println(version);
        lblVersion.setText(version);
        return version;
    }

    public void copyExportCommand()
    {
        String fileName = listView.getSelectionModel().getSelectedItem();
        String cmd = "export JAVA_HOME=" + directory + "/" + fileName + "/Contents/Home";
        System.out.println(cmd);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(cmd);
        clipboard.setContents(strSel, null);

    }

    public void shellCommand() throws IOException, InterruptedException
    {
        String cmd = "ls";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line=buf.readLine())!=null)
        {
            System.out.println(line);
        }
    }

//    public void openTermnial() throws IOException
//    {
//        System.out.println("Test");
////        Runtime.getRuntime().exec("/usr/bin/open -a Terminal /path/to/the/executable");
//        Console console = System.console();
//        if(console == null && !GraphicsEnvironment.isHeadless()){
//            String filename = Main.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
//            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
//        }else{
//            Main.main(new String[0]);
//            System.out.println("Program has ended, please type 'exit' to close the console");
//        }
//    }

}