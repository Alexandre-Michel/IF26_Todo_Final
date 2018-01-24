package fr.utt.if26.if26_projet_final;

/**
 * Created by Alexandre on 21/01/2018.
 */

public class ToDo {
    private Integer id;
    private String name;
    private String detail;
    private String date;

    ToDo(Integer id, String name, String detail, String date) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.date = date;
    }

    public Integer getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
