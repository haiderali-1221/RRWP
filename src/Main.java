import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
//Author: Eng.HaiderAli

public class Main extends Application {



    @Override
    public void init(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String currentUsersHomeDir = System.getProperty("user.home");

        File log = new File(currentUsersHomeDir + "\\log");
        try {
            if (!log.exists()) {
                log.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(java.lang.String.valueOf(System.currentTimeMillis()));
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("COULD NOT LOG!!");
        }

        List<String> lines = Files.readAllLines(Paths.get(log.getPath()), Charset.defaultCharset());

        Parent root;
        long latestTime;
        if (lines.size() > 1) {
            latestTime = Long.parseLong(lines.get(lines.size() - 2));
        } else {
            latestTime = 0;
        }
        if (Long.parseLong(lines.get(0)) + 2592000000l > System.currentTimeMillis()
                && latestTime < System.currentTimeMillis()) {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Reinforced Retaining Wall Pro (RRWP)");
            primaryStage.setScene(new Scene(root, 800, 600, true));
            primaryStage.getIcons().add(new Image("RRWPIcon.png"));
            primaryStage.show();
        } else {
            root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
            primaryStage.setTitle("Reinforced Retaining Wall Pro (RRWP)");
            primaryStage.setScene(new Scene(root, 600, 212, true));
            primaryStage.getIcons().add(new Image("RRWPIcon.png"));
            primaryStage.show();
        }
        Path path = Paths.get(log.getPath()); //< input target path
        try {
            Files.setAttribute(path, "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        LauncherImpl.launchApplication(Main.class, FirstPreloader.class, args);
    }
}


