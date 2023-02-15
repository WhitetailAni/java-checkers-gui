public class GUI extends Main{

    public static void main(String[] args) {
        System.out.println("hi");
        double timeElapsed = 0.017; //0.017 seconds-- this is how long each frame of our animation appears.
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setScale(0, 600);
        StdDraw.enableDoubleBuffering();
        while (true) {

            double xGuiPos = 37.5;
            double yGuiPos = 37.5;
            boolean altColor = true; //switches colors when drawing rectangles
            StdDraw.setPenColor(StdDraw.RED);
            for(int i=0; i<8; i++){
                for(int j=0; j<9; j++) {
                    StdDraw.filledRectangle(xGuiPos, yGuiPos, 37.5, 37.5);
                    if (altColor) {
                        StdDraw.setPenColor(StdDraw.BLUE); //COLORS NOT FINAL
                    } else {
                        StdDraw.setPenColor(StdDraw.RED);
                    }
                    altColor = !altColor;
                    xGuiPos += 75;
                }
                yGuiPos += 75;
                xGuiPos = -37.5;
            }


            //new frame
            StdDraw.show();
            StdDraw.pause((int) (timeElapsed * 1000));
            StdDraw.clear();
        }
    }
}
