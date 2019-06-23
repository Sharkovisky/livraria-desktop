package controller;

import dao.EditoraDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Editora;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LivroFormularioController implements Initializable {

    @FXML private TextField txdTitulo;
    @FXML private TextField txdData_lancamento;
    @FXML private TextField txdQuantidade;
    @FXML private TextField txdPreco;
    @FXML private ComboBox<Editora> comboEditoras = new ComboBox<>();

    public void initialize(URL location, ResourceBundle resources){
        initEditoras();
    }

    public void initEditoras(){

        //Editora editora = new Editora();
        EditoraDAO editoraDAO = new EditoraDAO();

        List<String> teste = new ArrayList<>();


        teste.add("Teste");

       comboEditoras.setItems(editoraDAO.listarNomes());

        System.out.println(editoraDAO.listarTodos());
    }

    public void salvar(){

    }


}
