package controller;

import dao.AutorDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Autor;

public class AutorFormularioController {

    @FXML private TextField txfID;
    @FXML private TextField txfNome;
    @FXML private TextField txfEmail;
    @FXML private Button btnSalvar;

    private void limparCampo(){
        txfID.setText(" ");
        txfNome.setText(" ");
        txfEmail.setText(" ");
        txfID.requestFocus();
    }

    public void salvar(){
        // TODO
        System.out.println("ID: "+txfID.getText());
        System.out.println("Nome: "+txfNome.getText());
        System.out.println("E-mail: "+txfEmail.getText());

        Autor autor = new Autor();
        int id = Integer.parseInt(txfID.getText());
        autor.setId(id);
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
