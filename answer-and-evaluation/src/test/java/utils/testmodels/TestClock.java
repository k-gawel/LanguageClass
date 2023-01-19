package utils.testmodels;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestClock extends Clock {

    public static TestClock INSTANCE() {
        return new TestClock();
    }

    @Override
    public ZoneId getZone() {
        return ZoneId.of("UTC");
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return Clock.system(zone);
    }

    @Override
    public Instant instant() {
        return Instant.ofEpochMilli(100_000L);
    }
    
    public LocalDateTime now() {
        return LocalDateTime.now(this);
    }

}
