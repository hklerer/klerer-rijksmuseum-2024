package klerer.rijks;

import klerer.rijks.json.ArtObject;
import klerer.rijks.json.RijksCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RijksServiceTest {
    @Test
    public void pageNum() {
        // given
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        // when
        RijksCollection rijksCollection = service.pageNum(apiKey.toString(), 1).blockingGet();

        // then
        assertNotNull(rijksCollection.rijksCollection[0].title);
        assertNotNull(rijksCollection.rijksCollection[0].longTitle);
        assertNotNull(rijksCollection.rijksCollection[0].principalOrFirstMaker);
        assertNotNull(rijksCollection.rijksCollection[0].webImage.url);
    }

    @Test
    public void queryAndPageNum() {
        // given
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        // when
        RijksCollection rijksCollection = service.queryAndPageNum(apiKey.toString(),
                "query",
                1).blockingGet();

        // then
        assertNotNull(rijksCollection.rijksCollection[0].title);
        assertNotNull(rijksCollection.rijksCollection[0].longTitle);
        assertNotNull(rijksCollection.rijksCollection[0].principalOrFirstMaker);
        assertNotNull(rijksCollection.rijksCollection[0].webImage.url);
    }

    @Test
    public void involvedMakerAndPageNum() {
        // given
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        // when
        RijksCollection rijksCollection = service.involvedMakerAndPageNum(apiKey.toString(),
                "Leonardo Da Vinci",
                1).blockingGet();

        // then
        assertNotNull(rijksCollection.rijksCollection[0].title);
        assertNotNull(rijksCollection.rijksCollection[0].longTitle);
        assertNotNull(rijksCollection.rijksCollection[0].principalOrFirstMaker);
        assertNotNull(rijksCollection.rijksCollection[0].webImage.url);
    }

}