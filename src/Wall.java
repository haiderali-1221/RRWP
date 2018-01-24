import java.util.ArrayList;

/**
 * //Author: Eng.HaiderAli
 */
public class Wall {
     double heightAboveGround;
     double frictionAngle;
     double bearingCapacity;
     double soilDensity;
     double surchargeDensity;
     double coefficientOfFriction;
     double surchargeLoad;
     double surchargeDistanceFromWall;
     double surchargeHeight;
     double sigmaST;
     double diaOfSecondaryRebar;
     double diaOfMainRebar;
     int gradeOfConcrete;
     double densityConcrete;
     double slopeAngle=0;
     double rowsOfRebars;
     double shearCheckFactor_Stem;
     double fS_Overturning;
     double ecentricityCheckFactor;
     double fS_Sliding;
     double loading;
     double noOfSecondaryBars_StemEarthFace;
     double noOfSecondaryBars_StemExposedFace;
     double areaOfSecondaryBarEarthFace;
     double areaOfSecondaryBarExposedFace;
     double spacingEarthFace;
     double spacingExposedFace;
     double lengthOfBase;
     double shearCheckFactorForToeWallJunction;
     double shearCheckFactorFOrHellWallJunction;
     double maxPressureUnderBaseCheckFactor;
     double heightHeelSlab;
     ArrayList<StemDataClass> stemDataArray;
     ArrayList<CounterfortMainSteelDataClass> counterfortMainSteelDataArray;
     ArrayList<CounterfortSecondarySteelDataClass> counterfortSecondarySteelDataArray;
    //////////////////
     double backfillEarthPressure;
     double h2;
     double dF;
     double thicknessofStem_bottom;
     double thicknessOfStem_Top;
     double eDepthOfHeelProvided;
     double areaOfMainSteelForHeel;
     double noOfMainBars_Heel;
     double spacingOfMainSteelForHeel;
     double spacingOfSecondarySteelForHeel;
     double areaOfSecondarySteelForHeel;
     double noOfSecondaryBars_Heel;
     double lengthOfHeel;
     double heightToeSlab;
     double eDepthOfToeProvided;
     double lengthOfToe;
     double areaOfMainSteelForToe;
     double noOfMainBars_Toe;
     double spacingOfMainSteelForToe;
     double areaOfSecondarySteelForToe;
     double spacingOfSecondarySteelForToe;
     double noOfSecondaryBars_Toe;
     double depthOfKey=0;
     double eWidthOfKeyProvided;
     double areaOFMainSteelForKey;
     double noOfMainBars_Key=0;
     double areaOFSecondarySteelForKey;
     double spacingOfMainSteelForKey=0;
     double spacingOfSecondarySteelForKey=0;
     double noOfSecondaryBars_Key=0;
     double spacingOfCounterforts;
     double eDepthOfCounterfort;
     double adoptedWidthOfCounterfort;
     double noOfBarsRequiredForHeelCounterfortConnection;
     double spacingOfTiesForHeelCounterfortConnection;
     double steelRequiredForHeelCounterfortConnection;
     double widthOfKeyProvided;
     StringBuilder stringBuilder= new StringBuilder();
     double seismicKey;
     double a_h;
     double a_v;
     double k_v;
     double k_h;
     double maxPressureUnderBase;
     double minimumPressureUnderBase;
     double eccentricityOfResultant;





        public  void runWallCode(){

    }
}
