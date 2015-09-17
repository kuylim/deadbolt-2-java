package be.objectify.deadbolt.java;

import play.libs.F;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class ConfigKeys
{
    public static final String DEFAULT_HANDLER_KEY = "defaultHandler";
    public static final String CACHE_DEADBOLT_USER = "deadbolt.java.cache-user";
    public static final F.Tuple<String, Boolean> CACHE_DEADBOLT_USER_DEFAULT = new F.Tuple<>(CACHE_DEADBOLT_USER, false);
    public static final String DEFAULT_VIEW_TIMEOUT = "deadbolt.java.view-timeout";
    public static final F.Tuple<String, Long> DEFAULT_VIEW_TIMEOUT_DEFAULT = new F.Tuple<>(DEFAULT_VIEW_TIMEOUT, 1000L);
    public static final String BLOCKING = "deadbolt.java.blocking";
    public static final F.Tuple<String, Boolean> BLOCKING_DEFAULT = new F.Tuple<>(BLOCKING, false);
    public static final String DEFAULT_BLOCKING_TIMEOUT = "deadbolt.java.blocking-timeout";
    public static final F.Tuple<String, Long> DEFAULT_BLOCKING_TIMEOUT_DEFAULT = new F.Tuple<>(DEFAULT_BLOCKING_TIMEOUT, 1000L);

    public static final String PATTERN_INVERT = "deadbolt.pattern.invert";

    private ConfigKeys()
    {
        // no-op
    }
}
