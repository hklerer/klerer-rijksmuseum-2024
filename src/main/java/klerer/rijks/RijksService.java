package klerer.rijks;

import io.reactivex.rxjava3.core.Single;
import klerer.rijks.json.ArtObject;
=import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RijksService {

    @GET("/api/nl/collection")
    Single<ArtObject> artObject(
            @Query("key") String apikey,
            @Query("p") int pageNum,
            @Query("q") String num,
            @Query("involvedMaker") String involvedMaker
    );
}

