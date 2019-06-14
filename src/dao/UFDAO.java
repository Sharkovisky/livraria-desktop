package dao;

import model.UF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UFDAO {

    private Connection conexao;

    public UFDAO(){
        conexao = new ConnectionFactory().getConnection();
    }

    public void inserir (UF uf){
        String sql = "insert into UF (nome, sigla)" +
                "values (?, ?)";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, uf.getNome());
            stmt.setString(2, uf.getSigla());

            stmt.execute();
            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<UF> listarTodos(){
        String sql = "select * from autores";
        List<UF> ufs = new ArrayList<>();

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet resultados = stmt.executeQuery();

            while(resultados.next()){
                UF uf = new UF();
                uf.setId(resultados.getInt("id"));
                uf.setNome(resultados.getString("nome"));
                uf.setSigla(resultados.getString("sigla"));

                ufs.add(uf);
            }

            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return ufs;
    }

    public void alterar(UF uf){
        String sql = "update UF set nome = ?, sigla = ?" +
                "where id = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, uf.getNome());
            stmt.setString(2, uf.getSigla());
            stmt.setInt(3, uf.getId());

            stmt.execute();
            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deletar(UF uf){
        String sql = "delete from UF where id = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, uf.getId());

            stmt.execute();
            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public UF listarPorId(int id){
        String sql = "select * from UF where id = ?";

        UF uf = new UF();

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet resultado = stmt.executeQuery();

            resultado.next();

            uf.setId(resultado.getInt("id"));
            uf.setNome(resultado.getString("nome"));
            uf.setSigla(resultado.getString("sigla"));

            conexao.close();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return uf;
    }
}
