package it.polito.tdp.ufo.db;

import java.util.List;


public class TestDB {

	public static void main(String[] args) {
		SightingsDAO dao = new SightingsDAO();
		List<String> formeUFO = dao.readShapes();
		int i = 0;
		for (String forma : formeUFO) {
			int count = dao.countByShape(forma);
			System.out.println(++i + ". Ufo di forma "+forma+" sono: "+count);
		}
	}

}
