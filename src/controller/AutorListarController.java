package controller;

import dao.AutorDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Autor;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AutorListarController implements Initializable {

    @FXML private TableView<Autor> tbvwAutores = new TableView<>();
    @FXML private TableColumn<Autor, Integer> columnId = new TableColumn<>("ID");
    @FXML private TableColumn<Autor, String> columnNome = new TableColumn<>("Nome");
    @FXML private TableColumn<Autor, String> columnEmail = new TableColumn<>("E-mail");

    private Autor autor = new Autor();
    private AutorDAO autorDAO = new AutorDAO();
    private Autor autorSelecionado;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        InitTable();
    }

    public void InitTable(){
        autorSelecionado = null;
        tbvwAutores.setEditable(true);
        columnId.setCellValueFactory(new PropertyValueFactory<Autor, Integer>("id"));

        columnNome.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNome()));
        columnNome.setCellFactory(TextFieldTableCell.forTableColumn());
        //columnNome.setOnEditCommit(SendCommitNome);

        columnEmail.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getEmail()));
        columnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        //columnEmail.setOnEditCommit(SendCommitEmail);

        tbvwAutores.setItems(autorDAO.listarTodos());
        //tbvwAutores.setOnMouseClicked(TableClick);
    }

}
