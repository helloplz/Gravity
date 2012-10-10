package com.gravity.map;

import java.util.List;

import org.newdawn.slick.geom.Shape;

import com.google.common.base.Preconditions;
import com.gravity.gameplay.GravityGameController;
import com.gravity.physics.Collision;
import com.gravity.player.Player;

public final class SpikeEntity extends TileWorldEntity {
    
    private final GravityGameController controller;
    
    public SpikeEntity(GravityGameController controller, Shape shape, TileWorld map) {
        super(shape, map);
        this.controller = controller;
    }
    
    @Override
    public Shape handleCollisions(int ticks, List<Collision> collisions) {
        Preconditions.checkArgument(collisions.size() == 1, "Expected one collision but received " + collisions.size());
        Player p = (Player) collisions.get(0).getOtherEntity(this);
        controller.playerHitSpikes(p);
        return super.handleCollisions(ticks, collisions);
    }
    
}
