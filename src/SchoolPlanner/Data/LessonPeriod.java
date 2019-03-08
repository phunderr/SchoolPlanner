package SchoolPlanner.Data;


/**
 * @Author Arno Nagtzaam
 * Draws a PeriodBlock to mark the lessons
 */
public class LessonPeriod {
    private String lessonStartTime;
    private String lessonEndTime;

    public LessonPeriod(String lessonStartTime, String lessonEndTime) {
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
    }

    public String getLessonStartTime() {
        return lessonStartTime;
    }

    public void setLessonStartTime(String lessonStartTime) {
        this.lessonStartTime = lessonStartTime;
    }

    public String getLessonEndTime() {
        return lessonEndTime;
    }

    public void setLessonEndTime(String lessonEndTime) {
        this.lessonEndTime = lessonEndTime;
    }

    public int getLessonTime(){
        return lessonStartTime.compareTo(lessonEndTime);
    }

}
