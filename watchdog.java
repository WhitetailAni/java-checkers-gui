public class watchdog extends GUI {

    //resets alerts so alertBoard doesn't get stuck
    public static void main(String[] args) {
        try{
            while (true) {
                if (moveBlack) {
                    Thread.sleep(5000);
                    moveBlack = false;
                }
                if (moveRed) {
                    Thread.sleep(5000);
                    moveRed = false;
                }
                if (emptyBlack) {
                    Thread.sleep(5000);
                    emptyBlack = false;
                }
                if (emptyRed) {
                    Thread.sleep(5000);
                    emptyRed = false;
                }
                if (selectNewPiece) {
                    Thread.sleep(5000);
                    selectNewPiece = false;
                }
                if (cheatMode) {
                    Thread.sleep(1500);
                    cheatMode = false;
                }
            }
        }
        catch (InterruptedException e){
        }
    }
}
