package timetablesystem.DataBaseHandler;

public class CreateTables {


        public  CreateTables(){
            System.out.println("table create");
        }

    static String timeSolts="CREATE TABLE IF NOT EXISTS timeslots ( id int PRIMARY KEY AUTO_INCREMENT ,tSlots varchar(100) )";

    static String building ="CREATE TABLE IF NOT EXISTS building (" +
            "  BuildingName varchar(60) NOT NULL ," +
            "  ID int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT" +
            ")";


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

    static String workingday=" CREATE TABLE workingday(\n" +
            "   idworkingday  INT NOT NULL IDENTITY,\n" +
            "   day  VARCHAR(100) NULL,\n" +
            "   workhoure  VARCHAR(45) NULL,\n" +
            "  PRIMARY KEY ( idworkingday ))";

    static String timeslot ="CREATE TABLE   timeslot  (\n" +
                        "   idtimeslot  INT NOT NULL IDENTITY,\n" +
                        "   timeslot  VARCHAR(45) NULL,\n" +
                        "  PRIMARY KEY ( idtimeslot ))\n";

    static  String lecturers= "CREATE TABLE   lecturers  (\n" +
            "   idemployee  INT NOT NULL IDENTITY,\n" +
            "   name  VARCHAR(45) NULL,\n" +
            "   lectureID VARCHAR(50) NULL,\n" +
            "   faculty  VARCHAR(50) NULL,\n" +
            "   department  VARCHAR(50) NULL,\n" +
            "   center  VARCHAR(50) NULL,\n" +
            "   building  VARCHAR(50) NULL,\n" +
            "   level  VARCHAR(50) NULL,\n" +
            "   rank  VARCHAR(20) NULL,\n" +
            "  PRIMARY KEY ( idemployee ))";

    static  String subjects ="CREATE TABLE   subject  (\n" +
            "   idsubjects  INT NOT NULL IDENTITY,\n" +
            "   year  VARCHAR(50) NULL,\n" +
            "   semester  VARCHAR(50) NULL,\n" +
            "   subject  VARCHAR(50) NULL,\n" +
            "   code  VARCHAR(50) NULL,\n" +
            "   lecturehrs  VARCHAR(10) NULL,\n" +
            "   tutehrs  VARCHAR(10) NULL,\n" +
            "   labhrs  VARCHAR(10) NULL,\n" +
            "   evaluationhrs  VARCHAR(10) NULL,\n" +
            "  PRIMARY KEY ( idsubjects ))";

    static String tag="CREATE TABLE   tag  (\n" +
            "   idtag  INT NOT NULL IDENTITY,\n" +
            "   name  VARCHAR(45) NULL,\n" +
            "  PRIMARY KEY ( idtag ))";


    static  String buildings ="CREATE TABLE   buildings  (\n" +
                            "   idbuildings  INT NOT NULL IDENTITY,\n" +
                            "   name  VARCHAR(45) NULL,\n" +
                            "  PRIMARY KEY ( idbuildings ))";

    static  String students_grp="CREATE TABLE   students_grp  (\n" +
            "   idstudents_grp  INT NOT NULL IDENTITY,\n" +
            "   year  VARCHAR(45) NULL,\n" +
            "   pro  VARCHAR(45) NULL,\n" +
            "   grp_no  VARCHAR(45) NULL,\n" +
            "   grp_id  VARCHAR(45) NULL,\n" +
            "   sgrp_no  VARCHAR(45) NULL,\n" +
            "   sgrp_id  VARCHAR(100) NOT NULL,\n" +
            "  PRIMARY KEY ( idstudents_grp ))";


    static String room="CREATE TABLE   room  (\n" +
                    "   idroom  INT NOT NULL IDENTITY ,\n" +
                    "   roomName  VARCHAR(45) NULL,\n" +
                    "   capacity  VARCHAR(45) NULL,\n" +
                    "   buildings_idbuildings  INT NULL,\n" +
                    "   notreservedtime  VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY ( idroom ),\n" +
                    "  CONSTRAINT  fk_room_buildings1 \n" +
                    "    FOREIGN KEY ( buildings_idbuildings )\n" +
                    "    REFERENCES   buildings  ( idbuildings )\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)";

    static String sessions = "CREATE TABLE   sessions  (\n" +
            "   idsessions  INT NOT NULL IDENTITY,\n" +
            "   numberofstudents  INT NULL,\n" +
            "   duration  VARCHAR(45) NULL,\n" +
            "   consecutive  VARCHAR(45) NULL,\n" +
            "   notavailble  VARCHAR(45) NULL,\n" +
            "   tag_idtag  INT NOT NULL,\n" +
            "   lecturer_idemployee  INT NOT NULL,\n" +
            "   subjects_idsubjects  INT NOT NULL,\n" +
            "   students_grp_idstudents_grp  INT NOT NULL,\n" +
            "   room_idroom  INT ,\n" +
            "   porder  INT NULL,\n" +

            "  PRIMARY KEY ( idsessions ,  tag_idtag ,  lecturer_idemployee ,  subjects_idsubjects ,  students_grp_idstudents_grp ),\n" +
            "  CONSTRAINT  fk_sessions_tag1 \n" +
            "    FOREIGN KEY ( tag_idtag )\n" +
            "    REFERENCES   tag  ( idtag )\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT  fk_sessions_lecturer1 \n" +
            "    FOREIGN KEY ( lecturer_idemployee )\n" +
            "    REFERENCES   lecturers  ( idemployee )\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT  fk_sessions_subjects1 \n" +
            "    FOREIGN KEY ( subjects_idsubjects )\n" +
            "    REFERENCES   subject  ( idsubjects )\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT  fk_sessions_students_grp1 \n" +
            "    FOREIGN KEY ( students_grp_idstudents_grp )\n" +
            "    REFERENCES   students_grp  ( idstudents_grp )\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT  fk_sessions_room1 \n" +
            "    FOREIGN KEY ( room_idroom )\n" +
            "    REFERENCES   room  ( idroom )\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION)";



static  String workingday_has_timeslot ="CREATE TABLE   workingday_has_timeslot  (\n" +
        "   workingday_idworkingday  INT NOT NULL,\n" +
        "   timeslot_idtimeslot  INT NOT NULL,\n" +
        "  PRIMARY KEY ( workingday_idworkingday ,  timeslot_idtimeslot ),\n" +
        "  CONSTRAINT  fk_workingday_has_timeslot_workingday1 \n" +
        "    FOREIGN KEY ( workingday_idworkingday )\n" +
        "    REFERENCES   workingday  ( idworkingday )\n" +
        "    ON DELETE NO ACTION\n" +
        "    ON UPDATE NO ACTION,\n" +
        "  CONSTRAINT  fk_workingday_has_timeslot_timeslot1 \n" +
        "    FOREIGN KEY ( timeslot_idtimeslot )\n" +
        "    REFERENCES   timeslot  ( idtimeslot )\n" +
        "    ON DELETE NO ACTION\n" +
        "    ON UPDATE NO ACTION)\n";

static  String room_notReserved="CREATE TABLE   room_notReserved  (\n" +
        "   idroom_notReserved  INT NOT NULL IDENTITY,\n" +
        "   notRservedTime  INT NULL,\n" +
        "   room_idroom  INT NOT NULL,\n" +
        "  PRIMARY KEY ( idroom_notReserved ,  room_idroom ),\n" +
        "  CONSTRAINT  fk_room_notReserved_room1 \n" +
        "    FOREIGN KEY ( room_idroom )\n" +
        "    REFERENCES   room  ( idroom )\n" +
        "    ON DELETE NO ACTION\n" +
        "    ON UPDATE NO ACTION)";

static String room_has_tag = "CREATE TABLE   room_has_tag  (\n" +
        "   room_idroom  INT NOT NULL,\n" +
        "   tag_idtag  INT NOT NULL,\n" +
        "  PRIMARY KEY ( room_idroom ,  tag_idtag ),\n" +
        "  CONSTRAINT  fk_room_has_tag_room1 \n" +
        "    FOREIGN KEY ( room_idroom )\n" +
        "    REFERENCES   room  ( idroom )\n" +
        "    ON DELETE NO ACTION\n" +
        "    ON UPDATE NO ACTION,\n" +
        "  CONSTRAINT  fk_room_has_tag_tag1 \n" +
        "    FOREIGN KEY ( tag_idtag )\n" +
        "    REFERENCES   tag  ( idtag )\n" +
        "    ON DELETE NO ACTION\n" +
        "    ON UPDATE NO ACTION)";


static  String room_has_lecturer="CREATE TABLE   room_has_lecturer  (\n" +
        "   room_idroom  INT NOT NULL,\n" +
        "   lecturer_idemployee  INT NOT NULL,\n" +
        "  PRIMARY KEY ( room_idroom ,  lecturer_idemployee ),\n" +
        "  CONSTRAINT  fk_room_has_lecturer_room1 \n" +
        "    FOREIGN KEY ( room_idroom )\n" +
        "    REFERENCES   room  ( idroom )\n" +
        "    ON DELETE NO ACTION\n" +
        "    ON UPDATE NO ACTION,\n" +
        "  CONSTRAINT  fk_room_has_lecturer_lecturer1 \n" +
        "    FOREIGN KEY ( lecturer_idemployee )\n" +
        "    REFERENCES   lecturer  ( ID )\n" +
        "    ON DELETE NO ACTION\n" +
        "    ON UPDATE NO ACTION)\n" +
        "\n";

static String room_has_students_grp="CREATE TABLE   room_has_students_grp  (\n" +
        "   room_idroom  INT NOT NULL,\n" +
        "   students_grp_idstudents_grp  INT NOT NULL,\n" +
        "  PRIMARY KEY ( room_idroom ,  students_grp_idstudents_grp ),\n" +
        "  CONSTRAINT  fk_room_has_students_grp_room1 \n" +
        "    FOREIGN KEY ( room_idroom )\n" +
        "    REFERENCES   room  ( idroom )\n" +
        "    ON DELETE NO ACTION\n" +
        "    ON UPDATE NO ACTION,\n" +
        "  CONSTRAINT  fk_room_has_students_grp_students_grp1 \n" +
        "    FOREIGN KEY ( students_grp_idstudents_grp )\n" +
        "    REFERENCES   students_grp  ( idstudents_grp )\n" +
        "    ON DELETE NO ACTION\n" +
        "    ON UPDATE NO ACTION)";


}
