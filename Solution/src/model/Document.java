package model;

import java.util.ArrayList;
import java.util.List;

public class Document {
    private String id;
    private String title;
    private List<Version> history;
    private Version currentVersion;

    public Document(String id, String title) {
        this.id = id;
        this.title = title;
        history = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Version> getHistory() {
        return history;
    }

    public void setHistory(List<Version> history) {
        this.history = history;
    }

    public Version getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(Version currentVersion) {
        this.currentVersion = currentVersion;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", history=" + history +
                ", currentVersion=" + currentVersion.toString() +
                '}';
    }
}
