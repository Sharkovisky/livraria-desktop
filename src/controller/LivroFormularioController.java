package controller;

import dao.EditoraDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Editora;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LivroFormularioController implements Initializable {

    @FXML private TextField txdTitulo;
    @FXML private TextField txdData_lancamento;
    @FXML private TextField txdQuantidade;
    @FXML private TextField txdPreco;
    @FXML private ChoiceBox choiceEditoras;

    public void initialize(URL location, ResourceBundle resources){
        initEditoras();
    }

    public void initEditoras(){
        Editora editora = new Editora();
        EditoraDAO editoraDAO = new EditoraDAO();
        choiceEditoras = new ChoiceBox(FXCollections.observableList(editoraDAO.listarTodos()));

        //ChoiceBox<Editora> choiceBox = new ChoiceBox<>();

        //choiceBox.setItems(editoraDAO.listarTodos());
        //choiceBox.setItems(FXCollections.observableArrayList());

        ArrayList<String> lista = new ArrayList<>();


        lista.add("Teste");

        choiceEditoras.getItems().add(lista);
        //choiceEditoras.setItems(editoraDAO.listarTodos());
    }

    public void salvar(){

    }


}
