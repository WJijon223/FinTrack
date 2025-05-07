package com.fintrack;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiService {

    public static String sendPostRequest(String urlString, String jsonInput) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        // Send request body
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int status = conn.getResponseCode();

        InputStream inputStream = (status < 400) ? conn.getInputStream() : conn.getErrorStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // If status is error, throw exception with message
            if (status >= 400) {
                throw new IOException(response.toString());
            }

            return response.toString();
        } finally {
            conn.disconnect();
        }
    }
}
