package codeuploader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.ApplicationSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static util.ApplicationSettings.getSelectedBoard;

public class CodeUploader {
    private CodeUploader() {
        // Die Klasse selber soll nie Instanziiert werden, sondern ist eine Sammlung von Funktionen die einzeln aufgerufen werden sollen
    }
    public static void compileCode() {
        System.out.println("compiling Code");
        ProcessBuilder compileProcess = new ProcessBuilder(
                "arduino-cli",
                "compile",
                "--fqbn",
                "arduino:avr:leonardo",
                "generatedCode");
        compileProcess.inheritIO();
        try {
            compileProcess.start();
        } catch (IOException e) {
            System.out.println("Fehler bei der Kompilierung des Mikrocontrollerprogramms!");
        }
    }

    public static void uploadCode() {
        if(!ApplicationSettings.hasSelectedBoard()) {
            System.out.println("Board muss gesetzt sein. Breche ab.");
            return;
        }
        if(!ApplicationSettings.hasPortAddress()) {
            System.out.println("Port muss gesetzt sein. Breche ab.");
            return;
        }
        System.out.println("flashing Code");
        ProcessBuilder compileProcess = new ProcessBuilder(
                "arduino-cli",
                "upload",
                "generatedCode",
                "-p",
                ApplicationSettings.getPortAddress(),
                "-b",
                ApplicationSettings.getSelectedBoard()
        );
        compileProcess.inheritIO();
        try {
            compileProcess.start();
        } catch (IOException e) {
            System.out.println("Fehler bei dem Hochladen des Mikrocontrollerprogramms!");
        }
    }

    public static Map<String, Map<String, String>> getConnectedBoards() {
        Map<String, Map<String, String>> boards = new HashMap<>();
        JSONObject processReturn;

        ProcessBuilder processBuilder = new ProcessBuilder(
                "arduino-cli",
                "board",
                "list",
                "--json"
        );

        try {
            final Process process = processBuilder.start();
            process.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            processReturn = new JSONObject(sb.toString());
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Fehler beim ermitteln angeschlossener Geräte");
            return null; // TODO Nicht so schlau wenn die erhaltende Seite da nicht mit rechnet
        }
        System.out.println(processReturn);
        JSONArray detectedBoards = processReturn.getJSONArray("detected_ports");
        for (int index = 0; index < detectedBoards.length(); index++) {
            try {
                String boardName = detectedBoards.getJSONObject(index).getJSONArray("matching_boards").getJSONObject(0).getString("name");
                String fqbn = detectedBoards.getJSONObject(index).getJSONArray("matching_boards").getJSONObject(0).getString("fqbn");
                String port = detectedBoards.getJSONObject(index).getJSONObject("port").getString("address");
                Map<String, String> infoMap = new HashMap<>();
                infoMap.put("name", boardName);
                infoMap.put("port", port);
                boards.put(fqbn, infoMap);
            }
            catch (JSONException e) {
                // Sollte der obere Block fehlschlagen bedeutet dass, dass der Key "matching_boards" nicht existiert
                // Das ist bei mir passiert als ich einen nachgemachten, billigen Arduino angeschlossen habe
                // Kann man in Zukunft mal implementieren, gerade aber nicht so wichtig
                System.out.println("\"matching_boards\" ist bei einem angeschlossenem Board leer. Das kann bedeuten, dass das Board zwar erkannt wird, aber nicht original von Arduino ist. Diese Boards werden eventuell in der Zukunft unterstützt.");
            }

        }


        return boards;
    }
}
