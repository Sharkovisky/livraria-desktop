package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class PrincipalController {

    @FXML private MenuItem menuItemCadastrar;

    public void menuCadastrar(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("autor_formulario.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
