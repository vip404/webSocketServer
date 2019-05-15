package com.bwck.mytest.control;

import android.util.Log;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName ServerManager
 * @Description TODO
 * @Author 14133
 * @Date 2019/5/15 13:50
 * @Version 1.0
 **/
public class ServerManager {
    private ServerSocket serverSocket=null;
    private Map<WebSocket, String> userMap=new HashMap<WebSocket, String>();

    public ServerManager(){

    }

    public void UserLogin(String userName,WebSocket socket){
        if (userName!=null||socket!=null) {
            userMap.put(socket, userName);
            Log.i("ServerManager","LOGIN:"+userName);
            SendMessageToAll(userName+"...Login...");
        }
    }

    public void UserLeave(WebSocket socket){
        if (userMap.containsKey(socket)) {
            String userName=userMap.get(socket);
            Log.i("ServerManager","Leave:"+userName);
            userMap.remove(socket);
            SendMessageToAll(userName+"...Leave...");
        }
    }

    public void SendMessageToUser(WebSocket socket,String message){
        if (socket!=null) {
            socket.send(message);
        }
    }

    public void SendMessageToUser(String userName,String message){
        Set<WebSocket> ketSet=userMap.keySet();
        for(WebSocket socket : ketSet){
            String name=userMap.get(socket);
            if (name!=null) {
                if (name.equals(userName)) {
                    socket.send(message);
                    break;
                }
            }
        }
    }

    public void SendMessageToAll(String message){
        Set<WebSocket> ketSet=userMap.keySet();
        for(WebSocket socket : ketSet){
            String name=userMap.get(socket);
            if (name!=null) {
                socket.send(message);
            }
        }
    }

    public boolean Start(int port){

        if (port<0) {
            Log.i("ServerManager","Port error...");
            return false;
        }

        Log.i("TAG","Start ServerSocket...");

        WebSocketImpl.DEBUG=false;
        try {
            serverSocket=new ServerSocket(this,port);
            serverSocket.start();
            Log.i("ServerManager","Start ServerSocket Success...");
            return true;
        } catch (Exception e) {
            Log.i("ServerManager","Start Failed...");
            e.printStackTrace();
            return false;
        }
    }

    public boolean Stop(){
        try {
            serverSocket.stop();
            Log.i("ServerManager","Stop ServerSocket Success...");
            return true;
        } catch (Exception e) {
            Log.i("ServerManager","Stop ServerSocket Failed...");
            e.printStackTrace();
            return false;
        }
    }
}
