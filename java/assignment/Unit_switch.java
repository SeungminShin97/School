import java.util.Scanner;

public class Unit_switch {
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

        switch(unit) {
            case "weight" -> {
                switch (from) {
                    case "lb" -> {
                        switch (to) {
                            case "mg" -> result = value * lb * mg;
                            case "g" -> result = value * lb * mg;
                            case "kg" -> result = value * lb * kg;
                        }
                    }
                    case "oz" -> {
                        switch (to) {
                            case "mg" -> result = value * oz * mg;
                            case "g" -> result = value * oz * mg;
                            case "kg" -> result = value * oz * kg;
                        }
                    }
                    case "gr" -> {
                        switch (to) {
                            case "mg" -> result = value * gr * mg;
                            case "g" -> result = value * gr * mg;
                            case "kg" -> result = value * gr * kg;
                        }
                    }
                }
            }
            case "length" -> {
                switch (from) {
                    case "in" -> {
                        switch (to) {
                            case "mm" -> result = value * in * mm;
                            case "cm" -> result = value * in * cm;
                            case "m" -> result = value * in * m;
                            case "km" -> result = value * in * km;
                        }
                    }
                    case "ft" -> {
                        switch (to) {
                            case "mm" -> result = value * ft * mm;
                            case "cm" -> result = value * ft * cm;
                            case "m" -> result = value * ft * m;
                            case "km" -> result = value * ft * km;
                        }
                    }
                    case "yd" -> {
                        switch (to) {
                            case "mm" -> result = value * yd * mm;
                            case "cm" -> result = value * yd * cm;
                            case "m" -> result = value * yd * m;
                            case "km" -> result = value * yd * km;
                        }
                    }
                    case "mile" -> {
                        switch (to) {
                            case "mm" -> result = value * mile * mm;
                            case "cm" -> result = value * mile * cm;
                            case "m" -> result = value * mile * m;
                            case "km" -> result = value * mile * km;
                        }
                    }
                }
            }
            default -> {
                System.out.println("5류 5류");
                n = 1;
            }
        }

        String sresult;
        sresult = String.format("%.1f",result);
        if(n == 0) System.out.println(value + " " + from + " ---> " + sresult + " " + to);






    }
}
