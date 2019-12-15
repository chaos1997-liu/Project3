import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

public class AddUserUI {
    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtUserName = new JTextField(10);
    public JTextField txtPassword = new JTextField(10);
    public JTextField txtFullName = new JTextField(10);
    public JTextField txtUserType = new JTextField(10);

    UserModel user = new UserModel();

    public AddUserUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add User");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("UserName "));
        line.add(txtUserName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Password "));
        line.add(txtPassword);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("FullName "));
        line.add(txtFullName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("UserType "));
        line.add(txtUserType);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnAdd);
        line.add(btnCancel);
        view.getContentPane().add(line);

        btnAdd.addActionListener(new AddButtonListener());
    }

    public void run() {

        view.setVisible(true);
    }


    class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String name = txtUserName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "UserName cannot be empty!");
                return;
            }
            user.mUsername = name;

            String password = txtPassword.getText();
            if (password.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty!");
                return;
            }
            user.mPassword = password;

            String fullname = txtFullName.getText();
            if (fullname.length() == 0) {
                JOptionPane.showMessageDialog(null, "FullName cannot be empty!");
                return;
            }
            user.mFullname = fullname;

            String usertype = txtUserType.getText();
            if (usertype.length() == 0) {
                JOptionPane.showMessageDialog(null, "UserType cannot be empty!");
                return;
            }
            try {
                user.mUserType = Integer.parseInt(usertype);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "UserType is invalid!");
                return;
            }


            switch (StoreManager.getInstance().getDataAdapter().saveUser(user)) {
                case SQLiteDataAdapter.USER_SAVE_FAILED:
                    JOptionPane.showMessageDialog(null, "User NOT added successfully! Duplicate customer ID!");
                default:
                    JOptionPane.showMessageDialog(null, "User added successfully!" + user);
            }
        }
    }

}
