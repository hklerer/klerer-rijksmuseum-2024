package klerer.rijks;

import com.andrewoid.ApiKey;
import klerer.rijks.json.RijksCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RijksServiceTest {
    @Test
    public void pageNum() {
        // given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijksService service = new RijksServiceFactory().getService();

        // when
        RijksCollection rijksCollection = service.pageNum(keyString, 1).blockingGet();

        // then
        assertNotNull(rijksCollection.artObjects[0].title);
        assertNotNull(rijksCollection.artObjects[0].longTitle);
        assertNotNull(rijksCollection.artObjects[0].principalOrFirstMaker);
        assertNotNull(rijksCollection.artObjects[0].webImage.url);
    }

    @Test
    public void queryAndPageNum() {
        // given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijksService service = new RijksServiceFactory().getService();

        // when
        RijksCollection rijksCollection = service.queryAndPageNum(keyString,
                "query",
                1).blockingGet();

        // then
        assertNotNull(rijksCollection.artObjects[0].title);
        assertNotNull(rijksCollection.artObjects[0].longTitle);
        assertNotNull(rijksCollection.artObjects[0].principalOrFirstMaker);
        assertNotNull(rijksCollection.artObjects[0].webImage.url);
    }

    @Test
    public void involvedMakerAndPageNum() {
        // given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijksService service = new RijksServiceFactory().getService();

        // when
        RijksCollection rijksCollection = service.involvedMakerAndPageNum(keyString,
                "Leonardo Da Vinci",
                1).blockingGet();

        // then
        assertNotNull(rijksCollection.artObjects[0].title);
        assertNotNull(rijksCollection.artObjects[0].longTitle);
        assertNotNull(rijksCollection.artObjects[0].principalOrFirstMaker);
        assertNotNull(rijksCollection.artObjects[0].webImage.url);
    }

}