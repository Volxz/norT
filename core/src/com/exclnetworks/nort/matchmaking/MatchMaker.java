package com.exclnetworks.nort.matchmaking;

import com.badlogic.gdx.math.Vector2;
import com.exclnetworks.nort.NortGame;
import com.exclnetworks.nort.entities.Player;

public class MatchMaker {
    MMSocket mmSocket;
    int playerCount = 0;
    public int myPlayerID = -1;
    public Player[] players = new Player[4];

    public MatchMaker() {
        this.mmSocket = new MMSocket(this);
        this.players[0] = new Player(0);
        this.players[1] = new Player(1);
        this.players[2] = new Player(2);
        this.players[3] = new Player(3);
    }

    public Player getLocalPlayer(){
      return players[myPlayerID];
    }

    public void moveLocalPlayer(Vector2 coords, char direction){
        mmSocket.moveLocalPlayer(coords,direction);
    }


}
