package data;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by roger on 31/01/2017.
 */

public class HttpClient {

    OkHttpClient client = new OkHttpClient();

    public String getData(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }

}
