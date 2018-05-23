package com.exclnetworks.nort.matchmaking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.exclnetworks.nort.entities.Player;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;


public class MMSocket {

    Socket socket;
    String localSocketID;

    MatchMaker matchMaker;
    MMSocket(MatchMaker matchMaker){
        this.matchMaker = matchMaker;
        initSocket();
        configSocketEvents();
    }


    private void initSocket() {
    try {
        socket = IO.socket("http://localhost:8080");
        socket.connect();
    } catch (Exception e) {
        System.out.println("Socket error occurred");
        System.out.println(e);
    }
}

    private void configSocketEvents() {
        socket.on(Socket.EVENT_CONNECT, (args) -> {
            Gdx.app.log("SocketIO", "Connected");
        }).on("socketID", (args) -> {
            JSONObject data = (JSONObject) args[0];
            localSocketID = getJSONString(data,"id");
            socket.emit("requestPlayerID");
        }).on("playerJoin", (args) -> {
            JSONObject data = (JSONObject) args[0];
            int playerNumber = getJSONInt(data,"playerID");
            if(getJSONString(data,"socketID").equals(localSocketID)){
                matchMaker.myPlayerID = playerNumber;
                Gdx.app.log("MatchMaking", "Local player appeared on the server");
            }
            matchMaker.players[playerNumber].setOnline(true);
            matchMaker.playerCount++;
            Gdx.app.log("MatchMaking", "Player #" + playerNumber + " Joined the server");
        }).on("playerMove", (args) -> {
            JSONObject data = (JSONObject) args[0];
            int playerID = getJSONInt(data, "playerID");
            if(playerID != matchMaker.myPlayerID){
                int x = getJSONInt(data, "x");
                int y = getJSONInt(data,"y");
                char direction = getJSONString(data,"direction").charAt(0);
                matchMaker.players[playerID].forceMove(new Vector2(x,y), direction);
            }
        }).on("death", (args) -> {
            JSONObject data = (JSONObject) args[0];
            int playerID = getJSONInt(data, "playerID");
            matchMaker.players[playerID].kill();
        });

    }

    public void moveLocalPlayer(Vector2 coords, char direction){
        JSONObject moveObj = new JSONObject();
        try {
            moveObj.put("x",coords.x);
            moveObj.put("y", coords.y);
            moveObj.put("direction", direction + "");
        } catch (JSONException e) {
            System.out.println(e);
        }
        socket.emit("playerMove", moveObj);
    }

    private String getJSONString(JSONObject o, String key){
        try {
            return o.getString(key);
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error parsing json, no key \"" + key +"\" provided");
        }
        return null;
    }

    private int getJSONInt(JSONObject o, String key){
        try {
            return o.getInt(key);
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error parsing json, no key \"" + key +"\" provided");
        }
        return -1;
    }



}
