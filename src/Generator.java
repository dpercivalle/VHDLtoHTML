import com.sun.javafx.css.StyleableProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Generator class of TableGeneratorGUI project.
 * User: donaldpercivalle
 * Date: 12/24/12
 * Time: 11:46 AM
 */
public class Generator {
   private JPanel code;
   private JTabbedPane tabbedPane1;
   private JPanel gui;
   private JPanel filechooser;
   private JPanel color_lists;
   private JPanel source_tab;
   private JPanel web_tab;
   private JPanel html_tab;
   private JTextArea web_code;
   private JTextArea html_code;
   private JPanel language_bar;
   private JButton choose_vhdl;
   private JButton choose_html;
   private JButton choose_java;
   private JButton browseButton;
   private JButton generateTableButton;
   private JTextField source_file;
   private JPanel colors_list;
   private JScrollPane vhdl_blue_scroll;
   private JScrollPane vhdl_pink_scroll;
   private JScrollPane vhdl_purple_scroll;
   private JTextArea vhdl_blue;
   private JTextArea vhdl_pink;
   private JTextArea vhdl_purple;



   public static void main(String[] args) {
      JFrame frame = new JFrame("Generator");
      frame.setContentPane(new Generator().gui);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
      frame.setResizable(false);
   }

   private FocusListener clearOnClick(final JTextField field) {
      final String default_text = field.getText();
      FocusListener clicked = new FocusListener() {
         @Override
         public void focusGained(FocusEvent e) {
            field.setText("");
            field.setFont(new Font("monospaced", Font.PLAIN, 12));
            field.setForeground(new Color (0,0,0));
         }

         @Override
         public void focusLost(FocusEvent e) {
            field.setText(default_text);
            field.setForeground(new Color(181, 193, 171));
            field.setFont(new Font("monospaced", Font.ITALIC, 12));
         }
      };

      return clicked;
   }

   private KeyListener addOnEnter(final JTextField field) {
      KeyListener entered = new KeyListener() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == KeyEvent.VK_ENTER) {
               field.setText("");
            }
            //To change body of implemented methods use File | Settings | File Templates.
         }

         @Override
         public void keyPressed(KeyEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
         }

         @Override
         public void keyReleased(KeyEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
         }
      };
      return entered;
   }

   public void vhdlLists () throws Exception {
      File blues = new File ("./temp_vhdl_blue.txt");
      File pinks = new File ("./temp_vhdl_pink.txt");
      File purples = new File ("./temp_vhdl_purple.txt");
      Scanner read_blues = new Scanner(blues);
      Scanner read_pinks = new Scanner(pinks);
      Scanner read_purples = new Scanner(purples);
      Dimension boxes = new Dimension(225, 275);

      this.vhdl_blue = new JTextArea();
      while (read_blues.hasNextLine()) vhdl_blue.append(read_blues.nextLine()
              + "\n");
      this.vhdl_blue.setPreferredSize(boxes);
      vhdl_blue.setEditable(false);

      this.vhdl_pink = new JTextArea();
      while (read_pinks.hasNextLine()) vhdl_pink.append(read_pinks.nextLine()
              + "\n");
      vhdl_pink.setPreferredSize(boxes);
      vhdl_pink.setEditable(false);

      this.vhdl_purple = new JTextArea();
      while (read_purples.hasNextLine()) vhdl_purple.append(read_purples.nextLine()
              + "\n");
      vhdl_purple.setPreferredSize(boxes);
      vhdl_purple.setEditable(false);
   }

}
