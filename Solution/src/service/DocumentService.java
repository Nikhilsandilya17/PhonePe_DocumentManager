package service;

import exceptions.PermissionDeniedException;
import model.Document;
import model.User;
import model.Version;

public interface DocumentService {
    Document createDocument(String title, User user);

    void updateDocument(Document document, String s, User user1) throws PermissionDeniedException;

    void deleteDocument(Document document2);

    Version getTheLatestVersion(Document document);

    void revertToPreviousVersion(Document document, User user) throws PermissionDeniedException;

    void viewDocument(Document document);
}
