package com.shopme.admin.shopmebackend.entity;

import com.shopme.common.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
