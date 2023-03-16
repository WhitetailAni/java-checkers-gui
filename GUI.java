import java.awt.*;

public class GUI extends backend {
    
    public static Color customRed = new Color(240, 25, 25);
    public static Color transparent = new Color(44,44,44,90);

    public static void main(String[] args) {

        //configure StdDraw
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int viewHeight = (int)(screenSize.getHeight()*0.8);
        int viewWidth = (int)(viewHeight*0.916030534);
        double timeElapsed = 0.017; //each frame is 0.017 seconds long
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(customRed);
        StdDraw.setCanvasSize(viewWidth, viewHeight);
        System.out.println("Screen dimensions are " + viewWidth + " by " + viewHeight);
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 655);
        StdDraw.enableDoubleBuffering();

        //resources for title bar
        Font title = new Font("Sans Serif", Font.BOLD, 30);
        Font alert = new Font("Sans Serif", Font.BOLD, 20);
        Font captured = new Font("Helvetica", Font.BOLD, 15);
        Font inBetween = new Font("Sans Serif", Font.BOLD, 25);

        double xGuiPos = -37.5;
        double yGuiPos = 37.5;


        //intro loop
        while (true) {
            //show board then darken it
            boardBG(xGuiPos, yGuiPos);
            screenTint();

            //mode select screen
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledRectangle(300,300,225,150);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setFont(alert);
            StdDraw.filledRectangle(300,190,225,2.5);
            StdDraw.text(300,167.5,"Quit");
            if((450 >= mouseX && mouseX >= 150)){
                System.exit(0);
            }
            StdDraw.show();
            StdDraw.pause((int) (timeElapsed * 1000));
            StdDraw.clear();
            try {
                Thread.sleep(200000);
            }
            catch(InterruptedException ignored) { }
            break;
        }
        backendRun(1);

        //gameloop
        while (true) {

            //this has to be in the while loop

            StdDraw.setPenColor(customRed);
            //set up board background
            boardBG(xGuiPos, yGuiPos);

            //display current turn
            if(redTurn){
                StdDraw.setPenColor(customRed);
            } else {
                StdDraw.setPenColor(StdDraw.BLACK);
            }

            if(StdDraw.isMousePressed() && ((mouseY > 170 && 430 > mouseY) && (mouseX > 617 && 645 > mouseX))){
                cheatMode = true;
            }

            //show alerts
            if (!misclick2) { //misclick prompt needs to override everything else rendered
                if (blackTurn) {
                    if (cheatMode) {
                        StdDraw.setFont(inBetween);
                        StdDraw.text(300, 627, "Cheat mode toggled");
                    } else if (selectPiece) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 638, "It is BLACK's turn");
                        StdDraw.text(300, 617, "Select a piece to move");
                    } else if (selectDest) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 638, "It is BLACK's turn");
                        StdDraw.text(300, 617, "Select the destination");
                    } else if (moveBlack) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 638, "You can't move");
                        StdDraw.text(300, 617, "RED's pieces!");
                    } else if (emptyBlack) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 638, "You can't move");
                        StdDraw.text(300, 617, "an empty space!");
                    } else {
                        StdDraw.setFont(title);
                        StdDraw.text(300, 625, "It is BLACK's turn");
                    }
                    if (cheatModeBlack) {
                        StdDraw.picture(12, 617, "checkra1n.png");
                    }
                } else if (redTurn) {
                    if (cheatMode) {
                        StdDraw.setFont(inBetween);
                        StdDraw.text(300, 627, "Cheat mode toggled");
                    } else if (selectPiece) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 638, "It is RED's turn");
                        StdDraw.text(300, 617, "Select a piece to move");
                    } else if (selectDest) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 638, "It is RED's turn");
                        StdDraw.text(300, 617, "Select the destination");
                    } else if (moveRed) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 638, "You can't move");
                        StdDraw.text(300, 617, "BLACK's pieces!");
                    } else if (emptyRed) {
                        StdDraw.setFont(alert);
                        StdDraw.text(300, 638, "You can't move");
                        StdDraw.text(300, 617, "an empty space!");
                    } else {
                        StdDraw.setFont(title);
                        StdDraw.text(300, 625, "It is RED's turn");
                    }
                    if (cheatModeRed) {
                        StdDraw.picture(12, 617, "checkra1n.png");
                    }
                }
                watchdogRun();

                    //display captured pieces
                    StdDraw.setFont(captured);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.text(90, 635, "Black has captured");
                    if (blackCaptured == 1) {
                        StdDraw.text(90, 621, "1 piece");
                    } else {
                        StdDraw.text(90, 621, blackCaptured + " pieces");
                    }
                    StdDraw.setPenColor(customRed);
                    StdDraw.text(510, 635, "Red has captured");
                    if (redCaptured == 1) {
                        StdDraw.text(510, 621, blackCaptured + " pieces");
                    } else {
                        StdDraw.text(510, 621, redCaptured + " pieces");
                    }
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.setFont(alert);
                    StdDraw.text(300, 638, "You can't move there!");
                    StdDraw.text(300, 617, "Select a new piece?");
                    StdDraw.text(520, 625, "Yes");
                    StdDraw.text(80, 625, "No");
                    if (StdDraw.isMousePressed()) {
                        if ((60 <= mouseY && mouseY <= 100) && (620 <= mouseX && mouseX <= 638)) {
                            System.out.println("no");
                            misclick = 0;
                            misclick2 = false;
                        } else if ((500 <= mouseY && mouseY <= 540) && (620 <= mouseX && mouseX <= 638)) {
                            System.out.println("yes");
                            misclick = 1;
                            misclick2 = false;
                        }
                        while (true) {
                            if (!StdDraw.isMousePressed()) {
                                break;
                            }
                        }
                    }
                }

            /*if(StdDraw.isMousePressed()) {//current mouse coordinates
                System.out.println("MouseX is at " + mouseY);
                System.out.println("MouseY is at " + mouseX);
            }*/

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
            if(!(xSel == 0 && ySel == 0) && (!selectPiece && !moveBlack && !emptyBlack && !moveRed && !emptyRed) && !(CheckerBoard[xSel][ySel] == 0)) {
                int xScale = 23;
                int yScale = 23;
                int king = 0;
                if(CheckerBoard[xSel][ySel] == 3){
                    yScale = 15;
                    king = 1;
                }
                StdDraw.picture(xConversionTherapy(ySel),yConversionTherapy(xSel)-king, "crosshair.png",xScale,yScale);
            }

           mouseX = StdDraw.mouseY(); //this is because of a weird bug. Easier to do this than figure out why
           mouseY = StdDraw.mouseX(); //Doesn't really affect anything as long as they're flipped like this

           if(blackWins || redWins) {
               screenTint();
               StdDraw.setPenColor(StdDraw.WHITE);
               StdDraw.filledRectangle(300,300,125,60);
               if (blackWins) {
                   StdDraw.setPenColor(StdDraw.BLACK);
                   StdDraw.setFont(title);
                   StdDraw.text(300,297,"Black wins!");
               } else if (redWins) {
                   StdDraw.setPenColor(StdDraw.RED);
                   StdDraw.setFont(title);
                   StdDraw.text(300,297,"Red wins!");
               }
               while (true){ } //prevent GUI from being interacted with after a win is detected
           }

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
        StdDraw.picture(xIn,yIn,"checker.png",67,67); //draws sheikah text ring that says "checker" 4x
    }
    static void redReg(double xIn, double yIn){ //draws a regular red checker
        StdDraw.setPenColor(222,15,15);
        StdDraw.filledCircle(xIn,yIn,34);
        StdDraw.picture(xIn,yIn,"checker.png",67,67);
    }
    static void blackKing(double xIn, double yIn){ //draws a black king checker
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(xIn,yIn,34);
        StdDraw.picture(xIn,yIn,"bking.png"); //white outline of crown
        StdDraw.picture(xIn,yIn,"king.png",67,67); //draws sheikah text ring that says "king" 8x
    }
    static void redKing(double xIn, double yIn){ //draws a red king checker
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(xIn,yIn,34);
        StdDraw.picture(xIn,yIn+1,"rking.png"); //black outline of crown
        StdDraw.picture(xIn,yIn,"king.png",67,67);
    }
    public static void backendRun(int mode){
        Thread backendGo = new Thread(() -> {
            String[] args = new String[1];
            if(mode == 0){
                args[0] = "single";
            } else if(mode == 1){
                args[0] = "multi";
            } else if(mode == 2){
                args[0] = "tobenamed";
            }
            backend.main(args);
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
    static void boardBG(double xGuiPos, double yGuiPos){
        //resources for drawing board
        boolean altColor = false; //switches colors when drawing rectangles
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
        StdDraw.setPenColor(57, 191, 182);
        StdDraw.filledRectangle(300,630,300,25);
    }
    static void screenTint(){
        StdDraw.setPenColor(transparent);
        for(int i=0; i<6; i++) {
            StdDraw.filledRectangle(300, 350, 300, 350);
        }
    }
}
