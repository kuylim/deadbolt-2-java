/*
 * Copyright 2010-2016 Steve Chaloner
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
package be.objectify.deadbolt.java.views.di.restrictTest;

import be.objectify.deadbolt.java.AbstractFakeApplicationTest;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.cache.HandlerCache;
import be.objectify.deadbolt.java.testsupport.TestHandlerCache;
import be.objectify.deadbolt.java.testsupport.TestRole;
import be.objectify.deadbolt.java.testsupport.TestSubject;
import be.objectify.deadbolt.java.views.html.di.restrict;
import be.objectify.deadbolt.java.views.html.di.restrictTest.restrictContent;
import org.junit.Assert;
import org.junit.Test;
import play.test.Helpers;
import play.twirl.api.Content;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class RestrictTest extends AbstractFakeApplicationTest
{
    private final HandlerCache handlers = handlers();

    @Test
    public void testSingleRole_present()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"foo"}),
                                                      handlers.apply("foo"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertTrue(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testSingleRole_notPresent()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"foo"}),
                                                      handlers.apply("bar"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testSingleRole_noRolesPresent()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"foo"}),
                                                      handlers.apply("noRoles"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testSingleRole_noSubject()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"foo"}),
                                                      handlers.apply("noSubject"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testOr_fooPresent()
    {
        final Content html = restrictContent().render(Arrays.asList(new String[]{"foo"},
                                                                    new String[]{"bar"}),
                                                      handlers.apply("foo"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertTrue(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testOr_barPresent()
    {
        final Content html = restrictContent().render(Arrays.asList(new String[]{"foo"},
                                                                    new String[]{"bar"}),
                                                      handlers.apply("bar"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertTrue(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testOr_bothPresent()
    {
        final Content html = restrictContent().render(Arrays.asList(new String[]{"foo"},
                                                                    new String[]{"bar"}),
                                                      handlers.apply("fooBar"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertTrue(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testOr_neitherPresent()
    {
        final Content html = restrictContent().render(Arrays.asList(new String[]{"foo"},
                                                                    new String[]{"bar"}),
                                                      handlers.apply("noRoles"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testOr_noSubject()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"foo"}),
                                                      handlers.apply("noSubject"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testAnd_fooPresent()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"foo", "bar"}),
                                                      handlers.apply("foo"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testAnd_barPresent()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"foo", "bar"}),
                                                      handlers.apply("bar"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testAnd_bothPresent()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"foo", "bar"}),
                                                      handlers.apply("fooBar"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertTrue(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testAnd_neitherPresent()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"foo", "bar"}),
                                                      handlers.apply("noRoles"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testAnd_noSubject()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"foo", "bar"}),
                                                      handlers.apply("noSubject"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testNegatedRole_subjectHasRole()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"!foo"}),
                                                      handlers.apply("foo"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testNegatedRole_subjectDoesNotHaveRole()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"!foo"}),
                                                      handlers.apply("bar"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertTrue(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testNegatedRole_subjectHasMultipleRole()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"!foo"}),
                                                      handlers.apply("fooBar"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testNegatedRole_noRolesPresent()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"!foo"}),
                                                      handlers.apply("noRoles"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertTrue(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testNegatedRole_noSubject()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"!foo"}),
                                                      handlers.apply("noSubject"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testOr_oneSideNegated_fooPresent()
    {
        final Content html = restrictContent().render(Arrays.asList(new String[]{"!foo"},
                                                                    new String[]{"bar"}),
                                                      handlers.apply("foo"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testOr_oneSideNegated_barPresent()
    {
        final Content html = restrictContent().render(Arrays.asList(new String[]{"!foo"},
                                                                    new String[]{"bar"}),
                                                      handlers.apply("bar"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertTrue(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testOr_oneSideNegated_bothPresent()
    {
        final Content html = restrictContent().render(Arrays.asList(new String[]{"!foo"},
                                                                    new String[]{"bar"}),
                                                      handlers.apply("fooBar"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertTrue(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testOr_oneSideNegated_neitherPresent()
    {
        final Content html = restrictContent().render(Arrays.asList(new String[]{"!foo"},
                                                                    new String[]{"bar"}),
                                                      handlers.apply("noRoles"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertTrue(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testOr_oneSideNegated_noSubject()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"!foo"}),
                                                      handlers.apply("noSubject"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testAnd_oneSideNegated_fooPresent()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"!foo", "bar"}),
                                                      handlers.apply("foo"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testAnd_oneSideNegated_barPresent()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"!foo", "bar"}),
                                                      handlers.apply("bar"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertTrue(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testAnd_oneSideNegated_bothPresent()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"!foo", "bar"}),
                                                      handlers.apply("fooBar"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testAnd_oneSideNegated_neitherPresent()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"!foo", "bar"}),
                                                      handlers.apply("noRoles"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    @Test
    public void testAnd_oneSideNegated_noSubject()
    {
        final Content html = restrictContent().render(Collections.singletonList(new String[]{"!foo", "bar"}),
                                                      handlers.apply("noSubject"));
        final String content = Helpers.contentAsString(html);
        Assert.assertTrue(content.contains("This is before the constraint."));
        Assert.assertFalse(content.contains("This is protected by the constraint."));
        Assert.assertTrue(content.contains("This is after the constraint."));
    }

    private restrictContent restrictContent() {
        return new restrictContent(new restrict(viewSupport(),
                                                handlers()));
    }

    public HandlerCache handlers()
    {
        final Map<String, DeadboltHandler> handlers = new HashMap<>();

        handlers.put("foo", handler(() -> new TestSubject.Builder().role(new TestRole("foo"))
                                                                   .build()));
        handlers.put("bar", handler(() -> new TestSubject.Builder().role(new TestRole("bar"))
                                                                   .build()));
        handlers.put("fooBar", handler(() -> new TestSubject.Builder().role(new TestRole("foo"))
                                                                      .role(new TestRole("bar"))
                                                                      .build()));
        handlers.put("noRoles", handler(() -> new TestSubject.Builder().build()));
        handlers.put("noSubject", handler(() -> null));

        return new TestHandlerCache(null,
                                    handlers);
    }
}