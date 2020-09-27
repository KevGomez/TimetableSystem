package timetablesystem.DataBaseHandler;

public class CreateTables {

    static String timeSolts="CREATE TABLE IF NOT EXISTS `timeslots` (" +
            "  tid int(11) PRIMARY KEY AUTO_INCREMENT ," +
            "  tSlots varchar(100) DEFAULT NULL" +
            ")";

    static String building ="CREATE TABLE IF NOT EXISTS building (" +
            "  BuildingName varchar(60) NOT NULL ," +
            "  ID int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT" +
            ")";

    static String lecture="CREATE TABLE IF NOT EXISTS lecturer (" +
            "  empid varchar(6) PRIMARY KEY," +    // emp id has 6 characters
            "  name varchar(100)," +
            "  faculty varchar(100)," +
            "  department varchar(100)," +
            "  center varchar(100)," +
            "  building varchar(1000)," +
            "  level_id int(11)" +
            ")";

    static String room ="CREATE TABLE IF NOT EXISTS room (" +
            "  rid varchar(20) PRIMARY KEY," +   // eg A307 601-Pclab
            "  islecturehall tinyint(1)," +   // lecture hall true, lab false
            "  capacity int(11) DEFAULT NULL" +
            ")";

    static String student = "CREATE TABLE IF NOT EXISTS student (" +
            "              ID int PRIMARY KEY AUTO_INCREMENT," +
            "              Name varchar(20) NOT NULL, "+               // eg Y1S1.01
            "              AcadamicYear int NOT NULL," +    //  eg 1,2,3,4
            "              AcadamicSemester int NOT NULL," +
            "              SubgroupID int NOT NULL, " + // 1,2,3,4,5
            "              center varchar(100),"+ // center name
            "              capacity int"+ // group capacity <50
            "            )";
    static String subject ="CREATE TABLE IF NOT EXISTS subjects (" +
            "  scodeid varchar(20) PRIMARY KEY," + // subject id eg IT1040-CS
            "  name varchar(100) ," +
            "  year int(11) ," +
            "  semester int(11) ," +
            "  lecture_hours int(11) ," +
            "  tutorial_hours int(11)," +
            "  lab_hours int(11) ," + // 1,2,3,
            "  evalution_houre int(11)" +
            ")";
    static String lecturersubjects="CREATE TABLE IF NOT EXISTS lecturersubjects (" +
            "  lecturerid varchar(20)," +
            "  subjectid varchar(20)," +
            "   UNIQUE KEY lecturerid (lecturerid,subjectid)," +
            "CONSTRAINT FK_lecturerid FOREIGN KEY (lecturerid) REFERENCES lecturer(empid),"+
            "CONSTRAINT FK_subjectid FOREIGN KEY (subjectid) REFERENCES subjects(scodeid)"+
            "  )";


}
