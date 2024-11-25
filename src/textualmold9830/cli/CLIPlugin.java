package textualmold9830.cli;

import textualmold9830.plugins.Plugin;
import textualmold9830.plugins.PluginUtils;

public class CLIPlugin implements Plugin {
    public static Plugin instance;

    @Override
    public String getName() {
        return "MPPDCLI";
    }
    
    @Override
    public void initialize() {
        instance = this;
        CommandManager.initCommands();
        parseCommands();
        new Thread(new ScanThread()).start();
    }
    private void parseCommands(){
        String[] commands = PluginUtils.getConfigData(this).split("\n");
        for (int i = 0; i < commands.length; i++) {
            if (!commands[i].isBlank()){

                CommandManager.executeFromString(commands[i]);
            }
        }

    }

    @Override
    public String defaultConfig() {
        return "";
    }
}