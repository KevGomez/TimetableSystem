package timetablesystem.DataBaseHandler;

public class CreateTables {
    static String timeSolts="CREATE TABLE IF NOT EXISTS timeslots ( id int PRIMARY KEY AUTO_INCREMENT ,tSlots varchar(100) )";

    static String building ="CREATE TABLE IF NOT EXISTS building (" +
            "  BuildingName int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "  ID int(11) NOT NULL" +
            ")";

    static String lecture="CREATE TABLE  IF NOT EXISTS lecture (" +
            "  ID int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "  Name varchar(60) NOT NULL," +
            "  Faculty varchar(60) NOT NULL," +
            "  Department varchar(60) NOT NULL," +
            "  Center varchar(60) NOT NULL," +
            "  Level int(11) NOT NULL" +
            ")";

    static String room ="CREATE TABLE IF NOT EXISTS room (" +
            "  RoomName varchar(60) NOT NULL," +
            "  Capacity int(11) NOT NULL," +
            "  BuildingName varchar(60) NOT NULL," +
            "  ID int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT" +
            ")";

    static String student = "CREATE TABLE IF NOT EXISTS student (" +
            "  ID int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "  Name varchar(60) NOT NULL," +
            "  AcadamicYear varchar(60) NOT NULL," +
            "  AcadamicSemester varchar(60) NOT NULL," +
            "  SubgroupID int(11) NOT NULL " +
            ")";
    static String subject ="CREATE TABLE IF NOT EXISTS subject (" +
            "  ID int(11) NOT NULL ," +
            "  Name varchar(60) NOT NULL," +
            "  Year varchar(60) NOT NULL," +
            "  Code varchar(60) NOT NULL," +
            "  Lecture int(11) NOT NULL" +
            ")";

}
