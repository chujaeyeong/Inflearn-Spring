package study.springdatajpa.repository;

public interface NestedClosedProjection {

    String getUsername();
    TeamInfo getTeam();

    interface TeamInfo {
        String getName();
    }
}
