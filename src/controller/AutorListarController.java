package controller;

import dao.AutorDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Autor;

import java.util.List;

public class AutorListarController {

    @FXML private TableView tbtvwAutores;

    public void listarAutores(){
        System.out.println("Funcionando?");
        AutorDAO a1 = new AutorDAO();
        a1.listarTodos();

    }
}
