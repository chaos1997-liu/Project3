import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

public class AddProductUI {
    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtProductID = new JTextField(10);
    public JTextField txtName = new JTextField(10);
    public JTextField txtPrice = new JTextField(10);
    public JTextField txtQuantity = new JTextField(10);


    ProductModel product;
    PurchaseModel purchase;
    CustomerModel customer;

    public AddProductUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add Product");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("ProductID "));
        line.add(txtProductID);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Name "));
        line.add(txtName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Price "));
        line.add(txtPrice);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Quantity "));
        line.add(txtQuantity);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnAdd);
        line.add(btnCancel);
        view.getContentPane().add(line);

        btnAdd.addActionListener(new AddButtonListerner());
    }

    public void run() {
        product = new ProductModel();
        view.setVisible(true);
    }


    class AddButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String id = txtProductID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
                return;
            }

            try {
                product.mProductID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ProductID is invalid!");
                return;
            }

            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty!");
                return;
            }
            product.mName = name;

            String price = txtPrice.getText();
            try {
                product.mPrice = Double.parseDouble(price);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Price is invalid!");
                return;
            }

            String quant = txtQuantity.getText();
            try {
                product.mQuantity = Double.parseDouble(quant);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantity is invalid!");
                return;
            }


            switch (StoreManager.getInstance().getDataAdapter().saveProduct(product)) {
                case SQLiteDataAdapter.PRODUCT_SAVE_FAILED:
                    JOptionPane.showMessageDialog(null, "Product NOT added successfully! Duplicate product ID!");
                default:
                    JOptionPane.showMessageDialog(null, "Product added successfully!" + product);
            }
        }
    }

}
