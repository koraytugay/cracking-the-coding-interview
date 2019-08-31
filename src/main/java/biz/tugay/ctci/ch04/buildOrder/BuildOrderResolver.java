package biz.tugay.ctci.ch04.buildOrder;

import java.util.*;

import static java.util.stream.Collectors.toSet;

class BuildOrderResolver {

    List<Set<Project>> resolveBuildOrder(Set<Project> projects) {
        List<Set<Project>> buildOrder = new ArrayList<>();

        Set<Project> resolved;
        do {
            resolved = resolve(projects, buildOrder.stream().flatMap(Collection::stream).collect(toSet()));
            projects.removeAll(resolved);
            if (!resolved.isEmpty())
                buildOrder.add(resolved);
        } while (!resolved.isEmpty());

        return projects.isEmpty() ? buildOrder : null;
    }

    Set<Project> resolve(Set<Project> projects, Set<Project> resolved) {
        return projects.stream()
                .filter(p -> p.dependencies.isEmpty() || resolved.containsAll(p.dependencies))
                .collect(toSet());
    }
}

class Project {
    String id;
    Set<Project> dependencies = new HashSet<>();

    static Project withId(String id) {
        Project project = new Project();
        project.id = id;
        return project;
    }

    @Override
    public String toString() {
        return id;
    }
}
