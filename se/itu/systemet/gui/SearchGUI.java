package se.itu.systemet.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import se.itu.systemet.domain.Product;
import se.itu.systemet.rest.ApiAccess;
import se.itu.systemet.rest.ApiAccessFactory;
import se.itu.systemet.rest.Param;
import se.itu.systemet.rest.Query;
import se.itu.systemet.rest.QueryFactory;

/**
 * A class representing the GUI for an application for
 * searching Systembolaget products.
 */
public class SearchGUI {
  // Instance variables below - mostly Swing components for the UI  
  private JFrame frame; // this is the actual window
  private JPanel panel; // a panel is a surface to put other components on
  private JPanel form;  
  private JTable table; // A table which looks like a spread sheet, kind of
  // input fields for searching
  private JTextField minAlcoField; 
  private JTextField maxAlcoField; 
  private JTextField minPriceField;
  private JTextField maxPriceField;

  private List<Product> products;
  private ApiAccess api; // For talking to the REST API
  
  public SearchGUI() {
    api = ApiAccessFactory.getApiAccess();    
    products = api.fetch(QueryFactory.getQuery());
    init(); // Initiate the components
    show(); // Show the frame
  }

  private void init() {
    frame = new JFrame("Search products");
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    panel = new JPanel(new GridLayout(1, 0));
    GridLayout formLayout = new GridLayout(2,4);
    form = new JPanel(formLayout);
    formLayout.setVgap(2);
    formLayout.setHgap(4);
    table = new JTable(new ProductTableModel(products));
    table.setPreferredScrollableViewportSize(new Dimension(1600, 1600));
    table.setFillsViewportHeight(true);
    table.setRowHeight(30);
    table.setAutoCreateRowSorter(true);
    table.getColumnModel().getColumn(0).setPreferredWidth(400);
    JScrollPane scrollPane = new JScrollPane(table);
    panel.add(scrollPane);
    panel.setOpaque(true);
    frame.add(panel, BorderLayout.CENTER);
    minPriceField = new JTextField(6);
    maxPriceField = new JTextField(6);
    minAlcoField = new JTextField(3);
    maxAlcoField = new JTextField(3);
    form.add(new JLabel("Minimum alcohol:"));
    form.add(minAlcoField);
    form.add(new JLabel("Maximum alcohol:"));
    form.add(maxAlcoField);
    form.add(new JLabel("Minimum price:"));
    form.add(minPriceField);
    form.add(new JLabel("Maximum price:"));
    form.add(maxPriceField);
    frame.add(form, BorderLayout.SOUTH);
    addListeners();
  }

  private void show() {
    frame.pack();
    frame.setVisible(true);
  }

  private List<JTextField> textFields() {
    List<JTextField> textFields = Arrays.asList(minPriceField, maxPriceField, minAlcoField, maxAlcoField);
    return textFields;
  }

  private void addListeners() {
    for (JTextField textField : textFields()) {
      textField.getDocument()
        .addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              newFilter();
            }
            public void insertUpdate(DocumentEvent e) {
              newFilter();
            }
            public void removeUpdate(DocumentEvent e) {
              newFilter();
            }
          });          
    }
  }

  private List<Param> params() {
    List<Param> params = new ArrayList<>();
    if (!"".equals(minAlcoField.getText())) {
      params.add(new Param("min_alcohol", minAlcoField.getText()));
    }
    if (!"".equals(maxAlcoField.getText())) {
      params.add(new Param("max_alcohol", maxAlcoField.getText()));
    }
    if (!"".equals(minPriceField.getText())) {
      params.add(new Param("min_price", minPriceField.getText()));
    }
    if (!"".equals(maxPriceField.getText())) {
      params.add(new Param("max_price", maxPriceField.getText()));
    }
    return params;
  }
  
  private void newFilter() {
    Query query = QueryFactory.getQuery();
    for (Param param : params() ) {
      query.addParam(param);
    }
    table.setModel(new ProductTableModel(api.fetch(query)));
  }
}
