package com.github.zly2006;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BabartosCommandExecutor implements CommandExecutor, TabCompleter {
    final Windtrace plugin;

    public BabartosCommandExecutor(Windtrace plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length==0 || args[0].equals("?") || args[0].equalsIgnoreCase("help")){
            if(commandSender.isOp()){
                ShowHelpOp(commandSender, 0);
            }
            else{
                ShowHelp(commandSender, 0);
            }
            return true;
        }
        else{
            switch (args[0].toLowerCase()){
                case "list":
                    break;
            }
            if(!(commandSender instanceof Player)){
                commandSender.sendMessage("This command can only be executed by players.");
                return true;
            }
            switch (args[0].toLowerCase()){

            }
        }
        return false;
    }
    public void ShowHelp(CommandSender sender, int page){

    }
    public void ShowHelpOp(CommandSender sender, int page){

    }
    public void ListSessions(CommandSender sender, int page){

    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String>ret=new ArrayList<>();
        if(args.length==0){
            ret.add("help");
            ret.add("queue");
            if(commandSender.isOp()){
                ret.add("list");
                ret.add("startnew");
                ret.add("stopall");
                ret.add("board");

            }
            return ret;
        }
        return null;
    }
}
