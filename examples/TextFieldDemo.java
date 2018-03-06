package examples;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;

import se.itu.systemet.domain.Product;
import se.itu.systemet.rest.ApiAccess;
import se.itu.systemet.rest.ApiAccessFactory;
import se.itu.systemet.rest.Param;
import se.itu.systemet.rest.Query;
import se.itu.systemet.rest.QueryFactory;

public class TextFieldDemo {
  // Instance variables below - mostly Swing components for the UI  
  private JFrame frame; // this is the actual window
  private JPanel form;  
  private JTextField minAlcoField; 
  private JTextField maxAlcoField; 
  private JTextField minPriceField;
  private JTextField maxPriceField;
  private JLabel resultLabel;
  
  private List<Product> products;
  private ApiAccess api; // For talking to the REST API

  private String uri;
  private static final String SERVLET_URL = "http://localhost:8080/search/products/all?";
  
  public TextFieldDemo() {
    api = ApiAccessFactory.getApiAccess();    
    products = api.fetch(QueryFactory.getQuery());
    init(); // Initiate the components
    show(); // Show the frame
  }

  private void init() {
    frame = new JFrame("Search products");
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    minPriceField = new JTextField(6);
    maxPriceField = new JTextField(6);
    minAlcoField = new JTextField(3);
    maxAlcoField = new JTextField(3);
    form = new JPanel(new GridLayout(2,4));
    form.add(new JLabel("Minimum alcohol:"));
    form.add(minAlcoField);
    form.add(new JLabel("Maximum alcohol:"));
    form.add(maxAlcoField);
    form.add(new JLabel("Minimum price:"));
    form.add(minPriceField);
    form.add(new JLabel("Maximum price:"));
    form.add(maxPriceField);
    resultLabel = new JLabel("Query result");
    resultLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    frame.add(form, BorderLayout.CENTER);
    frame.add(resultLabel, BorderLayout.SOUTH);
    addListeners();
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
    addLinkListener();
  }
  private void addLinkListener() {
    resultLabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent me) {
          try {
            Desktop.getDesktop().browse(new URL(uri).toURI());
          } catch (Exception e) {
            System.err.println("Error clicking link: " + e.getMessage());
            resultLabel.setText("link error");
          }
        }
      });
  }

  /* Return the JTextFields as a List */
  private List<JTextField> textFields() {
    List<JTextField> textFields = Arrays.asList(minPriceField, maxPriceField, minAlcoField, maxAlcoField);
    return textFields;
  }

  private void show() {
    frame.pack();
    frame.setVisible(true);
  }

  private void newFilter() {
    Query query = QueryFactory.getQuery();
    if (!"".equals(minAlcoField.getText())) {
      query.addParam(new Param("min_alcohol", minAlcoField.getText()));
    }
    if (!"".equals(maxAlcoField.getText())) {
      query.addParam(new Param("max_alcohol", maxAlcoField.getText()));
    }
    if (!"".equals(minPriceField.getText())) {
      query.addParam(new Param("min_price", minPriceField.getText()));
    }
    if (!"".equals(maxPriceField.getText())) {
      query.addParam(new Param("max_price", maxPriceField.getText()));
    }

    // If we wanted to fetch new products, we could do this:
    products = api.fetch(query);
    // Show the query string
    String link = "<html><a href=\"" + SERVLET_URL +
      query.toQueryString() + "\">" + query.toQueryString() + "</a></html>";
    resultLabel.setText(link);
    this.uri = SERVLET_URL + query.toQueryString();
  }
}
