import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.io.File;

public class Oblig7 extends Application {
    static GUIRute[][] RUTER;
    private BorderPane root = new BorderPane();
    private GridPane rutenett;
    private Stage window;
    private Labyrint labyrint;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage window){
        this.window = window;
        root.setTop(meny());
        Scene scene = new Scene(root);
        window.setTitle("Labyrint");
        window.setScene(scene);
        window.show();
    }

    // Starter programmet og setter utforming.
    private HBox meny(){
        Button hentFil = new Button("Velg en Labyrint");

        hentFil.setOnAction(new EventHandler<>() {

            public void handle(ActionEvent e){
                System.out.println("Starter FileChooser.");

                //Setter opp en filchooser
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Velg en Labyrint");
                File selectedFile = fileChooser.showOpenDialog(window);

                if(selectedFile != null){
                    System.out.println(selectedFile.getPath());
                    try {
                        labyrint = Labyrint.lesFraFil(selectedFile);
                        rutenett = lagGridPane(labyrint);
                        root.setCenter(rutenett);
                        root.setTop(nyMeny());
                        window.sizeToScene();
                    } catch (Exception n) {
                        System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", selectedFile.getPath());
                        System.exit(1);
                    }
                } else{
                    System.out.println("Ingen fil ble valgt.");
                }
            }
        });
        return new HBox(hentFil);
    }

    // Lager labyrinten.
    private GridPane lagGridPane(Labyrint l){
        GridPane nett = new GridPane();
        RUTER = new GUIRute[l.rader][l.kolonner];

        for (int rad  = 0; rad < l.rader; rad++){
            for (int kol = 0; kol < l.kolonner; kol++){
                RUTER[rad][kol] = new GUIRute(rad, kol, l);
                nett.add(RUTER[rad][kol], kol, rad);
            }
        }
        return nett;
    }

    // Lager en ny meny naar labyrinten er valgt.
    private HBox nyMeny(){
        Button hentFil = new Button("Bytt Labyrint");

        hentFil.setOnAction(new EventHandler<>() {

            public void handle(ActionEvent e){
                System.out.println("Starter FileChooser.");

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Velg en Labyrint fil");
                fileChooser.getExtensionFilters().addAll(
                        new ExtensionFilter("Labyrint Filer", "*.in"));
                File selectedFile = fileChooser.showOpenDialog(window);

                if (selectedFile != null){
                    System.out.println(selectedFile.getPath());
                    try {
                        labyrint = Labyrint.lesFraFil(selectedFile);
                        rutenett = lagGridPane(labyrint);
                        root.setCenter(rutenett);
                        root.setTop(nyMeny());
                        window.sizeToScene();
                    } catch (Exception n) {
                        System.out.printf("Kunne ikke lese fra '%s'\n", selectedFile.getPath());
                        System.exit(1);
                    }
                } else {
                    System.out.println("Ingen fil ble valgt.");
                }
            }
        });
        return new HBox(hentFil);
    }
}