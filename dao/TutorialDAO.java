package dao;

import model.Tutorial;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TutorialDAO extends DAO{
    public TutorialDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Tutorial tutorial) {
		boolean status = false;
		try {
			String sql = "INSERT INTO tutorial (passos, video, titulo, foto) "
		               + "VALUES ('" + tutorial.getPassos() + "', '"
		               + tutorial.getVideo() + "', "
                       + tutorial.getTitulo() + "', " 
                       + tutorial.getFoto() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Tutorial get(int id) {
		Tutorial tutorial = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM tutorial WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 tutorial = new Tutorial(rs.getInt("id"), rs.getString("passos"), rs.getString("video"), rs.getString("titulo"), rs.getString("foto"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return tutorial;
	}
	
	
	public List<Tutorial> get() {
		return get("");
	}

	
	public List<Tutorial> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Tutorial> getOrderByNome() {
		return get("nome");		
	}
	
	
	private List<Tutorial> get(String orderBy) {
		List<Tutorial> tutoriais = new ArrayList<Tutorial>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM tutorial" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Tutorial t = new Tutorial(rs.getInt("id"), rs.getString("passos"), rs.getString("video"), rs.getString("titulo"), rs.getString("foto"));
	            tutoriais.add(t);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return tutoriais;
	}
	
	
	public boolean update(Tutorial tutorial) {
		boolean status = false;
		try {  
			String sql = "UPDATE tutorial SET passos = '" + tutorial.getPassos() + "', "
					   + "video = '" + tutorial.getVideo() + "', "
                       + "titulo = '" + tutorial.getTitulo() + "', " 
                       + "foto = '" + tutorial.getFoto() + "'" + "WHERE id = " + tutorial.getId();
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
			st.executeUpdate("DELETE FROM tutorial WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
