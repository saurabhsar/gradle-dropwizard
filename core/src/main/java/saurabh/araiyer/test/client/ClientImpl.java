package saurabh.araiyer.test.client;

import com.fasterxml.jackson.databind.JsonNode;
import saurabh.araiyer.test.model.ServerResponse;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


public class ClientImpl {

    static Random random = new Random();

    public static ServerResponse getResponse(String url, JsonNode payload, int timeout) {
        ServerResponse serverResponse = new ServerResponse();
        try {
            URL urlToHit = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlToHit.openConnection();
            con.setRequestMethod("PUT");
            con.setConnectTimeout(timeout);
            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(payload.toString());
            serverResponse.setResponseStatus(con.getResponseCode() == 200 ? ServerResponse.ResponseStatus.SUCCESS :
                    ServerResponse.ResponseStatus.FAIL);
        } catch (Exception e) {
            serverResponse.setResponseStatus(ServerResponse.ResponseStatus.FAIL);
        }

        return serverResponse;
    }

    private static ServerResponse createDummyServerResponse() {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setResponse("");
        serverResponse.setStatus(random.nextInt() % 2 == 0/*even*/ ? 200 : 400 );
        return serverResponse;
    }

    private static ServerResponse createServerResponse(HttpURLConnection con) throws IOException {
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setResponse(readInput(con));
        serverResponse.setStatus(readResponse(con));
        return serverResponse;
    }

    private static String readInput(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }

    private static int readResponse(HttpURLConnection httpURLConnection) throws IOException {
        return httpURLConnection.getResponseCode();
    }
}