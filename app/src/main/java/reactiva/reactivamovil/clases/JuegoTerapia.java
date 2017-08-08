package reactiva.reactivamovil.clases;

/**
 * Created by Usuario on 2/8/2017.
 */

public class JuegoTerapia {

    String nameGame;
    int view_game;


    public JuegoTerapia(String nameGame, int view_game) {
        this.nameGame = nameGame;
        this.view_game = view_game;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public int getView_game() {
        return view_game;
    }

    public void setView_game(int view_game) {
        this.view_game = view_game;
    }


}
