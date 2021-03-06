package dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Autor;
import model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    private Connection conexao;

    public AutorDAO(){
        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(Autor autor){
        String sql = "insert into autores (nome, email)" +
                "values (?, ?)";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getEmail());

            ContatoDAO cDAO = new ContatoDAO();
            cDAO.inserirAutor(autor);

            stmt.execute();
            conexao.close();

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    public ObservableList listarTodos(){
           String sql = "select * from autores";
           List<Autor> autores = new ArrayList<>();

           try{
               PreparedStatement stmt = conexao.prepareStatement(sql);

               ResultSet resultados = stmt.executeQuery();

               while(resultados.next()){
                   Autor autor = new Autor();
                   autor.setId(resultados.getInt("id"));
                   autor.setNome(resultados.getString("nome"));
                   autor.setEmail(resultados.getString("email"));

                   autores.add(autor);
               }

               conexao.close();

           }catch(SQLException e){
               throw new RuntimeException(e);
           }
           ObservableList data = FXCollections.observableList(autores);
           return data;
    }

    public void alterar(Autor autor){
            String sql = "update autores set nome = ?, email = ? where id = ?";

            try{
                PreparedStatement stmt = conexao.prepareStatement(sql);

                stmt.setString(1, autor.getNome());
                stmt.setString(2, autor.getEmail());
                stmt.setInt(3, autor.getId());

                stmt.execute();
                conexao.close();

            }catch(SQLException e){
                throw new RuntimeException(e);
            }
    }

    public void deletar(Autor autor){
        String sql = "delete from autores where id = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, autor.getId());

            stmt.execute();
            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    public Autor listarPorId(int id){
            String sql = "select * from autores where id = ?";

            Autor autor = new Autor();

            try{
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, id);

                ResultSet resultado = stmt.executeQuery();

                resultado.next();

                autor.setId(resultado.getInt("id"));
                autor.setNome(resultado.getString("nome"));
                autor.setEmail(resultado.getString("email"));

                conexao.close();

            }catch(SQLException e){
                throw new RuntimeException(e);
            }
            return autor;
    }
}
