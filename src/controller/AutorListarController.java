package controller;

import dao.AutorDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Autor;

import java.util.List;

public class AutorListarController {

    @FXML private ListView lstvwAutores;

    private void listarAutores(){
        System.out.println("Funcionando?");
        AutorDAO a1 = new AutorDAO();
        a1.listarTodos();

    }
}
