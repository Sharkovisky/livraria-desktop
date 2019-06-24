package controller;

import dao.AutorDAO;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import model.Autor;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AutorListarController implements Initializable {

    @FXML private TableView<Autor> tbvwAutores = new TableView<>();
    @FXML private TableColumn<Autor, Integer> columnId = new TableColumn<>("ID");
    @FXML private TableColumn<Autor, String> columnNome = new TableColumn<>("Nome");
    @FXML private TableColumn<Autor, String> columnEmail = new TableColumn<>("E-mail");
    @FXML private Button btnResetar;
    @FXML private TextField txfID, txfNome, txfEmail;

    private Autor autor = new Autor();
    private AutorDAO autorDAO = new AutorDAO();
    private Autor autorSelecionado = null;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        InitTable();
    }

    private void limparCampo(){
        txfID.setText("");
        txfNome.setText("");
        txfEmail.setText("");
        txfNome.requestFocus();
    }

    public void InitTable(){
        //autorSelecionado = null;
        tbvwAutores.setEditable(false);
        columnId.setCellValueFactory(new PropertyValueFactory<Autor, Integer>("id"));

        columnNome.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNome()));
        columnNome.setCellFactory(TextFieldTableCell.forTableColumn());
        //columnNome.setOnEditCommit(SendCommitNome);

        columnEmail.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getEmail()));
        columnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        //columnEmail.setOnEditCommit(SendCommitEmail);

        tbvwAutores.setItems(autorDAO.listarTodos());
        tbvwAutores.setOnMouseClicked(tableClick);

    }

    private EventHandler<MouseEvent> tableClick = evt ->{
        autorSelecionado = tbvwAutores.getSelectionModel().getSelectedItem();
        if(autorSelecionado!=null){
            String id = Integer.toString(autorSelecionado.getId());
            txfID.setText(id);
            txfNome.setText(autorSelecionado.getNome());
            txfEmail.setText(autorSelecionado.getEmail());
        }
    };

    public void alterar(){
        Autor autor = new Autor();
        int id = Integer.parseInt(txfID.getText());
        autor.setId(id);
        autor.setNome(txfNome.getText());
        autor.setEmail(txfEmail.getText());

        AutorDAO autorDAO = new AutorDAO();
        autorDAO.alterar(autor);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de autores");
        alert.setHeaderText("Cadastro de autores");
        alert.setContentText("Autor alterado com sucesso");
        alert.showAndWait();

        resetar();
    }

    public void deletar(){
        Autor autor = new Autor();
        int id = Integer.parseInt(txfID.getText());
        autor.setId(id);

        AutorDAO autorDAO = new AutorDAO();
        autorDAO.deletar(autor);

        resetar();
    }

    public void resetar(){
        //System.out.println("Teste de reset2");
        tbvwAutores.refresh();
        limparCampo();
        //tbvwAutores.getItems().addAll(autorDAO.listarTodos());
    }

    public void salvar(){
        // TODO
        //System.out.println("Nome: "+txfNome.getText());
        //System.out.println("E-mail: "+txfEmail.getText());

        Autor autor = new Autor();
        autor.setNome(txfNome.getText());
        autor.setEmail(txfEmail.getText());

        AutorDAO autorDAO = new AutorDAO();
        autorDAO.inserir(autor);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de autores");
        alert.setHeaderText("Cadastro de autores");
        alert.setContentText("Autor cadastrado com sucesso");
        alert.showAndWait();

        limparCampo();
    }

}
