package net.justcoded.mc_core.components.commands.suggestions;

import net.justcoded.mc_core.components.collections.CMap;

public class StaticSuggestions {
    private static final CMap<SUGGESTION_TYPE, ISuggestion> map = new CMap<>();

    public static void add(SUGGESTION_TYPE type, ISuggestion suggestion) {
        map.set(type, suggestion);
    }

    public static CSimpleSuggestion get(SUGGESTION_TYPE type) {
        if (map.getKeys().size() == 0) {
            load();
        }
        if (map.containsKey(type)) {
            return new CSimpleSuggestion(map.getMap().get(type).getSuggestion());
        }
        return new CSimpleSuggestion("");
    }

    public static void load() {
        add(SUGGESTION_TYPE.ENCHANTS, new EnchantSuggestion());
        add(SUGGESTION_TYPE.ONLINE_PLAYERS, new OnlinePlayerSuggestion());
        add(SUGGESTION_TYPE.SOUND, new SoundSuggestion());
        add(SUGGESTION_TYPE.SOUND_CATEGORY, new SoundCategorySuggestion());
        add(SUGGESTION_TYPE.MATERIAL, new MaterialSuggestion());
    }

    public enum SUGGESTION_TYPE {
        ENCHANTS, ONLINE_PLAYERS, SOUND, SOUND_CATEGORY, MATERIAL
    }
}
