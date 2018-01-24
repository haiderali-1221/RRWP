import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * //Author: Eng.HaiderAli
 */
public class ImageController1 implements Initializable {

    @FXML
    Label thicknessOfStemTopCantiliverLabel;
    @FXML
    Label secondaryBarStemExposedCantiliverLabel;
    @FXML
    Label dFCantiliverLabel;
    @FXML
    Label secondaryBarToeCantiliverLabel;
    @FXML
    Label mainBarToeCantiliverLabel;
    @FXML
    Label lengthOfToeCantiliverLabel;
    @FXML
    Label thicknessOfStemBotCantiliverLabel;
    @FXML
    Label lengthOfHeelCantiliverLabel;
    @FXML
    Label heightOfToeSlabCantiliverLabel;
    @FXML
    Label h2CantiliverLabel;
    @FXML
    Label mainBarHeelCantiliverLabel;
    @FXML
    Label secondaryBarHeelCantiliverLabel;
    @FXML
    Label secondaryBarStemEarthCantiliverLabel;
    @FXML
    Label mainSteelKeyLabel;
    @FXML
    Label secondarySteelForKeyLabel;
    @FXML
    Label depthOfKeyLabel;
    @FXML
    Label heightOfHeelSlabCantiliverLabel;
    @FXML
    Label widthOfKeyLabel;
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
    Label m1Label;
    @FXML
    Label m2Label;
    @FXML
    Label m3Label;
    @FXML
    Label m4Label;
    @FXML
    Label m6Label;
    @FXML
    Label m7Label;
    @FXML
    Label m8Label;

    double thicknesOfStemBottom;
    double thicknessOfStemTop;
    double dF;
    double h2;
    double heightOfToe;
    double lengthOfToe;
    double lengthOfHeel;
    double noOfBarsStemExposed;
    double spacingStemExposed;
    double noOfBarsStemEarth;
    double spacingStemEarth;
    double noOfMainBarsToe;
    double noOfSecondaryBarsToe;
    double spacingOfMainBarsToe;
    double spacingOfSecondaryBarsToe;
    double noOfMainBarsHeel;
    double noOfSecondaryBarsHeel;
    double spacingOfMainBarsHeel;
    double spacingOfSecondaryBarsHeel;
    double depthOfKey;
    double noOfMainBarsKey;
    double spacingOfMainBarsKey;
    double noOfSecondaryBarsKey;
    double spacingOfSecondaryBarKey;
    double widthOfKey;
    double heightHeelSlab;
    double lD;
    double diaOfMainBar;
    double diaOfSecondaryBar;
    double unitKey;

    ImageController1(double thicknesOfStemBottom, double thicknessOfStemTop, double dF, double h2, double heightOfToe, double lengthOfToe, double lengthOfHeel, double noOfBarsStemExposed, double spacingStemExposed, double noOfBarsStemEarth, double spacingStemEarth, double noOfMainBarsToe, double noOfSecondaryBarsToe, double spacingOfMainBarsToe, double spacingOfSecondaryBarsToe, double noOfMainBarsHeel, double noOfSecondaryBarsHeel, double spacingOfMainBarsHeel, double spacingOfSecondaryBarsHeel, double depthOfKey, double widthOfKey, double noOfMainBarsKey, double spacingOfMainBarsKey, double noOfSecondaryBarsKey, double spacingOfSecondaryBarKey, double unitKey, double heightHeelSlab, double lD,double diaOfMainBar, double diaOfSecondaryBar) {
        this.unitKey=unitKey;
        if (unitKey == 1) {
            this.depthOfKey = depthOfKey;
            this.dF = dF;
            this.thicknesOfStemBottom = thicknesOfStemBottom;
            this.thicknessOfStemTop = thicknessOfStemTop;
            this.h2 = h2;
            this.heightOfToe = heightOfToe;
            this.spacingStemEarth = spacingStemEarth;
            this.lengthOfToe = lengthOfToe;
            this.lengthOfHeel = lengthOfHeel;
            this.noOfBarsStemExposed = noOfBarsStemExposed;
            this.spacingStemExposed = spacingStemExposed;
            this.noOfBarsStemExposed = noOfBarsStemExposed;
            this.noOfBarsStemEarth = noOfBarsStemEarth;
            this.noOfMainBarsToe = noOfMainBarsToe;
            this.noOfSecondaryBarsToe = noOfSecondaryBarsToe;
            this.spacingOfMainBarsToe = spacingOfMainBarsToe;
            this.spacingOfSecondaryBarsToe = spacingOfSecondaryBarsToe;
            this.noOfMainBarsHeel = noOfMainBarsHeel;
            this.noOfSecondaryBarsHeel = noOfSecondaryBarsHeel;
            this.noOfMainBarsKey = noOfMainBarsKey;
            this.spacingOfMainBarsKey = spacingOfMainBarsKey;
            this.noOfSecondaryBarsKey = noOfSecondaryBarsKey;
            this.spacingOfSecondaryBarsHeel = spacingOfSecondaryBarsHeel;
            this.spacingOfMainBarsHeel = spacingOfMainBarsHeel;
            this.spacingOfSecondaryBarKey = spacingOfSecondaryBarKey;
            this.widthOfKey = widthOfKey;
            this.heightHeelSlab = heightHeelSlab;
            this.lD = lD;
            this.diaOfMainBar=diaOfMainBar;
            this.diaOfSecondaryBar=diaOfSecondaryBar;
        } else {
            this.diaOfMainBar=diaOfMainBar*0.3937;
            this.diaOfSecondaryBar= diaOfSecondaryBar*0.3937;
            this.depthOfKey = depthOfKey * 3.28;
            this.dF = dF * 3.28;
            this.thicknesOfStemBottom = thicknesOfStemBottom * 0.3937;
            this.thicknessOfStemTop = thicknessOfStemTop * 0.3937;
            this.h2 = h2 * 3.28;
            this.heightOfToe = heightOfToe * 3.28;
            this.heightHeelSlab = heightHeelSlab * 3.28;
            this.spacingStemEarth = spacingStemEarth * 0.3937;
            this.lengthOfToe = lengthOfToe * 3.28;
            this.lengthOfHeel = lengthOfHeel * 3.28;
            this.noOfBarsStemExposed = noOfBarsStemExposed;
            this.spacingStemExposed = spacingStemExposed * 0.3937;
            this.noOfBarsStemExposed = noOfBarsStemExposed;
            this.noOfBarsStemEarth = noOfBarsStemEarth;
            this.noOfMainBarsToe = noOfMainBarsToe;
            this.noOfSecondaryBarsToe = noOfSecondaryBarsToe;
            this.spacingOfMainBarsToe = spacingOfMainBarsToe * 0.3937;
            this.spacingOfSecondaryBarsToe = spacingOfSecondaryBarsToe * 0.3937;
            this.noOfMainBarsHeel = noOfMainBarsHeel;
            this.noOfSecondaryBarsHeel = noOfSecondaryBarsHeel;
            this.noOfMainBarsKey = noOfMainBarsKey;
            this.spacingOfMainBarsKey = spacingOfMainBarsKey * 0.3937;
            this.noOfSecondaryBarsKey = noOfSecondaryBarsKey;
            this.spacingOfSecondaryBarsHeel = spacingOfSecondaryBarsHeel * 0.3937;
            this.spacingOfMainBarsHeel = spacingOfMainBarsHeel * 0.3937;
            this.spacingOfSecondaryBarKey = spacingOfSecondaryBarKey * 0.3937;
            this.widthOfKey = widthOfKey * 0.3937;
            this.lD = lD * 0.3937;
        }
    }

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        diaOfMainBarLabel.setText(String.format("%.2f", diaOfMainBar));
        diaOfSecondaryBarLabel.setText(String.format("%.2f", diaOfSecondaryBar));
        lDLabel.setText(String.format("%.2f", lD));
        thicknessOfStemBotCantiliverLabel.setText(String.format("%.2f", thicknesOfStemBottom));
        thicknessOfStemTopCantiliverLabel.setText(String.format("%.2f", thicknessOfStemTop));
        dFCantiliverLabel.setText(String.format("%.2f", dF));
        h2CantiliverLabel.setText(String.format("%.2f", h2));
        heightOfToeSlabCantiliverLabel.setText(String.format("%.2f", (heightOfToe)));
        heightOfHeelSlabCantiliverLabel.setText(String.format("%.2f", (heightHeelSlab)));
        lengthOfToeCantiliverLabel.setText(String.format("%.2f", lengthOfToe));
        lengthOfHeelCantiliverLabel.setText(String.format("%.2f", lengthOfHeel));
        secondaryBarStemExposedCantiliverLabel.setText(noOfBarsStemExposed + "Bars@" + spacingStemExposed + "c/c");
        secondaryBarStemEarthCantiliverLabel.setText(noOfBarsStemEarth + "Bars@" + spacingStemEarth + "c/c");
        mainBarToeCantiliverLabel.setText(noOfMainBarsToe + "Bars@" + spacingOfMainBarsToe + "c/c");
        secondaryBarToeCantiliverLabel.setText(noOfSecondaryBarsToe + "Bars@" + spacingOfSecondaryBarsToe + "c/c");
        mainBarHeelCantiliverLabel.setText(noOfMainBarsHeel + "Bars@" + spacingOfMainBarsHeel + "c/c");
        secondaryBarHeelCantiliverLabel.setText(noOfSecondaryBarsHeel + "Bars@" + spacingOfSecondaryBarsHeel + "c/c");
        mainSteelKeyLabel.setText(String.format("%.2f", noOfMainBarsKey) + "Bars@" + String.format("%.2f", spacingOfMainBarsKey) + "c/c");
        secondarySteelForKeyLabel.setText(String.format("%.2f", noOfSecondaryBarsKey) + "Bars@" + String.format("%.2f", spacingOfSecondaryBarKey) + "c/c");
        depthOfKeyLabel.setText(String.format("%.2f", depthOfKey));
        widthOfKeyLabel.setText(String.format("%.2f", widthOfKey));
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
            m6Label.setText("m");
            m7Label.setText("m");
            m8Label.setText("m");
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
            m6Label.setText("ft");
            m7Label.setText("ft");
            m8Label.setText("ft");
        }
    }
}
