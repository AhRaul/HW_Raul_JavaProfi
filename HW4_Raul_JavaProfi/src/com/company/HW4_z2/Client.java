package com.company.HW4_z2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class Client extends JFrame implements ClientUI {

    private JTextField jtf;
    private JTextArea jta;
    private Controller controller;
    String nickInFileName = "0";
    private String parentName = "D:\\javaworkspace\\HW3_Raul_JavaProfi";        //дирректория файла
    private String fileName = "history of " + nickInFileName + ".txt";          //имя файла
    private File history;

    public Client(Controller controller) {

        this.controller = controller;

        setBounds(600, 300, 500, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jta);
        add(jsp, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbSend = new JButton("SEND");
        bottomPanel.add(jbSend, BorderLayout.EAST);
        jtf = new JTextField();
        bottomPanel.add(jtf, BorderLayout.CENTER);

        JTextField loginField = new JTextField();
        JTextField passwordField = new JTextField();
        JButton jbAuth = new JButton("AUTH");
        bottomPanel.add(loginField);
        bottomPanel.add(passwordField);
        bottomPanel.add(jbAuth);
        jbAuth.addActionListener(e -> {
            String login = loginField.getText();
            String password = passwordField.getText();
            AuthMessage authMessage = new AuthMessage(login, password);
            sendMsg(authMessage);
        });

        jbSend.addActionListener(e -> sendMsg());
        jtf.addActionListener(e -> sendMsg());


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                controller.closeConnection();
            }
        });

    }

    public Client() {

    }

    private void sendMsg(AuthMessage authMessage) {
        controller.sendMessage(authMessage);
    }

    private void sendMsg() {
        if (!jtf.getText().trim().isEmpty()) {
            controller.sendMessage(jtf.getText());
            jtf.setText("");
            jtf.grabFocus();
        }
    }

    @Override
    public void addMessage(String w) {
        jta.append(w);
        //дз 3, з.1. запись локальной истории в текстовый файл
        if(!nickInFileName.equals("0")) {
            addMessageToFile(w);
            addMessageToFile("\r\n");   //дз 3, з.1. запись перехода на новую строку в файл
        }
        jta.append("\n");

    }

    /**
     * Метод добавляет сообщение в файл
     * @param w
     */
    public void addMessageToFile(String w) {
        if(!nickInFileName.equals("0")) {
            try {
                history = new File(parentName, fileName);
                history.createNewFile();
                if (!history.isFile())                       //проверить, что у нас есть файл
                {
                    System.out.println("Creating " + history.getPath() + " failed.");
                    return;
                }

                BufferedWriter out = new BufferedWriter(
                        new FileWriter(history.getPath(), true));

                out.write(w);
                out.close();

            } catch (IOException e) {
                System.out.println("Error writing the file " + e);
            }
        } else {
            System.out.println("Вы не зарегестрированы");
        }
    }

    /**
     * дз3 з.2. Метод заполняет поле сотней последних строк файла
     */
    public void readMessageToFile() {
        StringBuffer outStr = new StringBuffer();
        if (history.isFile()) {
            try {
                BufferedReader out = new BufferedReader(
                        new FileReader(fileName));
                String s;
                int i = numOfLines(fileName);
                int j = 0;
                while ((s = out.readLine()) != null) {
                    j++;
                    if (j >= i - 100) {
                        outStr.append(s + "\n");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading the file " + e);
            }
        }
        jta.append(outStr.toString());
    }

    /**
     * Метод, возвращает количество строк в файле
     * @param fileName  имя файла
     * @return
     */
    public int numOfLines (String fileName) {
        int i = 0;
        try {
            BufferedReader out = new BufferedReader(
                    new FileReader(fileName));
            String s;
            while ((s = out.readLine()) != null) {
                i++;
            }

        } catch (IOException e) {
            System.out.println("Error calculation of lines " + e);
        }
        return i;
    }

    @Override
    public void showUI() {
        setVisible(true);
    }
}
