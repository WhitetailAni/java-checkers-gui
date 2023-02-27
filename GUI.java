import java.awt.*;

public class GUI extends backend {

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
        Font inBetween = new Font("Sans Serif", Font.BOLD, 25);
        Font alert = new Font("Sans Serif", Font.BOLD, 20);
        Font captured = new Font("Sans Serif", Font.BOLD, 14);

        backendRun(); //runs the backend of checkers
        watchdogRun(); //runs watchdog

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
            if(redTurn){
                StdDraw.setPenColor(StdDraw.RED);
            } else {
                StdDraw.setPenColor(StdDraw.BLACK);
            }

            if(StdDraw.isMousePressed() && ((mouseY > 170 && 430 > mouseY) && (mouseX > 615 && 645 > mouseX))){
                cheatMode = true;
            }

            //show alerts
            if(!misclick2) { //misclick prompt needs to override everything else rendered
                if (blackTurn) {
                    if (cheatMode) {
                        StdDraw.setFont(inBetween);
                        StdDraw.text(300, 627, "Cheat mode toggled");
                    } else if(selectPiece) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 640, "It is BLACK's turn");
                        StdDraw.text(300, 615, "Select a piece to move");
                    } else if(selectDest) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 640, "It is BLACK's turn");
                        StdDraw.text(300, 615, "Select the destination");
                    } else if (moveBlack) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 640, "You can't move");
                        StdDraw.text(300, 615, "RED's pieces!");
                    } else if (emptyBlack) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 640, "You can't move");
                        StdDraw.text(300, 615, "an empty space!");
                    } else {
                        StdDraw.setFont(title);
                        StdDraw.text(300, 625, "It is BLACK's turn");
                    }
                } else {
                    if(cheatMode) {
                        StdDraw.setFont(inBetween);
                        StdDraw.text(300, 627, "Cheat mode toggled");
                    } else if(selectPiece) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 640, "It is RED's turn");
                        StdDraw.text(300, 615, "Select a piece to move");
                    } else if(selectDest) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 640, "It is RED's turn");
                        StdDraw.text(300, 615, "Select the destination");
                    } else if (moveRed) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 640, "You can't move");
                        StdDraw.text(300, 615, "BLACK's pieces!");
                    } else if (emptyRed) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 640, "You can't move");
                        StdDraw.text(300, 615, "an empty space!");
                    } else {
                        StdDraw.setFont(title);
                        StdDraw.text(300, 625, "It is RED's turn");
                    }
                }

                //display captured pieces
                StdDraw.setFont(captured);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text(90, 635, "Black has captured");
                if (blackCaptured == 1) {
                    StdDraw.text(90, 621, "1 piece");
                } else {
                    StdDraw.text(90, 621, blackCaptured + " pieces");
                }
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.text(510, 635, "Red has captured");
                if (redCaptured == 1) {
                    StdDraw.text(510, 621, blackCaptured + " pieces");
                } else {
                    StdDraw.text(510, 621, redCaptured + " pieces");
                }
            } else {
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setFont(alert);
                StdDraw.text(300, 640, "You can't move there!");
                StdDraw.text(300, 615, "Select a new piece?");
                StdDraw.text(520, 625, "Yes");
                StdDraw.text(80, 625, "No");
                if(StdDraw.isMousePressed()){
                    if((60 <= mouseY && mouseY <= 100) && (620 <= mouseX && mouseX <= 640)){
                        System.out.println("no");
                        misclick = 0;
                        misclick2 = false;
                    } else if((500 <= mouseY && mouseY <= 540) && (620 <= mouseX && mouseX <= 640)){
                        System.out.println("yes");
                        misclick = 1;
                        misclick2 = false;
                    }
                    while (true){
                        if(!StdDraw.isMousePressed()){
                            break;
                        }
                    }
                }
            }

            //if(StdDraw.isMousePressed()) {//current mouse coordinates
            //    System.out.println("MouseX is at " + mouseY);
            //    System.out.println("MouseY is at " + mouseX);
            //}

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

            //highlight selected checker
            if(!(xSel == 0 && ySel == 0) && !selectPiece) {
                if(CheckerBoard[xSel][ySel] == 3 && CheckerBoard[xSel][ySel] == 4) {
                    StdDraw.setPenColor(StdDraw.GREEN);
                    StdDraw.circle(xConversionTherapy(ySel), yConversionTherapy(xSel), 34);
                    StdDraw.circle(xConversionTherapy(ySel), yConversionTherapy(xSel), 33);
                    StdDraw.circle(xConversionTherapy(ySel), yConversionTherapy(xSel), 32);
                    StdDraw.circle(xConversionTherapy(ySel), yConversionTherapy(xSel), 31);
                    StdDraw.circle(xConversionTherapy(ySel), yConversionTherapy(xSel), 30);
                } else {
                    StdDraw.picture(xConversionTherapy(ySel),yConversionTherapy(xSel), "src/crosshair.png");
                }
            }
            //this is a bad way to do it. will be updated

            mouseX = StdDraw.mouseY(); //this is because of a weird bug. Easier to do this than figure out why
            mouseY = StdDraw.mouseX(); //Doesn't really affect anything as long as they're flipped like this

            //new frame
            StdDraw.show();
            StdDraw.pause((int) (timeElapsed * 1000));
            StdDraw.clear();
        }
    }
    public static double xConversionTherapy(int xIn){ //converts x-coordinates of CheckerBoard to x-coordinates for the GUI
        return 37.5 + (75*xIn);
    }
    public static double yConversionTherapy(int yIn){ //converts y-coordinates of CheckerBoard to y-coordinates for the GUI
        return 562.5 - (75*yIn);
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
        Thread backendGo = new Thread(() -> {
            String[] dummy = new String[3]; //because backend depends on an input string[]
            backend.main(dummy);
        }
        );
        backendGo.start(); //runs backend (this will be replaced as it doesn't let GUI interact with backend. just passive for now)
    }
    public static void watchdogRun(){
        Thread watchdogGo = new Thread(() -> {
            String[] dummy = new String[3]; //because backend depends on an input string[]
            watchdog.main(dummy);
        }
        );
        watchdogGo.start(); //runs backend (this will be replaced as it doesn't let GUI interact with backend. just passive for now)
    }
}
