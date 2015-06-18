package be.objectify.deadbolt.java;

import be.objectify.deadbolt.java.cache.DefaultPatternCache;
import be.objectify.deadbolt.java.cache.DefaultSubjectCache;
import be.objectify.deadbolt.java.cache.PatternCache;
import be.objectify.deadbolt.java.cache.SubjectCache;
import play.api.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import play.api.inject.Module;
import scala.collection.Seq;

import javax.inject.Singleton;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class DeadboltModule extends Module
{
    @Override
    public Seq<Binding<?>> bindings(final Environment environment,
                                    final Configuration configuration)
    {
        return seq(subjectCache(),
                   patternCache(),
                   analyzer(),
                   viewSupport(),
                   templateFailureListenerProvider());
    }

    /**
     * Create a binding for {@link TemplateFailureListenerProvider}.
     *
     * @return the binding
     */
    public Binding<TemplateFailureListenerProvider> templateFailureListenerProvider()
    {
        return bind(TemplateFailureListenerProvider.class).toSelf().in(Singleton.class);
    }

    /**
     * Create a binding for {@link ViewSupport}.
     *
     * @return the binding
     */
    public Binding<ViewSupport> viewSupport()
    {
        return bind(ViewSupport.class).toSelf().in(Singleton.class);
    }

    /**
     * Create a binding for {@link JavaAnalyzer}.
     *
     * @return the binding
     */
    public Binding<JavaAnalyzer> analyzer()
    {
        return bind(JavaAnalyzer.class).toSelf().in(Singleton.class);
    }

    /**
     * Create a binding for {@link PatternCache}.
     *
     * @return the binding
     */
    public Binding<PatternCache> patternCache()
    {
        return bind(PatternCache.class).to(DefaultPatternCache.class).in(Singleton.class);
    }

    /**
     * Create a binding for {@link SubjectCache}.
     *
     * @return the binding
     */
    public Binding<SubjectCache> subjectCache()
    {
        return bind(SubjectCache.class).to(DefaultSubjectCache.class).in(Singleton.class);
    }
}
