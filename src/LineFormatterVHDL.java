import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * LineFormatterVHDL class of SourceCodeParser project.
 * User: donaldpercivalle
 * Date: 12/23/12
 * Time: 12:48 PM
 */

public class LineFormatterVHDL implements Formatter{
   private final ArrayList<String> purples = new ArrayList<String>();
   private final ArrayList<String> pinks = new ArrayList<String>();
   private final ArrayList<String> blues = new ArrayList<String>();
   private int num_purple = 0;
   private int num_pink = 0;
   private int num_blue = 0;


   public LineFormatterVHDL() throws Exception {
      this.readDatabase();
   }

   public void readDatabase () throws Exception {
      File purple = new File("./vhdl_purple.txt");
      File pink = new File("./vhdl_pink.txt");
      File blue = new File("./vhdl_blue.txt");
      FileInputStream purple_list = new FileInputStream(purple);
      FileInputStream pink_list = new FileInputStream(pink);
      FileInputStream blue_list = new FileInputStream(blue);
      Scanner read_purple = new Scanner(purple_list);
      Scanner read_pink = new Scanner(pink_list);
      Scanner read_blue = new Scanner(blue_list);
      //PARSE THROUGH PURPLE LIST AND ADD TO PURPLES
      while (read_purple.hasNextLine()) {
         purples.add(read_purple.nextLine());
      }
      //PARSE THROUGH PINK LIST AND ADD TO PINKS
      while (read_pink.hasNextLine()) {
         pinks.add(read_pink.nextLine());
      }
      //PARSE THROUGH BLUE LIST AND ADD TO BLUES
      while (read_blue.hasNextLine()) {
         blues.add(read_blue.nextLine());
      }
   }

   public String format (String line) {
      String formatted = "";
      if (line.startsWith("--") || line.startsWith("--", 3)) {
         return comment(line);
      }
      Scanner reader = new Scanner (line);
      reader.useDelimiter("\\b");
      while (reader.hasNext()) {
         formatted += keyword(reader.next());
      }
      return formatted;
   }

   public String comment (String line) {
      return "<span style=\"color:#2CFF01\">" + line + "</span>";
   }

   public String keyword (String word) {
      if (purples.contains(word)) {
         num_purple++;
         return "<span style=\"color:#C900FF\">" + word + "</span>";
      }
      if (pinks.contains(word)) {
         num_pink++;
         return "<span style=\"color:#FF00C9\">" + word + "</span>";
      }
      if (blues.contains(word)) {
         num_blue++;
         return "<span style=\"color:#0000FF\">" + word + "</span>";
      }
      return word;
   }

   public int getNum_purple() {
      return num_purple;
   }

   public int getNum_pink() {
      return num_pink;
   }

   public int getNum_blue() {
      return num_blue;
   }
}
