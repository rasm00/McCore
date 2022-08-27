package net.justcoded.mc_core.utilities;

import net.justcoded.mc_core.annotations.ConfigItem;
import net.justcoded.mc_core.annotations.ConfigPath;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Injector {

    public static List<Field> getConfigFields(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(s -> s.isAnnotationPresent(ConfigPath.class)).collect(Collectors.toList());
    }

    public static List<Field> getItemFields(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(s -> s.isAnnotationPresent(ConfigItem.class)).collect(Collectors.toList());
    }

}
