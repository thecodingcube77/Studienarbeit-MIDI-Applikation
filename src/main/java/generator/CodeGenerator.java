package generator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {
    private List<CodeGeneratorComponent> componentList;
    private String midiInterfaceName;

    private final String configFilePath = "./resources/ConfiguratorData.json";
    private JSONArray controllerConfiguration;

    private final String codeOutputPath = "./generatedCode.ino";
    private JSONObject codeTemplates;

    private CodeGeneratorUtilities utilities = new CodeGeneratorUtilities();

    public CodeGenerator() {
        componentList = new ArrayList<>();
        this.midiInterfaceName = "midi";

        loadTemplates();
        loadConfiguration();  // Config laden und in JSONArray speichern
        applyConfiguration(); // componentList mit den elementen der config füllen
        writeCodeToFile();    // code aus allen elementen der componentList holen und in outputfile speichern
    }

    private void loadTemplates() {
        String codeTemplateFilePath = "./resources/config/CodeTemplates.json";
        StringBuilder jsonStringBuilder = new StringBuilder();
        File inputJSONFile = new File(codeTemplateFilePath);
        try (Scanner jsonFileReader = new Scanner(inputJSONFile)) {
            while (jsonFileReader.hasNextLine()) {
                jsonStringBuilder.append(jsonFileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read Midi Commands JSON File at:" + codeTemplateFilePath);
        }
        codeTemplates = new JSONObject(jsonStringBuilder.toString()).getJSONObject("code-snippets");
    }

    public CodeGenerator(JSONArray controllerConfiguration) {
        componentList = new ArrayList<>();
        this.midiInterfaceName = "midi";

        this.controllerConfiguration = controllerConfiguration;

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
    }

    private void applyConfiguration() {
        for (int i = 0; i < controllerConfiguration.length(); i++) {
            JSONObject arrayElement = controllerConfiguration.getJSONObject(i);
            arrayElement.put("pin-number", String.valueOf(i + 2));
            String inputType = arrayElement.getString("input-type");
            switch (inputType) {
                case "button":
                    System.out.println("button is supported");
                    componentList.add(new ButtonComponent(utilities, arrayElement));
                    break;
                case "potentiometer":
                default:
                    System.out.printf("Input Type \"%s\" is not supported by the Code Generator at the Moment%n", inputType);
            }
        }
    }

    private void writeCodeSection(String section, FileWriter fileWriter) throws IOException {
        fileWriter.write(getStartCode(section));
        for (CodeGeneratorComponent component : componentList) {
            fileWriter.write(getCode(component, section));
        }
        fileWriter.write(getEndCode(section));
    }

    private String getStartCode(String section) {
        String template = codeTemplates.getJSONObject(section).getString("start");
        return template.replaceAll("__midiInterfaceName__", this.midiInterfaceName);
    }

    private String getEndCode(String section) {
        String template = codeTemplates.getJSONObject(section).getString("end");
        return template.replaceAll("__midiInterfaceName__", this.midiInterfaceName);
    } // Hier fällt mir nicht ein wie ich Code-Duplizierung vermeiden könnte, ohne einen Haufen Argumente einzuführen

    private String getCode(CodeGeneratorComponent component, String section) {
        String command = component.getCommand();
        String inputType = component.getInputType();
        String template = codeTemplates.getJSONObject(section).getString(inputType + "-" + command);
        template = template.replaceAll("__midiInterfaceName__", this.midiInterfaceName);
        template = template.replaceAll("__btnName__", component.getInputName());
        template = template.replaceAll("__pinNumber__", component.getPinNumber());
        template = template.replaceAll("__noteAddrName__", component.getNodeAddressName());
        template = template.replaceAll("__channelName__", component.getChannelName());
        template = template.replaceAll("__velocityName__", component.getVelocityName());
        template = template.replaceAll("__velocityValue__", component.getVelocityValue());
        return template;
    }

    private void writeCodeToFile() {
        try (FileWriter codeOutputFileWriter = new FileWriter(codeOutputPath)) {
            writeCodeSection("global", codeOutputFileWriter);
            writeCodeSection("setup", codeOutputFileWriter);
            writeCodeSection("loop", codeOutputFileWriter);
        } catch (IOException e) {
            System.out.println("Gab ein Problem in writeCodeToFile()");
        }
    }
}
