package com.mycompany.blackjack;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author farha
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Panel {

    private UserManager userManager;

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel dealerCardsLabel;
    private static JLabel playerCardsLabel;
    private static JLabel dealerTotalLabel;
    private static JLabel playerTotalLabel;
    private static JLabel outcomeLabel;
    private static QuickGame quickGame;

    public static void main(String[] args) {
        // Frame
        frame = new JFrame("Blackjack Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);
        frame.setLayout(new BorderLayout());

        // Panel
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Components
        JLabel label = new JLabel("Welcome to Blackjack!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the current panel
                frame.getContentPane().removeAll();
                // Add the new panel
                frame.add(createGamePanel());
                // Refresh the frame
                refreshFrame();
            }
        });

        JButton tutorialButton = new JButton("Tutorial");
        tutorialButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        tutorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the current panel
                frame.getContentPane().removeAll();
                // Add the new panel
                frame.add(createTutorialPanel());
                // Refresh the frame
                refreshFrame();
            }
        });

        JButton scoreButton = new JButton("Scoreboard");
        scoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the current panel
                frame.getContentPane().removeAll();
                // Add the new panel
                frame.add(createScoreboardPanel());
                // Refresh the frame
                refreshFrame();
            }
        });
        // Setting Sizes
        Dimension buttonSize = new Dimension(150, 50);
        setButtonSize(startButton, buttonSize);
        setButtonSize(tutorialButton, buttonSize);
        setButtonSize(scoreButton, buttonSize);

        // Adding Components to Panel
        panel.add(label, gbc);
        panel.add(startButton, gbc);
        panel.add(tutorialButton, gbc);
        panel.add(scoreButton, gbc);

        // Add the panel to the frame at the center
        frame.add(panel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    private static JPanel createGamePanel() {
        // Panel
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Components
        JLabel gameLabel = new JLabel("Choose an Option:");
        gameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton quickGameButton = new JButton("Quick Game");
        quickGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quickGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Initialize QuickGame
                quickGame = new QuickGame("Player", 1000.0); // Example player initialization

                // Remove the current panel
                frame.getContentPane().removeAll();
                // Add the new panel
                frame.add(createQuickGamePanel());
                // Refresh the frame
                refreshFrame();
            }
        });

        JButton loginButton = new JButton("Log in");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the current panel
                frame.getContentPane().removeAll();
                // Add the new panel
                frame.add(createLogInPanel());
                // Refresh the frame
                refreshFrame();
            }
        });

        JButton signupButton = new JButton("Sign up");
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the current panel
                frame.getContentPane().removeAll();
                // Add the new panel
                frame.add(createSignUpPanel());
                // Refresh the frame
                refreshFrame();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the main panel
                frame.getContentPane().removeAll();
                frame.add(panel);
                refreshFrame();
            }
        });

        // Setting Sizes
        Dimension buttonSize = new Dimension(150, 50);
        setButtonSize(quickGameButton, buttonSize);
        setButtonSize(loginButton, buttonSize);
        setButtonSize(signupButton, buttonSize);
        setButtonSize(backButton, buttonSize);

        // Adding Components to Panel
        gamePanel.add(gameLabel, gbc);
        gamePanel.add(quickGameButton, gbc);
        gamePanel.add(loginButton, gbc);
        gamePanel.add(signupButton, gbc);
        gamePanel.add(backButton, gbc);

        return gamePanel;
    }

    private static JPanel createTutorialPanel() {
        // Panel
        JPanel tutorialPanel = new JPanel();
        tutorialPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Components
        JLabel label = new JLabel("How to play Blackjack!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tutorial = new JLabel("<html>• The goal of Blackjack is to beat the dealer's hand without going over 21.<br>"
                + "• Each player starts with two cards, one of the dealer's cards is hidden until the end.<br>"
                + "• To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn.<br>"
                + "• If you go over 21 you bust, and the dealer wins regardless of the dealer's hand.<br>"
                + "• The dealer will hit until his/her cards total 17 or higher.<br>"
                + "• Cards 2 through 10 are worth their face value. Kings, Queens, and Jacks are worth 10.<br>"
                + "• Aces are worth 1 or 11, whichever makes a better hand.<br>"
                + "• You win by having a higher score than the dealer without busting, or by the dealer busting.</html>");
        tutorial.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the main panel
                frame.getContentPane().removeAll();
                frame.add(panel);
                refreshFrame();
            }
        });

        // Setting Sizes
        Dimension buttonSize = new Dimension(150, 50);
        setButtonSize(backButton, buttonSize);

        // Adding Components to Panel
        tutorialPanel.add(label, gbc);
        tutorialPanel.add(tutorial, gbc);
        tutorialPanel.add(backButton, gbc);

        return tutorialPanel;
    }

    private static JPanel createScoreboardPanel() {
        // Panel
        JPanel scoreBoardPanel = new JPanel();
        scoreBoardPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Components
        JLabel label = new JLabel("Scoreboard");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreBoard = new JLabel();
        scoreBoard.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Assuming userManager is an instance of UserManager
//        userManager.displayScoreboard(scoreBoard);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the main panel
                frame.getContentPane().removeAll();
                frame.add(panel);
                refreshFrame();
            }
        });

        // Setting Sizes
        Dimension buttonSize = new Dimension(150, 50);
        setButtonSize(backButton, buttonSize);

        // Adding Components to Panel
        scoreBoardPanel.add(label, gbc);
        scoreBoardPanel.add(scoreBoard, gbc);
        scoreBoardPanel.add(backButton, gbc);

        return scoreBoardPanel;
    }

    private static JPanel createSignUpPanel() {
        // Panel
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Components
        JLabel label = new JLabel("Sign Up");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField usernameField = new JTextField(20);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    UserManager userManager = new UserManager("users.txt");
                    if (!userManager.userExist(username)) {
                        double startingBalance = 1000.0;
                        userManager.createPlayer(username, password, startingBalance);
                    } else {
                        System.out.println("User already exists.");
                    }
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the main panel
                frame.getContentPane().removeAll();
                frame.add(panel);
                refreshFrame();
            }
        });

        // Setting Sizes
        Dimension buttonSize = new Dimension(150, 50);
        setButtonSize(backButton, buttonSize);
        setButtonSize(signUpButton, buttonSize);

        // Adding Components to Panel
        signUpPanel.add(label, gbc);
        signUpPanel.add(usernameLabel, gbc);
        signUpPanel.add(usernameField, gbc);
        signUpPanel.add(passwordLabel, gbc);
        signUpPanel.add(passwordField, gbc);
        signUpPanel.add(signUpButton, gbc);
        signUpPanel.add(backButton, gbc);

        return signUpPanel;
    }

    private static JPanel createLogInPanel() {
        // Panel
        JPanel logInPanel = new JPanel();
        logInPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Components
        JLabel label = new JLabel("Log In");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField usernameField = new JTextField(20);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton logInButton = new JButton("Log In");
        logInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    UserManager userManager = new UserManager("users.txt");
                    if (userManager.loginPlayer(username, password)) {
                        double balance = userManager.getPlayerBalance(username);
                        frame.getContentPane().removeAll();
                        frame.add(createLoginGamePanel(username, balance));
                        refreshFrame();
                    } else {

                    }
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the main panel
                frame.getContentPane().removeAll();
                frame.add(panel);
                refreshFrame();
            }
        });

        // Setting Sizes
        Dimension buttonSize = new Dimension(150, 50);
        setButtonSize(backButton, buttonSize);
        setButtonSize(logInButton, buttonSize);

        // Adding Components to Panel
        logInPanel.add(label, gbc);
        logInPanel.add(usernameLabel, gbc);
        logInPanel.add(usernameField, gbc);
        logInPanel.add(passwordLabel, gbc);
        logInPanel.add(passwordField, gbc);
        logInPanel.add(logInButton, gbc);
        logInPanel.add(backButton, gbc);

        return logInPanel;
    }

    private static JPanel createQuickGamePanel() {
        // Panel
        JPanel quickGamePanel = new JPanel();
        quickGamePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Initialize the game
        quickGame.resetHands();

        // Dealer's Cards
        dealerCardsLabel = new JLabel("Dealer's cards: " + quickGame.dealer.getHand().getCards().get(0) + ", ?");
        dealerCardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Player's Cards
        playerCardsLabel = new JLabel("Your cards: " + quickGame.player.getHand());
        playerCardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Dealer's Total
        dealerTotalLabel = new JLabel("Dealer total: " + quickGame.dealer.getHand().calculateTotal());
        dealerTotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Player's Total
        playerTotalLabel = new JLabel("Player total: " + quickGame.player.getHand().calculateTotal());
        playerTotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Outcome Label
        outcomeLabel = new JLabel("");
        outcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Hit Button
        JButton hitButton = new JButton("Hit");
        hitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quickGame.player.draw(quickGame.deck);
                updateLabels();
                if (quickGame.player.getHand().calculateTotal() >= 21) {
                    quickGame.dealerTurn();
                    determineOutcomeAndDisplay();
                    disableButtons();
                    addButtonPanel(); // Add play again and go back buttons
                }
                refreshFrame();
            }
        });

        // Stand Button
        JButton standButton = new JButton("Stand");
        standButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quickGame.dealerTurn();
                determineOutcomeAndDisplay();
                disableButtons();
                addButtonPanel(); // Add play again and go back buttons
                refreshFrame();
            }
        });

        // Setting Sizes
        Dimension buttonSize = new Dimension(150, 50);
        setButtonSize(hitButton, buttonSize);
        setButtonSize(standButton, buttonSize);

        // Adding Components to Panel
        quickGamePanel.add(dealerCardsLabel, gbc);
        quickGamePanel.add(dealerTotalLabel, gbc);
        quickGamePanel.add(playerCardsLabel, gbc);
        quickGamePanel.add(playerTotalLabel, gbc);
        quickGamePanel.add(hitButton, gbc);
        quickGamePanel.add(standButton, gbc);
        quickGamePanel.add(outcomeLabel, gbc);

        return quickGamePanel;
    }

    private static JPanel createLoginGamePanel(String username, double balance) {
        // Panel
        JPanel loginGamePanel = new JPanel();
        loginGamePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Initialize the game
        boolean canPlay = true;
        LoginGame loginGame = new LoginGame(username, balance);
        if (canPlay == true) {
            loginGame.play();
            canPlay = false;
        }
        loginGame.resetHands();

        // Username and Balance Labels
        JLabel usernameLabel = new JLabel("Username: " + username);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel balanceLabel = new JLabel("Balance: $" + balance);
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Dealer's Cards Label
        JLabel dealerCardsLabel = new JLabel("Dealer's cards: " + loginGame.dealer.getHand().getCards().get(0) + ", ?");
        dealerCardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Player's Cards Label
        JLabel playerCardsLabel = new JLabel("Your cards: " + loginGame.player.getHand());
        playerCardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Dealer's Total Label
        JLabel dealerTotalLabel = new JLabel("Dealer total: " + loginGame.dealer.getHand().calculateTotal());
        dealerTotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Player's Total Label
        JLabel playerTotalLabel = new JLabel("Player total: " + loginGame.player.getHand().calculateTotal());
        playerTotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Outcome Label
        JLabel outcomeLabel = new JLabel("");
        outcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Hit Button
        JButton hitButton = new JButton("Hit");
        hitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginGame.player.draw(loginGame.deck);
                updateLabels();
                if (loginGame.player.getHand().calculateTotal() >= 21) {
                    loginGame.dealerTurn();
                    determineOutcomeAndDisplay();
                    disableButtons();
                    addButtonPanel(); // Add play again and go back buttons
                }
                refreshFrame();
            }
        });

        // Stand Button
        JButton standButton = new JButton("Stand");
        standButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginGame.dealerTurn();
                determineOutcomeAndDisplay();
                disableButtons();
                addButtonPanel(); // Add play again and go back buttons
                refreshFrame();
            }
        });

        // Setting Button Sizes
        Dimension buttonSize = new Dimension(150, 50);
        hitButton.setPreferredSize(buttonSize);
        standButton.setPreferredSize(buttonSize);

        // Adding Components to Panel
        loginGamePanel.add(dealerCardsLabel, gbc);
        loginGamePanel.add(dealerTotalLabel, gbc);
        loginGamePanel.add(playerCardsLabel, gbc);
        loginGamePanel.add(playerTotalLabel, gbc);
        loginGamePanel.add(hitButton, gbc);
        loginGamePanel.add(standButton, gbc);
        loginGamePanel.add(outcomeLabel, gbc);

        return loginGamePanel;
    }

    private static void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Play Again Button
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(createQuickGamePanel());
                refreshFrame();
            }
        });

        // Go Back Button
        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(createGamePanel());
                refreshFrame();
            }
        });

        // Adding Buttons to Panel
        buttonPanel.add(playAgainButton);
        buttonPanel.add(goBackButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private static void setButtonSize(JButton button, Dimension size) {
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);
    }

    private static void updateLabels() {
        dealerCardsLabel.setText("Dealer's cards: " + quickGame.dealer.getHand().getCards().get(0) + ", ?");
        dealerTotalLabel.setText("Dealer total: " + quickGame.dealer.getHand().calculateTotal());
        playerCardsLabel.setText("Your cards: " + quickGame.player.getHand());
        playerTotalLabel.setText("Player total: " + quickGame.player.getHand().calculateTotal());
    }

    private static void disableButtons() {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JPanel) {
                for (Component innerComp : ((JPanel) comp).getComponents()) {
                    if (innerComp instanceof JButton) {
                        innerComp.setEnabled(false);
                    }
                }
            }
        }
    }

    private static void determineOutcomeAndDisplay() {
        int playerTotal = quickGame.player.getHand().calculateTotal();
        int dealerTotal = quickGame.dealer.getHand().calculateTotal();

        if (playerTotal > 21 || (dealerTotal <= 21 && dealerTotal > playerTotal)) {
            outcomeLabel.setText("XXX Dealer wins! XXX");
        } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
            outcomeLabel.setText("$$$ Player wins! $$$");
        } else {
            outcomeLabel.setText("--- It's a tie! ---");
        }
    }

    private static void refreshFrame() {
        frame.revalidate();
        frame.repaint();
    }
}
