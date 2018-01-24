import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * //Author: Eng.HaiderAli
 */
public class StemSteelController implements Initializable {
    ArrayList<StemDataClass> arrayList;
    double unitKey;
    @FXML
    GridPane gridPane;

    public StemSteelController(ArrayList<StemDataClass> input, double unitKey) {
        arrayList = input;
        this.unitKey = unitKey;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (unitKey == 1) {
                Label label1 = new Label();
                label1.setText(String.valueOf(arrayList.get(i).getWallheight()));
                label1.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label1, 0, i + 1);  // column=3 row=1
                Label label2 = new Label();
                label2.setText(String.format("%.2f", arrayList.get(i).getBendingMoment()));
                label2.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label2, 1, i + 1);
                Label label3 = new Label();
                label3.setText(String.format("%.2f", arrayList.get(i).getAreaOfSteelRequired()));
                label3.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label3, 2, i + 1);
                Label label4 = new Label();
                label4.setText(String.valueOf(arrayList.get(i).getNoOfBars()));
                label4.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label4, 3, i + 1);
                Label label5 = new Label();
                label5.setText(String.valueOf(arrayList.get(i).getSpacingAdopted()));
                label5.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label5, 4, i + 1);
            } else {
                Label label1 = new Label();
                label1.setText(String.valueOf(arrayList.get(i).getWallheight() * 3.28));
                label1.setContentDisplay(ContentDisplay.CENTER);
                label1.setAlignment(Pos.CENTER);
                gridPane.add(label1, 0, i + 1);  // column=3 row=1
                Label label2 = new Label();
                label2.setText(String.format("%.2f", arrayList.get(i).getBendingMoment() * 7.23));
                label2.setContentDisplay(ContentDisplay.CENTER);
                label2.setAlignment(Pos.CENTER);
                gridPane.add(label2, 1, i + 1);
                Label label3 = new Label();
                label3.setText(String.format("%.2f", arrayList.get(i).getAreaOfSteelRequired() * 0.155));
                label3.setContentDisplay(ContentDisplay.CENTER);
                label3.setAlignment(Pos.CENTER);
                gridPane.add(label3, 2, i + 1);
                Label label4 = new Label();
                label4.setText(String.valueOf(arrayList.get(i).getNoOfBars()));
                label4.setContentDisplay(ContentDisplay.CENTER);
                label4.setAlignment(Pos.CENTER);
                gridPane.add(label4, 3, i + 1);
                Label label5 = new Label();
                label5.setText(String.valueOf(arrayList.get(i).getSpacingAdopted() * 0.3937));
                label5.setContentDisplay(ContentDisplay.CENTER);
                label5.setAlignment(Pos.CENTER);
                gridPane.add(label5, 4, i + 1);
                System.out.println(i);

            }
        }

    }
}

