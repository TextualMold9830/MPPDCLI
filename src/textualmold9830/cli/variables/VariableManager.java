package textualmold9830.cli.variables;

import java.util.HashMap;

public class VariableManager {
    private static HashMap<String, Variable> variables = new HashMap<>();
    public static String getValue(String variableName){
        Variable var = variables.get(variableName);
        if (var == null){
            System.out.println("Variable " + variableName +" is null");
            return null;
        }
        return variables.get(variableName).getValue();
    }
    public static void registerVariable(String name, Variable variable){
        variables.put(name, variable);
        System.out.println("Registered variable with name: " + name);
    }
    public static void removeVariable(String variableName) {
        variables.put(variableName, null);
    }
}
