package service;

import exceptions.PermissionDeniedException;
import model.Document;
import model.User;

public interface UserService {
    User registerUser(String name, String email);

    void givePermission(User sourceUser, User targetuser, Document document) throws PermissionDeniedException;
}
