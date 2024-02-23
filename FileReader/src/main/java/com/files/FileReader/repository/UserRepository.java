package com.files.FileReader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.files.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}

//@Repository
//public interface UserRepository extends CrudRepository<User, Long>{
//
//}
