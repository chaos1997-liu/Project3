import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageUserUI {

    public UserModel user = null;

    public JFrame view;

    public JButton btnLoad = new JButton("Load User");
    public JButton btnSave = new JButton("Save User");

    public JTextField txtUserName = new JTextField(10);
    public JTextField txtPassword = new JTextField(10);
    public JTextField txtFullName = new JTextField(10);
    public JTextField txtUserType = new JTextField(10);


    public ManageUserUI(UserModel user) {

        this.user = user;

        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Manage User Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnLoad);
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

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


        btnLoad.addActionListener(new LoadButtonListerner());

        btnSave.addActionListener(new SaveButtonListener());

    }

    public void run() {
        view.setVisible(true);
    }

    class LoadButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Gson gson = new Gson();

            if(user.mUserType==3){
                String name = txtUserName.getText();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "UserName cannot be empty!");
                    return;
                }

                System.out.println(name);


                user = StoreManager.getInstance().getDataAdapter().loadUser(user.mUsername);

                if (user == null) {
                    JOptionPane.showMessageDialog(null, "User NOT exists!");
                } else {
                    txtPassword.setText("*************");
                    txtFullName.setText("*************");
                    txtUserType.setText(Integer.toString(user.mUserType));
                }
            }else {
                user = StoreManager.getInstance().getDataAdapter().loadUser(user.mUsername);

                if (user == null) {
                    JOptionPane.showMessageDialog(null, "User NOT exists!");
                } else {
                    txtUserName.setText(user.mUsername);
                    txtPassword.setText(user.mPassword);
                    txtFullName.setText(user.mFullname);
                    txtUserType.setText("*************");
                }
            }



        }
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Gson gson = new Gson();

            if(user.mUserType==3){

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

            }else {
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
            }


            int res = StoreManager.getInstance().getDataAdapter().saveUser(user);

            if (res == IDataAdapter.USER_SAVE_FAILED)
                JOptionPane.showMessageDialog(null, "User is NOT saved successfully!");
            else
                JOptionPane.showMessageDialog(null, "User is SAVED successfully!");

        }
    }
}