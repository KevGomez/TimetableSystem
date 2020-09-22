/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

/**
 *
 * @author Vidula
 */
public class LecturerDB {
    
    
    
     private static Connection connect() {
       
       Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spm?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            return conn;
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }

	}
   
   
   public int createPatientAsForm(String name,String lectureID,String faculty,String department,String center,String building,String level,String rank )
	{
            int count=0;
        String sql="insert into lecturers (name, lectureID, faculty, department , center , building, level, rank) values (?,?,?,?,?,?,?,?)";
        
        
        try {
			Connection con = connect();
			
			

			PreparedStatement st =con.prepareStatement(sql);
			
			st.setString(1,name);
			st.setString(2,lectureID);
			st.setString(3,faculty);
			st.setString(4,department);
			st.setString(5,center);
			st.setString(6,building);
			st.setString(7,level);
			st.setString(8,rank);
			
			
			count=st.executeUpdate();
			
                        if(count>0)
                        {
                            con.close();
                            return 1;
                        }else{
                            return 0;
                        }
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("connection value"+e);
		}
       return 0;
        
        
        
        
        
        
        }
   
   
   public ObservableList<LecturerModel> getAllData(){
        ObservableList<LecturerModel> lecturelist = FXCollections.observableArrayList();
        Connection conn = connect();
        
        String query = "SELECT * FROM lecturers";
        Statement st;
        ResultSet rs;
        Button button=null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            LecturerModel lecturers;
            while(rs.next()){
               // button[1]=new Button();
                lecturers = new LecturerModel(rs.getInt("ID"),rs.getString("name"),rs.getString("lectureID"),rs.getString("faculty"),rs.getString("department"),rs.getString("center"),rs.getString("building"),rs.getString("level"),rs.getString("rank"));
                lecturelist.add(lecturers);
            }
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return lecturelist;
    }
   
   
   public int DeleteLecturer(int id)
 {  
	
	 
	  try 
	  {   
		  Connection con = connect();
	 
                  if(con==null)
                  {
                      return 0;
                  }
	     
	  
	      //create a prepared statement   
	      String sql = "delete from lecturers where ID=?"; 
	      PreparedStatement st =con.prepareStatement(sql); 
	      
	      //binding values    
	      //st.setInt(1, Integer.parseInt(patientID)); 
	 
              st.setInt(1, id);
	      // execute the statement
	      st.execute(); 
	      con.close(); 
	 
	      
	   } 
	   catch (Exception e)
	    {   
		  
		  System.err.println(e.getMessage());
		} 
	 return 1;
	  
	}
   
   
   
   public int UpdatePatient(int id,
		                    String name,String lectureID,String faculty,String department,String center,String building,String level,String rank ) {


		try {
			Connection con = connect();
			if (con == null) {
				return 0;
			}
			// create a prepared statement
			String query = "update lecturers set name = ? , lectureID = ? , faculty = ?,department = ?, center = ? , building = ?,level = ? , rank = ?  where ID = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, name);
			preparedStmt.setString(2, lectureID);
			preparedStmt.setString(3, faculty);
			preparedStmt.setString(4, department);
			preparedStmt.setString(5, center);
			preparedStmt.setString(6, building);
			preparedStmt.setString(7, level);
			preparedStmt.setString(8, rank);
			preparedStmt.setInt(9, id);
			//preparedStmt.setInt(10, Integer.parseInt(patientID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
	return 1;
			 
		}
		catch (Exception e)
		{
			
			 
			System.err.println(e.getMessage());
		}
			
		return 0;
			 
	} 
   
   
    
}
