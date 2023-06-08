package UniversitySystem_03.Entities;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "_03_students")
public class _03_Student extends _03_Person {
    @Column(name = "average_grade", nullable = false)

    private float averageGrade;

    private int attendance;

    @ManyToMany
    @JoinTable(
            name = "_03_student_courses",
            joinColumns = @JoinColumn(name = "students_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id", referencedColumnName = "id")
    )
    private Set<_03_Course> course;

    public _03_Student() {
        super();
    }

    public _03_Student(String firstName, String lastName, String phoneNumber, float averageGrade, int attendance) {
        super(firstName, lastName, phoneNumber);

        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }

    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
