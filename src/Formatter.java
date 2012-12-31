/**
 * Formatter class of SourceCodeParser project.
 * User: donaldpercivalle
 * Date: 12/23/12
 * Time: 1:46 PM
 */
public interface Formatter {
   public void readDatabase() throws Exception;
   public String format (String line);
   public String comment (String line);
   public String keyword (String word);
}
