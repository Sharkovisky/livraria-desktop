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
        String sql = "insert into livros (titulo, data_lancamento, quantidade, preco, editora_id)" +
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

            while(resultados.next()){
                Livro livro = new Livro();
                livro.setId(resultados.getInt("id"));
                livro.setTitulo(resultados.getString("titulo"));
                livro.setData_lancamento(resultados.getDate("data_lancamento"));
                livro.setQuantidade(resultados.getInt("quantidade"));
                livro.setPreco(resultados.getFloat("preco"));
                livro.setEditora_id(resultados.getInt("editora_id"));

                livros.add(livro);
            }

            conexao.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return livros;
    }

    public void alterar(Livro livro){
        String sql = "update livros set titulo = ?, data_lancamento = ?, quantidade = ?, " +
                "preco = ?, editora_id = ? where id = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, livro.getTitulo());
            stmt.setDate(2, livro.getData_lancamento());
            stmt.setInt(3, livro.getQuantidade());
            stmt.setFloat(4, livro.getPreco());
            stmt.setInt(5, livro.getEditora_id());
            stmt.setInt(6, livro.getId());

            stmt.execute();

            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deletar(Livro livro){
        String sql = "delete from livros where id = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, livro.getId());

            stmt.execute();

            conexao.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Livro listarPorId(int id){
        String sql = "select * from livros where id = ?";

        Livro livro = new Livro();

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet resultado = stmt.executeQuery();

            resultado.next();

            livro.setId(resultado.getInt("id"));
            livro.setTitulo(resultado.getString("titulo"));
            livro.setData_lancamento(resultado.getDate("data_lancamento"));
            livro.setQuantidade(resultado.getInt("quantidade"));
            livro.setPreco(resultado.getFloat("preco"));
            livro.setEditora_id(resultado.getInt("editora_id"));

            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return livro;
    }
}
