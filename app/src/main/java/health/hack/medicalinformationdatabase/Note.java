package health.hack.medicalinformationdatabase;

import android.content.Intent;

public class Note implements Comparable<Note> {

    String date;

    String time;

    String text;

    String patient;

    String caretaker;

    public Note(String date, String time, String text, String patient, String caretaker) {

        this.date = date;

        this.time = time;

        this.text = text;

        this.patient = patient;

        this.caretaker = caretaker;

    }

    public String getDate() {

        return date;

    }

    public String getTime() {

        return time;

    }

    public String getText() {

        return text;

    }

    public String getCaretaker() {

        return caretaker;

    }

    public String getPatient() {

        return patient;

    }

    public int compareTo(Note other) {

        String dtThis = this.date + this.time;

        String dtOth  = other.date + other.time;

        return -dtThis.compareTo(dtOth);

    }


    public String toString()
    {

            return "Date:  " + date + "      Time: " + time + "\n\n" + "Note:  "
                    + text + "\n\n" + "Caretaker:  " + caretaker;


    }



}

