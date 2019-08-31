package biz.tugay.ctci.ch04.buildOrder;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static biz.tugay.ctci.ch04.buildOrder.Project.withId;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class BuildOrderResolverTest {

    BuildOrderResolver buildOrderResolver = new BuildOrderResolver();

    @Test
    public void buildOrder2ProjectsLinear() {
        Project a = withId("a"), b = withId("b");
        a.dependencies.add(b);
        // a -- depends on --> b

        List<Set<Project>> buildOrder = buildOrderResolver.resolveBuildOrder(new HashSet<>(asList(a, b)));

        // Expected structure: [{b}, {a}]
        assertThat(buildOrder.size(), is(2));
        assertThat(buildOrder.get(0).size(), is(1));
        assertThat(buildOrder.get(0).contains(b), is(true));

        assertThat(buildOrder.get(1).size(), is(1));
        assertThat(buildOrder.get(1).contains(a), is(true));
    }

    @Test
    public void buildOrder3ProjectsLinear() {
        Project a = withId("a"), b = withId("b"), c = withId("c");
        a.dependencies.add(b);
        b.dependencies.add(c);
        // a --> b --> c

        List<Set<Project>> buildOrder = buildOrderResolver.resolveBuildOrder(new HashSet<>(asList(a, b, c)));

        assertThat(buildOrder.size(), is(3));
        assertThat(buildOrder.get(0).size(), is(1));
        assertThat(buildOrder.get(0).contains(c), is(true));

        assertThat(buildOrder.get(1).size(), is(1));
        assertThat(buildOrder.get(1).contains(b), is(true));

        assertThat(buildOrder.get(2).size(), is(1));
        assertThat(buildOrder.get(2).contains(a), is(true));
    }

    @Test
    public void buildOrderComplex() {
        Project a = withId("a"), b = withId("b"), c = withId("c"), d = withId("d"), e = withId("e"), f = withId("f");

        c.dependencies.add(d);
        d.dependencies.add(b);
        d.dependencies.add(a);
        b.dependencies.add(f);
        a.dependencies.add(f);

        //          |--> b --|
        // c --> d--|        |--> f
        //          |--> a --|
        //
        //                        e (has no dependencies and no dependents)

        List<Set<Project>> buildOrder = buildOrderResolver.resolveBuildOrder(new HashSet<>(asList(a, b, c, d, e, f)));
        System.out.println(buildOrder);

        assertThat(buildOrder.size(), is(4));

        assertThat(buildOrder.get(0).size(), is(2));
        assertThat(buildOrder.get(0).contains(f), is(true));
        assertThat(buildOrder.get(0).contains(e), is(true));

        assertThat(buildOrder.get(1).size(), is(2));
        assertThat(buildOrder.get(1).contains(b), is(true));
        assertThat(buildOrder.get(1).contains(a), is(true));

        assertThat(buildOrder.get(2).size(), is(1));
        assertThat(buildOrder.get(2).contains(d), is(true));

        assertThat(buildOrder.get(3).size(), is(1));
        assertThat(buildOrder.get(3).contains(c), is(true));
    }

    @Test
    public void buildOrderNoPossibleBuildOrder() {
        Project a = withId("a"), b = withId("b");
        a.dependencies.add(b);
        b.dependencies.add(a);

        assertThat(buildOrderResolver.resolveBuildOrder(new HashSet<>(asList(a, b))), nullValue());
    }

}
