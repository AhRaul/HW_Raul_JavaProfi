package ru.geekbrains.git;

public interface AuthService {
    String authByLoginAndPassword(String login, String password);

    void setNewNick(String nick, String newNick);

    User createOrActivateUser(String login, String password, String nick);

    boolean deactivateUser(String nick);
}
