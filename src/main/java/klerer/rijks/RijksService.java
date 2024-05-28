package klerer.rijks;

import io.reactivex.rxjava3.core.Single;
import klerer.rijks.json.ArtObjects;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RijksService {

    @GET("/api/en/collection")
    Single<ArtObjects> pageNum(
            @Query("key") String apikey,
            @Query("p") int pageNum
    );

    @GET("/api/en/collection")
    Single<ArtObjects> queryAndPageNum(
            @Query("key") String apikey,
            @Query("q") String query,
            @Query("p") int pageNum
            );

    @GET("/api/en/collection")
    Single<ArtObjects> involvedMakerAndPageNum(
            @Query("key") String apikey,
            @Query("involvedMaker") String involvedMaker,
            @Query("p") int pageNum
    );



}

