import java.util.ArrayList;

/**
 * //Author: Eng.HaiderAli
 */
public class CounterFortDesign extends Wall {
    // Started counterfort on Thursday 21 january
// Completed counterfort wall on wednesday january 27
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
        stringBuilder.append("-----Trial Section-----" + "\n");
        // Trial section
        dF = 1;
        double equationA = Math.cos(slopeAngle) * ((Math.cos(slopeAngle) - (Math.sqrt(Math.cos(slopeAngle) * Math.cos(slopeAngle) - Math.cos(frictionAngle) * Math.cos(frictionAngle)))) / (Math.cos(slopeAngle) + (Math.sqrt(Math.cos(slopeAngle) * Math.cos(slopeAngle) - Math.cos(frictionAngle) * Math.cos(frictionAngle)))));
        double equationB = (1 - Math.sin(frictionAngle)) / (1 + Math.sin(frictionAngle));
        double checkFactor = (bearingCapacity / soilDensity) * equationB * equationB;
        if (dF < checkFactor) {
            dF = Roundup(checkFactor, 0.5);
        }
        stringBuilder.append("Depth of Foundation is :" + dF + "\n");
        System.out.println("depth of foundation" + ":" + dF);
        stringBuilder.append("-----Earth Pressure Calculation-----" + "\n");
        //EarthPressure Calculation
        //h2=height from top of toe slab to bottom of surcharge
        double totalHeight = heightAboveGround + dF;
        double overallHeight = 0;
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
        heightToeSlab = Roundup(heightToeSlab, 0.05);
        stringBuilder.append("Height of Toe Slab is :" + heightToeSlab + "\n");
        System.out.println("height of toe slab" + heightToeSlab);
        if (heightHeelSlab == 0) {
            heightHeelSlab = totalHeight / 12;
            if (loading == 3) {
                heightHeelSlab = overallHeight / 12;
            }
        }
        heightHeelSlab = Roundup(heightHeelSlab, 0.05);
        stringBuilder.append("Height of Heel Slab is :" + heightHeelSlab + "\n");
        System.out.println("height of Heel slab" + heightHeelSlab);
        spacingOfCounterforts = (2 * totalHeight) / 5;
        if (loading == 3) {
            spacingOfCounterforts = (2 * overallHeight) / 5;
        }
        spacingOfCounterforts = Round(spacingOfCounterforts, 0.5);
        stringBuilder.append("Spacing of Counterforts is :" + spacingOfCounterforts + "\n");
        System.out.println("spacing ofcounterforts" + spacingOfCounterforts);
        h2 = totalHeight - heightHeelSlab;// height to be retained by wall
        double h1 = h2 - surchargeDistanceFromWall; //Only used in track loading see notes page 26
        double wallWidth;
        if (thicknessOfStem_Top == 0) {
            wallWidth = totalHeight / 24;
            if (loading == 3) {
                wallWidth = overallHeight / 12;
            }
        } else {
            wallWidth = thicknessOfStem_Top;
        }
        wallWidth = Round(wallWidth, .05);
        stringBuilder.append("Wall width is :" + wallWidth + "\n");
        System.out.println("wall width" + wallWidth);
        double surchargeEarthPressure = 0;
        if (loading == 1) {
            surchargeEarthPressure = surchargeDensity * surchargeHeight * equationA * h2;
            surchargeEarthPressure = Round(surchargeEarthPressure, 1);
        }
        if (loading == 3) {
            surchargeEarthPressure = soilDensity * surchargeHeight * equationA * h2;
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
        System.out.println("Surcharge Earth Pressure" + ":" + surchargeEarthPressure);
        stringBuilder.append("Backfill Earth Pressure is :" + backfillEarthPressure + "\n");
        System.out.println("BackFill Earth Pressure" + ":" + backfillEarthPressure);
        double totalEarthPressure = surchargeEarthPressure + backfillEarthPressure;
        double lateralEarthPressure = totalEarthPressure * Math.cos(slopeAngle);
        double verticalEarthPressure = totalEarthPressure * Math.sin(slopeAngle);
        stringBuilder.append("Total Earth Pressure is :" + totalEarthPressure + "\n");
        System.out.println("Total Earth Pressure" + ":" + totalEarthPressure);
        stringBuilder.append("Lateral Earth Pressure is :" + lateralEarthPressure + "\n");
        System.out.println("Lateral Earth Pressure" + ":" + lateralEarthPressure);
        stringBuilder.append("Vertical Earth Pressure is :" + verticalEarthPressure + "\n");
        System.out.println("Vertical Earth Pressure" + ":" + verticalEarthPressure);
        stringBuilder.append("-----Stem Design-----" + "\n");
        //Wall slab design or Stem design
        double maxBM_Stem = (soilDensity * h2 * equationA * spacingOfCounterforts * spacingOfCounterforts) / 12;
        double requiredEDepthStem = Math.sqrt((maxBM_Stem / coefficientofM));
        double requiredOverallDepth = requiredEDepthStem + 7.5;
        double eDepthProvided;
        if (requiredOverallDepth < (wallWidth * 100)) {
            eDepthProvided = (wallWidth * 100) - 7.5;
            thicknessOfStem_Top = wallWidth * 100;
        } else {
            eDepthProvided = requiredEDepthStem;
            thicknessOfStem_Top = requiredOverallDepth;
        }
        stringBuilder.append("Effective Depth of Stem is :" + eDepthProvided + "\n");
        System.out.println("Overall Depth of stem without cover" + ":" + eDepthProvided);
        thicknessofStem_bottom = thicknessOfStem_Top;
        //Bending Moment variation(Main Reinforcements)
        stemDataArray = new ArrayList<>();
        double areaofMainBar = AreaCalculator(diaOfMainRebar);
        for (double i = 0; i < h2 || i == h2; i += 0.5) {
            double BM_Stem_h;
            if (loading == 1) {
                BM_Stem_h = ((surchargeDensity * surchargeHeight * equationA * spacingOfCounterforts * spacingOfCounterforts) / 12) + ((soilDensity * i * equationA * spacingOfCounterforts * spacingOfCounterforts) / 12);

            } else if (loading == 3) {
                BM_Stem_h = ((soilDensity * surchargeHeight * equationA * spacingOfCounterforts * spacingOfCounterforts) / 12) + ((soilDensity * i * equationA * spacingOfCounterforts * spacingOfCounterforts) / 12);
            } else {
                BM_Stem_h = (soilDensity * i * equationA * spacingOfCounterforts * spacingOfCounterforts) / 12 * Math.cos(slopeAngle);
            }

            StemDataClass stemDataClass = new StemDataClass();
            stemDataClass.setBendingMoment(BM_Stem_h);
            stemDataClass.setWallheight(i);
            System.out.println(" BM at " + i + " Is " + BM_Stem_h);
            double steelRequired = (BM_Stem_h * 100) / (sigmaST * 0.87 * eDepthProvided);
            stemDataClass.setAreaOfSteelRequired(steelRequired);
            double noOfMainBars_Stem = steelRequired / areaofMainBar;
            noOfMainBars_Stem = Roundup(noOfMainBars_Stem, 1);
            stemDataClass.setNoOfBars(noOfMainBars_Stem);
            System.out.println("No of Main bars bars in stem at " + i + " is " + noOfMainBars_Stem);
            double spacingRequiredMainBar = (areaofMainBar / steelRequired) * 100;
            double spacingAdoptedMainBar = Rounddown(spacingRequiredMainBar, 0.5);
            if (spacingAdoptedMainBar > 60) {
                spacingAdoptedMainBar = 60;
            }
            stemDataClass.setSpacingAdopted(spacingAdoptedMainBar);

            System.out.println(spacingAdoptedMainBar);
            stemDataArray.add(stemDataClass);

        }
        if (h2 % 0.5 != 0) {
            double BM_Stem_h;
            if (loading == 1) {
                BM_Stem_h = ((surchargeDensity * surchargeHeight * equationA * spacingOfCounterforts * spacingOfCounterforts) / 12) + ((soilDensity * h2 * equationA * spacingOfCounterforts * spacingOfCounterforts) / 12);

            } else if (loading == 3) {
                BM_Stem_h = ((soilDensity * surchargeHeight * equationA * spacingOfCounterforts * spacingOfCounterforts) / 12) + ((soilDensity * h2 * equationA * spacingOfCounterforts * spacingOfCounterforts) / 12);
            } else {
                BM_Stem_h = (soilDensity * h2 * equationA * spacingOfCounterforts * spacingOfCounterforts) / 12 * Math.cos(slopeAngle);
            }
            StemDataClass stemDataClass = new StemDataClass();
            stemDataClass.setBendingMoment(BM_Stem_h);
            stemDataClass.setWallheight(h2);
            System.out.println(" BM at " + h2 + " Is " + BM_Stem_h);
            double steelRequired = (BM_Stem_h * 100) / (sigmaST * 0.87 * eDepthProvided);
            stemDataClass.setAreaOfSteelRequired(steelRequired);
            double noOfMainBars_Stem = steelRequired / areaofMainBar;
            noOfMainBars_Stem = Roundup(noOfMainBars_Stem, 1);
            stemDataClass.setNoOfBars(noOfMainBars_Stem);
            System.out.println("No of Main bars bars in stem at " + h2 + " is " + noOfMainBars_Stem);
            double spacingRequiredMainBar = (areaofMainBar / steelRequired) * 100;
            double spacingAdoptedMainBar = Rounddown(spacingRequiredMainBar, 0.5);
            if (spacingAdoptedMainBar > 60) {
                spacingAdoptedMainBar = 60;
            }

            System.out.println(spacingAdoptedMainBar);
            stemDataClass.setSpacingAdopted(spacingAdoptedMainBar);
            stemDataArray.add(stemDataClass);

        }
        stringBuilder.append("-----Secondary Rebars of Stem-----" + "\n");

        //Secondary Reinforcements

        double concreteArea_cm = wallWidth * 100 * 100; // in cm2 into page dimension is 100cm
        stringBuilder.append("Concrete Area for Secondary Rebars Design is :" + concreteArea_cm + "\n");
        System.out.println("Concrete Area for secondary design of stem" + ":" + concreteArea_cm);
        double areaofSteel = concreteArea_cm * 0.15 * 0.01;//In cm
        stringBuilder.append("Area of Steel for Secondary Rebars Design is :" + areaofSteel + "\n");
        System.out.println("Area of steel for secondary reinforcement of  stem " + ":" + areaofSteel);
        double areaofSecondaryBar = AreaCalculator(diaOfSecondaryRebar);
        double noOfSecondaryBars_Stem = areaofSteel / areaofSecondaryBar;
        noOfSecondaryBars_Stem = Roundup(noOfSecondaryBars_Stem, 1);
        stringBuilder.append("No of Secondary Bars in Stem is :" + noOfSecondaryBars_Stem + "\n");
        System.out.println("No of Secondary  bars in Stem is " + noOfSecondaryBars_Stem);
        double spacingSecondaryRebars = (areaofSecondaryBar * 100) / areaofSteel;
        stringBuilder.append("Spacing of Secondary Rebars in Stem is :" + spacingSecondaryRebars + "\n");
        System.out.println("Spacing of secondary rebar in stem" + spacingSecondaryRebars);
        spacingExposedFace = spacingSecondaryRebars;
        spacingEarthFace = spacingSecondaryRebars;
        areaOfSecondaryBarEarthFace = areaofSteel;
        areaOfSecondaryBarExposedFace = areaofSteel;
        noOfSecondaryBars_StemEarthFace = noOfSecondaryBars_StemExposedFace = noOfSecondaryBars_Stem;
        stringBuilder.append("-----Base Design-----" + "\n");
        //Base Design
        double weightOfStem = ((totalHeight - heightToeSlab) * wallWidth * densityConcrete) / 1000;//  in tons
        if (lengthOfBase == 0) {
            lengthOfBase = (3 * totalHeight) / 5;
            if (loading == 3) {
                lengthOfBase = (3 * overallHeight) / 5;
            }
            lengthOfBase = Roundup(lengthOfBase, 0.5);
        }
        stringBuilder.append("Length of Base is :" + lengthOfBase + "\n");
        System.out.println("length of Base" + lengthOfBase);
        lengthOfToe = lengthOfBase / 3;
        lengthOfToe = Roundup(lengthOfToe, 0.05);
        stringBuilder.append("Length of Toe is :" + lengthOfToe + "\n");
        System.out.println("Length of toe" + lengthOfToe);
        lengthOfHeel = lengthOfBase - (wallWidth + lengthOfToe);
        stringBuilder.append("Length of Heel is :" + lengthOfHeel + "\n");
        System.out.println("Length of heel " + lengthOfHeel);
        stringBuilder.append("Weight of Stem is :" + weightOfStem + "\n");
        double stemMomentArmFromToe = (wallWidth / 2) + lengthOfToe;
        double weighOfToe = (heightToeSlab * (lengthOfToe + thicknessofStem_bottom / 100) * densityConcrete);
        weighOfToe = weighOfToe / 1000;// Conversion to tons
        double weightOfHeel = (heightHeelSlab * lengthOfHeel * densityConcrete);
        weightOfHeel = weightOfHeel / 1000;
        stringBuilder.append("Weight of Toe is :" + weighOfToe + "\n");
        stringBuilder.append("Weight of Heel is : " + weightOfHeel + "\n");
        double toeMomentArmFromToe = (lengthOfToe + (thicknessofStem_bottom / 100)) / 2;
        double heelMomentArmFromToe = (lengthOfHeel / 2 + lengthOfToe + thicknessofStem_bottom / 100);
        double weightOfBackFill = (h2) * lengthOfHeel * soilDensity;
        weightOfBackFill = weightOfBackFill / 1000; // Conversion to tons
        stringBuilder.append("Weight of Backfill is :" + weightOfBackFill + "\n");
        double backFillMomentArmFromToe = (lengthOfHeel / 2) + wallWidth + lengthOfToe;
        double weightOfSurcharge = 0;
        double surchargeMomentArmFromToe = 0;
        if (loading == 1) {
            weightOfSurcharge = (surchargeHeight) * (lengthOfHeel) * surchargeDensity;
            weightOfSurcharge = weightOfSurcharge / 1000; // Conversion to tons
            surchargeMomentArmFromToe = (lengthOfHeel / 2) + wallWidth + lengthOfToe;
        } else if (loading == 3) {
            weightOfSurcharge = (surchargeHeight) * (lengthOfHeel) * soilDensity;
            weightOfSurcharge = weightOfSurcharge / 1000; // Conversion to tons
            surchargeMomentArmFromToe = (lengthOfHeel / 2) + wallWidth + lengthOfToe;
        } else {
            weightOfSurcharge = 0.5 * (lengthOfHeel) * Math.tan(slopeAngle) * (lengthOfHeel) * surchargeDensity;
            weightOfSurcharge = weightOfSurcharge / 1000; // Conversion to tons
            surchargeMomentArmFromToe = (2 * (lengthOfHeel) / 3 + lengthOfToe + wallWidth);
        }
        stringBuilder.append("Weight of Surcharge is :" + weightOfSurcharge + "\n");
        double verticalEarthPressureMomentArmFromToe = (lengthOfBase);
        double totalMoment = (weightOfStem * stemMomentArmFromToe) + (weighOfToe * toeMomentArmFromToe) + (weightOfHeel * heelMomentArmFromToe) + (weightOfBackFill * backFillMomentArmFromToe) + (weightOfSurcharge * surchargeMomentArmFromToe) + ((verticalEarthPressure / 1000) * verticalEarthPressureMomentArmFromToe);
        double totalVerticalLoad = (weightOfStem + weighOfToe + weightOfHeel + weightOfBackFill + weightOfSurcharge + verticalEarthPressure / 1000);
        double totalVerticalLoadMomentArm = totalMoment / totalVerticalLoad;
        System.out.println(totalMoment);
        stringBuilder.append("Total Vertical Load for Base Design is :" + totalVerticalLoad + "\n");
        System.out.println("Total vertical load for Base Design" + ":" + totalVerticalLoad);
        stringBuilder.append("Total Vertical Load Moment Arm from Base is :" + totalVerticalLoadMomentArm + "\n");
        System.out.println("Total vertical load moment arm for Base " + ":" + totalVerticalLoadMomentArm);
        stringBuilder.append("-----Resultant of Horizontal Force on Wall-----" + "\n");
        //Resultant of Horizontal Force on the Wall
        double surchargeMomentArmFromBase;
        double backFillMomentArmFromBase;
        double totalHorizontalLoadMomentArmFromBase;
        if (loading == 1 || loading == 3) {
            surchargeMomentArmFromBase = (h2 / 2) + heightHeelSlab;
            backFillMomentArmFromBase = (h2 / 3) + heightHeelSlab;
            totalHorizontalLoadMomentArmFromBase = ((surchargeEarthPressure * surchargeMomentArmFromBase) + (backfillEarthPressure * backFillMomentArmFromBase)) / lateralEarthPressure;

        } else {
            totalHorizontalLoadMomentArmFromBase = h2 / 3 + heightHeelSlab;
        }
        stringBuilder.append("Total Horizontal Load Moment Arm from Base is :" + totalHorizontalLoadMomentArmFromBase + "\n");

        System.out.println("Total horizontal load moment arm from base for base design" + ":" + totalHorizontalLoadMomentArmFromBase);
        stringBuilder.append("-----Eccentricity of Base-----" + "\n");
        //Point of Application Of Resustant Force on Base
        double resultantForceMomentArmFromBase = (totalVerticalLoad * totalVerticalLoadMomentArm - (lateralEarthPressure / 1000) * totalHorizontalLoadMomentArmFromBase) / totalVerticalLoad;
        // Eccentricity of the Resultant
        stringBuilder.append("Resultant Force Moment Arm from Base is :" + resultantForceMomentArmFromBase + "\n");
        System.out.println("Resultant force moment arm from base" + ":" + resultantForceMomentArmFromBase);
         eccentricityOfResultant = (lengthOfBase / 2) - resultantForceMomentArmFromBase;
        stringBuilder.append("Eccentricity of Resultant is :" + eccentricityOfResultant + "\n");
        System.out.println("Eccentricity of resultant" + ":" + eccentricityOfResultant);
        if (eccentricityOfResultant < (lengthOfBase / 6)) {
            stringBuilder.append("Eccentricity Check is ok" + "\n");
            System.out.println("Eccentricity check is ok i.e e<B/6");
            ecentricityCheckFactor = 1;
        } else {
            stringBuilder.append("Eccentricity Check Failed" + "\n");
            System.out.println("Eccentricity check failed i.e e>B/6");
            ecentricityCheckFactor = 0;
        }
        stringBuilder.append("-----Pressure Distribution under the Base-----" + "\n");
        //Pressure Distribution Under the Base
        maxPressureUnderBaseCheckFactor = 1;
         maxPressureUnderBase = (totalVerticalLoad / lengthOfBase) * (1 + (6 * eccentricityOfResultant) / lengthOfBase);
         minimumPressureUnderBase = (totalVerticalLoad / lengthOfBase) * (1 - (6 * eccentricityOfResultant) / lengthOfBase);
        if (maxPressureUnderBase > (bearingCapacity / 1000) || maxPressureUnderBase == (bearingCapacity / 1000)) {
            maxPressureUnderBaseCheckFactor = 0;
            stringBuilder.append("Maximum Pressure under the Base is more than Bearing Capacity of Soil" + "\n");
            System.out.println("Maximum pressure under base is more than bearing capacity of soil");
        }
        stringBuilder.append("Minimum Pressure under the Base is :" + minimumPressureUnderBase + "\n");
        System.out.println("Minimum pressure under the base" + ":" + minimumPressureUnderBase);
        stringBuilder.append("Maximum Pressure under the Base is :" + maxPressureUnderBase + "\n");
        System.out.println("Max pressure under the base" + ":" + maxPressureUnderBase);
        stringBuilder.append("-----Heel Design-----" + "\n");
        //Heel Design
        double totalSoilHeight = h2 + surchargeHeight;
        double uniformForceOfSoilLoad = (weightOfBackFill / lengthOfHeel) + (weightOfSurcharge / lengthOfHeel);
        double uniformForceOfHeelLoad = heightHeelSlab * (densityConcrete / 1000);
        double totalDownwardUDL = uniformForceOfHeelLoad + uniformForceOfSoilLoad;
        double pressureOrdinateAtHeelWallJunction = minimumPressureUnderBase + (maxPressureUnderBase - minimumPressureUnderBase) * (lengthOfHeel / lengthOfBase);
        double netDownwardUDL_left = totalDownwardUDL - pressureOrdinateAtHeelWallJunction;
        double netDownwardUDL_right = totalDownwardUDL - minimumPressureUnderBase;
        double maxBM_Heel = (netDownwardUDL_right * spacingOfCounterforts * spacingOfCounterforts) / 12;
        stringBuilder.append("Max Bending Moment in Heel is :" + maxBM_Heel + "\n");
        double depthOfHeelRequired = Math.sqrt((maxBM_Heel * 1000 / coefficientofM));
        stringBuilder.append("Depth of Heel Required is :" + depthOfHeelRequired + "\n");
        System.out.println("DepthOfHeelRequired" + depthOfHeelRequired);
        System.out.println("max BM Heel  " + maxBM_Heel);
        double totalDepthOfHeelProvided;
        if (depthOfHeelRequired < (heightHeelSlab * 100)) {
            totalDepthOfHeelProvided = heightHeelSlab * 100;
        } else {
            System.out.println("Depth of Heel provided is less than required depth");
            stringBuilder.append("Depth of Heel provided is less than requied depth of Heel" + "\n");
            totalDepthOfHeelProvided = depthOfHeelRequired;
            totalDepthOfHeelProvided = Round(totalDepthOfHeelProvided, 10);
        }
        eDepthOfHeelProvided = totalDepthOfHeelProvided - 7.5;
        stringBuilder.append("Effective Depth of Heel is :" + eDepthOfHeelProvided + "\n");
        System.out.println("Effective depth of heel provided" + ":" + eDepthOfHeelProvided);
        heightHeelSlab = totalDepthOfHeelProvided / 100;
        stringBuilder.append("-----Shear Check at Heel Wall Junction-----" + "\n");
        //Shear Check at Heel Wall junction
        double maxShearForceAtHeelWallJunction = (netDownwardUDL_left * netDownwardUDL_right) / 2;
        double maxShearAtHeelWallJunction = (maxShearForceAtHeelWallJunction * 1000) / (0.87 * 100 * totalDepthOfHeelProvided);
        stringBuilder.append("Max Shear at Heel Wall Junction is :" + maxShearAtHeelWallJunction + "\n");
        if (maxShearAtHeelWallJunction > concrete.shearStress) {
            stringBuilder.append("Heel-Wall Junction will Fail in Shear" + "\n");
            System.out.println("Hell-Wall junction will fail in shear");
            shearCheckFactorFOrHellWallJunction = 0;
        } else {
            stringBuilder.append("Heel-Wall Junction will not Fail in Shear" + "\n");
            System.out.println("Heel-Wall junction will not fail in shear");
            shearCheckFactorFOrHellWallJunction = 1;
        }
        stringBuilder.append("-----Main Rebars of Heel-----" + "\n");
        //Main Rebars of Heel
        areaOfMainSteelForHeel = (maxBM_Heel * 100000) / (sigmaST * 0.87 * eDepthOfHeelProvided);
        spacingOfMainSteelForHeel = (areaofMainBar * 100) / areaOfMainSteelForHeel;
        spacingOfMainSteelForHeel = Rounddown(spacingOfMainSteelForHeel, 5);
        stringBuilder.append("Area of Main Steel for Heel is :" + areaOfMainSteelForHeel + "\n");
        System.out.println("Area of main steel for heel" + ":" + areaOfMainSteelForHeel);
        noOfMainBars_Heel = areaOfMainSteelForHeel / areaofMainBar;
        noOfMainBars_Heel = Roundup(noOfMainBars_Heel, 1);
        stringBuilder.append("No of Main Bars in Heel is :" + noOfMainBars_Heel + "\n");
        System.out.println("No of Main bars bars in heel is " + noOfMainBars_Heel);
        stringBuilder.append("Spacing of Main Steel for Heel is :" + spacingOfMainSteelForHeel + "\n");
        System.out.println("Spacing of main steel for heel" + ":" + spacingOfMainSteelForHeel);
        stringBuilder.append("-----Secondary Bars of Heel-----" + "\n");
        //Secondary Bars of Heel
        areaOfSecondarySteelForHeel = 100 * heightHeelSlab * 100 * 0.0015;
        spacingOfSecondarySteelForHeel = (areaofSecondaryBar * 100) / areaOfSecondarySteelForHeel;
        spacingOfSecondarySteelForHeel = Rounddown(spacingOfSecondarySteelForHeel, 5);
        stringBuilder.append("Area of Secondary Steel for Heel is :" + areaOfSecondarySteelForHeel + "\n");
        System.out.println("Area of secondary steel for heel" + ":" + areaOfSecondarySteelForHeel);
        noOfSecondaryBars_Heel = areaOfSecondarySteelForHeel / areaofSecondaryBar;
        noOfSecondaryBars_Heel = Roundup(noOfSecondaryBars_Heel, 1);
        stringBuilder.append("No of Secondary Basrs in Heel is :" + noOfSecondaryBars_Heel + "\n");
        System.out.println("No of Secondary bars bars in heel is " + noOfSecondaryBars_Heel);
        stringBuilder.append("Spacing of Secondary Steel for Heel is :" + spacingOfSecondarySteelForHeel + "\n");
        System.out.println("Spacing of secondary steel for heel" + ":" + spacingOfSecondarySteelForHeel);
        stringBuilder.append("-----Toe Design-----" + "\n");
        //Toe Design
        double forceOfToeLoad = lengthOfToe * heightHeelSlab * (densityConcrete / 1000);
        double momentArmForToeLoad = lengthOfToe / 2;
        double pressureUnderBaseAtLengthOfToe = ((maxPressureUnderBase - minimumPressureUnderBase) / lengthOfBase) * (lengthOfHeel + wallWidth / 100) + minimumPressureUnderBase;
        double forceOfRectPressureUnderToe = (pressureUnderBaseAtLengthOfToe) * lengthOfToe;
        double momentArmOfRectPressureUnderToe = lengthOfToe / 2;
        double forceOfTriPressureUnderToe = 0.5 * (maxPressureUnderBase - pressureUnderBaseAtLengthOfToe) * lengthOfToe;
        double momentArmOfTriPressureUnderToe = (2 * lengthOfToe) / 3;
        double totalPositiveMomentOfToe = (forceOfRectPressureUnderToe * momentArmOfRectPressureUnderToe) + (forceOfTriPressureUnderToe * momentArmOfTriPressureUnderToe);
        double totalNegativeMomentOfToe = forceOfToeLoad * momentArmForToeLoad;
        double netMomentOfToe = totalPositiveMomentOfToe - totalNegativeMomentOfToe;
        stringBuilder.append("Net Moment of Toe is :" + netMomentOfToe + "\n");
        double depthOfToeRequired_BM = Math.sqrt((netMomentOfToe * 1000 / coefficientofM));
        double maxShear_Toe = forceOfRectPressureUnderToe + forceOfTriPressureUnderToe - forceOfToeLoad;
        double depthOfToeRequired_Shear = ((maxShear_Toe * 10) / (0.87 * concrete.shearStress)) + 7.5;
        double depthOfToeRequired = Math.max(depthOfToeRequired_BM, depthOfToeRequired_Shear);
        stringBuilder.append("Depth of Toe Required is :" + depthOfToeRequired + "\n");
        System.out.println("Depth of toe required" + ":" + depthOfToeRequired);
        System.out.println(depthOfToeRequired_BM);
        System.out.println(depthOfToeRequired_Shear);
        double totalDepthOfToeProvided;
        if (depthOfToeRequired < (heightToeSlab * 100)) {
            totalDepthOfToeProvided = heightToeSlab * 100;
        } else {
            stringBuilder.append("Depth of Toe provided is less than required depth" + "\n");
            System.out.println("Depth of Toe provided is less than required depth");
            totalDepthOfToeProvided = depthOfToeRequired;
            totalDepthOfToeProvided = Round(totalDepthOfToeProvided, 10);
        }
        eDepthOfToeProvided = totalDepthOfToeProvided - 7.5;
        stringBuilder.append("Effective Depth of Toe is :" + eDepthOfToeProvided + "\n");
        System.out.println("Effective depth of toe provided" + ":" + eDepthOfToeProvided);
        heightToeSlab = totalDepthOfToeProvided / 100;
        stringBuilder.append("-----Main Rebars of Toe-----" + "\n");
        //Main Rebars of Toe
        areaOfMainSteelForToe = (netMomentOfToe * 100000) / (sigmaST * 0.87 * eDepthOfToeProvided);
        spacingOfMainSteelForToe = (areaofMainBar * 100) / areaOfMainSteelForToe;
        spacingOfMainSteelForToe = Rounddown(spacingOfMainSteelForToe, 0.5);
        stringBuilder.append("Area of Main Steel for Toe is :" + areaOfMainSteelForToe + "\n");
        System.out.println("Area of main steel for toe" + ":" + areaOfMainSteelForToe);
        noOfMainBars_Toe = areaOfMainSteelForToe / areaofMainBar;
        noOfMainBars_Toe = Roundup(noOfMainBars_Toe, 1);
        stringBuilder.append("No of Main Bars in Toe is :" + noOfMainBars_Toe + "\n");
        System.out.println("No of Main bars bars in toe is " + noOfMainBars_Toe);
        stringBuilder.append("Spacing of Main Steel for Toe is :" + spacingOfMainSteelForToe + "\n");
        System.out.println("Spacing of main steel for toe" + ":" + spacingOfMainSteelForToe);
        stringBuilder.append("-----Secondary Bars of Toe-----" + "\n");
        //Secondary Bars of Toe
        areaOfSecondarySteelForToe = 100 * heightToeSlab * 100 * 0.0015;
        spacingOfSecondarySteelForToe = (areaofSecondaryBar * 100) / areaOfSecondarySteelForToe;
        spacingOfSecondarySteelForToe = Rounddown(spacingOfSecondarySteelForToe, 0.5);
        stringBuilder.append("Area of Secondary Steel for Toe is :" + areaOfSecondarySteelForToe + "\n");
        System.out.println("Area of secondary steel for toe" + ":" + areaOfSecondarySteelForToe);
        noOfSecondaryBars_Toe = areaOfSecondarySteelForToe / areaofSecondaryBar;
        noOfSecondaryBars_Toe = Roundup(noOfSecondaryBars_Toe, 1);
        stringBuilder.append("No of Secondary Bars in Toe is :" + noOfSecondaryBars_Toe + "\n");
        System.out.println("No of Secondary bars in toe is " + noOfSecondaryBars_Toe);
        stringBuilder.append("Spacing of Secondary Steel for Toe is :" + spacingOfSecondarySteelForToe + "\n");
        System.out.println("Spacing of secondary steel for toe" + ":" + spacingOfSecondarySteelForToe);
        stringBuilder.append("-----Shear Check at Toe-Stem Junction-----" + "\n");
        //Shear check at junction of toe and wall slab
        double maxShearForceAtToeWallJunction = (forceOfRectPressureUnderToe + forceOfTriPressureUnderToe - forceOfToeLoad) * 1000;
        double maxShearAtToeWallJunction = maxShearForceAtToeWallJunction / (100 * 0.87 * eDepthOfToeProvided);
        stringBuilder.append("Max Shear at Toe-Stem Junction is :" + maxShearAtToeWallJunction + "\n");
        if (maxShearAtToeWallJunction > concrete.shearStress) {
            stringBuilder.append("Toe-Stem Junction will Fail in Shear" + "\n");
            System.out.println("Toe and wall junction will fail in shear");
            shearCheckFactorForToeWallJunction = 0;
        } else {
            stringBuilder.append("Toe-Stem Junction will not Fail in Shear" + "\n");
            System.out.println("Toe and wall junction will not fail in shear");
            shearCheckFactorForToeWallJunction = 1;
        }
        stringBuilder.append("-----Design of Counterforts-----" + "\n");
        //Design of Counterfort
        double totalForceOnCounterforts = lateralEarthPressure * spacingOfCounterforts;
        stringBuilder.append("Total Force on Counterfort is :" + totalForceOnCounterforts + "\n");
        System.out.println("totalforce on counterforts" + totalForceOnCounterforts);
        double maxBM_Countefort = backfillEarthPressure * spacingOfCounterforts * (h2 / 3) + surchargeEarthPressure * spacingOfCounterforts * (h2 / 2);
        stringBuilder.append("Max Bending Moment in Counterfort is :" + maxBM_Countefort + "\n");
        System.out.println("Max BM Counterforts  " + maxBM_Countefort);
        //Effective depth of counterfort
        double x = (h2) / ((lengthOfHeel - wallWidth));
        double counterfortAngle = (Math.atan(x)) * 180 / 3.14;
        counterfortAngle = Math.toRadians(counterfortAngle);
        double depthOfCounterfort = (lengthOfHeel + wallWidth) * Math.sin(counterfortAngle);
        depthOfCounterfort = depthOfCounterfort * 100;//Conversion to cm
        System.out.println("Depth of Counterfort" + depthOfCounterfort);
        eDepthOfCounterfort = depthOfCounterfort - 10;// In cm and cover is 10 cm
        stringBuilder.append("Effective Depth of Counterfort is :" + eDepthOfCounterfort + "\n");
        double widthOfCounterfort_BM = (maxBM_Countefort * 100) / (coefficientofM * eDepthOfCounterfort * eDepthOfCounterfort);// In cm
        widthOfCounterfort_BM = Roundup(widthOfCounterfort_BM, 10);
        double areaOfSteelForCounterfort = (maxBM_Countefort * 100) / (0.87 * sigmaST * eDepthOfCounterfort);
        stringBuilder.append("Required Area of Steel for Counterfort Design is :" + areaOfSteelForCounterfort + "\n");
        System.out.println(" Required area Of steel for Counterfort " + areaOfSteelForCounterfort);
        double noOfRebarsRequired = areaOfSteelForCounterfort / areaofMainBar;
        noOfRebarsRequired = Roundup(noOfRebarsRequired, 1);
        stringBuilder.append("No of Main Steel Bars Required for Counterfort is :" + noOfRebarsRequired + "\n");
        System.out.println("No of rebars required " + noOfRebarsRequired);
        double noOfRebarsPerRow = noOfRebarsRequired / rowsOfRebars;
        noOfRebarsPerRow = Roundup(noOfRebarsPerRow, 1);
        double widthOfCounterfort_Rebars = 10 + noOfRebarsPerRow * diaOfMainRebar + (noOfRebarsPerRow - 1) * diaOfMainRebar;
        widthOfCounterfort_Rebars = Roundup(widthOfCounterfort_Rebars, 10);
        double maxShearForceAtBaseCounterforJunction = totalForceOnCounterforts - sigmaST * areaOfSteelForCounterfort * Math.cos(counterfortAngle);
        System.out.println("Max shear force at base counter junction" + maxShearForceAtBaseCounterforJunction);
        double widthOfCounterfor_Shear = maxShearForceAtBaseCounterforJunction / (concrete.shearStress * 0.87 * depthOfCounterfort);
        widthOfCounterfor_Shear = Roundup(widthOfCounterfor_Shear, 10);
        stringBuilder.append("Width of Counterfort Required to Accomodate Rebars is :" + widthOfCounterfort_Rebars + "\n");
        stringBuilder.append("Width of Counterfort required to Accomodate Shear is :" + widthOfCounterfor_Shear + "\n");
        System.out.println("Width of Counterfort Required to accomodate shear " + widthOfCounterfor_Shear);
        System.out.println("Width Of Counterfort Required to accomodate Rebars " + widthOfCounterfort_Rebars);
        stringBuilder.append("Width of Counterfort Required on Bending Moment Consideration is :" + widthOfCounterfort_BM + "\n");
        System.out.println("Width of Counterfort on BM consideration " + widthOfCounterfort_BM);
        adoptedWidthOfCounterfort = Math.max(widthOfCounterfort_Rebars, widthOfCounterfort_BM);
        adoptedWidthOfCounterfort = Math.max(adoptedWidthOfCounterfort, widthOfCounterfor_Shear);
        stringBuilder.append("Adopted Width of Counterfort is :" + adoptedWidthOfCounterfort + "\n");
        System.out.println("Adopted width of counterfort " + adoptedWidthOfCounterfort);
        // Curtailment of main steel
        counterfortMainSteelDataArray = new ArrayList<>();
        for (int i = 0; i < h2 || i == h2; i = i + 1) {
            double BM_Counterfort_h = 0.5 * soilDensity * equationA * i * i * spacingOfCounterforts * i / 3;
            double eDepth_h = ((wallWidth + wallWidth + ((lengthOfHeel - wallWidth) / h2) * i) * Math.sin(counterfortAngle) - 0.1) * 100;// In cm
            double steelRequired_h = BM_Counterfort_h * 100 / (0.87 * sigmaST * eDepth_h);
            double noOfBarsRequired_h = steelRequired_h / areaofMainBar;
            if (noOfBarsRequired_h > 2) {
                noOfBarsRequired_h = Roundup(noOfBarsRequired_h, 1);
            } else {
                noOfBarsRequired_h = 2;
            }
            System.out.println("No of bars required:" + noOfBarsRequired_h + "  BM  " + BM_Counterfort_h + "  E depth provided  " + eDepth_h + "  Steel required  " + steelRequired_h);
            CounterfortMainSteelDataClass counterfortMainSteelDataClass = new CounterfortMainSteelDataClass();
            counterfortMainSteelDataClass.setBendingMoment(BM_Counterfort_h);
            counterfortMainSteelDataClass.setWallHeight(i);
            counterfortMainSteelDataClass.seteDepth(eDepth_h);
            counterfortMainSteelDataClass.setNoOfBars(noOfBarsRequired_h);
            counterfortMainSteelDataClass.setAreaOfSteel(steelRequired_h);
            counterfortMainSteelDataArray.add(counterfortMainSteelDataClass);
        }
        if (h2 % 1 != 0) {
            double BM_Counterfort_h = 0.5 * soilDensity * equationA * h2 * h2 * spacingOfCounterforts * h2 / 3;
            double eDepth_h = ((wallWidth + wallWidth + ((lengthOfHeel - wallWidth) / h2) * h2) * Math.sin(counterfortAngle) - 0.1) * 100;// In cm
            double steelRequired_h = BM_Counterfort_h * 100 / (0.87 * sigmaST * eDepth_h);
            double noOfBarsRequired_h = steelRequired_h / areaofMainBar;
            if (noOfBarsRequired_h > 2) {
                noOfBarsRequired_h = Roundup(noOfBarsRequired_h, 1);
            } else {
                noOfBarsRequired_h = 2;
            }
            System.out.println("No of bars required:" + noOfBarsRequired_h + "  BM  " + BM_Counterfort_h + "  E depth provided  " + eDepth_h + "  Steel required  " + steelRequired_h);
            CounterfortMainSteelDataClass counterfortMainSteelDataClass = new CounterfortMainSteelDataClass();
            counterfortMainSteelDataClass.setBendingMoment(BM_Counterfort_h);
            counterfortMainSteelDataClass.setWallHeight(h2);
            counterfortMainSteelDataClass.seteDepth(eDepth_h);
            counterfortMainSteelDataClass.setNoOfBars(noOfBarsRequired_h);
            counterfortMainSteelDataClass.setAreaOfSteel(steelRequired_h);
            counterfortMainSteelDataArray.add(counterfortMainSteelDataClass);

        }

        //Connection of Counterfort with  wall slab
        counterfortSecondarySteelDataArray = new ArrayList<>();
        for (int i = 1; i < h2 || i == h2; i++) {
            double horizontalForce_Counterfort_h = soilDensity * i * equationA * spacingOfCounterforts;
            double secondarySteelRequired_h = horizontalForce_Counterfort_h / sigmaST;
            double noOfSecondaryBarsforCounterfort_h = secondarySteelRequired_h / (2 * areaofSecondaryBar);
            if (noOfSecondaryBarsforCounterfort_h > 2) {
                noOfSecondaryBarsforCounterfort_h = Rounddown(noOfSecondaryBarsforCounterfort_h, 1);
            } else {
                noOfSecondaryBarsforCounterfort_h = 2;
            }
            System.out.println("No of Bars for Secondary steel of counterfort is " + noOfSecondaryBarsforCounterfort_h);
            double spacingAdoptedSecondarySteel_h = (2 * areaofSecondaryBar * 100) / (secondarySteelRequired_h);
            spacingAdoptedSecondarySteel_h = Rounddown(spacingAdoptedSecondarySteel_h, 1);
            if (spacingAdoptedSecondarySteel_h > 50) {
                spacingAdoptedSecondarySteel_h = 50;
            }
            System.out.println(" Area of steel for secondary rebars in counterfort " + secondarySteelRequired_h + " Horizontal force for secondary rebar design in counterforts " + horizontalForce_Counterfort_h + " Spacing adopted for secondary rebar of counterfort " + spacingAdoptedSecondarySteel_h);
            CounterfortSecondarySteelDataClass counterfortSecondarySteelDataClass = new CounterfortSecondarySteelDataClass();
            counterfortSecondarySteelDataClass.setWallHeight(i);
            counterfortSecondarySteelDataClass.setHorizontalForce(horizontalForce_Counterfort_h);
            counterfortSecondarySteelDataClass.setAreaOfSteel(secondarySteelRequired_h);
            counterfortSecondarySteelDataClass.setNoOfSecondaryBars(noOfSecondaryBarsforCounterfort_h);
            counterfortSecondarySteelDataClass.setSpacingAdopted(spacingAdoptedSecondarySteel_h);
            counterfortSecondarySteelDataArray.add(counterfortSecondarySteelDataClass);
        }
        if (h2 % 1 != 0) {
            double horizontalForce_Counterfort_h = soilDensity * h2 * equationA * spacingOfCounterforts;
            double secondarySteelRequired_h = horizontalForce_Counterfort_h / sigmaST;
            double noOfSecondaryBarsforCounterfort_h = secondarySteelRequired_h / (2 * areaofSecondaryBar);
            if (noOfSecondaryBarsforCounterfort_h > 2) {
                noOfSecondaryBarsforCounterfort_h = Rounddown(noOfSecondaryBarsforCounterfort_h, 1);
            } else {
                noOfSecondaryBarsforCounterfort_h = 2;
            }
            System.out.println("No of Bars for Secondary steel of counterfort is " + noOfSecondaryBarsforCounterfort_h);
            double spacingAdoptedSecondarySteel_h = (2 * areaofSecondaryBar * 100) / (secondarySteelRequired_h);
            spacingAdoptedSecondarySteel_h = Rounddown(spacingAdoptedSecondarySteel_h, 1);
            if (spacingAdoptedSecondarySteel_h > 50) {
                spacingAdoptedSecondarySteel_h = 50;
            }
            System.out.println(" Area of steel for secondary rebars in counterfort " + secondarySteelRequired_h + " Horizontal force for secondary rebar design in counterforts " + horizontalForce_Counterfort_h + " Spacing adopted for secondary rebar of counterfort " + spacingAdoptedSecondarySteel_h);
            CounterfortSecondarySteelDataClass counterfortSecondarySteelDataClass = new CounterfortSecondarySteelDataClass();
            counterfortSecondarySteelDataClass.setWallHeight(h2);
            counterfortSecondarySteelDataClass.setHorizontalForce(horizontalForce_Counterfort_h);
            counterfortSecondarySteelDataClass.setAreaOfSteel(secondarySteelRequired_h);
            counterfortSecondarySteelDataClass.setNoOfSecondaryBars(noOfSecondaryBarsforCounterfort_h);
            counterfortSecondarySteelDataClass.setSpacingAdopted(spacingAdoptedSecondarySteel_h);
            counterfortSecondarySteelDataArray.add(counterfortSecondarySteelDataClass);
        }
        stringBuilder.append("-----Counterfort-Heel Junction-----" + "\n");
        //Connection of Counterfort to heel
        double totalDownwardForceAtHeelEdge = netDownwardUDL_right * spacingOfCounterforts;
        steelRequiredForHeelCounterfortConnection = (totalDownwardForceAtHeelEdge * 1000) / sigmaST;
        stringBuilder.append("Area of Steel Required for Heel-Counterfort Junction is :" + steelRequiredForHeelCounterfortConnection + "\n");
        noOfBarsRequiredForHeelCounterfortConnection = steelRequiredForHeelCounterfortConnection / areaofSecondaryBar;
        noOfBarsRequiredForHeelCounterfortConnection = Roundup(noOfBarsRequiredForHeelCounterfortConnection, 1);
        stringBuilder.append("No of Bars Required for Heel-Counterfort Junction is :" + noOfBarsRequiredForHeelCounterfortConnection + "\n");
        System.out.println("No of Secondary Bars required for Heel- Coounterfort Connection is " + noOfBarsRequiredForHeelCounterfortConnection);
        spacingOfTiesForHeelCounterfortConnection = (2 * areaofSecondaryBar * 100) / steelRequiredForHeelCounterfortConnection;
        stringBuilder.append("Spacing of Ties Required for Heel-Counterfort Junction is :" + spacingOfTiesForHeelCounterfortConnection + "\n");
        System.out.println("Steel required for Heel to counterfort connection: " + steelRequiredForHeelCounterfortConnection + "Spacing of double legged ties for heel to counterfort connection: " + spacingOfTiesForHeelCounterfortConnection);
        stringBuilder.append("-----Stability Analysis-----" + "\n");
        //Stability Analysis
        fS_Overturning = (totalVerticalLoad * totalVerticalLoadMomentArm) / ((lateralEarthPressure / 1000) * totalHorizontalLoadMomentArmFromBase);
        stringBuilder.append("FOS Overturning is " + fS_Overturning + "\n");
        if (fS_Overturning > 1.5) {
            stringBuilder.append("Overturning Failure will not occur" + "\n");
            System.out.println("Overturning Failure will not occur");
        } else {
            stringBuilder.append("Overturning Failure will occur" + "\n");
            System.out.println("Overturning Failure will occur");
        }
        fS_Sliding = (coefficientOfFriction * totalVerticalLoad) / (lateralEarthPressure / 1000);
        stringBuilder.append("FOS Sliding is :" + fS_Sliding + "\n");
        if (fS_Sliding > 1.5) {
            stringBuilder.append("Sliding Failure will not occur i.e Key is not Required" + "\n");
            System.out.println("Sliding Failure will not occur i.e Key is not required");

        } else {
            stringBuilder.append("Sliding Failure will occur i.e Key is Required" + "\n");
            System.out.println("Sliding Failure will occur i.e  Key is required");

        }


    }

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
}


