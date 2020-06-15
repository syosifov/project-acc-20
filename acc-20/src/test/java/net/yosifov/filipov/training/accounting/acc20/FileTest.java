package net.yosifov.filipov.training.accounting.acc20;

import net.yosifov.filipov.training.accounting.acc20.utils.AT;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileTest {

    @Test
    public void test() {
        Path path = Paths.get("src/main/resources/static/ledger.txt");
        boolean pathExists =
                Files.exists(path);
        System.out.println(pathExists);

        try {
            List<String> allLines = Files.readAllLines(path);
//            for (String line : allLines) {
//                System.out.println(line);
//            }
            for(String s: allLines) {
                String[] sa = s.split("\\|");
                String sName = sa[0];
                String sDescription = sa[1];
                if(sName.length()==1){
                    System.out.println(sa[0] + " " + sDescription);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Test
    public void t2() {
        String s1 = "1 | СМЕТКИ ЗА КАПИТАЛ И ЗАЕМИ";
        String[] sa = s1.split("\\|");
        System.out.println(sa.length);
    }

}
