package timetablesystem.DataBaseHandler;


import java.sql.*;
import java.util.Random;

public class DBHandler {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private String url,user,pass;


    public DBHandler(){
        url="jdbc:mysql://localhost/timeTableSystem";
        user="root";
        pass="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(url,user,pass);
            st=con.createStatement();
            System.out.println("DB Connected");

        } catch (Exception ex) {
            System.out.println("Error "+ ex);
        }
    }

    public void createTables(){
        try {

            st.executeUpdate(CreateTables.timeSolts) ;

            st.executeUpdate(CreateTables.building) ;
            st.executeUpdate(CreateTables.lecture) ;
            st.executeUpdate(CreateTables.room) ;
            st.executeUpdate(CreateTables.subject) ;
            st.executeUpdate(CreateTables.student) ;
            st.executeUpdate(CreateTables.lecturersubjects);

            System.out.println("Table Created SuccessFully");
        }
        catch (Exception e) {
            System.out.println(e);

        }
    }


    public void insertSampleData(){
        try {
            Random rand = new Random();
            int capacity[]= {100,150};

            for(int i=10;i<60;i++){
                // add Lecturer
                String query1="INSERT INTO `lecturer`(`empid`, `name`, `faculty`, `department`, `center`, `building`, `level_id`) VALUES ('0000"+i+"','Perera "+i+"','computing','SE','MORATUWA','Building',"+rand.nextInt(8)+")";
              //  DbInsert(query1);
                // add Subject
                String query2_1="INSERT INTO `subjects`(`scodeid`, `name`, `year`, `semester`, `lecture_hours`, `tutorial_hours`, `lab_hours`, `evalution_houre`) VALUES ('IT10"+i+"-SE','name',"+rand.nextInt(5)+","+rand.nextInt(3)+","+rand.nextInt(2)+",2,2,1)";
            //    DbInsert(query2_1);

                String query2_2="INSERT INTO `subjects`(`scodeid`, `name`, `year`, `semester`, `lecture_hours`, `tutorial_hours`, `lab_hours`, `evalution_houre`) VALUES ('IT20"+i+"-SE','name',"+rand.nextInt(5)+","+rand.nextInt(3)+","+rand.nextInt(2)+",2,2,1)";
            //    DbInsert(query2_2);

                //
                String query3_1="INSERT INTO `lecturersubjects`(`lecturerid`, `subjectid`) VALUES ('0000"+i+"','IT10"+i+"-SE')";
                DbInsert(query3_1);
                String query3_2="INSERT INTO `lecturersubjects`(`lecturerid`, `subjectid`) VALUES ('0000"+i+"','IT20"+i+"-SE')";
           //     DbInsert(query3_2);


                //
                Boolean even=(i%10==2||i%10==4);
                String query4_1;
                if (!even){
                    query4_1="INSERT INTO `room`(`rid`, `islecturehall`, `capacity`) VALUES ('A"+(i/10)+"0"+(i%10)+"',1,"+capacity[rand.nextInt(2)]+")";
                }else{
                    query4_1="INSERT INTO `room`(`rid`, `islecturehall`, `capacity`) VALUES ('A"+(i/10)+"0"+(i%10)+"',0,50)";
                }

               // DbInsert(query4_1);



            }

            for(int i=1;i<5;i++){
                for(int j=1;j<9;j++) {
                  String query5_1 = "INSERT INTO `student`(`ID`, `Name`, `AcadamicYear`, `AcadamicSemester`, `SubgroupID`, `center`, `capacity`) VALUES (null, 'Y"+i+"S1.0"+j+"',  "+ i +",  1,  "+j+",  'MORATUWA',  " + (rand.nextInt(50 - 40) + 40) + ")";
                  DbInsert(query5_1);
                }
                for(int j=1;j<9;j++) {
                    String query5_2 = "INSERT INTO `student`(`ID`, `Name`, `AcadamicYear`, `AcadamicSemester`, `SubgroupID`, `center`, `capacity`) VALUES (null,'Y"+i+"S2.0"+j+"'," + i + ",2,"+j+",'MORATUWA'," + (rand.nextInt(50 - 40) + 40) + ")";
                    DbInsert(query5_2);
                }



            }



        }catch (Exception e){
                System.out.println("Insert Error "+e);
        }
    }


    public String DbInsert(String query){
        String r;
        try {
            st.executeUpdate(query) ;
            r="Success";
        }
        catch (Exception e) {
            r= e.toString();
        }
        return r;
    }


    public  ResultSet DbGet(String query){
        ResultSet r=null;

        try {
            r= st.executeQuery(query);

        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

}
