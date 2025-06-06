package model;

import java.time.LocalDateTime;

public class Version {
    private int id;
    private String content;
    private LocalDateTime timestamp;
    private User author;

    public Version(int id, String content, LocalDateTime timestamp, User author) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public User getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", author=" + author.getName() +
                '}';
    }
}
