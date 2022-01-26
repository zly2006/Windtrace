package com.github.zly2006;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Windtrace extends JavaPlugin {

    HashMap<Player, Ability[]> playerAbilities=new HashMap<>();
    List<Player> queuingPlayers=new ArrayList<>();

    @Override
    public void onLoad(){
        if(getServer().getWorld("Mondstadt")==null){
            getServer().getWorlds().add(Bukkit.createWorld(new WorldCreator("Mondstadt")));
       }
        if(getServer().getWorld("Liyue")==null){
            getServer().getWorlds().add(Bukkit.createWorld(new WorldCreator("Liyue")));
       }
        if(getServer().getWorld("Mondstadt")==null){
            getServer().getWorlds().add(Bukkit.createWorld(new WorldCreator("Mondstadt")));
       }
    }
    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new MaraxPlayerListener(this),this);
        getCommand("windtrace").setExecutor(new BabartosCommandExecutor(this));
    }

}
