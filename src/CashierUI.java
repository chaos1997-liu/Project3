import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashierUI {
    public UserModel user = null;
    public JFrame view;

    public JButton btnAddCustomer = new JButton("Add New Customer");
    public JButton btnManageCustomer = new JButton("Manage Customer");
    public JButton btnAddPurchase = new JButton("Add New Purchase");
    public JButton btnManagePurchase = new JButton("Manage Purchase");
    public JButton btnManageUser = new JButton("Manage User");

    public CashierUI(UserModel user) {
        this.user=user;
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System - Cashier View");
        view.setSize(400, 300);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Store Management System");

        title.setFont(title.getFont().deriveFont(24.0f));
        view.getContentPane().add(title);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAddCustomer);
        panelButtons.add(btnManageCustomer);
        panelButtons.add(btnAddPurchase);
        panelButtons.add(btnManagePurchase);
        panelButtons.add(btnManageUser);

        view.getContentPane().add(panelButtons);

        btnAddCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddCustomerUI ap = new AddCustomerUI();
                ap.run();
            }
        });

        btnManageCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageCustomerUI ap = new ManageCustomerUI();
                ap.run();
            }
        });

        btnAddPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddPurchaseUI ap = new AddPurchaseUI();
                ap.run();
            }
        });


        btnManagePurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManagePurchaseUI ap = new ManagePurchaseUI();
                ap.run();
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