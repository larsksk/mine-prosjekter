import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

class GUIRute extends Pane {
    private static Labyrint labyrint;
    private Boolean hvit;
    private Boolean vei = false;
    private Rute rute;

    GUIRute(int rad, int kol, Labyrint l){
        labyrint = l;

        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                null, new BorderWidths(0.7))));
        setMinWidth(15);
        setMinHeight(15);

        this.rute = labyrint.ruter[rad][kol];
        if (rute instanceof HvitRute){
            setBackground(new Background(
                    new BackgroundFill(Color.WHITE, null, null)));
            hvit = true;
        } else {
            setBackground(new Background(
                    new BackgroundFill(Color.BLACK, null, null)));
            hvit = false;
        }

        // Kaller paa loesningen naar man trykker paa en rute.
        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<>(){
            public void handle(MouseEvent event){
                reset();
                if (hvit){
                    if (!vei){
                        fargeVei(rute.rad, rute.kol);

                        // Setter fargen til startruta.
                        setBackground(new Background(
                                new BackgroundFill(Color.DARKORANGE, null, null)));
                    }
                }
            }
        });
    }

    // Finner de raskeste loesningene og farger de roede.
    private void fargeVei(int rad, int kol) {
        boolean[][] bool = labyrint.finnUtveiFra(kol, rad);
        if (bool != null) {
            for (int i = 0; i < labyrint.kolonner; i++) {
                for (int j = 0; j < labyrint.rader; j++) {
                    if (bool[j][i]) {
                        Oblig7.RUTER[j][i].setBackground(new Background(
                                new BackgroundFill(Color.RED, null, null)));
                        Oblig7.RUTER[j][i].vei = true;
                    }
                }
            }
        }
    }

    // Setter alle hvite ruter tilbake til hvit.
    private static void reset(){
        for (int i = 0; i < labyrint.rader; i++){
            for (int j = 0; j < labyrint.kolonner; j++){
                if (Oblig7.RUTER[i][j].hvit){
                    Oblig7.RUTER[i][j].setBackground(new Background(
                            new BackgroundFill(Color.WHITE, null, null)));
                    Oblig7.RUTER[i][j].vei = false;
                }
            }
        }
    }
}