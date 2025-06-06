package service.impl;

import exceptions.PermissionDeniedException;
import model.Document;
import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;

import java.util.UUID;

import static constants.ApplicationConstants.PERMISSION_DENIED;

public class UserServiceImpl implements UserService {

    private static UserService instance;
    private final UserRepository userRepository;

    public static UserService getInstance(){
        if(instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserServiceImpl(){
        userRepository = new UserRepositoryImpl();
    }

    @Override
    public User registerUser(String name, String email) {
        User user = createUser(generateId(), name, email);
        userRepository.saveUser(user);
        return user;
    }

    @Override
    public void givePermission(User sourceUser, User targetUser, Document document) throws PermissionDeniedException {
        if (!sourceUser.getEditPermissions().contains(document)) {
            throw new PermissionDeniedException(PERMISSION_DENIED);
        }
        targetUser.getEditPermissions().add(document);
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    private User createUser(String id, String name, String email) {
        return new User(id, name, email);
    }

}

