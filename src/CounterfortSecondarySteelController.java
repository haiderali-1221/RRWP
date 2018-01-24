import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * //Author: Eng.HaiderAli
 */
public class CounterfortSecondarySteelController implements Initializable {
    double unitKey;
    ArrayList<CounterfortSecondarySteelDataClass> arrayList;
    @FXML
    GridPane gridPane;

    public CounterfortSecondarySteelController(ArrayList<CounterfortSecondarySteelDataClass> Input, double unitKey) {
        arrayList = Input;
        this.unitKey = unitKey;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (unitKey == 1) {
                Label label1 = new Label();
                label1.setText(String.format("%.2f", arrayList.get(i).getWallHeight()));
                label1.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label1, 0, i + 1);  // column=3 row=1
                Label label2 = new Label();
                label2.setText(String.format("%.2f", arrayList.get(i).getHorizontalForce()));
                label2.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label2, 1, i + 1);
                Label label3 = new Label();
                label3.setText(String.format("%.2f", arrayList.get(i).getAreaOfSteel()));
                label3.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label3, 2, i + 1);
                Label label4 = new Label();
                label4.setText(String.valueOf(arrayList.get(i).getNoOfSecondaryBars()));
                label4.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label4, 4, i + 1);
                Label label5 = new Label();
                label5.setText(String.format("%.2f", arrayList.get(i).getSpacingAdopted()));
                label5.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label5, 3, i + 1);
            } else {
                Label label1 = new Label();
                label1.setText(String.format("%.2f",arrayList.get(i).getWallHeight() * 3.28));
                label1.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label1, 0, i + 1);  // column=3 row=1
                Label label2 = new Label();
                label2.setText(String.format("%.2f", arrayList.get(i).getHorizontalForce() * 2.2046));
                label2.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label2, 1, i + 1);
                Label label3 = new Label();
                label3.setText(String.format("%.2f", arrayList.get(i).getAreaOfSteel() * 0.155));
                label3.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label3, 2, i + 1);
                Label label4 = new Label();
                label4.setText(String.valueOf(arrayList.get(i).getNoOfSecondaryBars()));
                label4.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label4, 4, i + 1);
                Label label5 = new Label();
                label5.setText(String.format("%.2f", arrayList.get(i).getSpacingAdopted() * 0.3937));
                label5.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label5, 3, i + 1);
            }
        }
    }

}
