package com.github.zly2006;

import com.github.zly2006.Abilities.Disguise;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class MaraxPlayerListener implements Listener {
    final Windtrace plugin;
    public MaraxPlayerListener(Windtrace plugin){
        this.plugin=plugin;
    }
    @EventHandler
    public void onClick(PlayerInteractEntityEvent event){
        event.setCancelled(true);
        // Hunter: Catch
        // Rebel: Disguise
        plugin.playerAbilities.get(event.getPlayer())[0].use();
    }
    @EventHandler
    public void onClick(PlayerInteractEvent event){
        event.setCancelled(true);
        if(event.getItem()==null) {
            switch (event.getAction()) {
                case LEFT_CLICK_AIR:
                case LEFT_CLICK_BLOCK:
                    // Left Click, Catch / Disguise
                    plugin.playerAbilities.get(event.getPlayer())[0].use();
                    break;
                case RIGHT_CLICK_AIR:
                case RIGHT_CLICK_BLOCK:
                    // Right Click, Skill
                    plugin.playerAbilities.get(event.getPlayer())[1].use();
                    break;
                default:
                    break;
            }
        }
        // Burst
        else if(event.getItem().getType() == Material.SLIME_BALL){

        }
    }
    @EventHandler
    public void onFlying(PlayerToggleFlightEvent event){
        if(event.isFlying()){
            Ability ability=plugin.playerAbilities.get(event.getPlayer())[0];
            if(ability instanceof Disguise){
                Disguise disguise=(Disguise) ability;
                disguise.reset();
            }
        }
    }
}
