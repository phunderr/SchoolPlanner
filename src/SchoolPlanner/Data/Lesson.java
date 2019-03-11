package SchoolPlanner.Data;


import java.io.Serializable;

/**
 * @author Arno Nagtzaam
 * An object lesson that contains a subject, teacher, lesson Period, aClass and a classroom
 */
public class Lesson implements Serializable {
    private Subject subject;
    private Teacher teacher;
    private LessonPeriod lessonPeriod;
    private ClassName aClass;
    private Classroom classroom;

    public Lesson(Subject subject, Teacher teacher, LessonPeriod lessonPeriod, Classroom classroom, ClassName aClass) {
        this.subject = subject;
        this.teacher = teacher;
        this.lessonPeriod = lessonPeriod;
        this.classroom = classroom;
        this.aClass = aClass;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public LessonPeriod getLessonPeriod() {
        return lessonPeriod;
    }

    public void setLessonPeriod(LessonPeriod lessonPeriod) {
        this.lessonPeriod = lessonPeriod;
    }

    public ClassName getaClass () {
        return aClass;
    }

    public void setaClass (ClassName aClass) {
        this.aClass = aClass;
    }

    public Classroom getClassroom () {
        return classroom;
    }

    public void setClassroom (Classroom classroom) {
        this.classroom = classroom;
    }
}
