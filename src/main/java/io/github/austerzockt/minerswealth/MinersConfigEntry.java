package io.github.austerzockt.minerswealth;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class MinersConfigEntry {
    public List<String> worldNames;
    public int money;
    public Material material;
    public MinersConfigEntry(List<String> worldNames) {
        this.worldNames = worldNames;
    }
    public MinersConfigEntry money(int money) {
        this.money = money;
        return this;
    }
    public MinersConfigEntry material(Material material) {
        this.material = material;
        return this;
    }
}
