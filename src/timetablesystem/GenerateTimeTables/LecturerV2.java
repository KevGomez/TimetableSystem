package timetablesystem.GenerateTimeTables;

public class LecturerV2 {

    String name,empid,department,center;
    int levelid;

    public LecturerV2(String name, String empid, String department, String center, int levelid) {
        this.name = name;
        this.empid = empid;
        this.department = department;
        this.center = center;
        this.levelid = levelid;
    }


    public String getName() {
        return name;
    }

    public String getEmpid() {
        return empid;
    }

    public String getDepartment() {
        return department;
    }

    public String getCenter() {
        return center;
    }

    public int getLevelid() {
        return levelid;
    }
}
