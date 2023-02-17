import java.awt.*;

public class GUI extends backend{

    public static void main(String[] args) {

        //configure StdDraw
        double timeElapsed = 0.017; //each frame is 0.017 seconds long
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setCanvasSize(600, 655);
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 655);
        StdDraw.enableDoubleBuffering();

        //resources for drawing board
        boolean altColor = false; //switches colors when drawing rectangles

        //resources for title bar
        Font title = new Font("Sans Serif", Font.BOLD, 30);
        Font captured = new Font("Sans Serif", Font.BOLD, 14);

        backendRun(); //runs the backend of checkers

        while (true) {

            //this has to be in the while loop
            double xGuiPos = -37.5;
            double yGuiPos = 37.5;

            StdDraw.setPenColor(StdDraw.RED);
            //set up board background
            for(int i=0; i<10; i++){
                for(int j=0; j<10; j++) {
                    StdDraw.filledRectangle(xGuiPos, yGuiPos, 37.5, 37.5);
                    if (altColor) {
                        StdDraw.setPenColor(StdDraw.BOOK_RED); //COLORS NOT FINAL
                    } else {
                        StdDraw.setPenColor(StdDraw.ORANGE);
                    }
                    altColor = !altColor;
                    xGuiPos += 75;
                }
                yGuiPos += 75;
                xGuiPos = -37.5;
                altColor = !altColor;
            }
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(300, 602.5, 300, 2.5);
            StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
            StdDraw.filledRectangle(300,630,300,25);

            //display current turn
            StdDraw.setFont(title);
            if(blackTurn){
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text(300,625,"It is BLACK's turn");
            } else {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.text(300,625,"It is RED's turn");
            }

            //display captured pieces
            StdDraw.setFont(captured);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(90,635, "Black has captured");
            if(blackCaptured == 1){
                StdDraw.text(90, 621, "1 piece");
            } else {
                StdDraw.text(90, 621, blackCaptured + " pieces");
            }
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(510,635, "Red has captured");
            if(redCaptured == 1) {
                StdDraw.text(510, 621, blackCaptured + " pieces");
            } else {
                StdDraw.text(510, 621, redCaptured + " pieces");
            }

            //update state of checkerboard
            for (int i=0; i<CheckerBoard.length; i++) {
                for (int j=0; j<CheckerBoard[i].length; j++) {
                    if(CheckerBoard[i][j] == 1){
                        blackReg(xConversionTherapy(j),yConversionTherapy(i));
                    } else if(CheckerBoard[i][j] == 2){
                        redReg(xConversionTherapy(j),yConversionTherapy(i));
                    } else if(CheckerBoard[i][j] == 3){
                        blackKing(xConversionTherapy(j),yConversionTherapy(i));
                    }if(CheckerBoard[i][j] == 4){
                        redKing(xConversionTherapy(j),yConversionTherapy(i));
                    }
                }
            }


            //new frame
            StdDraw.show();
            StdDraw.pause((int) (timeElapsed * 1000));
            StdDraw.clear();
        }
    }
    static double xConversionTherapy(int xIn){ //converts x-coordinates of CheckerBoard to x-coordinates for the GUI
        double xOut = 37.5 + (75*xIn);
        return xOut;
    }
    static double yConversionTherapy(int yIn){ //converts y-coordinates of CheckerBoard to y-coordinates for the GUI
        double yOut = 562.5 - (75*yIn);
        return yOut;
    }
    static int xTherapyConversion(double xIn){
        int xOut = (int)((0.0133333*(xIn + 0.1)) - 0.5); //this equation converts coordinates back. the +0.1 is used to fix that the equation rounds each number down 1
        return xOut;
    }
    static int yTherapyConversion(double yIn){
        int yOut = (int)(7.5 - (0.0133333*yIn));
        return yOut;
    }
    static void blackReg(double xIn, double yIn){ //draws a regular black checker
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(xIn,yIn,34);
    }
    static void redReg(double xIn, double yIn){ //draws a regular red checker
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(xIn,yIn,34);
    }
    static void blackKing(double xIn, double yIn){ //draws a black king checker
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(xIn,yIn,34);
        StdDraw.picture(xIn,yIn,"src/bking.png"); //white outline of crown
    }
    static void redKing(double xIn, double yIn){ //draws a red king checker
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(xIn,yIn+1,34);
        StdDraw.picture(xIn,yIn+1,"src/rking.png"); //black outline of crown
    }
    public static void backendRun(){
        Thread backendRun = new Thread(new Runnable() {
            public void run() {
                String[] dummy = new String[3]; //because backend depends on an input string[]
                backend.main(dummy);
            }
        }
        );
        backendRun.start(); //runs backend (this will be replaced as it doesn't let GUI interact with backend. just passive for now)
    }
}
