package net.justcoded.mc_core.components.cuboids;

import net.justcoded.mc_core.components.collections.CMap;
import org.bukkit.Location;

import java.io.Serializable;
import java.util.UUID;

public class SelectedTerritory extends CMap<UUID, SelectedTerritory.MagicSelect> implements Serializable {

    public static class MagicSelect {

        private Location first;
        private Location second;

        public MagicSelect() {

        }

        public MagicSelect(Location first, Location second) {
            this.first = first;
            this.second = second;
        }

        public Location getFirst() {
            return first;
        }

        public void setFirst(Location first) {
            this.first = first;
        }

        public Location getSecond() {
            return second;
        }

        public void setSecond(Location second) {
            this.second = second;
        }
    }
}

