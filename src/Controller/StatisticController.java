package Controller;

import Model.Lecture;
import Model.Student;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.chart.PieChart;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StatisticController implements Initializable {

    @FXML public PieChart lecture_center_pie;
    @FXML public PieChart lecture_level_pie;
    @FXML public PieChart lecture_departmet_pie;

    @FXML public  PieChart studet_accadamicyear_pichart;
    @FXML public  PieChart studet_accadamicsemester_pichart;

    @FXML public  PieChart subject_year_pichart;
    @FXML public  PieChart subject_code_pichart;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Lecture lecture=new Lecture();
        Student student=new Student();


        try {
            //lecture pie chart
            lecture_center_pie.setData(lecture.getPiCtartData(lecture.GroupBy("faculty","lecturers","ID")));
            lecture_level_pie.setData(lecture.getPiCtartData(lecture.GroupBy("department","lecturers","ID")));
            lecture_departmet_pie.setData(lecture.getPiCtartData(lecture.GroupBy("level","lecturers","ID")));

            //student pie chart
            studet_accadamicyear_pichart.setData(lecture.getPiCtartData(student.GroupBy("AcadamicYear","student","ID")));
            studet_accadamicsemester_pichart.setData(lecture.getPiCtartData(student.GroupBy("AcadamicSemester","student","ID")));
            //subject pie chart
            subject_year_pichart.setData(lecture.getPiCtartData(student.GroupBy("Year","subject","ID")));
            subject_code_pichart.setData(lecture.getPiCtartData(student.GroupBy("Code","subject","ID")));

            subject_year_pichart.setTitle("Academic Year");
            subject_code_pichart.setTitle("Subject Code");

            studet_accadamicyear_pichart.setTitle("Academic Year");
            studet_accadamicsemester_pichart.setTitle("Academic  Semester");








        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
