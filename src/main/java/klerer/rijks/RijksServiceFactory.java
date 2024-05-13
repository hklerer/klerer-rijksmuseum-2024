package klerer.rijks;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RijksServiceFactory {
    public RijksServiceFactory getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.rijksmuseum.nl/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return retrofit.create(RijksService.class);
    }
}