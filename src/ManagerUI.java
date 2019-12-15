import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerUI {
    public UserModel user = null;
    public JFrame view;

    public JButton btnAddProduct = new JButton("Add Products");
    public JButton btnManageProduct = new JButton("Manage Products");
    public JButton btnSummaryReport = new JButton("Summary Report");
    public JButton btnManageUser = new JButton("Manage User");

    public ManagerUI(UserModel user) {
        this.user=user;
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System - Manager View");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Store Management System");

        title.setFont(title.getFont().deriveFont(24.0f));
        view.getContentPane().add(title);

        JPanel panelButtons = new JPanel(new FlowLayout());

        panelButtons.add(btnAddProduct);
        panelButtons.add(btnManageProduct);
        panelButtons.add(btnSummaryReport);
        panelButtons.add(btnManageUser);

        view.getContentPane().add(panelButtons);

        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddProductUI ui = new AddProductUI();
                ui.run();
            }
        });


        btnManageProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageProductUI ui = new ManageProductUI();
                ui.run();
            }
        });

        btnSummaryReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ProductReportUI ui = new ProductReportUI();
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
