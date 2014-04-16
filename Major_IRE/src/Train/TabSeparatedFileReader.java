package Train;
import
java.io.BufferedReader;
import java.io.FileReader;
 
public class TabSeparatedFileReader {
 
    public static void main(String args[]) throws Exception {
        /**
         * Source file to read data from.
         */
        String dataFileName = "/media/82CC3BADCC3B9A7D/major_project/IRData/Training/tweet_text_modified/RL2013D01E001_texts.tsv";
 
        /**
         * Creating a buffered reader to read the file
         */
        BufferedReader bReader = new BufferedReader(
                new FileReader(dataFileName));
 
        String line;

    
        /**
         * Looping the read block until all lines in the file are read.
         */
        while ((line = bReader.readLine()) != null) {
 
            /**
             * Splitting the content of tabbed separated line
             */
            String datavalue[] = line.split("\t");
            String value1 = datavalue[0];
            String value2 = datavalue[1];
            String value3 = datavalue[2];
            String  value4 = datavalue[3];
 
            /**
             * Printing the value read from file to the console
             */
            System.out.println(value1 + "\t" + value2 + "\t" + value3 + "\t"
                    + value4);
        }
        bReader.close();
    }
}