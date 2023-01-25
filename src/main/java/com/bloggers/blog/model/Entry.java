package com.bloggers.blog.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "entries")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "creationDate")
    private String creationDate;

    @Column(name = "text")
    private String text;

    public Entry() {
    }

    public Entry(long id, String title, String creationDate, String text) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.text = text;
    }

    public Entry(String title, String creationDate, String text) {
        this.title = title;
        this.creationDate = creationDate;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                "title=" + title +
                ", creationDate=" + creationDate +
                ", text='" + text + '\'' +
                '}';
    }
}
