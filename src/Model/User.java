package Model;

public class User {
    private String username;
    private String password;
    public User(String username,String password){
        this.username = username;
        this.password = password;

    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean equals(Object obj) {
        User u = (User) obj;
        if (this.getUsername().equals(u.getUsername()) && this.getPassword().equals(u.getPassword())) {
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return String.format("%s-%s",this.getUsername(),this.getPassword());
    }

}
