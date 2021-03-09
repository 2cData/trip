package karate.version;

import com.intuit.karate.junit5.Karate;

//TODO this only runs from IDE and command line when I start the application first
class VersionRunner {

    @Karate.Test
    Karate testAll() {
        return Karate.run("version").relativeTo(getClass());
    }
}
