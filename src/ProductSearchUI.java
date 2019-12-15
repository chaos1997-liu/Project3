import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class ProductSearchUI {

    public JFrame view;
    public JTable productTable;
    public UserModel user = null;

    public JButton btnSearch = new JButton("Search");
    public JButton btnOK = new JButton("OK");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtName = new JTextField(10);
    public JTextField txtMinPrice = new JTextField(10);
    public JTextField txtMaxPrice = new JTextField(10);
    public JScrollPane scrollableList = new JScrollPane();

    private String name;
    private double maxPrice;
    private double minPrice;


    public ProductSearchUI(UserModel user) {
        this.user = user;

        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Search Product");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("Name "));
        line.add(txtName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Min Price "));
        line.add(txtMinPrice);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Max Price "));
        line.add(txtMaxPrice);
        view.getContentPane().add(line);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnSearch);
        panelButtons.add(btnCancel);

        view.getContentPane().add(panelButtons);

        btnSearch.addActionListener(new SearchButtonListener());

        JLabel title = new JLabel("Search results for " + user.mFullname);

        title.setFont(title.getFont().deriveFont(24.0f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        view.getContentPane().add(title);

        ProductListModel list = StoreManager.getInstance().getDataAdapter().searchProduct("Apple", 0, 1000);
        DefaultTableModel tableData = new DefaultTableModel();

        tableData.addColumn("ProductID");
        tableData.addColumn("Product Name");
        tableData.addColumn("Price");
        tableData.addColumn("Quantity");

        for (ProductModel p : list.products) {
            Object[] row = new Object[tableData.getColumnCount()];

            row[0] = p.mProductID;
            row[1] = p.mName;
            row[2] = p.mPrice;
            row[3] = p.mQuantity;
            tableData.addRow(row);
        }

//        purchases = new JList(data);

        productTable = new JTable(tableData);

        scrollableList.getViewport().add(productTable);

        view.getContentPane().add(scrollableList);
    }


    class SearchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty!");
                return;
            }

            String tmpMinPrice = txtMinPrice.getText();

            if (tmpMinPrice.length() == 0) {
                JOptionPane.showMessageDialog(null, "Min Price cannot be null!");
                return;
            }

            try {
                minPrice = Double.parseDouble(tmpMinPrice);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Min Price is invalid!");
                return;
            }

            String tmpMaxPrice = txtMaxPrice.getText();

            if (tmpMaxPrice.length() == 0) {
                JOptionPane.showMessageDialog(null, "Max Price cannot be null!");
                return;
            }

            try {
                maxPrice = Double.parseDouble(tmpMaxPrice);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Max Price is invalid!");
                return;
            }

            ProductListModel list = StoreManager.getInstance().getDataAdapter().searchProduct(name, minPrice, maxPrice);
            DefaultTableModel tableData = new DefaultTableModel();

            tableData.addColumn("ProductID");
            tableData.addColumn("Product Name");
            tableData.addColumn("Price");
            tableData.addColumn("Quantity");

            for (ProductModel p : list.products) {
                Object[] row = new Object[tableData.getColumnCount()];

                row[0] = p.mProductID;
                row[1] = p.mName;
                row[2] = p.mPrice;
                row[3] = p.mQuantity;
                tableData.addRow(row);
            }

//        purchases = new JList(data);

            productTable = new JTable(tableData);

            scrollableList.getViewport().add(productTable);
            scrollableList.repaint();

        }
    }

}

