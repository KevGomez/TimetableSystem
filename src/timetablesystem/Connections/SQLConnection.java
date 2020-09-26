package timetablesystem.Connections;



import java.sql.*;

public class SQLConnection {
    private  final String dbname="timetablesystem";
    private  final String dbusername="root";
    private  final String dbpassword="";
    private  final String url="jdbc:mysql://localhost:3306/timetablesystem";
    Statement statement;


    public SQLConnection()  {
         Connection connection=null;
        try {
            connection= (Connection) DriverManager.getConnection(url,dbusername,dbpassword);
            statement=connection.createStatement();
            System.out.println("Db Connected!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void InsertQuery(String query)  {
        try {
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet SelectQuery(String query){
        try {
            ResultSet resultSet =statement.executeQuery(query);
            return  resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

}
