import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * //Author: Eng.HaiderAli
 */
public class ImageController3 implements Initializable {
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
    Label h2Label;
    @FXML
    Label mainBarHeelLabel;
    @FXML
    Label secondaryBarHeelLabel;
    @FXML
    Label secondaryBarStemEarthLabel;
    @FXML
    Label mainSteelKeyLabel;
    @FXML
    Label secondarySteelForKeyLabel;
    @FXML
    Label depthOfKeyLabel;
    @FXML
    Label widthOfKeyLabel;
    @FXML
    Label heightOfHeelSlabLabel;
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
    Label m5Label;
    double lD;
    double thicknesOfStemBottom;
    double thicknessOfStemTop;
    double dF;
    double h2;
    double heightOfHeel;
    double lengthOfHeel;
    double noOfBarsStemExposed;
    double spacingStemExposed;
    double noOfBarsStemEarth;
    double spacingStemEarth;
    double noOfMainBarsHeel;
    double noOfSecondaryBarsHeel;
    double spacingOfMainBarsHeel;
    double spacingOfSecondaryBarsHeel;
    double depthOfKey;
    double widthOfKey;
    double noOfMainBarsKey;
    double spacingOfMainBarsKey;
    double noOfSecondaryBarsKey;
    double spacingOfSecondaryBarKey;
    double diaOfMainBar;
    double diaOfSecondaryBar;
    double unitKey;

    ImageController3(double thicknesOfStemBottom, double thicknessOfStemTop, double dF, double h2, double heightOfHeel, double lengthOfHeel, double noOfBarsStemExposed, double spacingStemExposed, double noOfBarsStemEarth, double spacingStemEarth, double noOfMainBarsHeel, double noOfSecondaryBarsHeel, double spacingOfMainBarsHeel, double spacingOfSecondaryBarsHeel, double depthOfKey, double widthOfKey, double noOfMainBarsKey, double spacingOfMainBarsKey, double noOfSecondaryBarsKey, double spacingOfSecondaryBarKey, double unitKey, double lD,double diaOfMainBar,double diaOfSecondaryBar) {
        this.unitKey=unitKey;
        if (unitKey == 1) {
            this.diaOfMainBar=diaOfMainBar;
            this.diaOfSecondaryBar=diaOfSecondaryBar;
            this.lD = lD;
            this.depthOfKey = depthOfKey;
            this.dF = dF;
            this.thicknesOfStemBottom = thicknesOfStemBottom;
            this.thicknessOfStemTop = thicknessOfStemTop;
            this.h2 = h2;
            this.heightOfHeel = heightOfHeel;
            this.spacingStemEarth = spacingStemEarth;
            this.lengthOfHeel = lengthOfHeel;
            this.noOfBarsStemExposed = noOfBarsStemExposed;
            this.spacingStemExposed = spacingStemExposed;
            this.noOfBarsStemExposed = noOfBarsStemExposed;
            this.noOfBarsStemEarth = noOfBarsStemEarth;
            this.noOfMainBarsHeel = noOfMainBarsHeel;
            this.noOfSecondaryBarsHeel = noOfSecondaryBarsHeel;
            this.noOfMainBarsKey = noOfMainBarsKey;
            this.spacingOfMainBarsKey = spacingOfMainBarsKey;
            this.noOfSecondaryBarsKey = noOfSecondaryBarsKey;
            this.spacingOfSecondaryBarsHeel = spacingOfSecondaryBarsHeel;
            this.spacingOfMainBarsHeel = spacingOfMainBarsHeel;
            this.spacingOfSecondaryBarKey = spacingOfSecondaryBarKey;
            this.widthOfKey = widthOfKey;

        } else {
            this.diaOfMainBar=diaOfMainBar*0.3937;
            this.diaOfSecondaryBar=diaOfSecondaryBar*0.3937;
            this.depthOfKey = depthOfKey * 3.28;
            this.dF = dF * 3.28;
            this.thicknesOfStemBottom = thicknesOfStemBottom * 0.3937;
            this.thicknessOfStemTop = thicknessOfStemTop * 0.3937;
            this.h2 = h2 * 3.28;
            this.heightOfHeel = heightOfHeel * 3.28;
            this.spacingStemEarth = spacingStemEarth * 0.3937;
            this.lengthOfHeel = lengthOfHeel * 3.28;
            this.noOfBarsStemExposed = noOfBarsStemExposed;
            this.spacingStemExposed = spacingStemExposed * 0.3937;
            this.noOfBarsStemExposed = noOfBarsStemExposed;
            this.noOfBarsStemEarth = noOfBarsStemEarth;
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
        thicknessOfStemBotLabel.setText(String.format("%.2f", thicknesOfStemBottom));
        thicknessOfStemTopLabel.setText(String.format("%.2f", thicknessOfStemTop));
        dFLabel.setText(String.format("%.2f", dF));
        h2Label.setText(String.format("%.2f", (h2)));
        heightOfHeelSlabLabel.setText(String.format("%.2f", (heightOfHeel)));
        lengthOfHeelLabel.setText(String.format("%.2f", lengthOfHeel));
        secondaryBarStemExposedLabel.setText(String.format("%.2f", noOfBarsStemExposed) + "Bars@" + String.format("%.2f", spacingStemExposed) + "c/c");
        secondaryBarStemEarthLabel.setText(String.format("%.2f", noOfBarsStemEarth) + "Bars@" + String.format("%.2f", spacingStemEarth) + "c/c");
        mainBarHeelLabel.setText(String.format("%.2f", noOfMainBarsHeel) + "Bars@" + String.format("%.2f", spacingOfMainBarsHeel) + "c/c");
        secondaryBarHeelLabel.setText(String.format("%.2f", noOfSecondaryBarsHeel) + "Bars@" + String.format("%.2f", spacingOfSecondaryBarsHeel) + "c/c");
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
            m5Label.setText("m");
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

        }


    }
}
