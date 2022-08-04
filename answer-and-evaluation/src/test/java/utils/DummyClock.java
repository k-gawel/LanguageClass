package utils;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DummyClock extends Clock {

    public static ZonedDateTime NOW = ZonedDateTime.of(
            2022,
            1,
            1,
            12,
            0,
            0,
            0,
            ZoneId.of(ZoneId.SHORT_IDS.get("ECT"))
    );

    @Override
    public ZoneId getZone() {
        return ZoneId.of(ZoneId.SHORT_IDS.get("ECT"));
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return null;
    }

    @Override
    public Instant instant() {
        return NOW.toInstant();
    }
}
