import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI {

    public UserModel user = null;

    public JFrame view;

    public JButton btnAddUser = new JButton("Add User");
    public JButton btnManageUser = new JButton("Manage User");

    public AdminUI(UserModel user) {
        this.user=user;

        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System - Admin View");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Store Management System");

        title.setFont(title.getFont().deriveFont(24.0f));
        view.getContentPane().add(title);

        JPanel panelUser = new JPanel(new FlowLayout());
        view.getContentPane().add(panelUser);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAddUser);
        panelButtons.add(btnManageUser);

        view.getContentPane().add(panelButtons);


        btnAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddUserUI ui = new AddUserUI();
                ui.view.setVisible(true);
            }
        });

        btnManageUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageUserUI ui = new ManageUserUI(user);
                ui.view.setVisible(true);
            }
        });

    }
}