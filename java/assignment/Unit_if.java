import java.util.Scanner;
// 1kg = 1000g, 1g = 1000mg
// weight : (lb , oz, gr) (mg, g, kg)
// length : (in, ft, yd, mile) (mm, cm, m, km)

public class Unit_if {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        double lb = 453592;
        double oz = 28349;
        double gr = 64.7989;

        double in = 25.4;
        double ft = 304.8;
        double yd = 914.4;
        double mile = 1609344;

        int mg = 1;
        double g = 0.0001;
        double kg = 0.0000001;

        int mm = 1;
        double cm = 0.1;
        double m = 0.001;
        double km = 0.0001;

        double result = 0, n = 0;

        String unit;
        System.out.print("Which unit? ");
        unit = sc.nextLine();

        String from;
        System.out.print("Convert from? ");
        from = sc.nextLine();

        String to;
        System.out.print("Conver to? ");
        to = sc.nextLine();

        float value;
        System.out.print("Value? ");
        value = sc.nextFloat();

        if(unit.equals("weight")){
            if(from.equals("lb")){
                if(to.equals("mg")) result = value * lb * mg;
                else if (to.equals("g")) result = value * lb * g;
                else if (to.equals("kg")) result = value * lb * kg;
            }
            else if (from.equals("oz")){
                if(to.equals("mg")) result = value * oz * mg;
                else if (to.equals("g")) result = value * oz * g;
                else if (to.equals("kg")) result = value * oz * kg;
            }
            else if (from.equals("gr")){
                if(to.equals("mg")) result = value * gr * mg;
                else if (to.equals("g")) result = value * gr * g;
                else if (to.equals("kg")) result = value * gr * kg;
            }
        }
        else if (unit.equals("length")){
            if(from.equals("in")){
                if(to.equals("mm")) result = value * in * mm;
                else if (to.equals("cm")) result = value * in * cm;
                else if (to.equals("m")) result = value * in * m;
                else if (to.equals("km")) result = value * in * km;
            }
            if(from.equals("ft")){
                if(to.equals("mm")) result = value * ft * mm;
                else if (to.equals("cm")) result = value * ft * cm;
                else if (to.equals("m")) result = value * ft * m;
                else if (to.equals("km")) result = value * ft * km;
            }
            if(from.equals("yd")){
                if(to.equals("mm")) result = value * yd * mm;
                else if (to.equals("cm")) result = value * yd * cm;
                else if (to.equals("m")) result = value * yd * m;
                else if (to.equals("km")) result = value * yd * km;
            }
            if(from.equals("mile")){
                if(to.equals("mm")) result = value * mile * mm;
                else if (to.equals("cm")) result = value * mile * cm;
                else if (to.equals("m")) result = value * mile * m;
                else if (to.equals("km")) result = value * mile * km;
            }
        }
        else n = 1;

        String sresult;
        sresult = String.format("%.1f",result);

        if(n == 1) System.out.println("오류 오류");
        else System.out.println(value + " " + from + " ---> " + sresult + " " + to);



    }
}
