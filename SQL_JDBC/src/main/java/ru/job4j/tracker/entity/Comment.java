package ru.job4j.tracker.entity;

/**
 * Class Comment.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 24.04.2018.
 */
public class Comment {
    /**
     * Id comment.
     */
    private Integer id;
    /**
     * Text comment.
     */
    private String textComment;

    /**
     * Constructor with set text comment.
     * @param textComment text comment.
     */
    public Comment(String textComment) {
        this.textComment = textComment;
    }

    /**
     * Default constructor.
     */
    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }
}