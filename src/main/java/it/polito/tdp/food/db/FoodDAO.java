package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.food.model.Adiacenza;
import it.polito.tdp.food.model.Condiment;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Portion;

public class FoodDAO {
	public List<Food> listAllFoods(){
		String sql = "SELECT * FROM food" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Food(res.getInt("food_code"),
							res.getString("display_name")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Condiment> listAllCondiments(){
		String sql = "SELECT * FROM condiment" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Condiment> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Condiment(res.getInt("condiment_code"),
							res.getString("display_name"),
							res.getDouble("condiment_calories"), 
							res.getDouble("condiment_saturated_fats")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Portion> listAllPortions(){
		String sql = "SELECT * FROM portion" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Portion> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Portion(res.getInt("portion_id"),
							res.getDouble("portion_amount"),
							res.getString("portion_display_name"), 
							res.getDouble("calories"),
							res.getDouble("saturated_fats"),
							res.getInt("food_code")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Food> listVertici(int port){
		String sql = "select f.`display_name`,f.food_code, count(*)\n" + 
				"from portion as p, food as f\n" + 
				"where f.`food_code`=p.`food_code`\n" + 
				"group by f.`display_name`, f.food_code\n" + 
				"having count(*)<=?" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, port);
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Food(res.getInt(2),
							res.getString(1)
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Adiacenza> listAdiacenze(int por){
		String sql = "select fc.`food_code`, fc2.`food_code`, avg(c.`condiment_calories`) as cal\n" + 
				"from food_condiment as fc, food_condiment as fc2, (select p.`food_code` as c\n" + 
				"													from portion as p\n" + 
				"													group by p.`food_code`\n" + 
				"													having count(*)<=?) as val, condiment as c\n" + 
				"where fc.`condiment_code`=fc2.`condiment_code` and fc.`food_code`>fc2.`food_code` \n" + 
				"	and val.c=fc2.food_code and fc.`condiment_code`=c.`condiment_code` and fc2.`condiment_code`=c.condiment_code\n" + 
				"group by fc.`food_code`, fc2.`food_code`" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, por);
			List<Adiacenza> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Adiacenza(res.getInt(1),
							res.getInt(2), res.getLong(3)));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
}
