import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        int keyIndex = Integer.parseInt(args[0]);// the key index is the first argument.
        int maxLines = Integer.parseInt(args[1]); //the number of lines is the seconed argument.
        String fileName = args[2]; //the address of the file is the third argument.
        //String example = "C:\\Users\\micha87\\Desktop\\JDBC\\adss_group_2-cb72255dee082e6d46359ca43a3a37e29e91f68c-dev\\tunefork2\\src\\abc.csv";
        String csvFile = fileName;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[][] words = new String[maxLines][];
            //reading from the csv file. separates the words by ','.
        try {

            br = new BufferedReader(new FileReader(csvFile));
            int i = 0;
            while ((line = br.readLine()) != null) {

                // use comma as separator
                words[i] = line.split(cvsSplitBy);

                i++;

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String fields[] = words[0];
        DaoImpl dao = new DaoImpl(null);
        dao.create_table(fields);
        String[][] temp = new String[words.length][];
        mergesort mergesort = new mergesort(words, temp, 0, words.length-1, keyIndex);
        mergesort.sort();
        for (int i = 0; i < maxLines; i++) {
            dao.create(words[i]);

        }
    }

}


