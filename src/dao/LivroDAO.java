package dao;

import model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private Connection conexao;

    public LivroDAO(){
        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(Livro livro){
        String sql = "insert into livros (nome, data_lancamento, quantidade, preco, editora_id)" +
                "values (?, ?, ?, ?, ?)";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setDate(2, livro.getData_lancamento());
            stmt.setInt(3, livro.getQuantidade());
            stmt.setFloat(4, livro.getPreco());
            stmt.setInt(5, livro.getEditora_id());

            stmt.execute();

            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }
    public List<Livro> listarTodos(){
        String sql = "select * from livros";
        List<Livro> livros = new ArrayList<>();

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet resultados = stmt.executeQuery();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return listarTodos();
    }
}
