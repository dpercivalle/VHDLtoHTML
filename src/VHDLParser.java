import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * VHDLParser class of SourceCodeParser project.
 * User: donaldpercivalle
 * Date: 12/22/12
 * Time: 3:01 PM
 */
class VHDLParser implements Parser{
   private final File source;
   private Scanner read;
   private PrintStream write;
   private int line_number =  1;
   private LineFormatterVHDL formatter;

   public VHDLParser(File source) throws Exception {
      this.source = source;
      File output = new File(source.toString().replaceFirst("vhd", "html"));
      this.read = new Scanner(source);
      FileOutputStream out_stream = new FileOutputStream(output);
      this.write = new PrintStream(out_stream);
      Scanner counter = new Scanner (this.source);
      int lines = 0;
      while (counter.hasNextLine()){
         lines++;
         counter.nextLine();
      }
      out.println(this.source.toString() + " has\n " + lines + " lines");
      this.formatter = new LineFormatterVHDL();
   }

   public void writeHTML () {
      this.htmlHead();
      this.tableHead();
      while (read.hasNextLine()) {
         String tr_class = "class=\"a\"";
         if (line_number % 2 == 0) {
            tr_class = "class=\"b\"";
         }
         String line = read.nextLine();
         write.println("            <tr " + tr_class + ">");
         write.println("               <td>" + line_number + "</td>");
         write.print("               <td>");
         write.print("<pre>");
         write.print(formatter.format(line)) ;
         write.print("</pre>");
         write.print("</td>");
         write.print("</tr>");
         line_number++;
         write.println();
      }
      this.tableFoot();
   }

   public void htmlHead() {
      write.println("<html>");
      write.println("<head>");
      write.println("<link href='http://fonts.googleapis.com/css?family=" +
              "Shadows+Into+Light+Two' rel='stylesheet' type='text/css'>");
      write.println("<link href=\"../../../styles/codestyle.css\" " +
              "rel=\"stylesheet\" type=\"text/css\">");
      write.println("</head>");
      write.println("<body>");


   }

   public void tableHead() {
      write.println("   <code>");
      write.println("      <pre>");
      write.println("         <!-- GENERATED TABLE FOR VHDL SOURCE CODE -->");
      write.println("            <table class=\"code\">");
      write.println("               <caption>");
      write.println("                  " + this.source.toString());
      write.println("               </caption>");
   }

   public void tableFoot() {
      write.println("            </table>");
      write.println("      </pre>");
      write.print("</code>");
      write.println("</body>");
      write.println("</html>");
   }

   public void printSummary() {
      System.out.println(" " + formatter.getNum_purple() + " purple words");
      System.out.println(" " + formatter.getNum_blue() + " blue words");
      System.out.println(" " + formatter.getNum_pink() + " pink words");
      System.out.println();
   }
}
