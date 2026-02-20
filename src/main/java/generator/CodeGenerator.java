package generator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {
    private List<CodeGeneratorComponent> componentList;
    private String midiInterfaceName;

    private final String configFilePath = "./ConfiguratorData.json";
    private JSONArray controllerConfiguration;
    private final String codeOutputPath = "./generatedCode.ino";

    public CodeGenerator() {
        componentList = new ArrayList<>();
        this.midiInterfaceName = "midi";

        loadConfiguration();  // Config laden und in JSONArray speichern
        applyConfiguration(); // componentList mit den elementen der config füllen
        writeCodeToFile();    // code aus allen elementen der componentList holen und in outputfile speichern
    }

    private void loadConfiguration() {
        String readJsonString = "";
        try {
            readJsonString = new String(Files.readAllBytes(Paths.get(new File(configFilePath).toURI())));
            // TODO App crasht, wenn die Datei nicht zu finden ist
        } catch (IOException e) {
            System.out.println("Problem reading the config File in the code generator");
            e.printStackTrace();
        }
        controllerConfiguration = new JSONArray(readJsonString);

        // Debug  print
        System.out.println(controllerConfiguration);
    }

    private void applyConfiguration() {
        for (int i = 0; i < controllerConfiguration.length(); i++) {
            JSONObject arrayElement = controllerConfiguration.getJSONObject(i);
            String inputType = arrayElement.getString("input-type");
            switch (inputType) {
                case "button":
                    System.out.println("button is supported");
                    componentList.add(new ButtonComponent(arrayElement, i));
                    System.out.println(this.componentList.get(i));
                    System.out.println(this.componentList.get(i).getSetupCode());
                    System.out.println(this.componentList.get(i).getLoopCode(midiInterfaceName));
                    break;
                default:
                    System.out.printf("Input Type \"%s\" is not supported by the Code Generator at the Moment%n", inputType);
            }
        }
    }

    private void writeCodeToFile() {
        try (FileWriter codeOutputFileWriter = new FileWriter(codeOutputPath)) {
            codeOutputFileWriter.write(String.format("""
                    #include<Control_Surface.h>
                    
                    USBMIDI_Interface %s;
                    """,
                    this.midiInterfaceName
            ));

            // Code im Globalen Teil
            for (CodeGeneratorComponent codeGeneratorComponent : componentList) {
                codeOutputFileWriter.write(codeGeneratorComponent.getGlobalCode());
            }

            // setup() Funktion
            codeOutputFileWriter.write("void setup() {");
            for (CodeGeneratorComponent codeGeneratorComponent : componentList) {
                codeOutputFileWriter.write(codeGeneratorComponent.getSetupCode());
            }
            codeOutputFileWriter.write(String.format("%s.update();", this.midiInterfaceName));
            codeOutputFileWriter.write("}");

            // loop() Funktion
            codeOutputFileWriter.write("void loop() {");
            codeOutputFileWriter.write(String.format("%s.update();", this.midiInterfaceName));
            for (CodeGeneratorComponent codeGeneratorComponent : componentList) {
                codeOutputFileWriter.write(codeGeneratorComponent.getLoopCode(this.midiInterfaceName));
            }
            codeOutputFileWriter.write("}");
        } catch (IOException e) {
            System.out.println("Error opening output file");
            throw new RuntimeException(e);
        }
    }

    // Weitere Methoden für andere Steps der Codegenerierung

    public String getMidiInterfaceName() {
        return midiInterfaceName;
    }

}
