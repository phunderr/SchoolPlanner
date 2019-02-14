package SchoolPlanner.Data;

public class Lesson {
    private String subject;
    private String teacher;
    private LessonPeriod lessonPeriod;
    private Class aClass;

    public Lesson(String subject, String teacher, LessonPeriod lessonPeriod) {
        this.subject = subject;
        this.teacher = teacher;
        this.lessonPeriod = lessonPeriod;
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

    public Class getaClass () {
        return aClass;
    }

    public void setaClass (Class aClass) {
        this.aClass = aClass;
    }
}
