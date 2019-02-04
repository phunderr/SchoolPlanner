package SchoolPlanner.Data;

import java.time.LocalDateTime;

public class LessonPeriod {
    private LocalDateTime lessonStartTime;
    private LocalDateTime lessonEndTime;

    public LessonPeriod(LocalDateTime lessonStartTime, LocalDateTime lessonEndTime) {
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
    }

    public LocalDateTime getLessonStartTime() {
        return lessonStartTime;
    }

    public void setLessonStartTime(LocalDateTime lessonStartTime) {
        this.lessonStartTime = lessonStartTime;
    }

    public LocalDateTime getLessonEndTime() {
        return lessonEndTime;
    }

    public void setLessonEndTime(LocalDateTime lessonEndTime) {
        this.lessonEndTime = lessonEndTime;
    }

    public int getLessonTime(){
        return lessonStartTime.compareTo(lessonEndTime);
    }

}
