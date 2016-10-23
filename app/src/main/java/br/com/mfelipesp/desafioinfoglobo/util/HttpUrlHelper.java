package br.com.mfelipesp.desafioinfoglobo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by roberto.c.campello on 6/4/2014.
 */
public class HttpUrlHelper {
    /**
     * Execute request URL
     *
     * @param urlAddress
     * @return
     */
    public String makeRequest(String urlAddress) {
        HttpURLConnection con = null;
        URL               url = null;
        String       response = null;

        try {
            // Create URL e execute request URL
            url = new URL(urlAddress);
            con = (HttpURLConnection) url.openConnection();

            // Read stream e convert to string
            response = readStream(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        } // end try
        return response;
    }

    /**
     * Read Stream
     *
     * @param in
     * @return
     */
    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        try {
            // Read stream
            reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            } // end while
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } // end try
            } // end if
        } // end try

        // Return string
        return builder.toString();
    }
}
