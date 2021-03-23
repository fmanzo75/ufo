package it.polito.tdp.ufo.db;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=root";
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			System.out.println("Connection ok..");
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
			st.close();
			System.out.println(formeUFO);
			
			String sql2 = "select count(*) as cnt from ufo_sightings.sighting s where s.shape = ?";
			String shapeScelta = "circle";
			PreparedStatement st2 = conn.prepareStatement(sql2);
			st2.setString(1, shapeScelta);
			ResultSet res2 = st2.executeQuery();
			res2.first();
			int count = res2.getInt("cnt");
			System.out.println("Gli Ufo di forma '"+shapeScelta+"' sono: "+count);
			st2.close();
			conn.close();
			System.out.println("Connection closed..");
		} catch (SQLException e) {
			// Stampa l'eccezione
			e.printStackTrace();
		}

	}

}
