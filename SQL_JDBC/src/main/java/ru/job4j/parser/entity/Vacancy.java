package ru.job4j.parser.entity;

import java.time.LocalDateTime;

/**
 * Class Vacancy.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 30.04.2018.
 */
public class Vacancy {
    /**
     * Id vacancy.
     */
    private Integer id;
    /**
     * Name vacancy.
     */
    private String name;
    /**
     * Text vacancy.
     */
    private String text;
    /**
     * Author vacancy.
     */
    private String author;
    /**
     * Url vacancy.
     */
    private String url;
    /**
     * Date vacancy.
     */
    private LocalDateTime date;

    /**
     * Default constructor.
     */
    public Vacancy() {
    }

    /**
     * Constructor with parameters.
     * @param name name vacancy.
     * @param text text vacancy.
     * @param author author vacancy.
     * @param url url vacancy.
     * @param date date vacancy.
     */
    public Vacancy(String name, String text, String author, String url, LocalDateTime date) {
        this.name = name;
        this.text = text;
        this.author = author;
        this.url = url;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", text='" + text + '\''
                + ", author='" + author + '\''
                + ", url='" + url + '\''
                + ", date=" + date
                + '}';
    }
}