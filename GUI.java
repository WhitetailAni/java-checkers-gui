import java.util.concurrent.Executor;

public class GUI extends Main{

    public static void main(String[] args) {

        //configure StdDraw
        double timeElapsed = 0.017; //each frame is 0.017 seconds long
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setScale(0, 600);
        StdDraw.enableDoubleBuffering();

        //draw board
        boolean altColor = false; //switches colors when drawing rectangles

        backendRun();

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

                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledCircle(37.5,37.5,34);
            }


            //new frame
            StdDraw.show();
            StdDraw.pause((int) (timeElapsed * 1000));
            StdDraw.clear();
        }
    }
    public static void backendRun(){
        Thread backendGo = new Thread(new Runnable() {
            public void run() {
                String[] dummy = new String[3]; //because backend depends on an input string[]
                Main.backend(dummy);
            }
        }
        );
        backendGo.start();
    }
}
