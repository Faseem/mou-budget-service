package lk.dialog.mou.domain;

import lombok.Data;

/**
 * Created by Aux072 on 17/09/2018.
 */
@Data
public class User {
    public User(){

    }
    public User(String userName){
        this.userName = userName;
    }
    private Long userId;
    private String userName;
}
