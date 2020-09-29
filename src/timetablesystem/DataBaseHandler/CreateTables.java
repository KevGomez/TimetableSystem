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


//    static String room ="CREATE TABLE IF NOT EXISTS room (" +
//            "  RoomName varchar(60) NOT NULL," +
//            "  Capacity int(11) NOT NULL," +
//            "  BuildingName varchar(60) NOT NULL," +
//            "  Notreservedtime varchar(60),"+
//            "  ID int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT" +
//            ")";

//    static String students = "CREATE TABLE IF NOT EXISTS students (" +
//            "  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
//            "  year varchar(60) NOT NULL," +
//            "  pro varchar(60) NOT NULL," +
//            "  grp_no varchar(60) NOT NULL," +
//            "  grp_id varchar(60) NOT NULL," +
//            "  sgrp_no varchar(60) NOT NULL," +
//            "  sgrp_id varchar(60) NOT NULL" +
//            ")";
//    static String subject ="CREATE TABLE IF NOT EXISTS subject (" +
//          "ID int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"+
//  "year varchar(10) NOT NULL,"+
//  "semester varchar(10) NOT NULL,"+
//  "subject varchar(50) NOT NULL,"+
//  "code varchar(50) NOT NULL,"+
//  "lecturehrs varchar(10) NOT NULL,"+
//  "tutehrs varchar(10) NOT NULL,"+
//  "labhrs varchar(10) NOT NULL,"+
//  "evaluationhrs varchar(10) NOT NULL"+
//            ")";
//
//    static String lecturers ="CREATE TABLE IF NOT EXISTS lecturers (" +
//            "ID int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"+
//  "name varchar(150) NOT NULL,"+
//  "lectureID varchar(50) NOT NULL,"+
//  "faculty varchar(50) NOT NULL,"+
//  "department varchar(50) NOT NULL,"+
//  "center varchar(50) NOT NULL,"+
//  "building varchar(50) NOT NULL,"+
//  "level varchar(50) NOT NULL,"+
//  "rank varchar(20) NOT NULL"+
//                    ")";
    
//    static String tags = "CREATE TABLE IF NOT EXISTS tags (" +
//                    "  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
//            "  name varchar(60) NOT NULL" +
//            ")";




    //SQL Create table

    static String workingday=" CREATE TABLE workingday( " +
            "   idworkingday  INT NOT NULL IDENTITY, " +
            "   day  VARCHAR(100) NULL, " +
            "   workhoure  VARCHAR(45) NULL, " +
            "  PRIMARY KEY ( idworkingday ))";

    static String timeslot ="CREATE TABLE   timeslot  ( " +
                        "   idtimeslot  INT NOT NULL IDENTITY, " +
                        "   timeslot  VARCHAR(45) NULL, " +
                        "  PRIMARY KEY ( idtimeslot )) ";

    static  String lecturer= "CREATE TABLE   lecturer  ( " +
            "   idemployee  INT NOT NULL IDENTITY, " +
            "   name  VARCHAR(45) NULL, " +
            "   level_id  INT NULL, " +
            "   notavaible  VARCHAR(45) NULL, " +
            "   faculty_idfaculty  INT NULL, " +
            "   level  INT NULL, " +
            "   centre  VARCHAR(45) NULL, " +
            "   department  VARCHAR(45) NULL, " +
            "  PRIMARY KEY ( idemployee ))";

    static  String subjects ="CREATE TABLE   subjects  ( " +
            "   idsubjects  INT NOT NULL IDENTITY, " +
            "   name  VARCHAR(45) NULL, " +
            "   code  VARCHAR(45) NULL, " +
            "   lecture_hours  VARCHAR(45) NULL, " +
            "   tutorial_hours  VARCHAR(45) NULL, " +
            "   lab_hours  VARCHAR(45) NULL, " +
            "   year  VARCHAR(45) NULL, " +
            "   evalution_houre  VARCHAR(45) NULL, " +
            "   semester  INT NULL, " +
            "   year_idyear  INT NULL, " +
            "  PRIMARY KEY ( idsubjects ))";

    static String tag="CREATE TABLE   tag  ( " +
            "   idtag  INT NOT NULL IDENTITY, " +
            "   tag  VARCHAR(45) NULL, " +
            "  PRIMARY KEY ( idtag ))";


    static  String buildings ="CREATE TABLE   buildings  ( " +
                            "   idbuildings  INT NOT NULL IDENTITY, " +
                            "   name  VARCHAR(45) NULL, " +
                            "  PRIMARY KEY ( idbuildings ))";

    static  String students_grp="CREATE TABLE   students_grp  ( " +
            "   idstudents_grp  INT NOT NULL, " +
            "   year_sem  VARCHAR(45) NULL, " +
            "   programme  VARCHAR(45) NULL, " +
            "   grp_no  INT NULL, " +
            "   sgrp_no  INT NULL, " +
            "   grp_id  VARCHAR(45) NULL, " +
            "   sgrp_id  VARCHAR(45) NOT NULL, " +
            "  PRIMARY KEY ( idstudents_grp ))";


    static String room="CREATE TABLE   room  ( " +
                    "   idroom  INT NOT NULL  , " +
                    "   roomName  VARCHAR(45) NULL, " +
                    "   capacity  VARCHAR(45) NULL, " +
                    "   buildings_idbuildings  INT NULL, " +
                    "   notreservedtime  VARCHAR(45) NULL, " +
                    "  PRIMARY KEY ( idroom ), " +
                    "  CONSTRAINT  fk_room_buildings1  " +
                    "    FOREIGN KEY ( buildings_idbuildings ) " +
                    "    REFERENCES   buildings  ( idbuildings ) " +
                    "    ON DELETE NO ACTION " +
                    "    ON UPDATE NO ACTION)";

    static String sessions = "CREATE TABLE   sessions  ( " +
            "   idsessions  INT NOT NULL IDENTITY, " +
            "   numberofstudents  INT NULL, " +
            "   duration  VARCHAR(45) NULL, " +
            "   consecutive  VARCHAR(45) NULL, " +
            "   notavailble  VARCHAR(45) NULL, " +
            "   tag_idtag  INT NOT NULL, " +
            "   lecturer_idemployee  INT NOT NULL, " +
            "   subjects_idsubjects  INT NOT NULL, " +
            "   students_grp_idstudents_grp  INT NOT NULL, " +
            "   room_idroom  INT NOT NULL, " +
            "  PRIMARY KEY ( idsessions ,  tag_idtag ,  lecturer_idemployee ,  subjects_idsubjects ,  students_grp_idstudents_grp ,  room_idroom ), " +
            "  CONSTRAINT  fk_sessions_tag1  " +
            "    FOREIGN KEY ( tag_idtag ) " +
            "    REFERENCES   tag  ( idtag ) " +
            "    ON DELETE NO ACTION " +
            "    ON UPDATE NO ACTION, " +
            "  CONSTRAINT  fk_sessions_lecturer1  " +
            "    FOREIGN KEY ( lecturer_idemployee ) " +
            "    REFERENCES   lecturer  ( idemployee ) " +
            "    ON DELETE NO ACTION " +
            "    ON UPDATE NO ACTION, " +
            "  CONSTRAINT  fk_sessions_subjects1  " +
            "    FOREIGN KEY ( subjects_idsubjects ) " +
            "    REFERENCES   subjects  ( idsubjects ) " +
            "    ON DELETE NO ACTION " +
            "    ON UPDATE NO ACTION, " +
            "  CONSTRAINT  fk_sessions_students_grp1  " +
            "    FOREIGN KEY ( students_grp_idstudents_grp ) " +
            "    REFERENCES   students_grp  ( idstudents_grp ) " +
            "    ON DELETE NO ACTION " +
            "    ON UPDATE NO ACTION, " +
            "  CONSTRAINT  fk_sessions_room1  " +
            "    FOREIGN KEY ( room_idroom ) " +
            "    REFERENCES   room  ( idroom ) " +
            "    ON DELETE NO ACTION " +
            "    ON UPDATE NO ACTION)";



static  String workingday_has_timeslot ="CREATE TABLE   workingday_has_timeslot  ( " +
        "   workingday_idworkingday  INT NOT NULL, " +
        "   timeslot_idtimeslot  INT NOT NULL, " +
        "  PRIMARY KEY ( workingday_idworkingday ,  timeslot_idtimeslot ), " +
        "  CONSTRAINT  fk_workingday_has_timeslot_workingday1  " +
        "    FOREIGN KEY ( workingday_idworkingday ) " +
        "    REFERENCES   workingday  ( idworkingday ) " +
        "    ON DELETE NO ACTION " +
        "    ON UPDATE NO ACTION, " +
        "  CONSTRAINT  fk_workingday_has_timeslot_timeslot1  " +
        "    FOREIGN KEY ( timeslot_idtimeslot ) " +
        "    REFERENCES   timeslot  ( idtimeslot ) " +
        "    ON DELETE NO ACTION " +
        "    ON UPDATE NO ACTION) ";

static  String room_notReserved="CREATE TABLE   room_notReserved  ( " +
        "   idroom_notReserved  INT NOT NULL IDENTITY, " +
        "   notRservedTime  INT NULL, " +
        "   room_idroom  INT NOT NULL, " +
        "  PRIMARY KEY ( idroom_notReserved ,  room_idroom ), " +
        "  CONSTRAINT  fk_room_notReserved_room1  " +
        "    FOREIGN KEY ( room_idroom ) " +
        "    REFERENCES   room  ( idroom ) " +
        "    ON DELETE NO ACTION " +
        "    ON UPDATE NO ACTION)";

static String room_has_tag = "CREATE TABLE   room_has_tag  ( " +
        "   room_idroom  INT NOT NULL, " +
        "   tag_idtag  INT NOT NULL, " +
        "  PRIMARY KEY ( room_idroom ,  tag_idtag ), " +
        "  CONSTRAINT  fk_room_has_tag_room1  " +
        "    FOREIGN KEY ( room_idroom ) " +
        "    REFERENCES   room  ( idroom ) " +
        "    ON DELETE NO ACTION " +
        "    ON UPDATE NO ACTION, " +
        "  CONSTRAINT  fk_room_has_tag_tag1  " +
        "    FOREIGN KEY ( tag_idtag ) " +
        "    REFERENCES   tag  ( idtag ) " +
        "    ON DELETE NO ACTION " +
        "    ON UPDATE NO ACTION)";


static  String room_has_lecturer="CREATE TABLE   room_has_lecturer  ( " +
        "   room_idroom  INT NOT NULL, " +
        "   lecturer_idemployee  INT NOT NULL, " +
        "  PRIMARY KEY ( room_idroom ,  lecturer_idemployee ), " +
        "  CONSTRAINT  fk_room_has_lecturer_room1  " +
        "    FOREIGN KEY ( room_idroom ) " +
        "    REFERENCES   room  ( idroom ) " +
        "    ON DELETE NO ACTION " +
        "    ON UPDATE NO ACTION, " +
        "  CONSTRAINT  fk_room_has_lecturer_lecturer1  " +
        "    FOREIGN KEY ( lecturer_idemployee ) " +
        "    REFERENCES   lecturer  ( idemployee ) " +
        "    ON DELETE NO ACTION " +
        "    ON UPDATE NO ACTION) " +
        " ";

static String room_has_students_grp="CREATE TABLE   room_has_students_grp  ( " +
        "   room_idroom  INT NOT NULL, " +
        "   students_grp_idstudents_grp  INT NOT NULL, " +
        "  PRIMARY KEY ( room_idroom ,  students_grp_idstudents_grp ), " +
        "  CONSTRAINT  fk_room_has_students_grp_room1  " +
        "    FOREIGN KEY ( room_idroom ) " +
        "    REFERENCES   room  ( idroom ) " +
        "    ON DELETE NO ACTION " +
        "    ON UPDATE NO ACTION, " +
        "  CONSTRAINT  fk_room_has_students_grp_students_grp1  " +
        "    FOREIGN KEY ( students_grp_idstudents_grp ) " +
        "    REFERENCES   students_grp  ( idstudents_grp ) " +
        "    ON DELETE NO ACTION " +
        "    ON UPDATE NO ACTION)";



}
