import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductReportUI {

    public JFrame view;
    public JTable productTable;


    public ProductReportUI() {

        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("View Summary Report - Manager View");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Summary Report");

        title.setFont(title.getFont().deriveFont(24.0f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        view.getContentPane().add(title);

        ProductListModel list = StoreManager.getInstance().getDataAdapter().loadAllProduct();
        DefaultTableModel tableData = new DefaultTableModel();


        tableData.addColumn("ProductID");
        tableData.addColumn("Name");
        tableData.addColumn("Price");
        tableData.addColumn("Quantity");

        for (ProductModel product : list.products) {
            Object[] row = new Object[tableData.getColumnCount()];
            row[0] = product.mProductID;
            row[1] = product.mName;
            row[2] = product.mPrice;
            row[3] = product.mQuantity;
            tableData.addRow(row);
        }


        productTable = new JTable(tableData);

        JScrollPane scrollableList = new JScrollPane(productTable);

        view.getContentPane().add(scrollableList);


    }
}