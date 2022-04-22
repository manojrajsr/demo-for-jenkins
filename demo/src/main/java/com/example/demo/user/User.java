package com.example.demo.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import com.google.gson.Gson;
import lombok.*;
import org.springframework.context.annotation.Bean;

@Entity
@Table(name = "users_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    private int id;
    private String name;
    private int age;

    @Bean
    public static User toUser(String str){
        /*System.out.println(str);
        User tmp = new User(3,"manoj", 20);
        //return tmp;

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        User user = gson.fromJson(str,User.class);
        System.out.println(user);
        return tmp;*/

        Gson g = new Gson();
        User usertmp = g.fromJson(str, User.class );
        return usertmp;
    }

    @Override
    public String toString() {
        /*final StringBuffer sb = new StringBuffer("{");
        sb.append("\"id\":").append(id).append("\",");
        sb.append("\"name\":\"").append(name).append("\",");
        sb.append("\"age\":").append(age);
        sb.append('}');
        System.out.println(sb.toString());
        return sb.toString();*/
        Gson g = new Gson();
        User tmp = new User(id, name, age);
        return g.toJson(tmp);
    }
}
