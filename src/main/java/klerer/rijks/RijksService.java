package klerer.rijks;

import io.reactivex.rxjava3.core.Single;
import klerer.rijks.json.ArtObject;
import klerer.rijks.json.RijksCollection;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RijksService {

    @GET("/api/nl/collection")
    Single<RijksCollection> pageNum(
            @Query("key") String apikey,
            @Query("p") int pageNum
    );

    @GET("/api/nl/collection")
    Single<RijksCollection> queryAndPageNum(
            @Query("key") String apikey,
            @Query("q") String query,
            @Query("p") int pageNum
            );

    @GET("/api/nl/collection")
    Single<RijksCollection> involvedMakerAndPageNum(
            @Query("key") String apikey,
            @Query("involvedMaker") String involvedMaker,
            @Query("p") int pageNum
    );



}

