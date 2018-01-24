import java.util.ArrayList;

/**
 * //Author: Eng.HaiderAli
 */
// started L shape on 30 january 2016
public class LShapeDesign extends Wall {
    static double Roundup(double input, double multiple) {


        return Math.ceil(input / multiple) * multiple;

    }

    static double Round(double input, double multiple) {


        return Math.round(input / multiple) * multiple;

    }

    static double Rounddown(double input, double multiple) {
        input = input - (input % multiple);
        return input;
    }

    public static double quadraticEquationRoot1(double a, double b, double c) {
        double root1, root2;
        root1 = (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
        root2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
        return Math.max(root1, root2);
    }

    static double AreaCalculator(double inputDia) {
        return (Math.PI / 4) * (inputDia) * (inputDia);
    }

    @Override
    public void runWallCode() {
        stringBuilder.append("Note: Following units are used in this file:" + "\n");
        stringBuilder.append("Lengths: m ; Smaller Lengths: cm ; Area: cm2 ; Lateral Earth Pressure: kg/m ; Moment: kgm " + "\n");
        stringBuilder.append("Pressure under base: ton/m2 ; ShearStress: kg/cm2" + "\n");
        stringBuilder.append("Please refer to software manual for any kind of misunderstanding" + "\n");
        //Concrete Properties
        Concrete concrete = new Concrete(gradeOfConcrete);
        //Characteristic Strength
        double m = 2800 / (3 * concrete.sigmaCB);
        double coefficientofN = 1 / ((sigmaST / (concrete.sigmaCB * m)) + 1);
        double coefficientofM = coefficientofN * 0.87 * (concrete.sigmaCB / 2);
        System.out.println("coefficientofM" + ":" + coefficientofM);
        // Trial section
        stringBuilder.append("-----Trial Section-----" + "\n");
        dF = 1;
        double equationA = Math.cos(slopeAngle) * ((Math.cos(slopeAngle) - (Math.sqrt(Math.cos(slopeAngle) * Math.cos(slopeAngle) - Math.cos(frictionAngle) * Math.cos(frictionAngle)))) / (Math.cos(slopeAngle) + (Math.sqrt(Math.cos(slopeAngle) * Math.cos(slopeAngle) - Math.cos(frictionAngle) * Math.cos(frictionAngle)))));
        double equationB = (1 - Math.sin(frictionAngle)) / (1 + Math.sin(frictionAngle));
        double checkFactor = (bearingCapacity / soilDensity) * equationB * equationB;
        if (dF < checkFactor) {
            dF = Roundup(checkFactor, 0.5);
        }
        stringBuilder.append("Depth of foundation is :" + dF + "\n");
        System.out.println("depth of foundation" + ":" + dF);
        stringBuilder.append("-----Earth Pressure Calculation-----" + "\n");
        //EarthPressure Calculation
        //h2=height from top of toe slab to bottom of surcharge
        double totalHeight = heightAboveGround + dF;
        double overallHeight = 0; // Initialized caz used later and just for super imposed case
        if (loading == 3) {
            surchargeHeight = surchargeLoad / soilDensity;
            overallHeight = totalHeight + surchargeHeight;
        }
        if (heightToeSlab == 0) {
            heightToeSlab = totalHeight / 12;
            if (loading == 3) {
                heightToeSlab = overallHeight / 12;
            }
        }
        heightToeSlab = Roundup(heightToeSlab, 0.1);
        stringBuilder.append("Height of toe slab is :" + heightToeSlab + "\n");
        System.out.println("Height of toe slab  " + heightToeSlab);
        h2 = totalHeight - heightToeSlab;
        double h1 = h2 - surchargeDistanceFromWall; //Only used in track loading see notes page 26
        if (thicknessofStem_bottom == 0) {
            thicknessofStem_bottom = (totalHeight / 12) * 100;   //In cm
            if (loading == 3) {
                thicknessofStem_bottom = (overallHeight / 12) * 100;
            }
        }
        thicknessofStem_bottom = Roundup(thicknessofStem_bottom, 10);
        if (thicknessOfStem_Top == 0) {
            thicknessOfStem_Top = (totalHeight / 24) * 100;   //In cm
            if (loading == 3) {
                thicknessOfStem_Top = (overallHeight / 24) * 100;
            }
        }
        thicknessOfStem_Top = Roundup(thicknessOfStem_Top, 5);
        stringBuilder.append("Thickness of Stem at top is :" + thicknessOfStem_Top + "\n");
        stringBuilder.append("Thickness of Stem at bottom is :" + thicknessofStem_bottom + "\n");
        System.out.println(thicknessofStem_bottom);
        System.out.println(thicknessOfStem_Top);
        double equivalentHeightOfFillDueToSurcharge = 0; // initialized just because it will be used in another loop later on.
        double surchargeEarthPressure = 0;
        if (loading == 1) {
            surchargeEarthPressure = surchargeDensity * surchargeHeight * equationA * h2;
            surchargeEarthPressure = Round(surchargeEarthPressure, 1);
        }
        if (loading == 3) {
            surchargeEarthPressure = soilDensity * surchargeHeight * equationA * h2;
            surchargeEarthPressure = Round(surchargeEarthPressure, 1);
        }
        if (loading == 4) {
            equivalentHeightOfFillDueToSurcharge = surchargeLoad / soilDensity;
            surchargeEarthPressure = soilDensity * equivalentHeightOfFillDueToSurcharge * equationA * h1;
            surchargeEarthPressure = Round(surchargeEarthPressure, 1);
        }
        double equationC; // Page 334  Principle of Soil Mechanics Braja M Das
        double theeta;
        double partial;
        double equationD;// Page 334  Principle of Soil Mechanics Braja M Das
        if (seismicKey == 1) {
            partial = 0.5 * frictionAngle;
            k_h = a_h / 9.8;
            k_v = a_v / 9.8;
            theeta = Math.atan(k_h / (1 - k_v));
            equationD = (1 + Math.sqrt((Math.sin(frictionAngle + partial) * Math.sin(frictionAngle - theeta - slopeAngle)) / (Math.cos(partial + theeta) * Math.cos(slopeAngle))));
            equationC = (Math.cos(frictionAngle - theeta) * Math.cos(frictionAngle - theeta)) / (Math.cos(theeta) * Math.cos(partial + theeta) * equationD * equationD);
            backfillEarthPressure = 0.5 * soilDensity * h2 * h2 * equationC * (1 - k_v);
            System.out.println("K_AE:  " + equationC);
        } else {
            if (backfillEarthPressure == 0) {
                backfillEarthPressure = 0.5 * soilDensity * h2 * equationA * h2;
            }
        }
        backfillEarthPressure = Round(backfillEarthPressure, 1);
        stringBuilder.append("Surcharge Earth Pressure is :" + surchargeEarthPressure + "\n");
        stringBuilder.append("Backfill Earth Pressure is :" + backfillEarthPressure + "\n");
        System.out.println("Surcharge Earth Pressure" + ":" + surchargeEarthPressure);
        System.out.println("BackFill Earth Pressure" + ":" + backfillEarthPressure);
        double totalEarthPressure = surchargeEarthPressure + backfillEarthPressure;
        double lateralEarthPressure = totalEarthPressure * Math.cos(slopeAngle);
        double verticalEarthPressure = totalEarthPressure * Math.sin(slopeAngle);
        stringBuilder.append("Total Earth Pressure is :" + totalEarthPressure + "\n");
        stringBuilder.append("Latertal Earth Pressure is :" + lateralEarthPressure + "\n");
        stringBuilder.append("Vertical Earth Pressure is :" + verticalEarthPressure + "\n");
        System.out.println("Total Earth Pressure" + ":" + totalEarthPressure);
        System.out.println("Lateral Earth Pressure" + ":" + lateralEarthPressure);
        System.out.println("Vertical Earth Pressure" + ":" + verticalEarthPressure);
        stringBuilder.append("-----Stem Design-----" + "\n");
        //Stem Design
        double maxBM_Stem = (surchargeEarthPressure * (h1 / 2)) + (backfillEarthPressure * (h2 / 3));
        double requiredEDepthStem = Math.sqrt((maxBM_Stem / coefficientofM));
        double requiredOverallDepth = requiredEDepthStem + 7.5;
        double eDepthProvided;
        if (requiredOverallDepth < thicknessofStem_bottom) {
            eDepthProvided = thicknessofStem_bottom - 7.5;
        } else {
            eDepthProvided = requiredEDepthStem;
            thicknessofStem_bottom = requiredOverallDepth;
        }
        thicknessofStem_bottom = Roundup(thicknessofStem_bottom, 10);
        stringBuilder.append("Effective Depth of Stem is :" + eDepthProvided + "\n");
        System.out.println("Overall Depth of stem without cover" + ":" + eDepthProvided);
        stringBuilder.append("-----Shear Check for Stem-----" + "\n");
        //Shear check
        double shearCheck = lateralEarthPressure / (0.87 * 100 * eDepthProvided);
        System.out.println("Shear Check" + ":" + shearCheck);
        stringBuilder.append("Max Shear Stress in Stem is :" + shearCheck + "\n");
        if (shearCheck < concrete.shearStress) {
            System.out.println("Shear check is ok for stem");
            stringBuilder.append("Shear Check is ok for Stem" + "\n");
            shearCheckFactor_Stem = 1;
        } else {
            System.out.println("Stem will fail in shear");
            stringBuilder.append("Stem will fail in shear" + "\n");
            shearCheckFactor_Stem = 0;
        }

        //Bending Moment variation(Main Reinforcements)
        stemDataArray = new ArrayList<>();
        double areaofMainBar = AreaCalculator(diaOfMainRebar);
        for (double i = 0.5; i < h2 || i == h2; i += 0.5) {
            double BM_Stem_h;

            if (loading == 1) {
                BM_Stem_h = (surchargeDensity * surchargeHeight * equationA * i * 0.5 * i) + (soilDensity * i * equationA * 0.5 * i * i / 3);
            } else if (loading == 2) {
                BM_Stem_h = equationA * soilDensity * 0.5 * Math.cos(slopeAngle) * ((i * i * i) / 3);
            } else if (loading == 3) {
                BM_Stem_h = (soilDensity * surchargeHeight * equationA * i * 0.5 * i) + (soilDensity * i * equationA * 0.5 * i * i / 3);
            } else {
                if (i > surchargeDistanceFromWall) {
                    BM_Stem_h = 0.5 * soilDensity * equivalentHeightOfFillDueToSurcharge * equationA * (i - surchargeDistanceFromWall) * (i - surchargeDistanceFromWall) + (0.5 * soilDensity * equationA * (i * i * i) / 3);
                } else {
                    BM_Stem_h = 0.5 * soilDensity * equationA * (i * i * i) / 3;
                }
            }
            StemDataClass stemDataClass = new StemDataClass();
            stemDataClass.setBendingMoment(BM_Stem_h);
            stemDataClass.setWallheight(i);
            System.out.println("Bm Stem at " + i + " is " + BM_Stem_h);
            double overallDepthProvided = (thicknessOfStem_Top) + ((thicknessOfStem_Top / (h2 * 100)) * i * 100); //In cm
            double effectiveDepthProvided = overallDepthProvided - 7.5;
            double steelRequired = (BM_Stem_h * 100) / (sigmaST * 0.87 * effectiveDepthProvided);
            stemDataClass.setAreaOfSteelRequired(steelRequired);
            double noOfMainBars_Stem = steelRequired / areaofMainBar;
            noOfMainBars_Stem = Roundup(noOfMainBars_Stem, 1);
            stemDataClass.setNoOfBars(noOfMainBars_Stem);
            System.out.println("No of Main bars bars in stem at " + i + " is " + noOfMainBars_Stem);
            double spacingRequiredMainBar = (areaofMainBar / steelRequired) * 100;
            double spacingAdoptedMainBar;
            if (spacingRequiredMainBar < 16) {
                spacingAdoptedMainBar = 8;
            } else {
                if (spacingRequiredMainBar < 32) {
                    spacingAdoptedMainBar = 16;
                } else {
                    if (spacingRequiredMainBar < 64) {
                        spacingAdoptedMainBar = 32;
                    } else {
                        spacingAdoptedMainBar = 64;
                    }
                }
            }

            System.out.println(spacingAdoptedMainBar);
            stemDataClass.setSpacingAdopted(spacingAdoptedMainBar);
            stemDataArray.add(stemDataClass);

        }
        if (h2 % 0.5 != 0) {
            double BM_Stem_h;

            if (loading == 1) {
                BM_Stem_h = (surchargeDensity * surchargeHeight * equationA * h2 * 0.5 * h2) + (soilDensity * h2 * equationA * 0.5 * h2 * h2 / 3);
            } else if (loading == 2) {
                BM_Stem_h = equationA * soilDensity * 0.5 * Math.cos(slopeAngle) * ((h2 * h2 * h2) / 3);
            } else if (loading == 3) {
                BM_Stem_h = (soilDensity * surchargeHeight * equationA * h2 * 0.5 * h2) + (soilDensity * h2 * equationA * 0.5 * h2 * h2 / 3);
            } else {
                BM_Stem_h = 0.5 * soilDensity * equivalentHeightOfFillDueToSurcharge * equationA * (h2 - surchargeDistanceFromWall) * (h2 - surchargeDistanceFromWall) + (0.5 * soilDensity * equationA * (h2 * h2 * h2) / 3);
            }
            StemDataClass stemDataClass = new StemDataClass();
            stemDataClass.setBendingMoment(BM_Stem_h);
            stemDataClass.setWallheight(h2);
            System.out.println("Bm stem at " + h2 + " is " + BM_Stem_h);
            double overallDepthProvided = (thicknessOfStem_Top) + ((thicknessOfStem_Top / (h2 * 100)) * h2 * 100); //In cm
            double effectiveDepthProvided = overallDepthProvided - 7.5;
            double steelRequired = (BM_Stem_h * 100) / (sigmaST * 0.87 * effectiveDepthProvided);
            stemDataClass.setAreaOfSteelRequired(steelRequired);
            double noOfMainBars_Stem = steelRequired / areaofMainBar;
            noOfMainBars_Stem = Roundup(noOfMainBars_Stem, 1);
            System.out.println("No of Main bars bars in stem at " + h2 + " is " + noOfMainBars_Stem);
            double spacingRequiredMainBar = (areaofMainBar / steelRequired) * 100;
            double spacingAdoptedMainBar;
            if (spacingRequiredMainBar < 16) {
                spacingAdoptedMainBar = 8;
            } else {
                if (spacingRequiredMainBar < 32) {
                    spacingAdoptedMainBar = 16;
                } else {
                    if (spacingRequiredMainBar < 64) {
                        spacingAdoptedMainBar = 32;
                    } else {
                        spacingAdoptedMainBar = 64;
                    }
                }
            }

            System.out.println(spacingAdoptedMainBar);
            stemDataClass.setSpacingAdopted(spacingAdoptedMainBar);
            stemDataArray.add(stemDataClass);

        }
        stringBuilder.append("-----Secondary Rebars of Stem-----" + "\n");
        //Secondary Reinforcements
        double concreteArea_cm = 0.5 * (thicknessOfStem_Top + thicknessofStem_bottom) * h2 * 100; // in cm
        concreteArea_cm = Roundup(concreteArea_cm, 100);
        stringBuilder.append("Concrete Area for Secondary Rebars Design is(cm 2) :" + concreteArea_cm + "\n");
        System.out.println("Concrete Area for secondary design of stem" + ":" + concreteArea_cm);
        double areaofSteel = concreteArea_cm * 0.15 * 0.01;//In cm
        stringBuilder.append("Steel Area for Secondary Rebars Design is :" + areaofSteel + "\n");
        System.out.println("Area of steel for secondary reinforcement of  stem " + ":" + areaofSteel);
        double areaofSecondaryBar = AreaCalculator(diaOfSecondaryRebar);
        areaOfSecondaryBarEarthFace = (areaofSteel / 3);
        noOfSecondaryBars_StemEarthFace = (areaofSteel / 3) / areaofSecondaryBar;
        noOfSecondaryBars_StemEarthFace = Roundup(noOfSecondaryBars_StemEarthFace, 1);
        stringBuilder.append("No of Secondary Bars at Earth Face is :" + noOfSecondaryBars_StemEarthFace + "\n");
        System.out.println("No of Secondary bars  in stem at Earth face  is " + noOfSecondaryBars_StemEarthFace);
        spacingEarthFace = (areaofSecondaryBar * h2 * 100) / (areaofSteel / 3);
        spacingEarthFace = Rounddown(spacingEarthFace, 5);
        stringBuilder.append("Spacing in Secondary Bars at Earth Face is :" + spacingEarthFace + "\n");
        System.out.println("Spacing on Earth face in secondary reinforcement of stem" + ":" + spacingEarthFace);// In cm
        areaOfSecondaryBarExposedFace = (2 * areaofSteel / 3);
        noOfSecondaryBars_StemExposedFace = (2 * areaofSteel / 3) / areaofSecondaryBar;
        noOfSecondaryBars_StemExposedFace = Roundup(noOfSecondaryBars_StemExposedFace, 1);
        stringBuilder.append("No of Seconday Bars at Exposed Face is :" + noOfSecondaryBars_StemExposedFace + "\n");
        System.out.println("No of Secondary bars  in stem at Exposed face  is " + noOfSecondaryBars_StemExposedFace);
        spacingExposedFace = (areaofSecondaryBar * h2 * 100) / (2 * areaofSteel / 3);
        spacingExposedFace = Rounddown(spacingExposedFace, 5);
        stringBuilder.append("Spacing in Secondary Bars at Exposed Face is :" + spacingExposedFace + "\n");
        System.out.println("Spacing on exposed face in secondary reinforcement of stem" + ":" + spacingExposedFace);
        stringBuilder.append("-----Base Design-----" + "\n");
        //Base Design
        double weighOfStemRect = (thicknessOfStem_Top / 100) * (totalHeight - heightToeSlab) * densityConcrete;
        weighOfStemRect = weighOfStemRect / 1000;// Conversion to tons
        if (lengthOfBase == 0) {
            lengthOfBase = (3 * totalHeight) / 5;
            if (loading == 3) {
                lengthOfBase = (3 * overallHeight) / 5;
            }
            lengthOfBase = Roundup(lengthOfBase, 0.5);
        }
        stringBuilder.append("Length of Base is :" + lengthOfBase + "\n");
        System.out.println("length of Base" + lengthOfBase);
        lengthOfHeel = lengthOfBase - (thicknessofStem_bottom / 100);
        double rectMomentArmFromToe = (thicknessOfStem_Top / 200) + lengthOfHeel;
        double weighOfStemTri = 0.5 * (thicknessofStem_bottom - thicknessOfStem_Top) / 100 * (totalHeight - heightToeSlab) * densityConcrete;
        weighOfStemTri = weighOfStemTri / 1000;// Conversion to tons
        double triMomentArmFromToe = (1 * (thicknessofStem_bottom - thicknessOfStem_Top) / 300) + (thicknessOfStem_Top / 100) + lengthOfHeel;
        double weighOfBase = heightToeSlab * lengthOfBase * densityConcrete;
        weighOfBase = weighOfBase / 1000;// Conversion to tons
        double baseMomentArmFromToe = lengthOfBase / 2;
        double weightOfBackFill = (h2) * (lengthOfHeel) * soilDensity;
        weightOfBackFill = weightOfBackFill / 1000; // Conversion to tons
        double backFillMomentArmFromToe = (lengthOfHeel / 2);
        double weightOfSurcharge;
        double surchargeMomentArmFromToe;
        if (loading == 1) {
            weightOfSurcharge = (surchargeHeight) * (lengthOfHeel) * surchargeDensity;
            weightOfSurcharge = weightOfSurcharge / 1000; // Conversion to tons
            surchargeMomentArmFromToe = (lengthOfHeel / 2);

        } else if (loading == 3) {
            weightOfSurcharge = (surchargeHeight) * (lengthOfHeel) * soilDensity;
            weightOfSurcharge = weightOfSurcharge / 1000; // Conversion to tons
            surchargeMomentArmFromToe = (lengthOfHeel / 2);

        } else {
            weightOfSurcharge = 0.5 * (lengthOfHeel) * Math.tan(slopeAngle) * (lengthOfHeel) * surchargeDensity;
            weightOfSurcharge = weightOfSurcharge / 1000; // Conversion to tons
            surchargeMomentArmFromToe = ((lengthOfHeel) / 3);
        }
        double verticalEarthPressureMomentArmFromToe = lengthOfHeel;
        double totalMoment = (weighOfStemRect * rectMomentArmFromToe) + (weighOfStemTri * triMomentArmFromToe) + (weighOfBase * baseMomentArmFromToe) + (weightOfBackFill * backFillMomentArmFromToe) + (weightOfSurcharge * surchargeMomentArmFromToe) + ((verticalEarthPressure / 1000) * verticalEarthPressureMomentArmFromToe);
        double totalVerticalLoad = (weighOfStemTri + weighOfStemRect + weighOfBase + weightOfBackFill + weightOfSurcharge + verticalEarthPressure / 1000);
        double totalVerticalLoadMomentArm = totalMoment / totalVerticalLoad;
        stringBuilder.append("Weight of Stem Rectangular Portion is :" + weighOfStemRect + "\n");
        stringBuilder.append("Weight of Stem Triangular Portion is :" + weighOfStemTri + "\n");
        stringBuilder.append("Weight of Base is :" + weighOfBase + "\n");
        stringBuilder.append("Weight of Backfill is :" + weightOfBackFill + "\n");
        stringBuilder.append("Weight of Surcharge is :" + weightOfSurcharge + "\n");
        stringBuilder.append("Total Vertical Load is :" + totalVerticalLoad + "\n");
        stringBuilder.append("Total Vertical Load Moment Arm is :" + totalVerticalLoadMomentArm + "\n");
        System.out.println("Total vertical load for Base Design" + ":" + totalVerticalLoad);
        System.out.println("Total vertical load moment arm for Base " + ":" + totalVerticalLoadMomentArm);
        System.out.println(weighOfStemRect);
        System.out.println(weighOfStemTri);
        System.out.println(weighOfBase);
        System.out.println(weightOfBackFill);
        System.out.println(surchargeHeight);
        System.out.println(weightOfSurcharge);
        System.out.println(verticalEarthPressure / 1000);
        stringBuilder.append("-----Resultant of Horizontal Forces on Wall-----" + "\n");
        //Resultant of Horizontal Force on the Wall
        double surchargeMomentArmFromBase;
        double backFillMomentArmFromBase;
        double totalHorizontalLoadMomentArmFromBase;

        if (loading == 1 || loading == 3) {
            surchargeMomentArmFromBase = (h2 / 2) + heightToeSlab;
            backFillMomentArmFromBase = (h2 / 3) + heightToeSlab;
            totalHorizontalLoadMomentArmFromBase = ((surchargeEarthPressure * surchargeMomentArmFromBase) + (backfillEarthPressure * backFillMomentArmFromBase)) / lateralEarthPressure;
        } else if (loading == 2) {
            totalHorizontalLoadMomentArmFromBase = h2 / 3 + heightToeSlab;
        } else {
            surchargeMomentArmFromBase = (h1 / 2) + heightToeSlab;
            backFillMomentArmFromBase = (h2 / 3) + heightToeSlab;
            totalHorizontalLoadMomentArmFromBase = ((surchargeEarthPressure * surchargeMomentArmFromBase) + (backfillEarthPressure * backFillMomentArmFromBase)) / lateralEarthPressure;
            stringBuilder.append("Surcharge Moment Arm from Base is :" + surchargeMomentArmFromBase + "\n");
            stringBuilder.append("Backfill Moment Arm from Base is :" + backFillMomentArmFromBase + "\n");
            System.out.println("SurchargeMomentArmFromBase" + surchargeMomentArmFromBase);
            System.out.println("BackFIllMomentArmFromBase" + backFillMomentArmFromBase);
        }
        stringBuilder.append("Total Horizontal Load Moment Arm from Base is :" + totalHorizontalLoadMomentArmFromBase + "\n");
        System.out.println("Total horizontal load moment arm from base for base design" + ":" + totalHorizontalLoadMomentArmFromBase);
        //Point of Application Of Resustant Force on Base
        double resultantForceMomentArmFromBase = (totalVerticalLoad * (lengthOfBase - totalVerticalLoadMomentArm) - (lateralEarthPressure / 1000) * totalHorizontalLoadMomentArmFromBase) / totalVerticalLoad;
        stringBuilder.append("-----Eccentricity of Resultant-----" + "\n");
        // Eccentricity of the Resultant
        stringBuilder.append("Resultant Force Moment Arm from Base is :" + resultantForceMomentArmFromBase + "\n");
        System.out.println("Resultant force moment arm from base" + ":" + resultantForceMomentArmFromBase);
        eccentricityOfResultant = (lengthOfBase / 2) - resultantForceMomentArmFromBase;
        stringBuilder.append("Eccentricity of Resultant is :" + eccentricityOfResultant + "\n");
        System.out.println("Eccentricity of resultant" + ":" + eccentricityOfResultant);
        if (eccentricityOfResultant < (lengthOfBase / 6)) {
            System.out.println("Eccentricity check is ok i.e e<B/6");
            stringBuilder.append("Eccentricity check is ok i.e e < B/6" + "\n");
            ecentricityCheckFactor = 1;
        } else {
            stringBuilder.append("Eccentricity Check Failed i.e e > B/6" + "\n");
            System.out.println("Eccentricity check failed i.e e>B/6");
            ecentricityCheckFactor = 0;

        }
        stringBuilder.append("-----Pressure Distribution Under the Base-----" + "\n");
        //Pressure Distribution Under the Base
        maxPressureUnderBaseCheckFactor = 1;
         maxPressureUnderBase = (totalVerticalLoad / lengthOfBase) * (1 + (6 * eccentricityOfResultant) / lengthOfBase);
         minimumPressureUnderBase = (totalVerticalLoad / lengthOfBase) * (1 - (6 * eccentricityOfResultant) / lengthOfBase);
        if (maxPressureUnderBase > (bearingCapacity / 1000) || maxPressureUnderBase == (bearingCapacity / 1000)) {
            maxPressureUnderBaseCheckFactor = 0;
            stringBuilder.append("Maximum Pressure under the Base is more than Bearing Capacity of Soil" + "\n");
            System.out.println("Maximum pressure under base is more than bearing capacity of soil");
        }
        stringBuilder.append("Maximum Pressure under the Base is :" + maxPressureUnderBase + "\n");
        stringBuilder.append("Minimum Pressure under the Base is :" + minimumPressureUnderBase + "\n");
        System.out.println("Minimum pressure under the base" + ":" + minimumPressureUnderBase);
        System.out.println("Max pressure under the base" + ":" + maxPressureUnderBase);
        stringBuilder.append("-----Heel Design-----" + "\n");
        //Heel Design
        double totalSoilHeight = h2 + surchargeHeight;
        double forceOfSoilLoad = weightOfBackFill + weightOfSurcharge;
        double momentArmOfSoilLoad = lengthOfHeel / 2;
        double forceOfHeelLoad = lengthOfHeel * heightToeSlab * (densityConcrete / 1000);
        double momentArmOfHeelLoad = lengthOfHeel / 2;
        double forceOfRectPressure = minimumPressureUnderBase * lengthOfHeel;
        double momentArmOfRectPressure = lengthOfHeel / 2;
        double pressureUnderBaseAtLengthOfHeel = ((maxPressureUnderBase - minimumPressureUnderBase) / lengthOfBase) * lengthOfHeel;
        double forceOfTriPressure = 0.5 * (pressureUnderBaseAtLengthOfHeel) * lengthOfHeel;
        double momentArmOfTriPressure = lengthOfHeel / 3;
        double totalPositiveMoment = (forceOfSoilLoad * momentArmOfSoilLoad) + (forceOfHeelLoad * momentArmOfHeelLoad);
        double totalNegativeMoment = (forceOfRectPressure * momentArmOfRectPressure) + (forceOfTriPressure * momentArmOfTriPressure);
        System.out.println("Soil load:" + forceOfSoilLoad);
        System.out.println("Heel load:" + forceOfHeelLoad);
        System.out.println("Rect pressure  load:" + forceOfRectPressure);
        System.out.println("Triangular pressure load:" + forceOfTriPressure);
        System.out.println("Soil load moment arm:" + momentArmOfSoilLoad);
        System.out.println("Heel load moment arm:" + momentArmOfHeelLoad);
        System.out.println("Rect pressure  load moment arm:" + momentArmOfRectPressure);
        System.out.println("Triangular pressure load moment arm:" + momentArmOfTriPressure);

        double netMomentOfHeel = totalPositiveMoment - totalNegativeMoment;
        stringBuilder.append("Net Moment is :" + netMomentOfHeel + "\n");
        System.out.println("Net moment of heel design" + netMomentOfHeel);
        double depthOfHeelRequired = Math.sqrt((netMomentOfHeel * 1000 / coefficientofM));
        stringBuilder.append("Depth of Heel Required is :" + depthOfHeelRequired + "\n");
        System.out.println("DepthOfHeelRequired" + depthOfHeelRequired);
        double totalDepthOfHeelProvided;
        if (depthOfHeelRequired < (heightToeSlab * 100)) {
            totalDepthOfHeelProvided = heightToeSlab * 100;
        } else {
            stringBuilder.append("Depth of Heel provided is less than required depth of Heel" + "\n");
            System.out.println("Depth of Heel provided is less than required depth");
            totalDepthOfHeelProvided = depthOfHeelRequired;
            totalDepthOfHeelProvided = Round(totalDepthOfHeelProvided, 10);
        }
        eDepthOfHeelProvided = totalDepthOfHeelProvided - 7.5;
        stringBuilder.append("Effective Depth of Heel is :" + eDepthOfHeelProvided + "\n");
        System.out.println("Effective depth of heel provided" + ":" + eDepthOfHeelProvided);
        heightHeelSlab = totalDepthOfHeelProvided / 100;
        stringBuilder.append("-----Main Rebars of Heel-----" + "\n");
        //Main Rebars of Heel
        areaOfMainSteelForHeel = (netMomentOfHeel * 100000) / (sigmaST * 0.87 * eDepthOfHeelProvided);
        spacingOfMainSteelForHeel = (areaofMainBar * 100) / areaOfMainSteelForHeel;
        System.out.println(spacingOfMainSteelForHeel);
        spacingOfMainSteelForHeel = Rounddown(spacingOfMainSteelForHeel, 0.5);
        stringBuilder.append("Area of Main Steel for Heel is :" + areaOfMainSteelForHeel + "\n");
        System.out.println("Area of main steel for heel" + ":" + areaOfMainSteelForHeel);
        noOfMainBars_Heel = areaOfMainSteelForHeel / areaofMainBar;
        noOfMainBars_Heel = Roundup(noOfMainBars_Heel, 1);
        stringBuilder.append("No of Main Bars in Heel is :" + noOfMainBars_Heel + "\n");
        stringBuilder.append("Spacing of Main Steel for Heel is :" + spacingOfMainSteelForHeel + "\n");
        System.out.println("No of Main bars bars in heel is " + noOfMainBars_Heel);
        System.out.println("Spacing of main steel for heel" + ":" + spacingOfMainSteelForHeel);
        stringBuilder.append("-----Secondary Bars of Heel-----" + "\n");
        //Secondary Bars of Heel
        areaOfSecondarySteelForHeel = 100 * heightToeSlab * 100 * 0.0015;
        spacingOfSecondarySteelForHeel = (areaofSecondaryBar * 100) / areaOfSecondarySteelForHeel;
        spacingOfSecondarySteelForHeel = Rounddown(spacingOfSecondarySteelForHeel, 0.5);
        stringBuilder.append("Area of Secondary Steel for Heel is :" + areaOfSecondarySteelForHeel + "\n");
        System.out.println("Area of secondary steel for heel" + ":" + areaOfSecondarySteelForHeel);
        noOfSecondaryBars_Heel = areaOfSecondarySteelForHeel / areaofSecondaryBar;
        noOfSecondaryBars_Heel = Roundup(noOfSecondaryBars_Heel, 1);
        stringBuilder.append("No of Secondary Bars of Heel is :" + noOfSecondaryBars_Heel + "\n");
        stringBuilder.append("Spacing of Secondary Bars for Heel is :" + spacingOfSecondarySteelForHeel + "\n");
        System.out.println("No of Secondary bars bars in heel is " + noOfSecondaryBars_Heel);
        System.out.println("Spacing of secondary steel for heel" + ":" + spacingOfSecondarySteelForHeel);
        stringBuilder.append("-----Shear Check at Heel-Stem junction-----" + "\n");
        // Shear Check for Heel-Stem Junction
        double maxShearForceAtHeelWallJunction = (forceOfHeelLoad + forceOfSoilLoad - forceOfRectPressure - forceOfTriPressure) * 1000;
        double maxShearAtHeelWallJunction = maxShearForceAtHeelWallJunction / (100 * 0.87 * eDepthOfHeelProvided);
        stringBuilder.append("Max Shear at Heel-Stem Junction is :" + maxShearAtHeelWallJunction + "\n");
        if (maxShearAtHeelWallJunction > concrete.shearStress) {
            System.out.println("Heel and wall junction will fail in shear");
            stringBuilder.append("Heel-Stem Junction will Fail in Shear" + "\n");
            shearCheckFactorFOrHellWallJunction = 0;
        } else {
            shearCheckFactorFOrHellWallJunction = 1;
            System.out.println("Heel and wall junction will not fail in shear");
            stringBuilder.append("Heel-Stem Junction will not Fail in Shear" + "\n");
        }
        stringBuilder.append("-----Stabality Analysis-----" + "\n");
        //Stability Analysis
        fS_Overturning = (totalVerticalLoad * totalVerticalLoadMomentArm) / ((lateralEarthPressure / 1000) * totalHorizontalLoadMomentArmFromBase);
        stringBuilder.append("FOS Overturning is :" + fS_Overturning + "\n");
        if (fS_Overturning > 1.5) {
            depthOfKey = 0;
            eWidthOfKeyProvided = 0;
            widthOfKeyProvided = 0;
            areaOFMainSteelForKey = 0;
            areaOFSecondarySteelForKey = 0;
            spacingOfMainSteelForKey = 0;
            spacingOfSecondarySteelForKey = 0;
            noOfMainBars_Key = 0;
            noOfSecondaryBars_Key = 0;
            System.out.println("Overturning Failure will not occur");
            stringBuilder.append("Overturning Failure will not occur" + "\n");
        } else {
            System.out.println("Overturning Failure will occur");
            stringBuilder.append("Overturning Failure will occur" + "\n");
        }
        fS_Sliding = (coefficientOfFriction * totalVerticalLoad) / (lateralEarthPressure / 1000);
        stringBuilder.append("FOS Sliding is :" + fS_Sliding + "\n");
        if (fS_Sliding > 1.5) {
            System.out.println("Sliding Failure will not occur i.e Key is not required");
            stringBuilder.append("Sliding failure will not occur" + "\n");
        } else {
            System.out.println("Sliding Failure will occur i.e  Key is required");
            stringBuilder.append("Sliding Failure will occur i.e key is required" + "\n");
            stringBuilder.append("-----Design of Key-----" + "\n");
            //Deisgn of Key
            double additionalResistanceRequired = (1.5 * lateralEarthPressure / 1000) - (coefficientOfFriction * totalVerticalLoad);
            double resistanceToBeDevelopedByKey = additionalResistanceRequired * 2;
            resistanceToBeDevelopedByKey = resistanceToBeDevelopedByKey * 1000;//Conversion to Kg
            double passivePressureAtLevelOfBase = soilDensity * heightToeSlab / equationA;
            stringBuilder.append("Passive Earth Pressure at Level of Base is :" + passivePressureAtLevelOfBase + "\n");
            System.out.println("Passive Earth pressure at level of base" + ":" + passivePressureAtLevelOfBase);
            depthOfKey = quadraticEquationRoot1(passivePressureAtLevelOfBase, passivePressureAtLevelOfBase, -resistanceToBeDevelopedByKey);
            depthOfKey = Round(depthOfKey, 1);
            stringBuilder.append("Depth of Key is :" + depthOfKey + "\n");
            double revisedFactorOfSafety = (coefficientOfFriction * totalVerticalLoad + (resistanceToBeDevelopedByKey / 1000)) / (lateralEarthPressure / 1000);
            stringBuilder.append("Revised FOS is :" + revisedFactorOfSafety + "\n");
            System.out.println("Revised factor of safety is" + " " + revisedFactorOfSafety);
            //Width of the Key Wall
            stringBuilder.append("-----Width of Key-----" + "\n");

            double forceOfRect = (additionalResistanceRequired) * depthOfKey;
            double momentArmOfRect = 0.5 * depthOfKey;
            double forceOfTri = 0.5 * (resistanceToBeDevelopedByKey / 1000) * depthOfKey;
            double momentArmOfTri = (2 * depthOfKey) / 3;
            double bendingMomentOfKey = (forceOfRect * momentArmOfRect) + (forceOfTri * momentArmOfTri);
            stringBuilder.append("Max Bending Moment for Design of Key is :" + bendingMomentOfKey + "\n");
            double widthOfKeyRequired = Math.sqrt((bendingMomentOfKey * 1000) / coefficientofM);
            stringBuilder.append("Width of Key Required is :" + widthOfKeyRequired + "\n");
            widthOfKeyProvided = Roundup(widthOfKeyRequired, 10);
            eWidthOfKeyProvided = widthOfKeyProvided - 7.5;
            stringBuilder.append("Effective Width of Key is :" + eWidthOfKeyProvided + "\n");
            System.out.println("Effective width of key provided" + ":" + eWidthOfKeyProvided);
            stringBuilder.append("-----Main Rebars of Key-----" + "\n");
            //Main Rebars of Key
            areaOFMainSteelForKey = (bendingMomentOfKey * 1000 * 100) / (sigmaST * 0.87 * eWidthOfKeyProvided);
            spacingOfMainSteelForKey = (areaofMainBar * 100) / (areaOFMainSteelForKey);
            stringBuilder.append("Area of Main Steel for Key is :" + areaOFMainSteelForKey + "\n");
            System.out.println("Area of main steel for key" + ":" + areaOFMainSteelForKey);
            noOfMainBars_Key = (areaOFMainSteelForKey) / areaofMainBar;
            noOfMainBars_Key = Roundup(noOfMainBars_Key, 1);
            stringBuilder.append("No of Main Bars in Key is :" + noOfMainBars_Key + "\n");
            stringBuilder.append("Spacing of Main Steel for Key is :" + spacingOfMainSteelForKey + "\n");
            System.out.println("No of Main bars  in key is " + noOfMainBars_Key);
            System.out.println("Spacing of main steel for key" + ":" + spacingOfMainSteelForKey);
            stringBuilder.append("-----Secondary Rebars of Key-----" + "\n");
            // Secondary Rebars of Key
            areaOFSecondarySteelForKey = (0.0015) * (100 * widthOfKeyProvided);
            stringBuilder.append("Area of Secondary Steel for Key is :" + areaOFSecondarySteelForKey + "\n");
            System.out.println("Area of secondary steel for key" + ":" + areaOFSecondarySteelForKey);
            noOfSecondaryBars_Key = (areaOFSecondarySteelForKey) / areaofSecondaryBar;
            noOfSecondaryBars_Key = Roundup(noOfSecondaryBars_Key, 1);
            System.out.println("No of Secondary bars  in key is " + noOfSecondaryBars_Key);
            spacingOfSecondarySteelForKey = (areaofSecondaryBar * 100) / (areaOFSecondarySteelForKey);
            stringBuilder.append("No of Secondary Bars in Key is :" + noOfSecondaryBars_Key + "\n");
            stringBuilder.append("Spacing of Secondary Steel for Key is :" + spacingOfSecondarySteelForKey + "\n");
            System.out.println("Spacing of secondary steel for key" + ":" + spacingOfSecondarySteelForKey);

        }


    }

}



