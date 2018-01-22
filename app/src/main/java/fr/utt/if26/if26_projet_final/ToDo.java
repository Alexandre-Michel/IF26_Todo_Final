package fr.utt.if26.if26_projet_final;

/**
 * Created by Alexandre on 21/01/2018.
 */

public class ToDo {
    String name;
    String detail;
    String date;

    public ToDo(String name, String detail, String date) {
        this.name = name;
        this.detail = detail;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
