package be.objectify.deadbolt.java;

import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Optional;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public abstract class AbstractNoPreAuthDeadboltHandler extends AbstractDeadboltHandler
{
    @Override
    public F.Promise<Optional<Result>> beforeAuthCheck(Http.Context context)
    {
        return F.Promise.pure(Optional.empty());
    }
}