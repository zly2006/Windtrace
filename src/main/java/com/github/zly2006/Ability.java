package com.github.zly2006;

import org.bukkit.entity.Player;

public interface Ability {
    public enum Type{
        Normal,
        Skill,
        Burst
    }
    public void use();
    public Type getType();
}
