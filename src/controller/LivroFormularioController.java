package controller;

import dao.EditoraDAO;
import dao.LivroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.Editora;
import model.Livro;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LivroFormularioController implements Initializable {

    @FXML private TextField txfTitulo;
    @FXML private TextField txfData_lancamento;
    @FXML private TextField txfQuantidade;
    @FXML private TextField txfPreco;
    @FXML private ComboBox<Editora> comboEditoras = new ComboBox<>();

    public void initialize(URL location, ResourceBundle resources){
        initEditoras();
    }

    public String toString(Editora editora){
        return editora.getNome();
    }

    public void initEditoras() {

        Editora editora = new Editora();
        EditoraDAO editoraDAO = new EditoraDAO();

        comboEditoras.setItems(editoraDAO.listarTodos());

        Callback<ListView<Editora>, ListCell<Editora>> cellFactory = new Callback<ListView<Editora>, ListCell<Editora>>() {
            @Override
            public ListCell<Editora> call(ListView<Editora> editoraListView) {
                return new ListCell<Editora>(){

                    @Override
                    protected void updateItem(Editora editora, boolean vazio){
                        super.updateItem(editora, vazio);
                        if(editora == null || vazio){
                            setGraphic(null);
                        }else{
                            setText(editora.getNome());
                        }
                    }
                };
            }
        };
        comboEditoras.setButtonCell(cellFactory.call(null));
        comboEditoras.setCellFactory(cellFactory);
    }

    public void salvar(){

        Livro livro = new Livro();
        livro.setTitulo(txfTitulo.getText());

        DateTimeFormatter formatoBrasil = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoMySql = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate d1 = LocalDate.parse(txfData_lancamento.getText(), formatoBrasil);
        Date d2 = java.sql.Date.valueOf(d1);

        livro.setData_lancamento(d2);
        int id = Integer.parseInt(txfQuantidade.getText());
        livro.setQuantidade(id);
        float preco = Float.parseFloat(txfPreco.getText());
        livro.setPreco(preco);
        livro.setEditora_id(comboEditoras.getValue().getId());
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.inserir(livro);
    }


}
