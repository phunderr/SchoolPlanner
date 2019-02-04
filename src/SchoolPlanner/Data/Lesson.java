package SchoolPlanner.Data;

public class Lesson {
    private String subject;
    private Teacher teacher;
    private LessonPeriod lessonPeriod;

    public Lesson(String subject, Teacher teacher, LessonPeriod lessonPeriod) {
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
}
