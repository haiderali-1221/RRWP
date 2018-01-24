/**
 * Created by Haider on 10/27/2015.
 */
public class Concrete {
    double sigmaCB;
    double sigmaC;
    double bondStress;
    double shearStress;
    public Concrete(int input){
        switch (input){
            case 100:
                sigmaCB=30;
                sigmaC=25;
                bondStress=4;
                shearStress=3;
                break;
            case 150:
                sigmaCB=50;
                sigmaC=40;
                bondStress=6;
                shearStress=5;
                break;
            case 200:
                sigmaCB=70;
                sigmaC=50;
                bondStress=8;
                shearStress=7;
                break;
            case 250:
                sigmaCB=85;
                sigmaC=60;
                bondStress=9;
                shearStress=8;
                break;
            case 300:
                sigmaCB=100;
                sigmaC=80;
                bondStress=10;
                shearStress=9;
                break;
            case 350:
                sigmaCB=115;
                sigmaC=90;
                bondStress=11;
                shearStress=10;
                break;
            case 400:
                sigmaCB=130;
                sigmaC=100;
                bondStress=12;
                shearStress=11;
                break;

            default:
                System.out.println("Invalid input to Concrete.java");
                break;
        }

    }

}
