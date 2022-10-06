package dao;

import model.Site;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SiteDAO extends DAO{
    public SiteDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Site site) {
		boolean status = false;
		try {
			String sql = "INSERT INTO site (link, logo, nome) "
		               + "VALUES ('" + site.getLink() + "', '"
		               + site.getLogo() + "', " 
                       + site.getNome() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Site get(int id) {
		Site site = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM site WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 site = new Site(rs.getInt("id"), rs.getString("link"), rs.getString("logo"), rs.getString("nome"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return site;
	}
	
	
	public List<Site> get() {
		return get("");
	}

	
	public List<Site> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Site> getOrderByNome() {
		return get("nome");		
	}
	
	
	private List<Site> get(String orderBy) {
		List<Site> sites = new ArrayList<Site>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM site" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Site s = new Site(rs.getInt("id"), rs.getString("link"), rs.getString("logo"), rs.getString("nome"));
	            sites.add(s);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return sites;
	}
	
	
	public boolean update(Site site) {
		boolean status = false;
		try {  
			String sql = "UPDATE site SET link = '" + site.getLink() + "', "
					   + "logo = '" + site.getLogo() + "', " 
                       + "nome = '" + site.getNome() + "'" + "WHERE id = " + site.getID();
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
			st.executeUpdate("DELETE FROM site WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
