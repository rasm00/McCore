package net.justcoded.mc_core.components.commands.arguments;

import net.justcoded.mc_core.components.collections.CLinkedMap;

public record CCommandArgument(
        CLinkedMap<String, CCommandArg> args,
        String[] strings,
        int startPosition) {

    public CCommandArg getArg(String name) {
        return args.getOrDefault(name, null);
    }

    public Object get(String name, int arg) {
        return getArg(name).getObject(strings[arg]);
    }

    public int getLength() {
        return strings.length;
    }

    public Object get(String name) {
        int i = 0;
        for (String s : args.getKeys()) {
            if (s.equals(name)) {
                if (startPosition == -1) {
                    return get(name, i);
                }
                return get(name, i + 1);
            }
            i++;
        }
        return name;
    }
}
