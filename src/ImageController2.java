import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * //Author: Eng.HaiderAli
 */
public class ImageController2 implements Initializable {

    @FXML
    Label thicknessOfStemTopLabel1;
    @FXML
    Label thicknessOfStemTopLabel;
    @FXML
    Label secondaryBarStemExposedLabel;
    @FXML
    Label dFLabel;
    @FXML
    Label thicknessOfStemBotLabel;
    @FXML
    Label lengthOfHeelLabel;
    @FXML
    Label heightOfToeSlabLabel;
    @FXML
    Label h2Label;
    @FXML
    Label mainBarHeelLabel;
    @FXML
    Label secondaryBarHeelLabel;
    @FXML
    Label lengthOfToeLabel;
    @FXML
    Label stirrupsSteelCountefortLabel;
    @FXML
    Label mainBarToeLabel;
    @FXML
    Label heightOfHeelSlabLabel;
    @FXML
    Label secondaryBarToeLabel;
    @FXML
    Label lDLabel;
    @FXML
    Label diaOfMainBarLabel;
    @FXML
    Label diaOfSecondaryBarLabel;
    @FXML
    Label cm1Label;
    @FXML
    Label cm2Label;
    @FXML
    Label cm3Label;
    @FXML
    Label cm4Label;
    @FXML
    Label cm5Label;
    @FXML
    Label cm6Label;
    @FXML
    Label m2Label;
    @FXML
    Label m3Label;
    @FXML
    Label m4Label;
    @FXML
    Label m5Label;
    @FXML
    Label m6Label;
    @FXML
    Label m1Label;
    double thicknesOfStemBottom;
    double thicknessOfStemTop;
    double dF;
    double h2;
    double heightOfToe;
    double heightOfHeel;
    double lengthOfToe;
    double lengthOfHeel;
    double noOfBarsStemExposed;
    double spacingStemExposed;
    double noOfMainBarsHeel;
    double noOfSecondaryBarsHeel;
    double spacingOfMainBarsHeel;
    double spacingOfSecondaryBarsHeel;
    double noOfMainBarsToe;
    double noOfSecondaryBarsToe;
    double spacingOfMainBarsToe;
    double spacingOfSecondaryBarsToe;
    double noOfBarsForStirrups;
    double spacingForSitrrups;
    double lD;
    double diaOfMainBar;
    double diaOfSecondaryBar;
    double unitKey;

    ImageController2(double thicknesOfStemBottom, double thicknessOfStemTop, double dF, double h2, double heightOfToe, double lengthOfToe, double lengthOfHeel, double noOfBarsStemExposed, double spacingStemExposed, double noOfMainBarsToe, double noOfSecondaryBarsToe, double spacingOfMainBarsToe, double spacingOfSecondaryBarsToe, double noOfMainBarsHeel, double noOfSecondaryBarsHeel, double spacingOfMainBarsHeel, double spacingOfSecondaryBarsHeel, double noOfBarsForStirrups, double spacingForSitrrups, double unitKey, double heightOfHeel, double lD,double diaOfMainBar, double diaOfSecondaryBar) {
        this.unitKey=unitKey;
        if (unitKey == 1) {
            this.dF = dF;
            this.thicknesOfStemBottom = thicknesOfStemBottom;
            this.thicknessOfStemTop = thicknessOfStemTop;
            this.h2 = h2;
            this.heightOfToe = heightOfToe;
            this.lengthOfHeel = lengthOfHeel;
            this.noOfBarsStemExposed = noOfBarsStemExposed;
            this.spacingStemExposed = spacingStemExposed;
            this.noOfBarsStemExposed = noOfBarsStemExposed;
            this.noOfMainBarsHeel = noOfMainBarsHeel;
            this.noOfSecondaryBarsHeel = noOfSecondaryBarsHeel;
            this.spacingOfSecondaryBarsHeel = spacingOfSecondaryBarsHeel;
            this.spacingOfMainBarsHeel = spacingOfMainBarsHeel;
            this.lengthOfToe = lengthOfToe;
            this.noOfMainBarsToe = noOfMainBarsToe;
            this.noOfSecondaryBarsToe = noOfSecondaryBarsToe;
            this.spacingOfMainBarsToe = spacingOfMainBarsToe;
            this.spacingOfSecondaryBarsToe = spacingOfSecondaryBarsToe;
            this.noOfBarsForStirrups = noOfBarsForStirrups;
            this.spacingForSitrrups = spacingForSitrrups;
            this.heightOfHeel = heightOfHeel;
            this.lD = lD;
            this.diaOfMainBar=diaOfMainBar;
            this.diaOfSecondaryBar=diaOfSecondaryBar;
        } else {
            this.diaOfMainBar=diaOfMainBar*0.3937;
            this.diaOfSecondaryBar=diaOfSecondaryBar*0.3937;
            this.dF = dF * 3.28;
            this.thicknesOfStemBottom = thicknesOfStemBottom * 0.3937;
            this.thicknessOfStemTop = thicknessOfStemTop * 0.3937;
            this.h2 = h2 * 3.28;
            this.heightOfToe = heightOfToe * 3.28;
            this.heightOfHeel = heightOfHeel * 3.28;
            this.lengthOfHeel = lengthOfHeel * 3.28;
            this.noOfBarsStemExposed = noOfBarsStemExposed;
            this.spacingStemExposed = spacingStemExposed * 0.3937;
            this.noOfBarsStemExposed = noOfBarsStemExposed;
            this.noOfMainBarsHeel = noOfMainBarsHeel;
            this.noOfSecondaryBarsHeel = noOfSecondaryBarsHeel;
            this.spacingOfSecondaryBarsHeel = spacingOfSecondaryBarsHeel * 0.3937;
            this.spacingOfMainBarsHeel = spacingOfMainBarsHeel * 0.3937;
            this.lengthOfToe = lengthOfToe * 3.28;
            this.noOfMainBarsToe = noOfMainBarsToe;
            this.noOfSecondaryBarsToe = noOfSecondaryBarsToe;
            this.spacingOfMainBarsToe = spacingOfMainBarsToe * 0.3937;
            this.spacingOfSecondaryBarsToe = spacingOfSecondaryBarsToe * 0.3937;
            this.noOfBarsForStirrups = noOfBarsForStirrups;
            this.spacingForSitrrups = spacingForSitrrups * 0.3937;
            this.lD = lD * 0.3937;


        }


    }

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        diaOfMainBarLabel.setText(String.format("%.2f", diaOfMainBar));
        diaOfSecondaryBarLabel.setText(String.format("%.2f", diaOfSecondaryBar));
        lDLabel.setText(String.format("%.2f", lD));
        thicknessOfStemBotLabel.setText(String.format("%.2f", thicknesOfStemBottom));
        thicknessOfStemTopLabel.setText(String.format("%.2f", thicknessOfStemTop));
        dFLabel.setText(String.format("%.2f", dF));
        h2Label.setText(String.format("%.2f", h2));
        heightOfToeSlabLabel.setText(String.format("%.2f", (heightOfToe)));
        heightOfHeelSlabLabel.setText(String.format("%.2f", (heightOfHeel)));
        lengthOfHeelLabel.setText(String.format("%.2f", lengthOfHeel));
        lengthOfToeLabel.setText(String.format("%.2f", lengthOfToe));
        secondaryBarStemExposedLabel.setText(String.format("%.2f", noOfBarsStemExposed) + "Bars@" + String.format("%.2f", spacingStemExposed) + "c/c");
        mainBarHeelLabel.setText(String.format("%.2f", noOfMainBarsHeel) + "Bars@" + String.format("%.2f", spacingOfMainBarsHeel) + "c/c");
        secondaryBarHeelLabel.setText(String.format("%.2f", noOfSecondaryBarsHeel) + "Bars@" + String.format("%.2f", spacingOfSecondaryBarsHeel) + "c/c");
        mainBarToeLabel.setText(String.format("%.2f", noOfMainBarsToe) + "Bars@" + String.format("%.2f", spacingOfMainBarsToe) + "c/c");
        secondaryBarToeLabel.setText(String.format("%.2f", noOfSecondaryBarsToe) + "Bars@" + String.format("%.2f", spacingOfSecondaryBarsToe) + "c/c");
        stirrupsSteelCountefortLabel.setText(String.format("%.2f", noOfBarsForStirrups) + "Bars@" + String.format("%.2f", spacingForSitrrups) + "c/c");
        thicknessOfStemTopLabel1.setText(String.format("%.2f", thicknessOfStemTop));
        if(unitKey==1){
            cm1Label.setText("cm");
            cm2Label.setText("cm");
            cm3Label.setText("cm");
            cm4Label.setText("cm");
            cm5Label.setText("cm");
            cm6Label.setText("cm");
            m1Label.setText("m");
            m2Label.setText("m");
            m3Label.setText("m");
            m4Label.setText("m");
            m5Label.setText("m");
            m6Label.setText("m");
        }else{
            cm1Label.setText("in");
            cm2Label.setText("in");
            cm3Label.setText("in");
            cm4Label.setText("in");
            cm5Label.setText("in");
            cm6Label.setText("in");
            m1Label.setText("ft");
            m2Label.setText("ft");
            m3Label.setText("ft");
            m4Label.setText("ft");
            m5Label.setText("ft");
            m6Label.setText("ft");

        }
    }
}
