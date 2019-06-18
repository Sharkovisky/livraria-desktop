package dao;

import model.Autor;
import model.Contato;
import model.Editora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Contato> listarTodos(){
        String sql = "select * from contatos";
        List<Contato> contatos = new ArrayList<>();

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet resultados = stmt.executeQuery();

            while(resultados.next()){
                Contato contato = new Contato();
                contato.setId(resultados.getInt("id"));
                contato.setNome(resultados.getString("nome"));
                contato.setContato(resultados.getString("contato"));

                contatos.add(contato);
            }

            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return contatos;
    }

    public void alterar(Contato contato){
        String sql = "update contatos set nome = ?, contato = ? where id = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getContato());
            stmt.setInt(3, contato.getId());

            stmt.execute();
            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deletar(Contato contato){
        String sql = "delete from contatos where id = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, contato.getId());

            stmt.execute();
            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Contato listarPorId(int id){
        String sql = "select * from contatos where id = ?";

        Contato contato = new Contato();

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet resultado = stmt.executeQuery();

            resultado.next();

            contato.setId(resultado.getInt("id"));
            contato.setNome(resultado.getString("nome"));
            contato.setContato(resultado.getString("contato"));

            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return contato;
    }
}
