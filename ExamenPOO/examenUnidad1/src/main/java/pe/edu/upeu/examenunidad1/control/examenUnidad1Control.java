package pe.edu.upeu.examenunidad1.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.examenunidad1.modelo.examenUnidad1TO;

import java.io.*;

@Controller
public class examenUnidad1Control {

    String jugador1;
    String jugador2;
    int indexId = 1;
    boolean equis = true;

    @FXML
    Label label_turno;

    @FXML
    TableView<examenUnidad1TO> tableView;
    private ObservableList<examenUnidad1TO> datos;

    @FXML
    TableColumn<examenUnidad1TO, String> partidox;

    @FXML
    TableColumn<examenUnidad1TO, String> jugador1x;

    @FXML
    TableColumn<examenUnidad1TO, String> jugador2x;

    @FXML
    TableColumn<examenUnidad1TO, String> ganadorx;

    @FXML
    TableColumn<examenUnidad1TO, String> puntuacionx;

    @FXML
    TableColumn<examenUnidad1TO, String> estadox;

    @FXML
    Button butt1_1;

    @FXML
    Button butt1_2;

    @FXML
    Button butt1_3;

    @FXML
    Button butt2_1;

    @FXML
    Button butt2_2;

    @FXML
    Button butt2_3;

    @FXML
    Button butt3_1;

    @FXML
    Button butt3_2;

    @FXML
    Button butt3_3;

    @FXML
    private TextField txt_jugador1;

    @FXML
    private TextField txt_jugador2;

    @FXML
    Label label_aviso;

    @FXML
    Button butt_iniciar;

    @FXML
    private void initialize() {
        // Inicializar el TableView y las columnas
        datos = FXCollections.observableArrayList();
        tableView.setItems(datos);

        // Vincular las columnas a las propiedades de TresEnRayaJO
        partidox.setCellValueFactory(new PropertyValueFactory<>("partido"));
        jugador1x.setCellValueFactory(new PropertyValueFactory<>("jugador1"));
        jugador2x.setCellValueFactory(new PropertyValueFactory<>("jugador2"));
        ganadorx.setCellValueFactory(new PropertyValueFactory<>("ganador"));
        puntuacionx.setCellValueFactory(new PropertyValueFactory<>("puntuacion"));
        estadox.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Cargar partidas desde el archivo CSV
        cargarPartidasDesdeCSV();

        // Configuración inicial de los botones
        butt_iniciar.setDisable(false);
        butt1_1.setDisable(true);
        butt1_2.setDisable(true);
        butt1_3.setDisable(true);
        butt2_1.setDisable(true);
        butt2_2.setDisable(true);
        butt2_3.setDisable(true);
        butt3_1.setDisable(true);
        butt3_2.setDisable(true);
        butt3_3.setDisable(true);
    }

    private void cargarPartidasDesdeCSV() {
        File file = new File("partidas.csv");
        if (!file.exists()) {
            return; // Si el archivo no existe, no hay nada que cargar
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Saltar la primera línea (encabezado)
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    examenUnidad1TO partida = new examenUnidad1TO();
                    partida.setPartido(parts[0]);
                    partida.setJugador1(parts[1]);
                    partida.setJugador2(parts[2]);
                    partida.setGanador(parts[3]);
                    partida.setPuntuacion(parts[4]);
                    partida.setEstado(parts[5]);
                    datos.add(partida);

                    // Actualizar indexId basado en el último número de partida
                    int partidaNum = Integer.parseInt(parts[0]);
                    if (partidaNum >= indexId) {
                        indexId = partidaNum + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar partidas: " + e.getMessage());
        }
    }

    private void guardarPartidaEnCSV(examenUnidad1TO partida) {
        File file = new File("partidas.csv");
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(file, true)) {
            // Si el archivo no existe, escribir el encabezado
            if (!fileExists) {
                writer.write("N° Partida,Jugador 1,Jugador 2,Ganador,Puntuación,Estado\n");
            }
            // Escribir los datos de la partida
            writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    partida.getPartido(),
                    partida.getJugador1(),
                    partida.getJugador2(),
                    partida.getGanador(),
                    partida.getPuntuacion(),
                    partida.getEstado()));
        } catch (IOException e) {
            System.out.println("Error al guardar partida: " + e.getMessage());
        }
    }

    private void buttIniciar() {
        jugador1 = txt_jugador1.getText();
        label_turno.setText(jugador1);
        jugador2 = txt_jugador2.getText();
        butt1_1.setDisable(false);
        butt1_2.setDisable(false);
        butt1_3.setDisable(false);
        butt2_1.setDisable(false);
        butt2_2.setDisable(false);
        butt2_3.setDisable(false);
        butt3_1.setDisable(false);
        butt3_2.setDisable(false);
        butt3_3.setDisable(false);
        butt_iniciar.setDisable(true);
    }

    private void btnAnular() {
        butt1_1.setText("1");
        butt1_1.setStyle("-fx-text-fill: transparent;");
        butt1_2.setText("2");
        butt1_2.setStyle("-fx-text-fill: transparent;");
        butt1_3.setText("3");
        butt1_3.setStyle("-fx-text-fill: transparent;");
        butt2_1.setText("4");
        butt2_1.setStyle("-fx-text-fill: transparent;");
        butt2_2.setText("5");
        butt2_2.setStyle("-fx-text-fill: transparent;");
        butt2_3.setText("6");
        butt2_3.setStyle("-fx-text-fill: transparent;");
        butt3_1.setText("7");
        butt3_1.setStyle("-fx-text-fill: transparent;");
        butt3_2.setText("8");
        butt3_2.setStyle("-fx-text-fill: transparent;");
        butt3_3.setText("9");
        butt3_3.setStyle("-fx-text-fill: transparent;");
        initialize();
    }

    private void btn_1_1() {
        if (butt1_1.getText().equals("1")) {
            if (equis) {
                butt1_1.setText("X");
                butt1_1.setStyle("-fx-font-size: 36px;-fx-text-fill: red");
                equis = false;
            } else {
                butt1_1.setText("O");
                butt1_1.setStyle("-fx-font-size: 36px;-fx-text-fill: blue");
                equis = true;
            }
            if (label_turno.getText().equals(jugador1)) {
                label_turno.setText(jugador2);
            } else if (label_turno.getText().equals(jugador2)) {
                label_turno.setText(jugador1);
            }
            ganar();
        }
    }

    private void btn_1_2() {
        if (butt1_2.getText().equals("2")) {
            if (equis) {
                butt1_2.setText("X");
                butt1_2.setStyle("-fx-font-size: 36px;-fx-text-fill: red");
                equis = false;
            } else {
                butt1_2.setText("O");
                butt1_2.setStyle("-fx-font-size: 36px;-fx-text-fill: blue");
                equis = true;
            }
            if (label_turno.getText().equals(jugador1)) {
                label_turno.setText(jugador2);
            } else if (label_turno.getText().equals(jugador2)) {
                label_turno.setText(jugador1);
            }
            ganar();
        }
    }

    private void btn_1_3() {
        if (butt1_3.getText().equals("3")) {
            if (equis) {
                butt1_3.setText("X");
                butt1_3.setStyle("-fx-font-size: 36px;-fx-text-fill: red");
                equis = false;
            } else {
                butt1_3.setText("O");
                butt1_3.setStyle("-fx-font-size: 36px;-fx-text-fill: blue");
                equis = true;
            }
            if (label_turno.getText().equals(jugador1)) {
                label_turno.setText(jugador2);
            } else if (label_turno.getText().equals(jugador2)) {
                label_turno.setText(jugador1);
            }
            ganar();
        }
    }

    private void btn_2_1() {
        if (butt2_1.getText().equals("4")) {
            if (equis) {
                butt2_1.setText("X");
                butt2_1.setStyle("-fx-font-size: 36px;-fx-text-fill: red");
                equis = false;
            } else {
                butt2_1.setText("O");
                butt2_1.setStyle("-fx-font-size: 36px;-fx-text-fill: blue");
                equis = true;
            }
            if (label_turno.getText().equals(jugador1)) {
                label_turno.setText(jugador2);
            } else if (label_turno.getText().equals(jugador2)) {
                label_turno.setText(jugador1);
            }
            ganar();
        }
    }

    private void btn_2_2() {
        if (butt2_2.getText().equals("5")) {
            if (equis) {
                butt2_2.setText("X");
                butt2_2.setStyle("-fx-font-size: 36px;-fx-text-fill: red");
                equis = false;
            } else {
                butt2_2.setText("O");
                butt2_2.setStyle("-fx-font-size: 36px;-fx-text-fill: blue");
                equis = true;
            }
            if (label_turno.getText().equals(jugador1)) {
                label_turno.setText(jugador2);
            } else if (label_turno.getText().equals(jugador2)) {
                label_turno.setText(jugador1);
            }
            ganar();
        }
    }

    private void btn_2_3() {
        if (butt2_3.getText().equals("6")) {
            if (equis) {
                butt2_3.setText("X");
                butt2_3.setStyle("-fx-font-size: 36px;-fx-text-fill: red");
                equis = false;
            } else {
                butt2_3.setText("O");
                butt2_3.setStyle("-fx-font-size: 36px;-fx-text-fill: blue");
                equis = true;
            }
            if (label_turno.getText().equals(jugador1)) {
                label_turno.setText(jugador2);
            } else if (label_turno.getText().equals(jugador2)) {
                label_turno.setText(jugador1);
            }
            ganar();
        }
    }

    private void btn_3_1() {
        if (butt3_1.getText().equals("7")) {
            if (equis) {
                butt3_1.setText("X");
                butt3_1.setStyle("-fx-font-size: 36px;-fx-text-fill: red");
                equis = false;
            } else {
                butt3_1.setText("O");
                butt3_1.setStyle("-fx-font-size: 36px;-fx-text-fill: blue");
                equis = true;
            }
            if (label_turno.getText().equals(jugador1)) {
                label_turno.setText(jugador2);
            } else if (label_turno.getText().equals(jugador2)) {
                label_turno.setText(jugador1);
            }
            ganar();
        }
    }

    private void btn_3_2() {
        if (butt3_2.getText().equals("8")) {
            if (equis) {
                butt3_2.setText("X");
                butt3_2.setStyle("-fx-font-size: 36px;-fx-text-fill: red");
                equis = false;
            } else {
                butt3_2.setText("O");
                butt3_2.setStyle("-fx-font-size: 36px;-fx-text-fill: blue");
                equis = true;
            }
            if (label_turno.getText().equals(jugador1)) {
                label_turno.setText(jugador2);
            } else if (label_turno.getText().equals(jugador2)) {
                label_turno.setText(jugador1);
            }
            ganar();
        }
    }

    private void btn_3_3() {
        if (butt3_3.getText().equals("9")) {
            if (equis) {
                butt3_3.setText("X");
                butt3_3.setStyle("-fx-font-size: 36px;-fx-text-fill: red");
                equis = false;
            } else {
                butt3_3.setText("O");
                butt3_3.setStyle("-fx-font-size: 36px;-fx-text-fill: blue");
                equis = true;
            }
            if (label_turno.getText().equals(jugador1)) {
                label_turno.setText(jugador2);
            } else if (label_turno.getText().equals(jugador2)) {
                label_turno.setText(jugador1);
            }
            ganar();
        }
    }

    private void ganar() {
        String ganador = null;

        // Determinar quién es el jugador actual basado en el turno
        if (label_turno.getText().equals(jugador1)) {
            ganador = jugador2; // El último movimiento fue de jugador2
        } else if (label_turno.getText().equals(jugador2)) {
            ganador = jugador1; // El último movimiento fue de jugador1
        }

        // Verificar condiciones de victoria
        boolean hayGanador = false;
        if (butt1_1.getText().equals(butt1_2.getText()) && butt1_2.getText().equals(butt1_3.getText()) && !butt1_1.getText().isEmpty()) {
            label_turno.setText("Ganador: " + ganador);
            hayGanador = true;



        } else if (butt2_1.getText().equals(butt2_2.getText()) && butt2_2.getText().equals(butt2_3.getText()) && !butt2_1.getText().isEmpty()) {
            label_turno.setText("Ganador: " + ganador);
            hayGanador = true;



        } else if (butt3_1.getText().equals(butt3_2.getText()) && butt3_2.getText().equals(butt3_3.getText()) && !butt3_1.getText().isEmpty()) {
            label_turno.setText("Ganador: " + ganador);
            hayGanador = true;



        } else if (butt1_1.getText().equals(butt2_1.getText()) && butt2_1.getText().equals(butt3_1.getText()) && !butt1_1.getText().isEmpty()) {
            label_turno.setText("Ganador: " + ganador);
            hayGanador = true;



        } else if (butt1_2.getText().equals(butt2_2.getText()) && butt2_2.getText().equals(butt3_2.getText()) && !butt1_2.getText().isEmpty()) {
            label_turno.setText("Ganador: " + ganador);
            hayGanador = true;



        } else if (butt1_3.getText().equals(butt2_3.getText()) && butt2_3.getText().equals(butt3_3.getText()) && !butt1_3.getText().isEmpty()) {
            label_turno.setText("Ganador: " + ganador);
            hayGanador = true;



        } else if (butt1_1.getText().equals(butt2_2.getText()) && butt2_2.getText().equals(butt3_3.getText()) && !butt1_1.getText().isEmpty()) {
            label_turno.setText("Ganador: " + ganador);
            hayGanador = true;



        } else if (butt1_3.getText().equals(butt2_2.getText()) && butt2_2.getText().equals(butt3_1.getText()) && !butt1_3.getText().isEmpty()) {
            label_turno.setText("Ganador: " + ganador);
            hayGanador = true;

        }



        // Verificar empate
        boolean tableroLleno = true;
        Button[] botones = {butt1_1, butt1_2, butt1_3, butt2_1, butt2_2, butt2_3, butt3_1, butt3_2, butt3_3};


        for (Button boton : botones) {
            if (boton.getText().matches("[1-9]")) {
                tableroLleno = false;
                break;
            }
        }

        // Registrar la partida si hay ganador o empate
        if (hayGanador) {
            examenUnidad1TO partida = new examenUnidad1TO();
            partida.setPartido(String.valueOf(indexId));
            partida.setJugador1(jugador1);
            partida.setJugador2(jugador2);
            partida.setGanador(ganador);
            partida.setPuntuacion("1"); // 1 punto para el ganador
            partida.setEstado("Finalizado");
            datos.add(partida);
            guardarPartidaEnCSV(partida);
            indexId++;
            btnAnular();
        } else if (tableroLleno) {
            examenUnidad1TO partida = new examenUnidad1TO();
            partida.setPartido(String.valueOf(indexId));
            partida.setJugador1(jugador1);
            partida.setJugador2(jugador2);
            partida.setGanador("Ninguno");
            partida.setPuntuacion("0");
            partida.setEstado("Empate");
            datos.add(partida);
            guardarPartidaEnCSV(partida);
            indexId++;
            btnAnular();
        }
    }

    @FXML
    private void controlClick(ActionEvent event) {
        Button boton = (Button) event.getSource();
        switch (boton.getId()) {
            case "butt_iniciar":
                if (txt_jugador1.getText() != "" && txt_jugador2.getText() != "") {
                    buttIniciar();
                    label_aviso.setText("");
                    label_aviso.setStyle("-fx-background-color: transparent;");
                } else {
                    label_aviso.setText(" INGRESAR NOMBRES");
                    label_aviso.setStyle("-fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold;" +
                            "-fx-background-color:red ;");
                }
                break;
            case "butt_anular":
                btnAnular();
                break;
            case "butt1_1":
                btn_1_1();
                break;
            case "butt1_2":
                btn_1_2();
                break;
            case "butt1_3":
                btn_1_3();
                break;
            case "butt2_1":
                btn_2_1();
                break;
            case "butt2_2":
                btn_2_2();
                break;
            case "butt2_3":
                btn_2_3();
                break;
            case "butt3_1":
                btn_3_1();
                break;
            case "butt3_2":
                btn_3_2();
                break;
            case "butt3_3":
                btn_3_3();
                break;
        }
    }
}