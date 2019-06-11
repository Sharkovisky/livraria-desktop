package app;

import dao.AutorDAO;
import model.Autor;

public class teste {

    public static void main(String[] args) {

        Autor a1 = new Autor();

        AutorDAO a1DAO = new AutorDAO();

        a1.setNome("Francicano Aeho");
        a1.setEmail("franciscano.aeho@gmail.com");

        //a1DAO.inserir(a1);

        a1.setId(14);

        //a1DAO.deletar(a1);
        a1DAO.alterar(a1);
    }
}
