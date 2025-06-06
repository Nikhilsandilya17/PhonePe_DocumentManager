package repository;

import model.Document;

public interface DocumentRepository {
    Document createDocument(Document document);

    void deleteDocument(String id);
}
