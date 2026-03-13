import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class BankAccount {
    String name;
    String accNo;
    double balance;

    BankAccount(String name, String accNo, double balance) {
        this.name = name;
        this.accNo = accNo;
        this.balance = balance;
    }
}

public class BankGUI {

    static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bank Account Application");
        frame.setSize(400, 380);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton createBtn = new JButton("Create Account");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton displayBtn = new JButton("Display Account");
        JButton exitBtn = new JButton("Exit");

        frame.add(createBtn);
        frame.add(depositBtn);
        frame.add(withdrawBtn);
        frame.add(displayBtn);
        frame.add(exitBtn);

        // CREATE ACCOUNT
        createBtn.addActionListener(e -> {
            JTextField nameField = new JTextField();
            JTextField accField = new JTextField();
            JTextField amountField = new JTextField();

            Object[] input = {
                    "Name:", nameField,
                    "Account Number:", accField,
                    "Opening Amount:", amountField
            };

            int option = JOptionPane.showConfirmDialog(frame, input, "Create Account", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String name = nameField.getText().trim();
                String acc = accField.getText().trim();
                double amt = Double.parseDouble(amountField.getText().trim());
                accounts.add(new BankAccount(name, acc, amt));
                JOptionPane.showMessageDialog(frame, "Account Created Successfully!");
            }
        });

        // DEPOSIT
        depositBtn.addActionListener(e -> {
            JTextField accField = new JTextField();
            JTextField amountField = new JTextField();
            Object[] input = {
                    "Account Number:", accField,
                    "Amount:", amountField
            };

            int option = JOptionPane.showConfirmDialog(frame, input, "Deposit", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String acc = accField.getText().trim();
                double amt = Double.parseDouble(amountField.getText().trim());

                for (BankAccount a : accounts) {
                    if (a.accNo.trim().equals(acc)) {
                        a.balance += amt;
                        JOptionPane.showMessageDialog(frame, "Deposit Successful!");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Account Not Found!");
            }
        });

        // WITHDRAW
        withdrawBtn.addActionListener(e -> {
            JTextField accField = new JTextField();
            JTextField amountField = new JTextField();
            Object[] input = {
                    "Account Number:", accField,
                    "Amount:", amountField
            };

            int option = JOptionPane.showConfirmDialog(frame, input, "Withdraw", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String acc = accField.getText().trim();
                double amt = Double.parseDouble(amountField.getText().trim());

                for (BankAccount a : accounts) {
                    if (a.accNo.trim().equals(acc)) {
                        if (a.balance >= amt) {
                            a.balance -= amt;
                            JOptionPane.showMessageDialog(frame, "Withdraw Successful!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Insufficient Balance!");
                        }
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Account Not Found!");
            }
        });

        // DISPLAY 
        displayBtn.addActionListener(e -> {
            String acc = JOptionPane.showInputDialog(frame, "Enter Account Number:");
            if (acc != null && !acc.trim().isEmpty()) {
                acc = acc.trim();
                for (BankAccount a : accounts) {
                    if (a.accNo.trim().equals(acc)) {
                        JOptionPane.showMessageDialog(frame,
                                "Name: " + a.name +
                                        "\nAccount Number: " + a.accNo +
                                        "\nBalance: " + a.balance,
                                "Account Details", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Account Not Found!");
            }
        });

        // EXIT
        exitBtn.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }
}
