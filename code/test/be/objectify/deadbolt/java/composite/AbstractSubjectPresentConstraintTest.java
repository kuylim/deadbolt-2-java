/*
 * Copyright 2012-2016 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.objectify.deadbolt.java.composite;

import be.objectify.deadbolt.java.DeadboltHandler;
import org.junit.Assert;
import org.junit.Test;
import play.libs.F;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public abstract class AbstractSubjectPresentConstraintTest extends AbstractConstraintTest
{
    @Test
    public void testSubjectPresent() throws Exception
    {
        final Constraint constraint = constraint(withSubject(this::subject));
        final CompletionStage<Boolean> result = constraint.test(context,
                                                                withSubject(this::subject),
                                                                Executors.newSingleThreadExecutor());
        Assert.assertTrue(toBoolean(result));
    }

    @Test
    public void testSubjectNotPresent() throws Exception
    {
        final Constraint constraint = constraint(withSubject(() -> null));
        final CompletionStage<Boolean> result = constraint.test(context,
                                                                withSubject(() -> null),
                                                                Executors.newSingleThreadExecutor());
        Assert.assertFalse(toBoolean(result));
    }

    public abstract SubjectPresentConstraint constraint(final DeadboltHandler handler);

    @Override
    protected F.Tuple<Constraint, Function<Constraint, CompletionStage<Boolean>>> satisfy()
    {
        return new F.Tuple<>(constraint(withSubject(this::subject)),
                             c -> c.test(context,
                                         withSubject(this::subject),
                                         Executors.newSingleThreadExecutor()));
    }
}