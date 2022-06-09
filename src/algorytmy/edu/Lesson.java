package algorytmy.edu;

import java.util.UUID;

public class Lesson {

    public UUID lesson_id;
    public String lesson_date;
    public String start_time;
    public float duration;
    public float cost;
    public UUID student_uuid;
    public UUID tutor_uuid;

    public Lesson(){}

    public Lesson(UUID lesson_id,String lesson_date, String start_time, UUID student_uuid,
                  UUID tutor_uuid, float duration, float cost){
        this.lesson_id = lesson_id;
        this.lesson_date = lesson_date;
        this.start_time = start_time;
        this.student_uuid = student_uuid;
        this.tutor_uuid = tutor_uuid;
        this.duration = duration;
        this.cost = cost;

    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public UUID getTutor_uuid() {
        return tutor_uuid;
    }

    public void setTutor_uuid(UUID tutor_uuid) {
        this.tutor_uuid = tutor_uuid;
    }

    public UUID getStudent_uuid() {
        return student_uuid;
    }

    public void setStudent_uuid(UUID student_uuid) {
        this.student_uuid = student_uuid;
    }

    public String getLesson_date() {
        return lesson_date;
    }

    public void setLesson_date(String lesson_date) {
        this.lesson_date = lesson_date;
    }

    public UUID getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(UUID lesson_id) {
        this.lesson_id = lesson_id;
    }

    public void RandLesson_id() {
        lesson_id = UUID.randomUUID();
    }

    public int getDay(String date) {
        String[] date_str_list = date.split("\\.");
        return Integer.parseInt(date_str_list[0]);
    }

    public int getMonth(String date) {
        String[] date_str_list = date.split("\\.");
        return Integer.parseInt(date_str_list[1]);
    }

    public int getYear(String date) {
        String[] date_str_list = date.split("\\.");
        return Integer.parseInt(date_str_list[2]);
    }

    public void Print_Lesson_Full(Person student,Person tutor) {


        System.out.println("----------------------------------");
        System.out.println("Id lekcji: " + lesson_id);
        System.out.println("Data lekcji: " + lesson_date);
        System.out.println("Godzina Rozpoczecie: " + start_time);
        System.out.println("Id ucznia: " + student_uuid);
        System.out.println("Dane ucznia: " + student.name + " " + student.surname);
        System.out.println("Id korepetytora: " + tutor_uuid);
        System.out.println("Dane korepetytora: " + tutor.name + " " + tutor.surname);
        System.out.println("Czas zajec: " + duration);
        System.out.println("Koszt zajec: " + cost);


    }

    public void Print_Lesson_Basic() {


        System.out.println("----------------------------------");
        System.out.println("Id lekcji: " + lesson_id);
        System.out.println("Data lekcji: " + lesson_date);
        System.out.println("Godzina Rozpoczecie: " + start_time);
        System.out.println("Id ucznia: " + student_uuid);
        System.out.println("Id korepetytora: " + tutor_uuid);
        System.out.println("Czas zajec: " + duration);
        System.out.println("Koszt zajec: " + cost);

    }
}
