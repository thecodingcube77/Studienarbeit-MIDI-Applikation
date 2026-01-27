// initialisiert die Auswahlmöglichkeiten der Dropdown-Felder

package configurator.model;

import java.util.*;

public class DropdownModel {
    private final Map<String, String[]> functions =
            Map.of("Knopf", new String[]{"Note On", "Note Off", "Polyphonic Aftertouch", "Aftertouch", "Pitch Bend Change"});
    // muss der String[] bei den parameters mit diesen Werten gefüllt werden? Wie kommen hier die "Kategorien" wie Velocity rein?
    private final Map<String, String[]> parameters = new HashMap<>();
    {
        parameters.put("Note On", new String[]{"leer", "leer"});
        parameters.put("Note Off", new String[]{"leer", "leer"});
        parameters.put("Polyphonic Aftertouch", new String[]{"leer", "leer"});
        parameters.put("Aftertouch", new String[]{"leer", null});
        parameters.put("Pitch Bend Change", new String[]{"leer", "leer"});
    }
    private final Map<String, String[]> parameterNames = new HashMap<>();
    {
        parameterNames.put("Note On", new String[]{"Number", "Velocity"});
        parameterNames.put("Note Off", new String[]{"Number", "Velocity"});
        parameterNames.put("Polyphonic Aftertouch", new String[]{"Number", "Pressure"});
        parameterNames.put("Aftertouch", new String[]{"Pressure", null});
        parameterNames.put("Pitch Bend Change", new String[]{"LSB", "MSB"});
    }

    public String[] getElement() {
        return functions.keySet().toArray(new String[0]);
    }
    public String[] getFunctions(String function) {
        return functions.getOrDefault(function, new String[]{});
    }
    public String[] getParameters(String parameter) {
        if (parameter == null)
            return new String[0];
        return parameters.getOrDefault(parameter, new String[]{});
    }
    public String[] getParameterNames(String parameter) {
        if (parameter == null)
            return new String[0];
        return parameterNames.getOrDefault(parameter, new String[]{});
    }

}
