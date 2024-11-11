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
        new Thread(new ScanThread()).start();
    }
}