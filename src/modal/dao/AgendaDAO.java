/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal.dao;

import connection.ConectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Agenda;

/**
 *
 * @author Eduardo
 */
public class AgendaDAO {
    
    public void create(Agenda a){
        Connection con = ConectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO contatos (nomeContato, telefoneContato, celularContato, emailContato, categoria) VALUES (?, ?, ? ,?, ?)");
            stmt.setString(1, a.getNomeContato());
            stmt.setString(2, a.getTelefoneContato());
            stmt.setString(3, a.getCelularContato());
            stmt.setString(4, a.getEmailContato());
            stmt.setString(5, a.getCategoria());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: "+ex);
        }finally{
            ConectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Agenda> read(){
        Connection con = ConectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs= null;
        
        List<Agenda> contatos = new ArrayList<>();
        
        
        try{
            stmt = con.prepareStatement("SELECT * FROM contatos");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Agenda agenda = new Agenda();
                agenda.setIdContato(rs.getInt("idContato"));
                agenda.setNomeContato(rs.getString("nomeContato"));
                agenda.setTelefoneContato(rs.getString("telefoneContato"));
                agenda.setCelularContato(rs.getString("celularContato"));
                agenda.setCategoria(rs.getString("categoria"));
                contatos.add(agenda);
            }
            
        }catch(SQLException ex){
        
              }
        finally{
            ConectionFactory.closeConnection(con, stmt, rs);
        }
        
        return contatos;
    }
}
