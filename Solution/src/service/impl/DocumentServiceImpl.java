package service.impl;

import exceptions.PermissionDeniedException;
import model.Document;
import model.User;
import model.Version;
import repository.DocumentRepository;
import repository.impl.DocumentRepositoryImpl;
import service.DocumentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static constants.ApplicationConstants.NO_PREVIOUS_VERSION;
import static constants.ApplicationConstants.PERMISSION_DENIED;
import static constants.ApplicationConstants.UPDATING_DOCUMENT;
import static constants.ApplicationConstants.V1_VERSION;

public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private static DocumentService documentService;

    private DocumentServiceImpl() {
        documentRepository = new DocumentRepositoryImpl();
    }

    public static DocumentService getInstance() {
        if (documentService == null) {
            documentService = new DocumentServiceImpl();
        }
        return documentService;
    }

    @Override
    public Document createDocument(String title, User user) {
        Document document = getDocument(title);
        Version version = getVersion(document, V1_VERSION, user);
        setUserPermissionsAndDocumentHistory(user, document, version);
        documentRepository.createDocument(document);
        return document;
    }

    private void setUserPermissionsAndDocumentHistory(User user, Document document, Version version) {
        user.getEditPermissions().add(document);
        setDocumentHistoryAndCurrentVersion(document, version);
    }

    private void setDocumentHistoryAndCurrentVersion(Document document, Version version) {
        document.getHistory().add(version);
        document.setCurrentVersion(version);
    }

    private Document getDocument(String title) {
        return new Document(generateId(), title);
    }

    private Version getVersion(Document document, String v1Version, User user) {
        return new Version(document.getHistory().size() + 1, v1Version, LocalDateTime.now(), user);
    }

    @Override
    public void updateDocument(Document document, String newContent, User user) throws PermissionDeniedException {
        if (user.getEditPermissions().contains(document)) {
            Version newVersion = getVersion(document, V1_VERSION + newContent, user);
            setDocumentHistoryAndCurrentVersion(document, newVersion);
            System.out.println(UPDATING_DOCUMENT + document.getTitle() + " and Author " +document.getCurrentVersion().getAuthor().getName());
        } else {
            throw new PermissionDeniedException(user.getName() + PERMISSION_DENIED);
        }
    }

    @Override
    public void deleteDocument(Document document2) {
        documentRepository.deleteDocument(document2.getId());
    }

    @Override
    public Version getTheLatestVersion(Document document) {
        return document.getCurrentVersion();
    }

    @Override
    public void revertToPreviousVersion(Document document, User user) throws PermissionDeniedException {
        if(user.getEditPermissions().contains(document)){
            List<Version> versionHistory = document.getHistory();
            if (versionHistory.size() > 1) {
                Version previousVersion = versionHistory.get(versionHistory.size() - 2);
                document.setCurrentVersion(previousVersion);
            } else {
                throw new IllegalStateException(NO_PREVIOUS_VERSION);
            }
        }
        else{
            throw new PermissionDeniedException(user.getName() + PERMISSION_DENIED);
        }
        
    }

    @Override
    public void viewDocument(Document document) {
        System.out.println(document.getCurrentVersion().getContent());
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

}
