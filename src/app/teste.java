package app;

import dao.AutorDAO;
import dao.EditoraDAO;
import dao.LivroDAO;
import model.Autor;
import model.Editora;
import model.Livro;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class teste {

    public static void main(String[] args) {

        /*TESTE AUTOR

        Autor a1 = new Autor();
        AutorDAO a1DAO = new AutorDAO();

        a1.setNome("Francicano Aeho");
        a1.setEmail("franciscano.aeho@gmail.com");

        a1DAO.inserir(a1);

        a1.setId(14);

        a1DAO.deletar(a1);
        a1DAO.alterar(a1);

        System.out.println(a1DAO.listarPorId(1).getNome());
        System.out.println(a1DAO.listarTodos());

        */

        /*TESTE LIVRO
        Livro l1 = new Livro();
        LivroDAO l1DAO = new LivroDAO();


        DateTimeFormatter formatoBrasil = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoMySql = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate d1 = LocalDate.parse("12/12/2018", formatoBrasil);

        LocalDate d3 = LocalDate.parse("13/01/2019", formatoBrasil);

        System.out.println(d1.format(formatoBrasil));
        Date d2 = java.sql.Date.valueOf(d1);
        Date d4 = java.sql.Date.valueOf(d3);

        l1.setTitulo("As aventuras de 3,14");
        //l1.setData_lancamento(d2);
        l1.setQuantidade(18);
        l1.setPreco(15);
        l1.setEditora_id(1);

        //l1DAO.inserir(l1);

        l1.setData_lancamento(d4);
        l1.setId(11);

        //l1DAO.alterar(l1);

        l1DAO.deletar(l1);

        //System.out.println(l1DAO.listarPorId(11).getData_lancamento().toLocalDate().format(formatoBrasil));
        */

        Editora ed1 = new Editora();
        EditoraDAO ed1DAO = new EditoraDAO();

        ed1.setNome("Faz Livros");
        ed1.setSite("www.fazlivros.com.br");
        ed1.setEndereco("Rua 720");
        ed1.setBairro("Marcos Freire");
        ed1.setMunicipio(1);
        ed1.setTelefone("+55 (69) 3321-4175");

        //ed1DAO.inserir(ed1);
        ed1.setId(6);

        //ed1DAO.alterar(ed1);
        ed1DAO.deletar(ed1);

    }
}
