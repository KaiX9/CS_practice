package sg.edu.nus.iss.app.Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Idiom {
    
    public static String getRandomIdiom(String idiomFilePath, String idiomResultPath) {
        String randomIdiom = "";
        List<String> idioms = new LinkedList<>();
        List<String> idiomsResult = new ArrayList<>();
        try {
            idioms = getDataFromText(idiomFilePath);
            idiomsResult = getDataFromText(idiomResultPath);
            System.out.println(idiomsResult.size());
            if(idiomsResult.size() == 0) {
                System.out.println("Initializing result file...");
                for (int x = 0; x < idioms.size(); x++) {
                    idiomsResult.add("");
                }
                initIdiomResultFile(idiomResultPath, idioms.size());
            }

            Random rand = new Random();
            int randInput = rand.nextInt(idioms.size());
            System.out.println(randInput);
            randomIdiom = idioms.get(randInput);

            writeIdiomToResultFile(randomIdiom, randInput, idiomsResult, idiomResultPath);
            System.out.println("Random Idiom -> " + randomIdiom );
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return randomIdiom;
    }

    private static void writeIdiomToResultFile(String randomIdiom, int randomIndex, List<String> idiomsResult,
            String idiomResultPath) throws IOException {
                System.out.println("idiomsResult >>> " + idiomsResult);
                if (!idiomsResult.contains(randomIdiom)) {
                    idiomsResult.set(randomIndex, randomIdiom);
                }
                System.out.println(idiomsResult);
                PrintWriter pw = new PrintWriter(idiomResultPath);
                pw.print("");
                pw.close();

                FileWriter fw = new FileWriter(idiomResultPath);
                for (String idiomFromArray : idiomsResult) {
                    fw.write(idiomFromArray + "\n");
                }
                fw.close();
    }

    private static void initIdiomResultFile(String idiomResultPath, int resultSize) throws IOException {
        FileWriter fw = new FileWriter(idiomResultPath);
        if (resultSize > 0) {
            for (int i = 0; i < resultSize; i++) {
                fw.write("" + "\n");
            }
        } else {
            fw.write("" + "\n");
        }
        fw.close();
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
