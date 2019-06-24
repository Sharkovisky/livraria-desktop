package controller;

import com.mysql.cj.xdevapi.Table;
import dao.EditoraDAO;
import dao.LivroDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.Editora;
import model.Livro;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LivroFormularioController implements Initializable {

    @FXML private TextField txfID;
    @FXML private TextField txfTitulo;
    @FXML private TextField txfData_lancamento;
    @FXML private TextField txfQuantidade;
    @FXML private TextField txfPreco;
    @FXML private TableView<Livro> tbvwLivros = new TableView<>();
    @FXML private TableColumn<Livro, Integer> columnId = new TableColumn<>("ID");
    @FXML private TableColumn<Livro, String> columnTitulo = new TableColumn<>("Título");
    @FXML private TableColumn<Livro, Date> columnData = new TableColumn<>("Data de lançamento");
    @FXML private TableColumn<Livro, Integer> columnQuantidade = new TableColumn<>("Quantidade");
    @FXML private TableColumn<Livro, Float> columnPreco = new TableColumn<>("Preço");
    @FXML private TableColumn<Livro, Editora> columnEditora = new TableColumn<>("Editora");
    @FXML private ComboBox<Editora> comboEditoras = new ComboBox<>();

    private Livro livro = new Livro();
    private LivroDAO livroDAO = new LivroDAO();
    private Livro livroSelecionado = null;

    public void initialize(URL location, ResourceBundle resources){
        initEditoras();
        iniciarTabela();
    }

    public void iniciarTabela(){
        tbvwLivros.setEditable(false);
        columnId.setCellValueFactory(new PropertyValueFactory<Livro, Integer>("id"));

        columnTitulo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTitulo()));
        //columnTitulo.setCellFactory(TextFieldTableCell.forTableColumn());

        columnData.setCellValueFactory(new PropertyValueFactory<Livro, Date>("data_lancamento"));

        columnQuantidade.setCellValueFactory(new PropertyValueFactory<Livro, Integer>("quantidade"));

        columnPreco.setCellValueFactory(new PropertyValueFactory<Livro, Float>("preco"));

        columnEditora.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getEditora_id()));

        tbvwLivros.setItems(livroDAO.listarTodos());
        tbvwLivros.setOnMouseClicked(tableClick);

    }

    private EventHandler<MouseEvent> tableClick = evt ->{
        livroSelecionado = tbvwLivros.getSelectionModel().getSelectedItem();
        if(livroSelecionado!=null){
            String id = Integer.toString(livroSelecionado.getId());
            txfID.setText(id);
            txfTitulo.setText(livroSelecionado.getTitulo());

            DateTimeFormatter formatoBrasil = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatoMySql = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //LocalDate d1 = LocalDate.parse(livroSelecionado.getData_lancamento(), formatoBrasil);
            //Date d2 = java.sql.Date.valueOf(d1);

            LocalDate d3 = livroSelecionado.getData_lancamento().toLocalDate();
            String d4 = d3.format(formatoBrasil);

            txfData_lancamento.setText(d4);
            String quantidade = Integer.toString(livroSelecionado.getQuantidade());
            txfQuantidade.setText(quantidade);
            String preco = Float.toString(livroSelecionado.getPreco());
            txfPreco.setText(preco);

            Callback<ListView<Editora>, ListCell<Editora>> cellFactory = new Callback<ListView<Editora>, ListCell<Editora>>() {
                @Override
                public ListCell<Editora> call(ListView<Editora> editoraListView) {
                    return new ListCell<Editora>(){

                        @Override
                        protected void updateItem(Editora editora, boolean vazio){
                            super.updateItem(editora, vazio);
                            if(editora == null || vazio){
                                setGraphic(null);
                            }else if (editora.getId() == livroSelecionado.getId()){
                                setText(editora.getNome());
                            }
                        }
                    };
                }
            };

            comboEditoras.setButtonCell(cellFactory.call(null));
            //comboEditoras.setCellFactory(cellFactory);
        }
    };

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
