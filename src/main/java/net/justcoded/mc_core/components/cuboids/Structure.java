package net.justcoded.mc_core.components.cuboids;

import net.justcoded.mc_core.components.tasks.AsyncTask;
import net.justcoded.mc_core.components.tasks.Task;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public class Structure {

    private JavaPlugin main;
    private final Territory territory;
    private Material[][][] schema;
    private Task<JavaPlugin> copyTask;
    private Task<JavaPlugin> loadTask;
    private Task<JavaPlugin> removeBlockTask;

    public Structure(JavaPlugin main, Territory territory) {
        this.main = main;
        this.territory = territory;
    }

    public void saveToSchema() {
        this.copyTask = new AsyncTask<>(main).createTask(() -> {
            int sizeX = territory.getMax().getX() + 1 - territory.getMin().getX();
            int sizeY = territory.getMax().getY() + 1 - territory.getMin().getY();
            int sizeZ = territory.getMax().getZ() + 1 - territory.getMin().getZ();

            schema = new Material[sizeX][sizeY][sizeZ];

            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    for (int k = 0; k < sizeZ; k++) {
                        int a = territory.getMin().getX()+i;
                        int b = territory.getMin().getY()+j;
                        int c = territory.getMin().getZ()+k;

                        schema[i][j][k] = (new Location(
                                territory.getMax().getLocation().getWorld(), a, b, c)
                                .getBlock().getType());
                    }
                }
            }
        });
        this.copyTask.runTask();
    }

    public void loadSchema() {
        this.loadTask = new AsyncTask<>(main).createTask(() -> {
            int sizeX = territory.getMax().getX() + 1 - territory.getMin().getX();
            int sizeY = territory.getMax().getY() + 1 - territory.getMin().getY();
            int sizeZ = territory.getMax().getZ() + 1 - territory.getMin().getZ();

            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    for (int k = 0; k < sizeZ; k++) {
                        int a = territory.getMin().getX()+i;
                        int b = territory.getMin().getY()+j;
                        int c = territory.getMin().getZ()+k;

                        new Location(territory.getMax().getLocation().getWorld(), a, b, c).getBlock().setType(schema[i][j][k]);
                    }
                }
            }
        });
        this.loadTask.runTask();
    }

    public void removeBlock(Material material) {
        this.removeBlockTask = new AsyncTask<>(main).createTask(() -> {
            int sizeX = territory.getMax().getX() + 1 - territory.getMin().getX();
            int sizeY = territory.getMax().getY() + 1 - territory.getMin().getY();
            int sizeZ = territory.getMax().getZ() + 1 - territory.getMin().getZ();

            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    for (int k = 0; k < sizeZ; k++) {
                        int a = territory.getMin().getX() + i;
                        int b = territory.getMin().getY() + j;
                        int c = territory.getMin().getZ() + k;

                        Block block = new Location(territory.getMax().getLocation().getWorld(), a, b, c).getBlock();
                        Material type = block.getType();
                        if (type.equals(material)) {
                            block.setType(Material.AIR);
                        }
                    }
                }
            }
        });
        this.removeBlockTask.runTask();
    }

    private void cancelCopyTask() {
        this.copyTask.stopTask();
    }

    private void cancelPasteTask() {
        this.loadTask.stopTask();
    }

    private void cancelRemoveBlockTask() {
        this.removeBlockTask.stopTask();
    }
}
