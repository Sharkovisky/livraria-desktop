package dao;

import model.Autor;
import model.Contato;
import model.Editora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContatoDAO {

    private Connection conexao;

    public ContatoDAO(){
        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(Contato contato, int selecao){
        String sql = "";
        if(selecao==1) {
            sql = "insert into contatos (nome, contato)" +
                    "select nome, email from autores" +
                    "where nome = ?";
        }else if (selecao==2){
            sql = "insert into contatos (nome, contato)" +
                    "select nome, site from editoras" +
                    "where nome = ?";
        }

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, contato.getNome());

            stmt.execute();
            conexao.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void inserirAutor(Autor autor){
        String sql = "insert into contatos (nome, contato)" +
                    "select nome, email from autores " +
                    "where email = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, autor.getEmail());

            stmt.execute();
            conexao.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void inserirEditora(Editora editora){
        String sql = "insert into contatos (nome, contato)" +
                "select nome, site from editoras " +
                "where site = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, editora.getSite());

            stmt.execute();
            conexao.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
