package com.example.demo.repository;

import com.example.demo.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findById(int id);

    /*@Modifying
    @Query("delete from users_details where id = :id;")
    default void delete(@Param("id") int id) {
    }*/
}
