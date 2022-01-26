package com.github.zly2006.Abilities;

import com.github.zly2006.Ability;
import com.github.zly2006.BaelGameSession;
import com.github.zly2006.Windtrace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Starstep implements Ability {
    final BaelGameSession session;
    final Windtrace plugin;
    final Player player;
    public Starstep(){
        this(null,null,null);
    }
    public Starstep(BaelGameSession session, Windtrace plugin, Player player){
        this.session = session;
        this.plugin=plugin;
        this.player = player;
    }
    @Override
    public void use() {
        player.setWalkSpeed(1.5f*player.getWalkSpeed());
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setWalkSpeed(player.getWalkSpeed()/1.5f);
            }
        }.runTaskLater(plugin,20*30);// 30s
    }

    @Override
    public Type getType() {
        return Type.Burst;
    }
}
