package klerer.rijks;

import klerer.rijks.json.ArtObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RijksServiceTest {
    @Test
    public void artObject() {
        // given
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        // when
        ArtObject artObject = service.artObject(apiKey.toString()).blockingGet();

        // then
        assertNotNull(artObject.title);
        assertNotNull(artObject.longTitle);
        assertNotNull(artObject.principalOrFirstMaker);
    }

}