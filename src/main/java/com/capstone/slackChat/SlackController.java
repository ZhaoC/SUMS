package com.capstone.slackChat;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 * Created by Chan on 11/6/2016.
 */
public class SlackController {

    static final String DO_NOT_FOUND_ERROR = "DO NOT FOUND";

    //Method used for creating a private group with name
    public static String createGroup (String name){
        return HttpURLConnectionSlack.doPost("groups.create", new String[]{"name", name});
    }

    //Method used for creating a public channel with name
    public static String createChannel (String name){
        return HttpURLConnectionSlack.doPost("channels.create", new String[]{"name", name});
    }

    //Method used for inviting new user to team by email
    public static String inviteUserByEmail (String email) {
        return HttpURLConnectionSlack.doPost("users.admin.invite", new String[]{"email", email});
    }

    //Method used for inviting a user to a public channel
    public static String inviteUserToChannel (String channelId, String userId) {
        return HttpURLConnectionSlack.doPost("channels.invite", new String[]{"channel", channelId, "user", userId});
    }

    //Method used for inviting a user to a private group
    public static String inviteUserToGroup (String groupId, String userId) {
        return HttpURLConnectionSlack.doPost("groups.invite", new String[]{"channel", groupId, "user", userId});
    }

    //Method used for retrieving all users
    public static String getAllUsers (){
        return HttpURLConnectionSlack.doPost("users.list", null);
    }

    //Method used for retrieving all channels
    public static String getAllChannels (){
        return HttpURLConnectionSlack.doPost("channels.list", null);
    }

    //Method used for retrieving all groups
    public static String getAllGroups (){
        return HttpURLConnectionSlack.doPost("groups.list", null);
    }

    //Method used for retrieving user info by user name
    public static String getUserByName(String name){
        JSONObject records = new JSONObject(getAllUsers());
        JSONArray members = records.getJSONArray("members");

        for ( int i = 0; i < members.length(); i++){
            JSONObject user = members.getJSONObject(i);
            String userName = user.getString("name");
            if( userName.equals(name)){
                System.out.println("-----------User Found-----------------");
                System.out.println(user.toString());
                System.out.println("-----------ID-----------------" + user.getString("id"));
                return user.toString();
            }
        }
        return DO_NOT_FOUND_ERROR;
    }

    //Method used for retrieving user info by user email
    public static String getUserByEmail(String email){
        JSONObject records = new JSONObject(getAllUsers());
        JSONArray members = records.getJSONArray("members");

        for ( int i = 0; i < members.length() ; i++){
            JSONObject user = members.getJSONObject(i);
            JSONObject profile = user.getJSONObject("profile");
            if( !profile.getString("first_name").equalsIgnoreCase("slackbot")){
                String userEmail = profile.getString("email");
                if( userEmail.equalsIgnoreCase(email)){
                    System.out.println("-----------User Found-----------------");
                    System.out.println(user.toString());
                    System.out.println("-----------ID-----------------" + user.getString("id"));
                    return user.toString();
                }
            }
        }
        return DO_NOT_FOUND_ERROR;
    }

    //Method used for retrieving a channel info by channel name
    public static String getChannelByName(String name){
        JSONObject records = new JSONObject(getAllChannels());
        JSONArray channels = records.getJSONArray("channels");

        name = name.replace(" ", "-");

        for ( int i = 0; i < channels.length(); i++){
            JSONObject chan = channels.getJSONObject(i);
            String channelName = chan.getString("name");
            if( channelName.equalsIgnoreCase(name)){
                System.out.println("-----------Channel Found-----------------");
                System.out.println(chan.toString());
                System.out.println("-----------ID-----------------" + chan.getString("id"));
                return chan.toString();
            }
        }
        return DO_NOT_FOUND_ERROR;
    }

    //Method used for retrieving a group info by group name
    public static String getGroupByName(String name){
        JSONObject records = new JSONObject(getAllGroups());
        JSONArray groups = records.getJSONArray("groups");

        name = name.replace(" ", "-");

        for ( int i = 0; i < groups.length(); i++){
            JSONObject group = groups.getJSONObject(i);
            String groupName = group.getString("name");
            if( groupName.equalsIgnoreCase(name)){
                System.out.println("-----------Group Found-----------------");
                System.out.println(group.toString());
                System.out.println("-----------ID-----------------" + group.getString("id"));
                return group.toString();
            }
        }
        return DO_NOT_FOUND_ERROR;
    }



}
