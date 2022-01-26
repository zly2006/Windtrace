package com.github.zly2006;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BaelGameSession {
    public final List<Player> players;
    public final Player hunter;
    public final Windtrace plugin;
    BukkitTask myTask;
    int stage=0;

    public BaelGameSession(Windtrace plugin, List<Player> players) {
        this(plugin, players, new Random());
    }

    public BaelGameSession(Windtrace plugin, List<Player> players, Random random){
        this.players=players;
        hunter = players.get(random.nextInt()%players.size());
        this.plugin=plugin;
    }
    

    public void Start(){
        myTask=new BukkitRunnable() {
            List<Player>forceStartPlayers=new ArrayList<>();
            int rest=0;
            @Override
            public void run() {
                if(rest==0){
                    switch (stage) {
                        default:
                            for (Player player : players) {
                                player.sendMessage("游戏结束！正在返回游戏大厅...");
                                player.teleport(new Location(plugin.getServer().getWorld("world"),2,2,2));
                            }
                            break;
                        case 0:
                            rest=60;
                            break;
                        case 1:
                            rest=5;
                            break;
                        case 2:
                            rest=30;

                            break;
                        case 3:
                            rest=3;
                            break;
                        case 4:
                            hunter.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,210*20,1,false,false,false));
                            rest=210;
                            break;
                    }
                    stage=stage+1;
                }
                switch (stage){
                    case 1:
                        if(forceStartPlayers.size()==4){
                            rest=0;
                        }
                        for(Player player:players){
                            player.resetTitle();
                            player.sendTitle(null,String.format("游戏将在%d秒后开始",rest),0,20,0);
                        }
                        break;
                    case 2:// Wait
                    case 4:// for players
                        for(Player player:players){
                            player.resetTitle();
                            player.sendTitle(String.format("%d",rest),null,0,20,0);
                        }
                        break;
                    case 3:
                        for(Player player:players){
                            player.resetTitle();
                            player.sendTitle("躲藏",String.valueOf(rest) ,0,20,0);
                        }
                        break;
                    case 5:
                        if(rest==70){
                            for(Player player:players){
                                player.sendMessage("");
                            }
                            //Favor
                        }
                        break;
                }
                rest--;
            }
        }.runTaskTimer(plugin,0,1);
    }

    public void SelectSkill(Player player, int index, Class<? extends Ability> skill){
        try {
            if(stage!=1){
                player.sendMessage("Error: 超出允许设定的时间");
                return;
            }
            if((skill.newInstance().getType())!=Ability.Type.values()[index]){
                player.sendMessage("Error: 索引无效");
                return;
            }
            plugin.playerAbilities.get(player)[index]=skill.getConstructor(BaelGameSession.class,Windtrace.class,Player.class).newInstance(this,plugin,player);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
