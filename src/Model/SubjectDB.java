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
public class SubjectDB {
    
    
          private static Connection connect() {
       
       Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/timeTableSystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            return conn;
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }

	}
       
       
       
       
       
        public int createSubject(String year,String semester,String subject,String code,String lecturehrs,String tutehrs,String labhrs,String evaluationhrs )
	{
            int count=0;
        String sql="insert into subject (year, semester, subject, code , lecturehrs , tutehrs, labhrs, evaluationhrs) values (?,?,?,?,?,?,?,?)";
        
        
        try {
			Connection con = connect();
			
			

			PreparedStatement st =con.prepareStatement(sql);
			//System.out.println(st);
		
			st.setString(1,year);
			st.setString(2,semester);
			st.setString(3,subject);
			st.setString(4,code);
			st.setString(5,lecturehrs);
			st.setString(6,tutehrs);
			st.setString(7,labhrs);
			st.setString(8,evaluationhrs);
			
			
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
   
       
   
        
           public ObservableList<SubjectModel> getAllSubjectData(){
        ObservableList<SubjectModel> subjectlist = FXCollections.observableArrayList();
        Connection conn = connect();
        
        String query = "SELECT * FROM subject";
        Statement st;
        ResultSet rs;
        Button button=null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            SubjectModel subjects;
            while(rs.next()){
               // button[1]=new Button();
                subjects = new SubjectModel(rs.getInt("ID"),rs.getString("year"),rs.getString("subject"),rs.getString("lecturehrs"),rs.getString("labhrs"),rs.getString("semester"),rs.getString("code"),rs.getString("tutehrs"),rs.getString("evaluationhrs"));
                subjectlist.add(subjects);
            }
        }catch(Exception e){
                e.printStackTrace();
        }
        
        return subjectlist;
    }
           
           
           
             public int DeleteSubject(int id)
 {  
	
	 
	  try 
	  {   
		  Connection con = connect();
	 
                  if(con==null)
                  {
                      return 0;
                  }
	     
	  
	      //create a prepared statement   
	      String sql = "delete from subject where ID=?"; 
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
             
             
             
             
             public int UpdateSubject(int id,
		                    String year,String semester,String subject,String code,String lecturehrs,String tutehrs,String labhrs,String evaluationhrs ) {


		try {
			Connection con = connect();
			if (con == null) {
				return 0;
			}
			// create a prepared statement
			String query = "update subject set year = ? , semester = ? , subject = ?,code = ?, lecturehrs = ? , tutehrs = ?,labhrs = ? , evaluationhrs = ?  where ID = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, year);
			preparedStmt.setString(2, semester);
			preparedStmt.setString(3, subject);
			preparedStmt.setString(4, code);
			preparedStmt.setString(5, lecturehrs);
			preparedStmt.setString(6, tutehrs);
			preparedStmt.setString(7, labhrs);
			preparedStmt.setString(8, evaluationhrs);
			preparedStmt.setInt(9, id);
			//preparedStmt.setInt(10, Integer.parseInt(patientID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
	
			 
		}
		catch (Exception e)
		{
			
			 
			System.err.println(e.getMessage());
		}
			
		return 0;
			 
	} 
   
    
    
    
}
