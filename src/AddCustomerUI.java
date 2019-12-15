import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

public class AddCustomerUI {
    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtCustomerID = new JTextField(10);
    public JTextField txtName = new JTextField(10);
    public JTextField txtPhone = new JTextField(10);
    public JTextField txtAddress = new JTextField(10);


    ProductModel product;
    PurchaseModel purchase;
    CustomerModel customer;

    public AddCustomerUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add Customer");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("CustomerID "));
        line.add(txtCustomerID);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Name "));
        line.add(txtName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Phone "));
        line.add(txtPhone);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Address "));
        line.add(txtAddress);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnAdd);
        line.add(btnCancel);
        view.getContentPane().add(line);

        btnAdd.addActionListener(new AddButtonListerner());
    }

    public void run() {
        customer = new CustomerModel();
        view.setVisible(true);
    }


    class AddButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String id = txtCustomerID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }

            try {
                customer.mCustomerID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }

            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty!");
                return;
            }
            customer.mName = name;

            String phone = txtPhone.getText();
            if (phone.length() == 0) {
                JOptionPane.showMessageDialog(null, "Phone cannot be empty!");
                return;
            }
            customer.mPhone = phone;

            String address = txtAddress.getText();
            if (address.length() == 0) {
                JOptionPane.showMessageDialog(null, "Address cannot be empty!");
                return;
            }
            customer.mAddress = address;


            switch (StoreManager.getInstance().getDataAdapter().saveCustomer(customer)) {
                case SQLiteDataAdapter.CUSTOMER_SAVE_FAILED:
                    JOptionPane.showMessageDialog(null, "Customer NOT added successfully! Duplicate customer ID!");
                default:
                    JOptionPane.showMessageDialog(null, "Customer added successfully!" + customer);
            }
        }
    }

}
