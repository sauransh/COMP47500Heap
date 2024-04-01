package com.stockX.stockmarket;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AlphaVantageApidata implements RealTimeDataProvider {
    private static final String API_KEY;
    private static final String ALPHA_VANTAGE_URL = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=%s&apikey=%s";

    static {
        API_KEY = "3QO2NT38S2J94IF7";
    }

    @Override
    public Stock fetchRealTimeStockData(String symbol) throws IOException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format(ALPHA_VANTAGE_URL, symbol, API_KEY)))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseResponse(response.body());
        } catch (InterruptedException e) {
            throw new IOException("Interrupted while fetching data from API", e);
        }
    }

    private Stock parseResponse(String responseBody) {
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonObject globalQuote = jsonObject.getAsJsonObject("Global Quote");
    
        String symbol = globalQuote.has("01. symbol") ? globalQuote.get("01. symbol").getAsString() : "N/A";
        double price = globalQuote.has("05. price") ? parseDoubleOrNull(globalQuote.get("05. price")) : 0.0;
        long volume = globalQuote.has("06. volume") ? parseLongOrNull(globalQuote.get("06. volume")) : 0;
        double changePercentage = globalQuote.has("10. change percent") ? parseDoubleOrNull(globalQuote.get("10. change percent")) : 0.0;
    
        return new Stock(symbol, price, volume, changePercentage);
    }
    
    
    
    private Double parseDoubleOrNull(JsonElement element) {
        return (element != null && !element.isJsonNull()) ? Double.parseDouble(element.getAsString().replaceAll("%", "")) : null;
    }
    
    private Long parseLongOrNull(JsonElement element) {
        return (element != null && !element.isJsonNull()) ? Long.parseLong(element.getAsString()) : null;
    }
}
