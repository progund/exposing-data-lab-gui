package examples;

public class QueryBuilder {
  public static void main(String[] args) {
    fixLookAndFeel();
    
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          TextFieldDemo gui = new TextFieldDemo();
        }
      });
  }

  // For Rikard's computer - you can ignore this
  private static void fixLookAndFeel() {
    try {
      // Ignore the lines below - it's a fix for Rikard's computer. Hell Dell!
      javax.swing.UIManager
        .setLookAndFeel((javax.swing.LookAndFeel)Class
                        .forName("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
                        .newInstance());
    } catch (Exception ignore) {}
  }  
}
