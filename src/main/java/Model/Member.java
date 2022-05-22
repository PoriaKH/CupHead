package Model;

public class Member {
    public String name;
    public String password;
    public int score;

    public Member(String name,int score,String password){
        this.score = score;
        this.name = name;
        this.password = password;
    }
}
