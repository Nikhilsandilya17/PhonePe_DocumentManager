package repository.impl;

import model.Document;
import repository.DocumentRepository;

import java.util.HashMap;
import java.util.Map;

public class DocumentRepositoryImpl implements DocumentRepository {

    private final Map<String, Document> documentDatabase;

    public DocumentRepositoryImpl() {
        documentDatabase = new HashMap<>();
    }

    @Override
    public Document createDocument(Document document) {
        System.out.println("Creating document with title: " + document.getTitle());
        documentDatabase.put(document.getId(), document);
        return document;
    }

    @Override
    public void deleteDocument(String id) {
        documentDatabase.remove(id);
    }
}
