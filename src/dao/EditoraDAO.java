package dao;

import model.Editora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {

    private Connection conexao;

    public  EditoraDAO(){
        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(Editora editora){
        String sql = "insert into editoras (nome, site, endereco, bairro, municipio, telefone)" +
                "values (?, ?, ?, ?, ?, ?)";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getSite());
            stmt.setString(3, editora.getEndereco());
            stmt.setString(4, editora.getBairro());
            stmt.setInt(5, editora.getMunicipio());
            stmt.setString(6, editora.getTelefone());

            stmt.execute();

            conexao.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Editora> listarTodos(){
        String sql = "select * from editoras";
        List<Editora> editoras = new ArrayList<>();

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet resultados = stmt.executeQuery();

            while(resultados.next()){
                Editora editora = new Editora();
                editora.setId(resultados.getInt("id"));
                editora.setNome(resultados.getString("nome"));
                editora.setSite(resultados.getString("site"));
                editora.setEndereco(resultados.getString("endereco"));
                editora.setBairro(resultados.getString("bairro"));
                editora.setMunicipio(resultados.getInt("municipio"));
                editora.setTelefone(resultados.getString("telefone"));

                editoras.add(editora);
            }

            conexao.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return editoras;
    }

    public void alterar(Editora editora){
        String sql = "update editoras set nome = ?, site = ?, endereco = ?," +
                "bairro = ?, municipio = ?, telefone = ? where id = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getSite());
            stmt.setString(3, editora.getEndereco());
            stmt.setString(4, editora.getBairro());
            stmt.setInt(5, editora.getMunicipio());
            stmt.setString(6, editora.getTelefone());
            stmt.setInt(7, editora.getId());

            stmt.execute();

            conexao.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deletar(Editora editora){
        String sql = "delete from editoras where id = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, editora.getId());

            stmt.execute();

            conexao.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Editora listarPorId(int id){
        String sql = "select * from editoras where id = ?";

        Editora editora = new Editora();

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet resultado = stmt.executeQuery();

            resultado.next();

            editora.setId(resultado.getInt("id"));
            editora.setNome(resultado.getString("nome"));
            editora.setSite(resultado.getString("site"));
            editora.setEndereco(resultado.getString("endereco"));
            editora.setBairro(resultado.getString("bairro"));
            editora.setMunicipio(resultado.getInt("municipio"));
            editora.setTelefone(resultado.getString("telefone"));

            conexao.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return editora;
    }
}
