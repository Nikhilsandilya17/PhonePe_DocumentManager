package model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String id;
    private String name;
    private String email;
    private Set<Document> editPermissions;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        editPermissions = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Document> getEditPermissions() {
        return editPermissions;
    }

    public void setEditPermissions(Set<Document> editPermissions) {
        this.editPermissions = editPermissions;
    }
}
