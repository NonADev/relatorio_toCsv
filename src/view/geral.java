package view;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class geral {
    public static void main(String[] args) {
        ArrayList<String> relatorioBruto = getRelatorio();
        String relatorioCSV = generateCSV(relatorioBruto);
        saveCSV(relatorioCSV);
    }

    public static String generateCSV(ArrayList<String> relatorio) {
        String toReturn = "";
        for (String a : relatorio) {
            if ((a.split(" ").length == 4) && (isNumeric(a.split(" ")[0]))) {
                toReturn += String.format("%s;\"%s\";%s;%s\n", a.split(" ")[0],a.split(" ")[1],a.split(" ")[2].replace(".",","),a.split(" ")[3]);
            }
        }
        return toReturn;
    }

    public static void saveCSV(String content) {
        try {
            Files.write(Paths.get("out/production/colevati_relatorio_to_csv/view/results.csv"), content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> getRelatorio() {
        ArrayList<String> toReturn = new ArrayList<>();
        try {
            File relatorio = new File("out/production/colevati_relatorio_to_csv/view/relatorio.txt");
            Scanner s = new Scanner(relatorio);
            while (s.hasNextLine()) {
                toReturn.add(s.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toReturn;
    }
}
