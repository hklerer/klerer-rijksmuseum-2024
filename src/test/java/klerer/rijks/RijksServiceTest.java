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
        ArtObject artObject = service.artObject(
                apiKey.toString(),
                15,
                "25",
                "involved maker"
        ).blockingGet();

        // then
        assertNotEquals(0, artObject.title);
        assertNotEquals(0, artObject.longTitle);
        assertNotEquals(0, artObject.principalOrFirstMaker);
    }

}