package pkg;
import java.util.Scanner;
import java.awt.*;

public class MosaicCreator {
    private static Scanner in = new Scanner(System.in);

    public static int getRows(int rowAmount){
        System.out.println("How many rows would you like?");
        rowAmount = in.nextInt();
        return rowAmount;
    }

    public static int getCols(int colAmount){
        System.out.println("How many columns would you like?");
        colAmount = in.nextInt();
        return colAmount;
    }

    public static void getColors(int rowAmount, int colAmount){
        Scanner in2 = new Scanner (System.in);
        String colors = "";
        for (int activeRow = 0; activeRow<rowAmount; activeRow++) {
        System.out.println("Here is your color code:");
        System.out.println("r: red      c: cyan     y: yellow        w: white");
        System.out.println("g: green    b: blue     m: magenta       any other char: black");
        System.out.println("Input your color choices:");
        colors = in2.nextLine();
            if (colors.length() > colAmount) {
                colors = colors.substring(0, colAmount);
            } else if (colors.length() < colAmount) {
                StringBuilder bldr = new StringBuilder();
                for (int y = colors.length(); y < colAmount; y++) {
                    bldr.append("z"); //will default in the case statement
                }
                colors += bldr.toString();
            }
            buildMosaic(colors,activeRow,colAmount);
        }
        System.out.println("Input finished!");
        in2.close();
    }

    public static Color findColor(char colorChar){
        switch (colorChar) {
            case 'r':
            case 'R': return Color.RED;
            case 'c':
            case 'C': return Color.CYAN;
            case 'y':
            case 'Y': return Color.YELLOW;
            case 'w':
            case 'W': return Color.WHITE;
            case 'g':
            case 'G': return Color.GREEN;
            case 'b':
            case 'B': return Color.BLUE;
            case 'm':
            case 'M': return Color.MAGENTA;
            default: return Color.BLACK;
        }
    }

    public static void buildMosaic(String input,int rows, int cols){
        for (int activeColumn = 0; activeColumn<cols; activeColumn++) {
                Mosaic.setColor(rows, activeColumn, findColor(input.charAt(activeColumn)));
        }
    }

    public static void rotateColor (int colAmount, int rowAmount){
        Mosaic.delay(1000);
        for (int activeRow = 0; activeRow<rowAmount; activeRow++){
            for (int activeColumn = 0; activeColumn<colAmount;activeColumn++){
                if (Mosaic.getColor(activeColumn,activeRow).equals(Color.RED)){
                    Mosaic.setColor(activeColumn,activeRow,Color.CYAN);
                } else if (Mosaic.getColor(activeColumn,activeRow).equals(Color.CYAN)){
                    Mosaic.setColor(activeColumn,activeRow,Color.YELLOW);
                } else if (Mosaic.getColor(activeColumn,activeRow).equals(Color.YELLOW)) {
                    Mosaic.setColor(activeColumn,activeRow,Color.white);
                } else if (Mosaic.getColor(activeColumn,activeRow).equals(Color.WHITE)) {
                    Mosaic.setColor(activeColumn,activeRow,Color.GREEN);
                } else if (Mosaic.getColor(activeColumn,activeRow).equals(Color.GREEN)) {
                    Mosaic.setColor(activeColumn,activeRow,Color.BLUE);
                } else if (Mosaic.getColor(activeColumn,activeRow).equals(Color.BLUE)) {
                    Mosaic.setColor(activeColumn,activeRow,Color.MAGENTA);
                } else if (Mosaic.getColor(activeColumn,activeRow).equals(Color.MAGENTA)) {
                    Mosaic.setColor(activeColumn,activeRow,Color.BLACK);
                } else if (Mosaic.getColor(activeColumn,activeRow).equals(Color.BLACK)) {
                    Mosaic.setColor(activeColumn,activeRow,Color.RED);
                }
            }
        }
    }

    public static void main(String[] args) {
        final int BLOCK_SIZE = 40;
        int cols = 0;
        int rows = 0;
        rows = getRows(rows);
        cols = getCols(cols);
        Mosaic.open(rows, cols, BLOCK_SIZE, BLOCK_SIZE); //open template
        getColors(rows,cols);
        while (true) {
                rotateColor(rows, cols);
            }
        }
    }