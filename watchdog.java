public class watchdog extends GUI {

    //resets alerts so alertBoard doesn't get stuck
    public static void main(String[] args) {
        try{
            if (moveBlack) {
                Thread.sleep(10000);
                moveBlack = false;
                selectPiece = true;
            }
            if (moveRed) {
                Thread.sleep(10000);
                moveRed = false;
                selectPiece = true;
            }
            if (emptyBlack) {
                Thread.sleep(10000);
                emptyBlack = false;
                selectPiece = true;
            }
            if (emptyRed) {
                Thread.sleep(10000);
                emptyRed = false;
                selectPiece = true;
            }
            if (cheatMode) {
                Thread.sleep(3000);
                cheatMode = false;
                selectPiece = true;
            }
        }
        catch (InterruptedException e){ }
    }
}
