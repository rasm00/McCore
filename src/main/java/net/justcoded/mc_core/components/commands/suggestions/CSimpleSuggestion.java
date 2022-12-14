package net.justcoded.mc_core.components.commands.suggestions;

import java.util.Arrays;
import java.util.List;

public class CSimpleSuggestion {
    private final List<String> suggestions;

    public CSimpleSuggestion(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    public CSimpleSuggestion(String... suggestion) {
        this(Arrays.asList(suggestion));
    }

    public List<String> getSuggestions() {
        return suggestions;
    }
}
