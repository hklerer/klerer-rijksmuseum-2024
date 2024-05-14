package klerer.rijks;

import io.reactivex.rxjava3.core.Single;
import klerer.rijks.json.ArtObject;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RijksService {

    @GET("/api/nl/collection")
    Single<ArtObject> artObject(
            @Query("key") String apikey
    );

    @GET("/api/nl/collection")
    Single<ArtObject> artObject(
            @Query("p") int pageNum
    );

    @GET("/api/nl/collection")
    Single<ArtObject> artObject(
            @Query("q") String term,
            @Query("involvedMaker") String involvedMaker
    );



}

