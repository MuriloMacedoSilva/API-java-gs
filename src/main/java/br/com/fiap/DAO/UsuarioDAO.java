package br.com.fiap.DAO;

import br.com.fiap.beans.Usuario;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Connection minhaConexao;

    // metodo construtor com parâmetro vazio
    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Insert
    public String inserir(Usuario usuario) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("Insert into GS_USUARIO values (?, ?, ?, ?, ?)");
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getSobrenome());
        stmt.setInt(3, usuario.getIdade());
        stmt.setString(4, usuario.getEmail());
        stmt.setString(5, usuario.getSenha());

        stmt.execute();
        stmt.close();

        return "Usuario cadastrado com sucesso!";
    }

    // Delete
    public String deletar(String email) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("Delete from GS_USUARIO where email = ?");
        stmt.setString(1, email);

        stmt.execute();
        stmt.close();

        return  "Usuario deletado com sucesso!";
    }

    // UpDate
    public String atualizar(Usuario usuario) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("Update GS_USUARIO set NOME = ?, SOBRENOME = ?, IDADE = ?, EMAIL = ?, SENHA = ? where EMAIL = ?");
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getSobrenome());
        stmt.setInt(3, usuario.getIdade());
        stmt.setString(4, usuario.getEmail());
        stmt.setString(5, usuario.getSenha());

        stmt.executeUpdate();
        stmt.close();

        return "Usuario atualizado com sucesso!";
    }

    // Select
    public List<Usuario> selecionar() throws SQLException {
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("select * from GS_USUARIO");
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString(1));
            usuario.setSobrenome(rs.getString(2));
            usuario.setIdade(rs.getInt(3));
            usuario.setEmail(rs.getString(4));
            usuario.setSenha(rs.getString(5));
            listaUsuario.add(usuario);
        }
        return listaUsuario;
    }


}