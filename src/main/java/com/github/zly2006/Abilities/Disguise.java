package com.github.zly2006.Abilities;

import com.github.zly2006.Ability;
import com.github.zly2006.BaelGameSession;
import com.github.zly2006.Windtrace;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Disguise implements Ability {
    final BaelGameSession session;
    final Windtrace plugin;
    final Player player;
    int index=0;
    Mob bindEntity;
    BukkitTask bukkitTask;

    public Disguise(BaelGameSession session, Windtrace plugin, Player player){
        this.session = session;
        this.plugin=plugin;
        this.player = player;
    }

    @Override
    public void use() {
        if(bukkitTask!=null){
            bukkitTask.cancel();
        }
        if(bindEntity!=null){
            bindEntity.setHealth(0);
        }
        Class<? extends Mob> target;
        switch (index){
            case 1:
                target=Cat.class;
                break;
            case 2:
                target=Cow.class;
                break;
            default:
                index=0;
                target=Sheep.class;
                break;
        }
        index++;
        bindEntity = player.getLocation().getWorld().spawn(player.getLocation(),target);
        bindEntity.setAware(false);
        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                bindEntity.teleport(player);
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    public void reset(){
        bindEntity.setHealth(0);
        bukkitTask.cancel();
        index=0;
        bindEntity=null;
        bukkitTask=null;
    }
    @Override
    public Type getType() {
        return Type.Normal;
    }
}
