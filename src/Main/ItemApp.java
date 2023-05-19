package Main;

import Model.Product;
import Lib.XFile;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;

public class ItemApp extends JFrame {
    private JPanel mainPanel;
    private JTextField txtId;
    private JTextField txtName;
    private JTable tbCan;
    private JComboBox cbType;
    private JTextField txtAm;
    private JTextField txtUp;
    private JRadioButton rdSmall;
    private JRadioButton rdBig;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton exitButton;
    private JButton refreshButton;
    private JButton sortButton;

    DefaultTableModel tbModel;
    DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
    ArrayList<Product> itemList;
    String filePath = "Item.dat";
    int currentRow = -1;
    JFrame frontScreen;

    public ItemApp(String title, Login aThis) {
        //1. Initialize Setup

        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel); // thiet lap khung noi dung chinh cho this frame
        this.pack(); // Make the window the right size
        frontScreen= aThis;
        initTable(); // set Ordinal data
        loadCb(); // load combo box
        tbCan.setDefaultEditor(Object.class, null); // not to directly edit into table
        itemList = new ArrayList<>(); // empty list
        boolean file = loadFile();// load data file in your project
        if (file) {
            fillToTable();
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateList();
            }
        });
        tbCan.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentRow = tbCan.getSelectedRow();
                showDetail(currentRow);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteL();
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reFresh();
            }
        });
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortByName();
                fillToTable();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitPro();
            }
        });
    }

    private void exitPro() {
        System.exit(0);

    }

    private void sortByName() {
        Collections.sort(itemList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                    return o1.getName().compareTo(o2.getName());
            }
        }); // compare String
    }


    private void reFresh() {
        txtId.setText("");
        txtName.setText("");
        txtAm.setText("");
        cbType.setSelectedIndex(0);
        txtUp.setText("");
    }

    private void deleteL() {
        deleteItem();
        //2.fill to table (renew table)
        fillToTable();
        //3.Save arrayList item
        saveFile();
    }

    private void deleteItem() {
        int del = JOptionPane.showConfirmDialog(
                this, "" + "Are you sure you want to delete this product?",
                "Delete Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (del == JOptionPane.YES_OPTION) {
            itemList.remove(currentRow);
            resetForm();
        }
    }

    private void resetForm() {
        txtId.setText("");
        txtName.setText("");
        txtAm.setText("");
        cbType.setSelectedIndex(0);
        txtUp.setText("");

    }

    private void showDetail(int currentRow) {
        Product c = itemList.get(currentRow);
        String id = c.getID();
        txtId.setText(id);
        String name = c.getName();
        txtName.setText(name);
        String type = c.getType();
        cbType.setSelectedItem(type);
        Integer amount = c.getAmount();
        txtAm.setText(String.valueOf(amount));
        String up = c.getUnitPrice();
        txtUp.setText(up);
        Boolean size = c.getSize();
        rdSmall.setSelected(size);
        rdBig.setSelected(!size);



    }

    private void updateList() {
        updateItem();
        //2.fill to table (renew table)
        fillToTable();
        //3.Save arrayList item
        saveFile();
    }

    private void updateItem() {
            Product c = itemList.get(currentRow);
            String id = txtId.getText();
            c.setID(id);
            String name = txtName.getText();
            c.setName(name);
            String type =cbType.getSelectedItem().toString();
            c.setType(type);
            Integer amount = Integer.parseInt(txtAm.getText());
            c.setAmount(amount);
            String up = txtUp.getText();
            c.setUnitPrice(up);
            Boolean size = rdSmall.isSelected();
            c.setSize(size);

    }


    private void addProduct() {
                  // 1.add new item to arraylist
                addToList();
//                //2.fill to table (renew table)
                fillToTable();
//                //3.Save arrayList item
                saveFile();
//            
    }


    private void showMess(String mess) {JOptionPane.showMessageDialog(ItemApp.this,mess);

    }

    private void initTable() {
        String[] columnNames ={"ID","Name","Type","Amount","Unit Price","Size"};
        tbModel = new DefaultTableModel(columnNames,0);
        tbCan.setModel(tbModel);
    }

    private void addToList() {

       try {
            String id = txtId.getText();
            String name = txtName.getText();
            Integer amount = Integer.parseInt(txtAm.getText());
            String unitPrice = txtUp.getText();
            Boolean size = rdSmall.isSelected();

            if (id.isEmpty() || name.isEmpty()  || unitPrice.isEmpty()) {
               showMess("Please fill in all information requirements");
               return;
            }
            if ( size == null) {
               showMess("Please choose size.");
               return;
            }
            String type = "";
            if(cbType.getSelectedIndex()== 0){
               showMess("Please choose kind of item");
               return;
             }else {type=cbType.getSelectedItem().toString();}// check if user not choose type

            Product c;
            c = new Product(id, name, type, amount, unitPrice ,size);
            itemList.add(c);
            }catch (NumberFormatException e){
            showMess("Amount must be integer number"); // string to integer
            }


    }


    private void fillToTable() {
        //Clear old data in the table
        tbModel.setRowCount(0);
        for (Product c : itemList) {
            Object[] row = new Object[]{
                    c.getID(), c.getName(), c.getType(), c.getAmount(), c.getUnitPrice(), c.getSize()
            };
            tbModel.addRow(row);
        }
    }
    private boolean loadFile() {
        if(XFile.readObject(filePath)==null){ // doc du lieu object trong thu vien xfile duong dan filePath neu khong tra ve false
            return false;
        }
        itemList = (ArrayList<Product>) XFile.readObject(filePath);// neu co doc object trong duong dan filepath roi load ra itemlist
        return true;
    }
    private void loadCb() {
        String[] TypeLst = {"Choose Product Type","Can","pack","barrel"};
        for(String s:TypeLst){
            cbModel.addElement(s);
        }
        cbType.setModel(cbModel);
    }
    private void saveFile() {
        XFile.writeObject(filePath, itemList); // viet lai du lieu cua itemlist roi đổ dữ liệu vào filePath
    }


//    public static void main(String[] args) {
//        ItemApp c = new ItemApp("Mini market Management");
//        c.setVisible(true);
//        c.setLocationRelativeTo(null);
//    }


}
