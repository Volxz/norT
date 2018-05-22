package com.exclnetworks.nort.matchmaking;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.exclnetworks.nort.NortGame;


public class MMConfig {

    //Note: static dimensions are based on a standing up bike (Long side horizontal)
    private final int BIKE_HEIGHT = 40;
    private final int BIKE_WIDTH = 10;

    //Team names are used to select the correct colored sprite for the players bike
    public final String[] TEAMNAMES = {
            "blue",
            "yellow",
            "green",
            "red"
    };

    //Used to determine trail color
    public final Color[] PLAYER_COLORS = {
            Color.BLUE,
            Color.YELLOW,
            Color.GREEN,
            Color.RED
    };

    public final Vector2[] STARTING_POINTS = {
            //Note: static dimensions are based on a standing up bike (Long side horizontal)

            /* Starting positions are as shown below
             *    2
             * 3     1
             *    0
            */

            new Vector2(NortGame.WIDTH/2 - (BIKE_WIDTH /2), 0), //Bottom
            new Vector2(NortGame.WIDTH, NortGame.HEIGHT/2 - (BIKE_WIDTH/2)), // Right
            new Vector2(NortGame.WIDTH/2 - (BIKE_WIDTH/2), NortGame.HEIGHT), //Top
            new Vector2(-BIKE_HEIGHT, NortGame.HEIGHT/2 - (BIKE_WIDTH/2)) //Left
    };

    public final char[] STARTING_DIRECTIONS = {
            'N',
            'W',
            'S',
            'E'
    };

}
