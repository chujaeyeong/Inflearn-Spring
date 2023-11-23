package hellojpa_joinMapping;

import javax.persistence.*;

@Entity
public class Member3 {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team; // Member 다 : Team 1

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this); // 이렇게 넣어주면 실행 코드에다가 양쪽 값 설정 안 해줘도 됨 -> 연관관계 편의 메소드 설정 이라고 한다
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
