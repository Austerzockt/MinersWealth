package io.github.austerzockt.minerswealth;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

public class BlockBreakListener implements Listener {
    private MinersWealth minersWealth;
    public BlockBreakListener(MinersWealth minersWealth) {
        this.minersWealth = minersWealth;
    }
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer() == null) return;
            minersWealth.getMinersConfigEntryList().forEach(s -> {
                if (!s.worldNames.contains(event.getBlock().getWorld().getName())) return;
                    if (s.material.equals(event.getBlock().getType())) {
                    minersWealth.getVaultHook().getEconomy().depositPlayer(event.getPlayer(), s.money);
                }
            });


    }


}
