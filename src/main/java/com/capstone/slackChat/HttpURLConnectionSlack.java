package com.capstone.slackChat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Chan on 10/16/2016.
 */
public class HttpURLConnectionSlack {

    static final String HTTP_URL_CONNECTION_ERROR = "ERROR";

    //Token is used for slack authentication, manually generated online
    static final String token = "insert your token";
    static final String url = "https://slack.com/api/";


    //Method used for sending Http Post method
    public static String doPost(String function, String arg[]){

        StringBuffer response = new StringBuffer();

        try {
            URL obj = new URL (url + function);
            java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();

            //add request header
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            StringBuffer urlParameters = new StringBuffer();
            urlParameters.append("token=");
            urlParameters.append(token);
            if( arg != null){
                for( int i = 0; i < arg.length; i++){
                    urlParameters.append("&");
                    urlParameters.append(arg[i]);
                    urlParameters.append("=");
                    urlParameters.append(arg[++i]);
                }
            }
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters.toString());
            wr.flush();
            wr.close();


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //Print out response
            System.out.println("-------------All Records-----------------");
            System.out.println(response.toString());
            System.out.println("---------------------------------------------------------");


        } catch (ProtocolException e) {
            e.printStackTrace();
            return HTTP_URL_CONNECTION_ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return HTTP_URL_CONNECTION_ERROR;
        }
        return response.toString();
    }
}
