package se.itu.systemet.gui;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

import java.util.List;

import se.itu.systemet.domain.Product;

/**
 * A class representing the contents of a table with products.
 * See: https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
 */
public class ProductTableModel extends AbstractTableModel {
  private String[] columnNames = { "Name", "Alcohol", "Price", "Volume", "cl alc per SEK"};
  private Object[][] data;
  
  public ProductTableModel(List<Product> products) {
    data = new Object[products.size()][5];
    for (int i=0; i<products.size(); i++) {
      Product product = products.get(i);
      data[i][0] = product.name();
      data[i][1] = product.alcohol();
      data[i][2] = product.price();
      data[i][3] = product.volume();
      data[i][4] = product.alcohol()/100.0 * product.volume()/10 / product.price();
    }
  }

  public int getColumnCount() {
    return columnNames.length;
  }

  public int getRowCount() {
      return data.length;
  }

  public String getColumnName(int col) {
    return columnNames[col];
  }

  public Object getValueAt(int row, int col) {
    if(data.length!=0) {
      return data[row][col];
    } else {
      return "";
    }
  }

  public Class getColumnClass(int c) {
    return getValueAt(0, c).getClass();
  }

  /*
  public void setValueAt(Object value, int row, int col) {
    data[row][col] = value;
    fireTableCellUpdated(row, col);
  }
  */
}
