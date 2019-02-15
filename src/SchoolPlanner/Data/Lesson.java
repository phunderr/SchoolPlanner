package SchoolPlanner.Data;

public class Lesson {
    private String subject;
    private String teacher;
    private LessonPeriod lessonPeriod;
    private String aClass;
    private Classroom classroom;

    public Lesson(String subject, String teacher, LessonPeriod lessonPeriod, Classroom classroom) {
        this.subject = subject;
        this.teacher = teacher;
        this.lessonPeriod = lessonPeriod;
        this.classroom = classroom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public LessonPeriod getLessonPeriod() {
        return lessonPeriod;
    }

    public void setLessonPeriod(LessonPeriod lessonPeriod) {
        this.lessonPeriod = lessonPeriod;
    }

    public String getaClass () {
        return aClass;
    }

    public void setaClass (String aClass) {
        this.aClass = aClass;
    }

    public Classroom getClassroom () {
        return classroom;
    }

    public void setClassroom (Classroom classroom) {
        this.classroom = classroom;
    }
}
