package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SightingsDAO {
		
	public List<String> readShapes(){
	
		try {
			Connection conn = DBConnect.getConnection();
			//System.out.println("Connection ok..");
			String sql = "select distinct  s.shape " +
					"from ufo_sightings.sighting s " + 
					"where s.shape <> '' " +
					"order by shape asc";	

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();
			List<String> formeUFO = new ArrayList<>();
			while (res.next()) {
				String forme = res.getString("shape");
				formeUFO.add(forme);
			}
			//System.out.println("Gli Ufo di forma '"+shape+"' sono: "+count);
			st.close();
			conn.close();
			return formeUFO;
		} catch (SQLException e) {
			throw new RuntimeException("Data base Error in readShapes",e);
		}		
	}
	
	public int countByShape(String shape) {	
		try {
			Connection conn = DBConnect.getConnection();
			//System.out.println("Connection ok..");			
			String sql2 = "select count(*) as cnt from ufo_sightings.sighting s where s.shape = ?";
			PreparedStatement st2 = conn.prepareStatement(sql2);
			st2.setString(1, shape);
			ResultSet res2 = st2.executeQuery();
			res2.first();
			int count = res2.getInt("cnt");
			//System.out.println("Gli Ufo di forma '"+shape+"' sono: "+count);
			st2.close();
			conn.close();
			//System.out.println("Connection closed..");
			return count;
		} catch (SQLException e) {
			throw new RuntimeException("Data base Error in countShapes",e);
		}
	}
}
