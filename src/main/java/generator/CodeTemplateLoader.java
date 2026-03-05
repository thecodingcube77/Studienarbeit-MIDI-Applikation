package generator;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CodeTemplateLoader {
    private static CodeTemplateLoader instance = null;
    private JSONObject codeTemplates;
    private final String codeTemplateFilePath = "./resources/config/CodeTemplates.json";
    private CodeTemplateLoader() {
        loadCodeTemplates();
        System.out.println(codeTemplates);
    }

    private void loadCodeTemplates() {
        StringBuilder jsonStringBuilder = new StringBuilder();
        File inputJSONFile = new File(codeTemplateFilePath);
        try (Scanner jsonFileReader = new Scanner(inputJSONFile)) {
            while (jsonFileReader.hasNextLine()) {
                jsonStringBuilder.append(jsonFileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read Midi Commands JSON File at:" + codeTemplateFilePath);
        }
        codeTemplates = new JSONObject(jsonStringBuilder.toString());
    }

    public static CodeTemplateLoader getInstance() {
        if(instance == null) {
            return new CodeTemplateLoader();
        }
        return instance;
    }
}
