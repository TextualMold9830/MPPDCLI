package textualmold9830.cli.commands;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.items.Item;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import textualmold9830.cli.util.StringToInt;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ItemCommand implements Command {
    public static HashMap<String, String> classMap = new HashMap<>();
    static {
        ClassGraph graph = new ClassGraph();
        graph.enableClassInfo();
        graph.verbose(false);
        ScanResult result = graph.scan();
        for(ClassInfo info : result.getAllClasses()){
            if (info.extendsSuperclass(Item.class)){
                classMap.put(info.getSimpleName(), info.getName());
            }
        }
        result.close();
    }
    @Override
    public String name() {
        return "item";
    }

    @Override
    public void run() {
        System.out.println("item <item> <pos>");
    }

    @Override
    public void run(String[] args) {
        String itemClass;
        int pos;
        if (args[0] != null) {
            itemClass = classMap.get(args[0]);
            if (itemClass == null) {
                itemClass = args[0];
            }
        } else {
            System.out.println("an item type is needed");
            return;
        }
        if (args[1] != null && StringToInt.isInt(args[1])){
            pos = Integer.parseInt(args[1]);
        } else {
            System.out.println("Invalid position");
            return;
        }
        Item item;
        try {
            Class<? extends Item> clazz = (Class<? extends Item>) Class.forName(itemClass);
            item = clazz.getDeclaredConstructor().newInstance();
            if (StringToInt.isInt(args[2])){
                item.upgrade(Integer.parseInt(args[2]));
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            System.out.println("Invalid item class name");
            return;
        }
        Dungeon.level.drop(item, pos);
    }
}
