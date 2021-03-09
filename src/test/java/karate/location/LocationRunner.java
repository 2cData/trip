package karate.location;

import com.intuit.karate.junit5.Karate;

public class LocationRunner {

    @Karate.Test
    Karate testAll() {
        return Karate.run("location").relativeTo(getClass());
    }
}
