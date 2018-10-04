package ru.geekbrains.git;

public interface Controller {
    void sendMessage(String msg);

    void closeConnection();

    void showUI(ClientUI clientUI);

    void sendMessage(Message authMessage);
}
