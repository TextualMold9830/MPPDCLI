package textualmold9830.cli.commands;


import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.scenes.InterLevelSceneServer;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;
import textualmold9830.cli.CLIPlugin;
import textualmold9830.plugins.PluginUtils;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ScriptCommand implements Command {
    public static List<String> args = new ArrayList<>();
    public static ScriptEngine engine = new NashornScriptEngineFactory().getScriptEngine();

    static {
        Bindings bindings = engine.createBindings();
        bindings.put("Dungeon", Dungeon.class);
        bindings.put("heroes", Dungeon.heroes);
        bindings.put("InterLevelSceneServer", InterLevelSceneServer.class);
        bindings.put("args", args);
        engine.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);
        System.out.println(engine.getBindings(ScriptContext.GLOBAL_SCOPE));
    }

    @Override
    public String name() {
        return "js";
    }

    @Override
    public void run(String[] args) {
        String code;
        if (args[0] != null) {
            try {
                code = Files.readString(Path.of(String.valueOf(PluginUtils.getConfigDirectory(CLIPlugin.instance)), args[0]));

            } catch (IOException e) {
                System.out.println("File not found: " + Path.of(URI.create(String.valueOf(PluginUtils.getConfigDirectory(CLIPlugin.instance))) + "/" + args[0]));
                return;
            }
            try {
                ScriptCommand.args.clear();
                if (args.length > 1) {
                    for (int i = 1; i < args.length; i++) {
                        ScriptCommand.args.add(args[i]);
                    }
                }
                engine.eval(code);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        } else {
            run();
        }
    }

    @Override
    public void run() {
        System.out.println("usage:");
        System.out.println("js <file>");

    }
}
