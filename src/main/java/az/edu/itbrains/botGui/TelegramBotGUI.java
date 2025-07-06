package az.edu.itbrains.services;

import az.edu.itbrains.bots.GameBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class TelegramBotGUI {
//    private JFrame frame;
//    private JTextField groupLinkField;
//    private JButton joinButton;
//    private GameBot bot;
//
//    public TelegramBotGUI(GameBot bot) {
//        this.bot = bot;
//        initialize();
//    }
//
//    private void initialize() {
//        frame = new JFrame("GameBot Control Panel");
//        frame.setSize(500, 300);  // Pəncərə ölçüsünü tənzimləyirik
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(2, 2));
//
//        panel.add(new JLabel("Qrup Linki:"));
//        groupLinkField = new JTextField();
//        panel.add(groupLinkField);
//
//        joinButton = new JButton("Join Group");
//        panel.add(joinButton);
//
//        frame.add(panel, BorderLayout.CENTER);
//
//        joinButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                joinGroup();
//            }
//        });
//
//        frame.setVisible(true);
//    }
//
//    private void joinGroup() {
//        String groupLink = groupLinkField.getText().trim();
//
//        if (groupLink.isEmpty()) {
//            JOptionPane.showMessageDialog(frame, "Zəhmət olmasa qrup linkini daxil edin!", "Xəta", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        openWebpage(groupLink);
//    }
//
//    private void openWebpage(String url) {
//        try {
//            Desktop.getDesktop().browse(new URI(url));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
//            GameBot bot = new GameBot();
//            telegramBotsApi.registerBot(bot);
//
//            javax.swing.SwingUtilities.invokeLater(() -> new TelegramBotGUI(bot));
//
//        } catch (Exception e) {
//            System.out.println(" Xəta baş verdi: " + e.getMessage());
//        }
//    }
}
