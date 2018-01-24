/**
 * //Author: Eng.HaiderAli
 */
public class CounterfortMainSteelDataClass {
    private double bendingMoment;
    private  double wallHeight;
    private  double eDepth;
    private  double noOfBars;

    public double getBendingMoment() {
        return bendingMoment;
    }

    public void setBendingMoment(double bendingMoment) {
        this.bendingMoment = bendingMoment;
    }

    public double getWallHeight() {
        return wallHeight;
    }

    public void setWallHeight(double wallHeight) {
        this.wallHeight = wallHeight;
    }

    public double geteDepth() {
        return eDepth;
    }

    public void seteDepth(double eDepth) {
        this.eDepth = eDepth;
    }

    public double getNoOfBars() {
        return noOfBars;
    }

    public void setNoOfBars(double noOfBars) {
        this.noOfBars = noOfBars;
    }

    public double getAreaOfSteel() {
        return areaOfSteel;
    }

    public void setAreaOfSteel(double areaOfSteel) {
        this.areaOfSteel = areaOfSteel;
    }

    private  double areaOfSteel;
}
