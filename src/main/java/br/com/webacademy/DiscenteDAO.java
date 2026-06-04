package br.com.webacademy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscenteDAO {
    
    public static void salvar(Discente discente) throws Exception {

        var sql = "INSERT INTO discentes (nome, matricula, curso, periodo_atual) VALUES (?, ?, ?, ?)";

        try (var conexao = Conexao.obterConexao(); 
                var stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, discente.nome());
                stmt.setLong(2, discente.matricula());
                stmt.setString(3, discente.curso());
                stmt.setInt(4, discente.periodo_atual());
                stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public List<Discente> buscarTodos() throws Exception {
        
        var sql = "select * from discentes";
        List<Discente> discentes = new ArrayList<>();
        try (var conexao = Conexao.obterConexao(); 
                var stmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Discente discente = new Discente(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getLong("matricula"),
                        rs.getString("curso"),
                        rs.getInt("periodo_atual"));
                    discentes.add(discente);
                }
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return discentes;
    }

    public Discente buscarPorId(Long id) throws Exception {
        
        var sql = "select * from discentes where id = ?";
        Discente discente = null;
        try (var conexao = Conexao.obterConexao(); 
                var stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    discente = new Discente(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getLong("matricula"),
                        rs.getString("curso"),
                        rs.getInt("periodo_atual"));
                }
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return discente;
    }

    public void atualizar(Discente discente) throws Exception {
        var sql = "update discentes set nome = ?, matricula = ?, curso = ?, periodo_atual = ? where id = ?";
        try (var conexao = Conexao.obterConexao(); 
                var stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, discente.nome());
            stmt.setLong(2, discente.matricula());
            stmt.setString(3, discente.curso());
            stmt.setInt(4, discente.periodo_atual());
            stmt.setLong(5, discente.id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public void excluir(Long id) throws Exception {
        var sql = "delete from discentes where id = ?";
        try (var conexao = Conexao.obterConexao(); 
                var stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}