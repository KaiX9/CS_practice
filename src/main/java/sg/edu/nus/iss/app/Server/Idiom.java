package sg.edu.nus.iss.app.Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Idiom {
    
    public static String getRandomIdiom(String idiomFilePath) {
        String randomIdiom = "";
        List<String> idioms = new LinkedList<>();
        try {
            idioms = getDataFromText(idiomFilePath);

            Random rand = new Random();
            int randInput = rand.nextInt(idioms.size());
            System.out.println(randInput);
            randomIdiom = idioms.get(randInput);
            System.out.println("Random Idiom -> " + randomIdiom );
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return randomIdiom;
    }

    private static List<String> getDataFromText(String idiomFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(idiomFilePath));

        List<String> lists = new LinkedList<>();

        String line;
        while ((line = br.readLine()) != null) {
            lists.add(line);
        }
        br.close();
        return lists;
    }
}
