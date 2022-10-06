package dao;

import model.Adm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AdmDAO extends DAO {	
	public AdmDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Adm adm) {
		boolean status = false;
		try {
			String sql = "INSERT INTO adm (nome, senha) "
		               + "VALUES ('" + adm.getNome() + "', '"
		               + adm.getSenha() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Adm get(int id) {
		Adm adm = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM adm WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 adm = new Adm(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return adm;
	}
	
	
	public List<Adm> get() {
		return get("");
	}

	
	public List<Adm> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Adm> getOrderByNome() {
		return get("nome");		
	}
	
	
	private List<Adm> get(String orderBy) {
		List<Adm> adms = new ArrayList<Adm>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM adm" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Adm a = new Adm(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"));
	            adms.add(a);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return adms;
	}
	
	
	public boolean update(Adm adm) {
		boolean status = false;
		try {  
			String sql = "UPDATE adm SET nome = '" + adm.getNome() + "', "
					   + "senha = '" + adm.getSenha() + "'" + "WHERE id = " + adm.getId();
			PreparedStatement st = conexao.prepareStatement(sql);
		  
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM adm WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}