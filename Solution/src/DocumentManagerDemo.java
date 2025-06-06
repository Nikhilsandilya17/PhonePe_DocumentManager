import exceptions.PermissionDeniedException;
import model.Document;
import model.User;
import model.Version;
import service.DocumentService;
import service.UserService;
import service.impl.DocumentServiceImpl;
import service.impl.UserServiceImpl;

public class DocumentManagerDemo {
    public static void main(String[] args) throws PermissionDeniedException {
        UserService userService = UserServiceImpl.getInstance();
        DocumentService documentService = DocumentServiceImpl.getInstance();
        //Add new User
        User user1 = userService.registerUser("Nikhil", "nikhil@gcom");
        User user2  = userService.registerUser("Sandy", "sandy@gcom");

        //Create a new document
        Document document1 = documentService.createDocument("First Document", user1);
        Document document2 = documentService.createDocument("Second Document", user2);

        //Update a document having permisson
        documentService.updateDocument(document1, "This is the updated content of the first document.", user1);

        //Update a document not having permisson
        try {
            documentService.updateDocument(document1, "This is the updated content of the first document.", user2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Give permission
        userService.givePermission(user1, user2, document1);

        //Update a document after having permisson
        documentService.updateDocument(document1, "This is the updated content from user2.", user2);
        System.out.println("After update: " + document1.toString());

        documentService.deleteDocument(document2);

        //Get the latest version of the document
        Version version = documentService.getTheLatestVersion(document1);
        System.out.println("Latest Version: " + version.toString());

        //Revert to previous version
        System.out.println("Before reverting to previous version: " + document1.getCurrentVersion().toString());
        documentService.revertToPreviousVersion(document1, user1);
        System.out.println("After reverting to previous version: " + document1.getCurrentVersion().toString());

        //View Document
        documentService.viewDocument(document1);



    }
}
