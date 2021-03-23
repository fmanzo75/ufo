package it.polito.tdp.ufo.db;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=root";
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			System.out.println("Connection ok..");
			
			Statement st = conn.createStatement();
			
			String sql = "select distinct  s.shape " +
					"from ufo_sightings.sighting s " + 
					"where s.shape <> '' " +
					"order by shape asc";
			
			ResultSet res = st.executeQuery(sql);
			List<String> formeUFO = new ArrayList<>();
			while (res.next()) {
				String forme = res.getString("shape");
				formeUFO.add(forme);
			}
			st.close();
			System.out.println(formeUFO);
			conn.close();
			System.out.println("Connection closed..");
		} catch (SQLException e) {
			// Stampa l'eccezione
			e.printStackTrace();
		}

	}

}
