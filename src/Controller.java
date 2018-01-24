//Author: Eng.HaiderAli

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class Controller {


    Wall wall;
    int wallType;
    double lD = 0;
    @FXML
    private ToggleGroup unitToggleGroup;
    @FXML
    private ToggleGroup wallToggleGroup;
    @FXML
    private ToggleGroup loadingToggleGroup;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private AnchorPane anchorPane3;
    @FXML
    TextField tfHeightOfWallAboveGround;
    @FXML
    TextField tFFrictionAngle;
    @FXML
    TextField tFBearingCapacity;
    @FXML
    TextField tFSurchargeDensity;
    @FXML
    TextField tFSoilDensity;
    @FXML
    TextField tFCoefficientOfFriction;
    @FXML
    TextField tFDensityOfConcrete;
    @FXML
    TextField tFSlopeAngle;
    @FXML
    TextField tFSurchargeLoad;
    @FXML
    TextField tFSurchargeHeight;
    @FXML
    TextField tFSurchargeDistanceFromWall;
    @FXML
    TextField tFTensileStressOfSteel;
    @FXML
    TextField tFDiaOfMainRebar;
    @FXML
    TextField tFDiaOfSecondaryRebar;
    @FXML
    Label ldLabel;
    @FXML
    Label dFLabel;
    @FXML
    Label thicknessOfStem_bottomLabel;
    @FXML
    Label thicknessOfStem_topLabel;
    @FXML
    Label eDepth_HeelLabel;
    @FXML
    Label lengthOfHeelLabel;
    @FXML
    Label areaOfMainSteelForHeelLabel;
    @FXML
    Label spacingOfMainSteelForHeelLabel;
    @FXML
    Label areaOfSecondarySteelForHeelLabel;
    @FXML
    Label spacingOfSecondarySteelForHeelLabel;
    @FXML
    Label heightOfToeLabel;
    @FXML
    Label lengthOfToeLabel;
    @FXML
    Label eDepthOfToeLabel;
    @FXML
    Label areaOfMainSteelForToeLabel;
    @FXML
    Label spacingOfMainSteelForToeLabel;
    @FXML
    Label areaOfSecondarySteelForToeLabel;
    @FXML
    Label spacingOfSecondarySteelForToeLabel;
    @FXML
    Label depthOfKeyLabel;
    @FXML
    Label widthOfKeyLabel;
    @FXML
    Label areaOfMainSteelForKeyLabel;
    @FXML
    Label spacingOfMainSteelForKeyLabel;
    @FXML
    Label areaOfSecondarySteelForKeyLabel;
    @FXML
    Label spacingOfSecondarySteelForKeyLabel;
    @FXML
    Label spacingOfCounterfortLabel;
    @FXML
    Label eDepthOfCounterfortLabel;
    @FXML
    Label widthOfCounterfortLabel;
    @FXML
    Label areaOfSteelForStirrupsLabel;
    @FXML
    Label spacingOfSteelForStirrupsLabel;
    @FXML
    Label eccentricityCheckLabel;
    @FXML
    Label overturningFailureCheckLabel;
    @FXML
    Label slidingFailureCheckLabel;
    @FXML
    Label shearCheckHeel_WallLabel;
    @FXML
    Label shearCheck_Wall_ToeLab;
    @FXML
    Button stemSteelButton;
    @FXML
    Button mainSteelCounterfortButton;
    @FXML
    Button secondarySteelCounterfortButton;
    @FXML
    Label shearCheckLabel;
    @FXML
    Label areaOfSecondarySteelExposedFaceLabel;
    @FXML
    Label spacingOfSecondarySteelEarthFaceLabel;
    @FXML
    Label areaOfSecondarySteelEarthFaceLabel;
    @FXML
    Label spacingOfSecondarySteelExposedFaceLabel;
    @FXML
    Label lengthOfBaseLabel;
    @FXML
    Label noOfSecondaryBarsEarthFaceLabel;
    @FXML
    Label noOfSecondaryBarsExposedFaceLabel;
    @FXML
    Label noOfSecondaryBarsOfToeLabel;
    @FXML
    Label noOfMainBarsOfToeLabel;
    @FXML
    Label noOfMainBarsOfHeelLabel;
    @FXML
    Label noOfSecondaryBarsOfHeelLabel;
    @FXML
    Label noOfMainBarsOfKeyLabel;
    @FXML
    Label noOfSecondaryBarsOfKeyLabel;
    @FXML
    Label noOfDoubleLeggedStirrupsLabel;
    @FXML
    Label maxPressureUnderBaseLabel;
    @FXML
    CheckBox seismicYesBox;
    @FXML
    CheckBox seismicNoBox;
    @FXML
    CheckBox advancedInputsCheckBox;
    @FXML
    TextField tfah;
    @FXML
    TextField tfav;
    @FXML
    Label errorLabel1;
    @FXML
    Label errorLabel2;
    @FXML
    TextField tFNoOfRowsOfRebars;
    @FXML
    TextField tfThicknessOfStem_Bottom;
    @FXML
    TextField tfThicknessOfStem_Top;
    @FXML
    TextField tfLengthOfBase;
    @FXML
    TextField tfHeightOfToeSlab;
    @FXML
    TextField tfHeightOfHeelSlab;
    @FXML
    TextField tfLateralEarthPressre;
    @FXML
    Button lateralEarthPressureButton;
    @FXML
    Label pressureUnderToeLabel;
    @FXML
    Label pressureUnderHeelLabel;
    @FXML
    Button finishButton;
    @FXML
    Label eccentricityLabel;
    double seismicKey;
    ObservableList<Integer> optionsForSteelGrade =
            FXCollections.observableArrayList(
                    100,
                    150,
                    200,
                    250,
                    300,
                    350,
                    400
            );
    double unitKey;


    @FXML
    ComboBox<Integer> comboBoxGradeOfSteel = new ComboBox<>();

    @FXML
    private void mainSteelOfCounterfortButtonController(ActionEvent actionEvent) {
        CounterfortMainSteelController controller = new CounterfortMainSteelController(wall.counterfortMainSteelDataArray, unitKey);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CounterfortMainSteel.fxml"));
        loader.setController(controller);
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Counterfort Main Steel");
            stage.setScene(new Scene(root, 1000, 450));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void finishButtonController(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Are you sure you want to Exit RRWP?");
        alert.setContentText("Choose your option");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {
        }
    }

    @FXML
    private void handleAdvancedInputsCheckBox() {
        if (advancedInputsCheckBox.isSelected()) {
            if (wallType != 2) {
                tfThicknessOfStem_Bottom.setText("");
                tfThicknessOfStem_Bottom.setDisable(false);

            }
            if (wallType != 3) {
                tfHeightOfToeSlab.setText("");
                tfHeightOfToeSlab.setDisable(false);

            }
            tfThicknessOfStem_Top.setDisable(false);
            tfLengthOfBase.setDisable(false);
            tfHeightOfHeelSlab.setDisable(false);
            tfLateralEarthPressre.setDisable(false);
            if (seismicYesBox.isSelected()) {
                lateralEarthPressureButton.setDisable(true);
            } else {
                lateralEarthPressureButton.setDisable(false);
            }
        } else {
            lateralEarthPressureButton.setDisable(true);
            tfHeightOfHeelSlab.setText("");
            tfHeightOfToeSlab.setText("");
            tfLengthOfBase.setText("");
            tfThicknessOfStem_Top.setText("");
            tfThicknessOfStem_Bottom.setText("");
            tfLateralEarthPressre.setText("");
            tfThicknessOfStem_Bottom.setDisable(true);
            tfThicknessOfStem_Top.setDisable(true);
            tfLengthOfBase.setDisable(true);
            tfHeightOfToeSlab.setDisable(true);
            tfHeightOfHeelSlab.setDisable(true);
            tfLateralEarthPressre.setDisable(true);

        }
    }

    @FXML
    private void lateralEarthPressureCaller(ActionEvent event) {

        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "LateralEarthPressureCalculator.jar");
        pb.directory(new File("./"));
        try {
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleYesBox() {
        if (seismicYesBox.isSelected()) {
            seismicNoBox.setSelected(false);
            seismicKey = 1;
            tfav.setDisable(false);
            tfah.setDisable(false);
            lateralEarthPressureButton.setDisable(true);
        }
    }

    @FXML
    private void handleNoBox() {
        if (seismicNoBox.isSelected()) {
            seismicYesBox.setSelected(false);
            seismicKey = 0;
            tfah.setText("0");
            tfav.setText("0");
            tfah.setDisable(true);
            tfav.setDisable(true);
        }
    }

    @FXML
    private void imageController(ActionEvent actionEvent) {
        if (wallType == 1) {
            ImageController1 controller = new ImageController1(wall.thicknessofStem_bottom, wall.thicknessOfStem_Top, wall.dF, wall.h2, wall.heightToeSlab, wall.lengthOfToe, wall.lengthOfHeel, wall.noOfSecondaryBars_StemExposedFace, wall.spacingExposedFace, wall.noOfSecondaryBars_StemEarthFace, wall.spacingEarthFace, wall.noOfMainBars_Toe, wall.noOfSecondaryBars_Toe, wall.spacingOfMainSteelForToe, wall.spacingOfSecondarySteelForToe, wall.noOfMainBars_Heel, wall.noOfSecondaryBars_Heel, wall.spacingOfMainSteelForHeel, wall.spacingOfSecondarySteelForHeel, wall.depthOfKey, wall.widthOfKeyProvided, wall.noOfMainBars_Key, wall.spacingOfMainSteelForKey, wall.noOfSecondaryBars_Key, wall.spacingOfSecondarySteelForKey, unitKey, wall.heightHeelSlab, lD, wall.diaOfMainRebar, wall.diaOfSecondaryRebar);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ImageController1.fxml"));
            loader.setController(controller);
            Parent root;
            try {
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Cantiliver Wall Image");
                stage.setScene(new Scene(root, 763, 693));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (wallType == 2) {
            ImageController2 controller = new ImageController2(wall.thicknessofStem_bottom, wall.thicknessOfStem_Top, wall.dF, wall.h2, wall.heightToeSlab, wall.lengthOfToe, wall.lengthOfHeel, wall.noOfSecondaryBars_StemExposedFace, wall.spacingExposedFace, wall.noOfMainBars_Toe, wall.noOfSecondaryBars_Toe, wall.spacingOfMainSteelForToe, wall.spacingOfSecondarySteelForToe, wall.noOfMainBars_Heel, wall.noOfSecondaryBars_Heel, wall.spacingOfMainSteelForHeel, wall.spacingOfSecondarySteelForHeel, wall.noOfBarsRequiredForHeelCounterfortConnection, wall.spacingOfTiesForHeelCounterfortConnection, unitKey, wall.heightHeelSlab, lD, wall.diaOfMainRebar, wall.diaOfSecondaryRebar);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ImageController2.fxml"));
            loader.setController(controller);
            Parent root;
            try {
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Counterfort Wall Image");
                stage.setScene(new Scene(root, 755, 645));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ImageController3 controller = new ImageController3(wall.thicknessofStem_bottom, wall.thicknessOfStem_Top, wall.dF, wall.h2, wall.heightToeSlab, wall.lengthOfHeel, wall.noOfSecondaryBars_StemExposedFace, wall.spacingExposedFace, wall.noOfSecondaryBars_StemEarthFace, wall.spacingEarthFace, wall.noOfMainBars_Heel, wall.noOfSecondaryBars_Heel, wall.spacingOfMainSteelForHeel, wall.spacingOfSecondarySteelForHeel, wall.depthOfKey, wall.widthOfKeyProvided, wall.noOfMainBars_Key, wall.spacingOfMainSteelForKey, wall.noOfSecondaryBars_Key, wall.spacingOfSecondarySteelForKey, unitKey, lD, wall.diaOfMainRebar, wall.diaOfSecondaryRebar);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ImageController3.fxml"));
            loader.setController(controller);
            Parent root;
            try {
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("L-shape Wall Image");
                stage.setScene(new Scene(root, 579, 616));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void secondarySteelOfCounterfortButtonController(ActionEvent actionEvent) {
        CounterfortSecondarySteelController controller = new CounterfortSecondarySteelController(wall.counterfortSecondarySteelDataArray, unitKey);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CounterfortSecondarySteel.fxml"));
        loader.setController(controller);
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Counterfort Secondary Steel");
            stage.setScene(new Scene(root, 1000, 450));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void stemSteelButtonController(ActionEvent actionEvent) {
        StemSteelController controller = new StemSteelController(wall.stemDataArray, unitKey);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StemSteel.fxml"));
        loader.setController(controller);
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Stem Steel");
            stage.setScene(new Scene(root, 1000, 450));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void nextPage1Action(ActionEvent actionEvent) {
        try {
            errorLabel1.setText("");
//      Wall init
            RadioButton wallRadioButton = (RadioButton) wallToggleGroup.getSelectedToggle();
            if (wallRadioButton.getId().equals("wall1")) {
                wallType = 1;
                wall = new CantiliverDesign();
                shearCheckLabel.setDisable(false);
                tFNoOfRowsOfRebars.setText("0");
                tFNoOfRowsOfRebars.setDisable(true);
                mainSteelCounterfortButton.setDisable(true);
                secondarySteelCounterfortButton.setDisable(true);
            } else if (wallRadioButton.getId().equals("wall2")) {
                wallType = 2;
                wall = new CounterFortDesign();
                shearCheckLabel.setDisable(true);
                tFNoOfRowsOfRebars.setDisable(false);
                mainSteelCounterfortButton.setDisable(false);
                secondarySteelCounterfortButton.setDisable(false);
            } else if (wallRadioButton.getId().equals("wall3")) {
                wallType = 3;
                wall = new LShapeDesign();
                shearCheckLabel.setDisable(false);
                heightOfToeLabel.setText("Not applicable");
                lengthOfToeLabel.setText("Not applicable");
                eDepthOfToeLabel.setText("Not applicable");
                areaOfMainSteelForToeLabel.setText("Not applicable");
                spacingOfMainSteelForToeLabel.setText("Not applicable");
                areaOfSecondarySteelForToeLabel.setText("Not applicable");
                spacingOfSecondarySteelForToeLabel.setText("Not applicable");
                noOfSecondaryBarsOfToeLabel.setText("Not applicable");
                noOfMainBarsOfToeLabel.setText("Not applicable");
                tFNoOfRowsOfRebars.setText("0");
                tFNoOfRowsOfRebars.setDisable(true);
                mainSteelCounterfortButton.setDisable(true);
                secondarySteelCounterfortButton.setDisable(true);

            }


//        Loading Init
            RadioButton loadingRadioButton = (RadioButton) loadingToggleGroup.getSelectedToggle();
            if (loadingRadioButton.getId().equals("loading1")) {
                wall.loading = 1;
            } else if (loadingRadioButton.getId().equals("loading2")) {
                wall.loading = 2;
            } else if (loadingRadioButton.getId().equals("loading3")) {
                wall.loading = 3;
            } else if (loadingRadioButton.getId().equals("loading4")) {
                wall.loading = 4;
            }
            RadioButton unitRadioButton = (RadioButton) unitToggleGroup.getSelectedToggle();
            if (unitRadioButton.getId().equals("unit1")) {
                unitKey = 1;
            } else {
                unitKey = 2;
            }
            if (seismicKey == 1) {
                wall.seismicKey = 1;
            } else {
                wall.seismicKey = 0;
            }
            anchorPane1.setTranslateZ(1);
            anchorPane2.setTranslateZ(0);
        } catch (Exception e) {
            errorLabel1.setText("Invalid Inputs! Please recheck your inputs");
        }
        tfHeightOfHeelSlab.setText("");
        tfHeightOfToeSlab.setText("");
        tfLengthOfBase.setText("");
        tfThicknessOfStem_Top.setText("");
        tfThicknessOfStem_Bottom.setText("");
        tfLateralEarthPressre.setText("");
    }

    @FXML
    private void backOfPage2Action(ActionEvent actionEvent) {
        anchorPane1.setTranslateZ(0);
        anchorPane2.setTranslateZ(1);
    }

    @FXML
    private void nextPage2Action(ActionEvent actionEvent) {
        wall.stringBuilder.delete(0, wall.stringBuilder.length());
        System.out.println(wall.stringBuilder.toString());
        try {
            errorLabel2.setText("");
            if (unitKey == 1) {
                wall.heightAboveGround = Double.parseDouble(tfHeightOfWallAboveGround.getText());
                wall.frictionAngle = Double.parseDouble(tFFrictionAngle.getText()) * Math.PI / 180;
                wall.bearingCapacity = Double.parseDouble(tFBearingCapacity.getText()) * 100;
                wall.surchargeDensity = Double.parseDouble(tFSurchargeDensity.getText()) * 100;
                wall.soilDensity = Double.parseDouble(tFSoilDensity.getText()) * 100;
                wall.coefficientOfFriction = Double.parseDouble(tFCoefficientOfFriction.getText());
                wall.densityConcrete = Double.parseDouble(tFDensityOfConcrete.getText()) * 100;
                wall.slopeAngle = Double.parseDouble(tFSlopeAngle.getText()) * Math.PI / 180;
                wall.surchargeLoad = Double.parseDouble(tFSurchargeLoad.getText()) * 100;
                wall.surchargeHeight = Double.parseDouble(tFSurchargeHeight.getText());
                wall.surchargeDistanceFromWall = Double.parseDouble(tFSurchargeDistanceFromWall.getText());
                wall.sigmaST = Double.parseDouble(tFTensileStressOfSteel.getText());
                wall.diaOfMainRebar = Double.parseDouble(tFDiaOfMainRebar.getText());
                wall.diaOfSecondaryRebar = Double.parseDouble(tFDiaOfSecondaryRebar.getText());
                wall.gradeOfConcrete = comboBoxGradeOfSteel.getValue();
                wall.rowsOfRebars = Double.parseDouble(tFNoOfRowsOfRebars.getText());
                if (wall.seismicKey == 1) {
                    wall.a_h = Double.parseDouble(tfah.getText());
                    wall.a_v = Double.parseDouble(tfav.getText());
                }
                try {
                    wall.thicknessofStem_bottom = Double.parseDouble(tfThicknessOfStem_Bottom.getText());

                } catch (Exception e) {
                    wall.thicknessofStem_bottom = 0;
                }
                try {
                    wall.thicknessOfStem_Top = Double.parseDouble(tfThicknessOfStem_Top.getText());

                } catch (Exception e) {
                    wall.thicknessOfStem_Top = 0;
                }
                try {
                    wall.lengthOfBase = Double.parseDouble(tfLengthOfBase.getText());

                } catch (Exception e) {
                    wall.lengthOfBase = 0;
                }
                try {
                    wall.heightToeSlab = Double.parseDouble(tfHeightOfToeSlab.getText());

                } catch (Exception e) {
                    wall.heightToeSlab = 0;
                }
                try {
                    wall.heightHeelSlab = Double.parseDouble(tfHeightOfHeelSlab.getText());

                } catch (Exception e) {
                    wall.heightHeelSlab = 0;
                }
                try {
                    wall.backfillEarthPressure = Double.parseDouble(tfLateralEarthPressre.getText()) * 100;

                } catch (Exception e) {
                    wall.backfillEarthPressure = 0;
                }

            } else {
                wall.heightAboveGround = Double.parseDouble(tfHeightOfWallAboveGround.getText()) * 0.3048;
                wall.frictionAngle = Double.parseDouble(tFFrictionAngle.getText()) * Math.PI / 180;
                wall.bearingCapacity = Double.parseDouble(tFBearingCapacity.getText()) * 4882.41;
                wall.surchargeDensity = Double.parseDouble(tFSurchargeDensity.getText()) * 16.08;
                wall.soilDensity = Double.parseDouble(tFSoilDensity.getText()) * 16.08;
                wall.coefficientOfFriction = Double.parseDouble(tFCoefficientOfFriction.getText());
                wall.densityConcrete = Double.parseDouble(tFDensityOfConcrete.getText()) * 16;
                wall.slopeAngle = Double.parseDouble(tFSlopeAngle.getText()) * Math.PI / 180;
                wall.surchargeLoad = Double.parseDouble(tFSurchargeLoad.getText()) * 4.8824;
                wall.surchargeHeight = Double.parseDouble(tFSurchargeHeight.getText()) * 0.3048;
                wall.surchargeDistanceFromWall = Double.parseDouble(tFSurchargeDistanceFromWall.getText()) * 0.3048;
                wall.sigmaST = Double.parseDouble(tFTensileStressOfSteel.getText());
                wall.diaOfMainRebar = Double.parseDouble(tFDiaOfMainRebar.getText()) * 2.54;
                wall.diaOfSecondaryRebar = Double.parseDouble(tFDiaOfSecondaryRebar.getText()) * 2.54;
                wall.gradeOfConcrete = comboBoxGradeOfSteel.getValue();
                wall.rowsOfRebars = Double.parseDouble(tFNoOfRowsOfRebars.getText());
                if (wall.seismicKey == 1) {
                    wall.a_h = Double.parseDouble(tfah.getText()) * 0.3048;
                    wall.a_v = Double.parseDouble(tfav.getText()) * 0.3048;
                }
                try {
                    wall.thicknessofStem_bottom = Double.parseDouble(tfThicknessOfStem_Bottom.getText()) * 2.54;

                } catch (Exception e) {
                    wall.thicknessofStem_bottom = 0;
                }
                try {
                    wall.thicknessOfStem_Top = Double.parseDouble(tfThicknessOfStem_Top.getText()) * 2.54;

                } catch (Exception e) {
                    wall.thicknessOfStem_Top = 0;
                }
                try {
                    wall.lengthOfBase = Double.parseDouble(tfLengthOfBase.getText()) * 0.3048;

                } catch (Exception e) {
                    wall.lengthOfBase = 0;
                }
                try {
                    wall.heightToeSlab = Double.parseDouble(tfHeightOfToeSlab.getText()) * 0.3048;

                } catch (Exception e) {
                    wall.heightToeSlab = 0;
                }
                try {
                    wall.heightHeelSlab = Double.parseDouble(tfHeightOfHeelSlab.getText()) * 0.3048;

                } catch (Exception e) {
                    wall.heightHeelSlab = 0;
                }
                try {
                    wall.backfillEarthPressure = Double.parseDouble(tfLateralEarthPressre.getText()) * 1.48816;

                } catch (Exception e) {
                    wall.backfillEarthPressure = 0;
                }
            }
            wall.runWallCode();
            anchorPane2.setTranslateZ(2);
            anchorPane3.setTranslateZ(0);
        } catch (Exception e) {
            errorLabel2.setText("Invalid Inputs! Please recheck your inputs");

        }

    }

    @FXML
    private void backOfPage3Action() {
        anchorPane2.setTranslateZ(0);
        anchorPane3.setTranslateZ(2);

    }

    @FXML
    void horizontalSurchargeSelectionAction(ActionEvent actionEvent) {
        tFSlopeAngle.setText("0");
        tFSlopeAngle.setDisable(true);
        tFSurchargeLoad.setText("0");
        tFSurchargeLoad.setDisable(true);
        tFSurchargeHeight.setDisable(false);
        tFSurchargeDistanceFromWall.setText("0");
        tFSurchargeDistanceFromWall.setDisable(true);
        tFSurchargeDensity.setDisable(false);
    }

    @FXML
    void slopingSurchargeSelectionAction(ActionEvent actionEvent) {
        tFSlopeAngle.setDisable(false);
        tFSurchargeLoad.setText("0");
        tFSurchargeLoad.setDisable(true);
        tFSurchargeHeight.setText("0");
        tFSurchargeHeight.setDisable(true);
        tFSurchargeDistanceFromWall.setText("0");
        tFSurchargeDistanceFromWall.setDisable(true);
        tFSurchargeDensity.setDisable(false);
    }

    @FXML
    void superImposedLoadingSelectionAction(ActionEvent actionEvent) {
        tFSlopeAngle.setText("0");
        tFSlopeAngle.setDisable(true);
        tFSurchargeLoad.setDisable(false);
        tFSurchargeHeight.setText("0");
        tFSurchargeHeight.setDisable(true);
        tFSurchargeDistanceFromWall.setText("0");
        tFSurchargeDistanceFromWall.setDisable(true);
        tFSurchargeDensity.setText("0");
        tFSurchargeDensity.setDisable(true);
    }

    @FXML
    void trackLoadingSelectionAction(ActionEvent actionEvent) {
        tFSlopeAngle.setText("0");
        tFSlopeAngle.setDisable(true);
        tFSurchargeLoad.setDisable(false);
        tFSurchargeHeight.setText("0");
        tFSurchargeHeight.setDisable(true);
        tFSurchargeDistanceFromWall.setDisable(false);
        tFSurchargeDensity.setText("0");
        tFSurchargeDensity.setDisable(true);
    }

    @FXML
    void hideTrackLoading(ActionEvent event) {
        ((RadioButton) (loadingToggleGroup.getToggles().get(3))).setDisable(true);
    }

    @FXML
    void showTrackLoading(ActionEvent event) {
        ((RadioButton) (loadingToggleGroup.getToggles().get(3))).setDisable(false);
    }

    @FXML
    private void calculateAction(ActionEvent actionEvent) {
        if (unitKey == 1) {
            eccentricityLabel.setText(String.format("%.4f", wall.eccentricityOfResultant));
            pressureUnderToeLabel.setText(String.format("%.2f", wall.maxPressureUnderBase * 10));
            pressureUnderHeelLabel.setText(String.format("%.2f", wall.minimumPressureUnderBase * 10));
            areaOfSecondarySteelEarthFaceLabel.setText(String.format("%.2f", wall.areaOfSecondaryBarEarthFace));
            areaOfSecondarySteelExposedFaceLabel.setText(String.format("%.2f", wall.areaOfSecondaryBarExposedFace));
            spacingOfSecondarySteelEarthFaceLabel.setText(String.format("%.2f", wall.spacingEarthFace));
            spacingOfSecondarySteelExposedFaceLabel.setText(String.format("%.2f", wall.spacingExposedFace));
            lengthOfBaseLabel.setText(String.format("%.2f", wall.lengthOfBase));
            noOfSecondaryBarsEarthFaceLabel.setText(String.format("%.2f", wall.noOfSecondaryBars_StemEarthFace));
            noOfSecondaryBarsExposedFaceLabel.setText(String.format("%.2f", wall.noOfSecondaryBars_StemExposedFace));
            noOfMainBarsOfHeelLabel.setText(String.format("%.2f", wall.noOfMainBars_Heel));
            noOfSecondaryBarsOfHeelLabel.setText(String.format("%.2f", wall.noOfSecondaryBars_Heel));
            dFLabel.setText(String.format("%.2f", wall.dF));
            thicknessOfStem_bottomLabel.setText(String.format("%.2f", wall.thicknessofStem_bottom));
            thicknessOfStem_topLabel.setText(String.format("%.2f", wall.thicknessOfStem_Top));
            eDepth_HeelLabel.setText(String.format("%.2f", wall.eDepthOfHeelProvided));
            lengthOfHeelLabel.setText(String.format("%.2f", wall.lengthOfHeel));
            areaOfMainSteelForHeelLabel.setText(String.format("%.2f", wall.areaOfMainSteelForHeel));
            spacingOfMainSteelForHeelLabel.setText(String.format("%.2f", wall.spacingOfMainSteelForHeel));
            areaOfSecondarySteelForHeelLabel.setText(String.format("%.2f", wall.areaOfSecondarySteelForHeel));
            spacingOfSecondarySteelForHeelLabel.setText(String.format("%.2f", wall.spacingOfSecondarySteelForHeel));
            if (wallType != 3) {
                heightOfToeLabel.setText(String.format("%.2f", wall.heightToeSlab));
                lengthOfToeLabel.setText(String.format("%.2f", wall.lengthOfToe));
                eDepthOfToeLabel.setText(String.format("%.2f", wall.eDepthOfToeProvided));
                areaOfMainSteelForToeLabel.setText(String.format("%.2f", wall.areaOfMainSteelForToe));
                spacingOfMainSteelForToeLabel.setText(String.format("%.2f", wall.spacingOfMainSteelForToe));
                areaOfSecondarySteelForToeLabel.setText(String.format("%.2f", wall.areaOfSecondarySteelForToe));
                spacingOfSecondarySteelForToeLabel.setText(String.format("%.2f", wall.spacingOfSecondarySteelForToe));
                noOfMainBarsOfToeLabel.setText(String.format("%.2f", wall.noOfMainBars_Toe));
                noOfSecondaryBarsOfToeLabel.setText(String.format("%.2f", wall.noOfSecondaryBars_Toe));
            }
            noOfSecondaryBarsOfKeyLabel.setText(String.format("%.2f", wall.noOfSecondaryBars_Key));
            noOfMainBarsOfKeyLabel.setText(String.format("%.2f", wall.noOfMainBars_Key));
            depthOfKeyLabel.setText(String.format("%.2f", wall.depthOfKey));
            widthOfKeyLabel.setText(String.format("%.2f", wall.eWidthOfKeyProvided));
            areaOfMainSteelForKeyLabel.setText(String.format("%.2f", wall.areaOFMainSteelForKey));
            spacingOfMainSteelForKeyLabel.setText(String.format("%.2f", wall.spacingOfMainSteelForKey));
            areaOfSecondarySteelForKeyLabel.setText(String.format("%.2f", wall.areaOFSecondarySteelForKey));
            spacingOfSecondarySteelForKeyLabel.setText(String.format("%.2f", wall.spacingOfSecondarySteelForKey));
            if (wallType == 2) {
                noOfDoubleLeggedStirrupsLabel.setText(String.format("%.2f", wall.noOfBarsRequiredForHeelCounterfortConnection));
                spacingOfCounterfortLabel.setText(String.format("%.2f", wall.spacingOfCounterforts));
                eDepthOfCounterfortLabel.setText(String.format("%.2f", wall.eDepthOfCounterfort));
                widthOfCounterfortLabel.setText(String.format("%.2f", wall.adoptedWidthOfCounterfort));
                areaOfSteelForStirrupsLabel.setText(String.format("%.2f", wall.steelRequiredForHeelCounterfortConnection));
                spacingOfSteelForStirrupsLabel.setText(String.format("%.2f", wall.spacingOfTiesForHeelCounterfortConnection));
            } else {
                noOfDoubleLeggedStirrupsLabel.setText("Not applicable");
                spacingOfCounterfortLabel.setText("Not applicable");
                eDepthOfCounterfortLabel.setText("Not applicable");
                widthOfCounterfortLabel.setText("Not applicable");
                areaOfSteelForStirrupsLabel.setText("Not applicable");
                spacingOfSteelForStirrupsLabel.setText("Not applicable");

            }
            lD = 55 * wall.diaOfMainRebar / 10;
        } else {
            eccentricityLabel.setText(String.format("%.24", wall.eccentricityOfResultant * 3.28));
            pressureUnderToeLabel.setText(String.format("%.2f", wall.maxPressureUnderBase * 23.73036));
            pressureUnderHeelLabel.setText(String.format("%.2f", wall.minimumPressureUnderBase * 23.73036));
            areaOfSecondarySteelEarthFaceLabel.setText(String.format("%.2f", wall.areaOfSecondaryBarEarthFace * 0.155));
            areaOfSecondarySteelExposedFaceLabel.setText(String.format("%.2f", wall.areaOfSecondaryBarExposedFace * 0.155));
            spacingOfSecondarySteelEarthFaceLabel.setText(String.format("%.2f", wall.spacingEarthFace * 0.3937));
            spacingOfSecondarySteelExposedFaceLabel.setText(String.format("%.2f", wall.spacingExposedFace * 0.3937));
            lengthOfBaseLabel.setText(String.format("%.2f", wall.lengthOfBase * 3.28));
            noOfSecondaryBarsEarthFaceLabel.setText(String.format("%.2f", wall.noOfSecondaryBars_StemEarthFace));
            noOfSecondaryBarsExposedFaceLabel.setText(String.format("%.2f", wall.noOfSecondaryBars_StemExposedFace));
            noOfMainBarsOfHeelLabel.setText(String.format("%.2f", wall.noOfMainBars_Heel));
            noOfSecondaryBarsOfHeelLabel.setText(String.format("%.2f", wall.noOfSecondaryBars_Heel));
            dFLabel.setText(String.format("%.2f", wall.dF * 3.28));
            thicknessOfStem_bottomLabel.setText(String.format("%.2f", wall.thicknessofStem_bottom * 0.3937));
            thicknessOfStem_topLabel.setText(String.format("%.2f", wall.thicknessOfStem_Top * 0.3937));
            eDepth_HeelLabel.setText(String.format("%.2f", wall.eDepthOfHeelProvided * 0.3937));
            lengthOfHeelLabel.setText(String.format("%.2f", wall.lengthOfHeel * 3.28));
            areaOfMainSteelForHeelLabel.setText(String.format("%.2f", wall.areaOfMainSteelForHeel * 0.155));
            spacingOfMainSteelForHeelLabel.setText(String.format("%.2f", wall.spacingOfMainSteelForHeel * 0.3937));
            areaOfSecondarySteelForHeelLabel.setText(String.format("%.2f", wall.areaOfSecondarySteelForHeel * 0.155));
            spacingOfSecondarySteelForHeelLabel.setText(String.format("%.2f", wall.spacingOfSecondarySteelForHeel * 0.3937));
            if (wallType != 3) {
                heightOfToeLabel.setText(String.format("%.2f", wall.heightToeSlab * 3.28));
                lengthOfToeLabel.setText(String.format("%.2f", wall.lengthOfToe * 3.28));
                eDepthOfToeLabel.setText(String.format("%.2f", wall.eDepthOfToeProvided * 0.3937));
                areaOfMainSteelForToeLabel.setText(String.format("%.2f", wall.areaOfMainSteelForToe * 0.155));
                spacingOfMainSteelForToeLabel.setText(String.format("%.2f", wall.spacingOfMainSteelForToe * 0.3937));
                areaOfSecondarySteelForToeLabel.setText(String.format("%.2f", wall.areaOfSecondarySteelForToe * 0.155));
                spacingOfSecondarySteelForToeLabel.setText(String.format("%.2f", wall.spacingOfSecondarySteelForToe * 0.3937));
                noOfMainBarsOfToeLabel.setText(String.format("%.2f", wall.noOfMainBars_Toe));
                noOfSecondaryBarsOfToeLabel.setText(String.format("%.2f", wall.noOfSecondaryBars_Toe));
            }
            noOfSecondaryBarsOfKeyLabel.setText(String.format("%.2f", wall.noOfSecondaryBars_Key));
            noOfMainBarsOfKeyLabel.setText(String.format("%.2f", wall.noOfMainBars_Key));
            depthOfKeyLabel.setText(String.format("%.2f", wall.depthOfKey * 3.28));
            widthOfKeyLabel.setText(String.format("%.2f", wall.eWidthOfKeyProvided * 0.3937));
            areaOfMainSteelForKeyLabel.setText(String.format("%.2f", wall.areaOFMainSteelForKey * 0.155));
            spacingOfMainSteelForKeyLabel.setText(String.format("%.2f", wall.spacingOfMainSteelForKey * 0.3937));
            areaOfSecondarySteelForKeyLabel.setText(String.format("%.2f", wall.areaOFSecondarySteelForKey * 0.155));
            spacingOfSecondarySteelForKeyLabel.setText(String.format("%.2f", wall.spacingOfSecondarySteelForKey * 0.3937));
            if (wallType == 2) {
                noOfDoubleLeggedStirrupsLabel.setText(String.format("%.2f", wall.noOfBarsRequiredForHeelCounterfortConnection));
                spacingOfCounterfortLabel.setText(String.format("%.2f", wall.spacingOfCounterforts * 3.28));
                eDepthOfCounterfortLabel.setText(String.format("%.2f", wall.eDepthOfCounterfort * 0.3937));
                widthOfCounterfortLabel.setText(String.format("%.2f", wall.adoptedWidthOfCounterfort * 0.3937));
                areaOfSteelForStirrupsLabel.setText(String.format("%.2f", wall.steelRequiredForHeelCounterfortConnection * 0.155));
                spacingOfSteelForStirrupsLabel.setText(String.format("%.2f", wall.spacingOfTiesForHeelCounterfortConnection * 0.3937));
            } else {
                noOfDoubleLeggedStirrupsLabel.setText("Not applicable");
                spacingOfCounterfortLabel.setText("Not applicable");
                eDepthOfCounterfortLabel.setText("Not applicable");
                widthOfCounterfortLabel.setText("Not applicable");
                areaOfSteelForStirrupsLabel.setText("Not applicable");
                spacingOfSteelForStirrupsLabel.setText("Not applicable");

            }
            lD = 55 * wall.diaOfMainRebar / 10;
        }

        if (wall.shearCheckFactor_Stem == 1) {
            shearCheckLabel.setText("Shear check is ok for stem");
        } else {
            shearCheckLabel.setText("Stem will fail in shear");
        }
        if (wall.maxPressureUnderBaseCheckFactor == 1) {
            maxPressureUnderBaseLabel.setText("Max pressure under base is less than bearing capacity of soil");
        } else {
            maxPressureUnderBaseLabel.setText("Max pressure under base is more than bearing capacity of soil");
        }
        if (wall.fS_Overturning > 1.5) {
            overturningFailureCheckLabel.setText("Overturning failure will not occur");
        } else {
            overturningFailureCheckLabel.setText("Overturning failure will occur");

        }
        if (wall.fS_Sliding > 1.5) {
            slidingFailureCheckLabel.setText("Sliding failure will not occur key is not required");
        } else {
            slidingFailureCheckLabel.setText("Sliding failure will occur key is required");
        }
        if (wall.ecentricityCheckFactor == 1) {
            eccentricityCheckLabel.setText("Eccentricity check for base is OK");
        } else {
            eccentricityCheckLabel.setText("Base fails in eccentricity check");
        }
        if (wall.shearCheckFactorFOrHellWallJunction == 1) {
            shearCheckHeel_WallLabel.setText("Shear check for heel-wall junction is OK");
        } else {
            shearCheckHeel_WallLabel.setText("Shear check for heel-wall junction fails");
        }
        if (wall.shearCheckFactorForToeWallJunction == 1) {
            shearCheck_Wall_ToeLab.setText("Shear check for toe wall junction is OK");
        } else {
            shearCheck_Wall_ToeLab.setText("Shear check for toe wall junction fails");
        }


    }

    @FXML
    private void excelFileGenerator() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setTitle("Export Results");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("CSV", ".csv"));
        File file = fileChooser.showSaveDialog(anchorPane3.getScene().getWindow());
        if (file == null) return;
        if (!file.toString().endsWith(".csv")) file = new File(file.toString() + ".csv");

        try {
            Files.write(
                    Paths.get(file.getPath()),
                    wall.stringBuilder.toString().getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

        } catch (IOException e) {
        }
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void initialize() {
        setupCustomTooltipBehavior(500, 99999, 500);
        tfah.setTooltip(new Tooltip("Horizontal component of  earthquake accelration"));
        tfav.setTooltip(new Tooltip("Vertical component of  earthquake accelration"));
        tfHeightOfWallAboveGround.setTooltip(new Tooltip("Height of Soil to be retained"));
        tFFrictionAngle.setTooltip(new Tooltip("Phi of backfill soil (degree):\n" + "Wet Sand:   30-35\n" + "Gravel:   35-45\n" + "Coarse to medium sand :   35-40\n" + "Fine to salty sand:   30-35\n" + "Granite and shale:   30-35\n" + "Basalt & Dolerites:   35-45\n" + "Limestone & Sandstone:   35-45\n" + "Chalk:   35-45\n" + "Broken brick:   35-45\n" + "Ashes:   35-45\n"));
        tFBearingCapacity.setTooltip(new Tooltip("Bearing Capacity of soil(KN/m2(Kip/ft2)):\n" + "Dry clay:  50(1.04)-100(2.08) \n" + "Damp clay:  100(2.08)-150(3.13)\n" + "Dry earth:  150(3.13)-250(5.22)\n" + "Moist earth:  150(3.13)-200(4.17)\n" + "Dry sand:  100(2.08)-250(5.22)\n" + "Wet Sand:   100(2.08)-250(5.22)\n" + "Gravel:   450(9.39)\n" + "Coarse to medium sand :   250(5.22)-450(9.39)\n" + "Fine to salty sand:   150(3.13)\n" + "Granite and shale:   450(9.39)-900(18.79)\n" + "Basalt & Dolerites:   900(18.79)\n" + "Limestone & Sandstone:   450(9.39)\n"));
        tFSurchargeDensity.setTooltip(new Tooltip("Density of Surcharge Soil (KN/m3(lb/ft3)): \n" + "Dry clay:  14.5(90.52) \n" + "Damp clay:  17.5(109.24)\n" + "Dry earth:  15(93.64)-18(112.37)\n" + "Moist earth:  16(99.88)-20(124.85)\n" + "Dry sand:  15.5(96.76)-16(99.88)\n" + "Wet Sand:   17.5(109.24)-20(124.85)\n" + "Gravel:   16(99.88)-20(124.85)\n" + "Coarse to medium sand :   16.5(103)-21(131.09)\n" + "Fine to salty sand:   17.5(109.24)-21.5(134.22)\n" + "Granite and shale:   16(99.88)-21(131.09)\n" + "Basalt & Dolerites:   17.5(109.24)-22(137.34)\n" + "Limestone & Sandstone:   13(81.15)-19(118.61)\n" + "Chalk:   10(62.42)-13(81.15)\n" + "Broken brick:   11(68.67)-17.5(109.24)-\n" + "Ashes:   6.5(40.57)-10(62.42)\n"));
        tFSoilDensity.setTooltip(new Tooltip("Density of Surcharge Soil (KN/m3(lb/ft3)): \n" + "Dry clay:  14.5(90.52) \n" + "Damp clay:  17.5(109.24)\n" + "Dry earth:  15(93.64)-18(112.37)\n" + "Moist earth:  16(99.88)-20(124.85)\n" + "Dry sand:  15.5(96.76)-16(99.88)\n" + "Wet Sand:   17.5(109.24)-20(124.85)\n" + "Gravel:   16(99.88)-20(124.85)\n" + "Coarse to medium sand :   16.5(103)-21(131.09)\n" + "Fine to salty sand:   17.5(109.24)-21.5(134.22)\n" + "Granite and shale:   16(99.88)-21(131.09)\n" + "Basalt & Dolerites:   17.5(109.24)-22(137.34)\n" + "Limestone & Sandstone:   13(81.15)-19(118.61)\n" + "Chalk:   10(62.42)-13(81.15)\n" + "Broken brick:   11(68.67)-17.5(109.24)-\n" + "Ashes:   6.5(40.57)-10(62.42)\n"));
        tFCoefficientOfFriction.setTooltip(new Tooltip("Coefficient of friction between soil and concrete:\n" + "Dry clay:  0.1-0.6 \n" + "Dry earth:  0.33-0.45\n" + "Wet Sand:   0.5-0.6\n" + "Gravel:   0.5-0.6\n" + "Granite and shale:   0.5-0.6"));
        tFDensityOfConcrete.setTooltip(new Tooltip("Density of concrete (usual value 24 KN/m3(150 lb/ft3))"));
        tFSlopeAngle.setTooltip(new Tooltip("Angle of sloping surcharge with horizontal"));
        tFSurchargeLoad.setTooltip(new Tooltip("Load of surcharge on soil under consideration"));
        tFSurchargeHeight.setTooltip(new Tooltip("Height of Surcharge above soil"));
        tFSurchargeDistanceFromWall.setTooltip(new Tooltip("Distance between tracking loading and wall stem at top"));
        tFTensileStressOfSteel.setTooltip(new Tooltip("Tensile stress of steel being used (For mild steel= 1400 kg/cm2)"));
        tFNoOfRowsOfRebars.setTooltip(new Tooltip("No of layers of rebars in counterfort"));
        comboBoxGradeOfSteel.setTooltip(new Tooltip("  Grade 100:           \n" +
                "               sigma(Compressive-Bending)=30(kg/cm2)\n" +
                "                sigma(Compressive-Direct)=25(kg/cm2)\n" +
                "                bondStress=4(kg/cm2)\n" +
                "                shearStress=3(kg/cm2)\n" +
                "Grade 150:\n" +
                "               sigma(Compressive-Bending)=50(kg/cm2)\n" +
                "                sigma(Compressive-Direct)=40(kg/cm2)\n" +
                "                bondStress=6(kg/cm2)\n" +
                "                shearStress=5(kg/cm2)\n" +
                "Grade 200:\n" +
                "               sigma(Compressive-Bending)=70(kg/cm2)\n" +
                "                sigma(Compressive-Direct)=50(kg/cm2)\n" +
                "                bondStress=8(kg/cm2)\n" +
                "                shearStress=7(kg/cm2)\n" +
                "Grade 250:\n" +
                "                sigma(Compressive-Bending)=85(kg/cm2)\n" +
                "                sigma(Compressive-Direct)=60(kg/cm2)\n" +
                "                bondStress=9(kg/cm2)\n" +
                "                shearStress=8(kg/cm2)\n" +
                "Grade 300:\n" +
                "                sigma(Compressive-Bending)=100(kg/cm2)\n" +
                "                sigma(Compressive-Direct)=80(kg/cm2)\n" +
                "                bondStress=10(kg/cm2)\n" +
                "                shearStress=9(kg/cm2)\n" +
                "Grade 350:\n" +
                "                sigma(Compressive-Bending)=115(kg/cm2)\n" +
                "                sigma(Compressive-Direct)=90(kg/cm2)\n" +
                "                bondStress=11(kg/cm2)\n" +
                "                shearStress=10(kg/cm2)\n" +
                "Grade 400:\n" +
                "                sigma(Compressive-Bending)=130(kg/cm2)\n" +
                "                sigma(Compressive-Direct)=100(kg/cm2)\n" +
                "                bondStress=12(kg/cm2)\n" +
                "                shearStress=11(kg/cm2)"));
        comboBoxGradeOfSteel.setItems(optionsForSteelGrade);
    }

    private void setupCustomTooltipBehavior(int openDelayInMillis, int visibleDurationInMillis, int closeDelayInMillis) {
        try {

            Class TTBehaviourClass = null;
            Class<?>[] declaredClasses = Tooltip.class.getDeclaredClasses();
            for (Class c : declaredClasses) {
                if (c.getCanonicalName().equals("javafx.scene.control.Tooltip.TooltipBehavior")) {
                    TTBehaviourClass = c;
                    break;
                }
            }
            if (TTBehaviourClass == null) {
                // abort
                return;
            }
            Constructor constructor = TTBehaviourClass.getDeclaredConstructor(
                    Duration.class, Duration.class, Duration.class, boolean.class);
            if (constructor == null) {
                // abort
                return;
            }
            constructor.setAccessible(true);
            Object newTTBehaviour = constructor.newInstance(
                    new Duration(openDelayInMillis), new Duration(visibleDurationInMillis),
                    new Duration(closeDelayInMillis), false);
            if (newTTBehaviour == null) {
                // abort
                return;
            }
            Field ttbehaviourField = Tooltip.class.getDeclaredField("BEHAVIOR");
            if (ttbehaviourField == null) {
                // abort
                return;
            }
            ttbehaviourField.setAccessible(true);

            // Cache the default behavior if needed.
            Object defaultTTBehavior = ttbehaviourField.get(Tooltip.class);
            ttbehaviourField.set(Tooltip.class, newTTBehaviour);

        } catch (Exception e) {
            System.out.println("Aborted setup due to error:" + e.getMessage());
        }
    }


}
