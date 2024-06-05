package com.shopme.admin.shopmebackend.entity;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateNewUserWithOneRole(){
        Role roleAdmin = entityManager.find(Role.class, 1);
        User somansin = new User("somansin@google.com","admin1234","Sourav","Mansingh");
        somansin.addRole(roleAdmin);
        User savedUser = repo.save(somansin);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRoles(){
        User abhijit = new User("placid@gmail.com","admin1234","Abhijit","Routray");
        Role roleEditor = new Role(3);
        Role roleAssitant = new Role(5);
        abhijit.addRole(roleEditor);
        abhijit.addRole(roleAssitant);

        User savedUser = repo.save(abhijit);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers(){
        Iterable<User> listUser = repo.findAll();
        listUser.forEach(user-> System.out.println(user));

    }

    @Test
    public void testGetUserById(){
        User user =  repo.findById(1).get();
        System.out.println(user);
        assertThat(user).isNotNull();
    }

    @Test
    public void testUpdateUserDetails(){
        User user =  repo.findById(1).get();
        user.setEnabled(true);
        user.setEmail("sourav.mansingh@gmail.com");
        repo.save(user);
    }

    @Test
    public void testUpdateUserRoles(){
        User abhijit = repo.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesperson = new Role(2);
        abhijit.getRoles().remove(roleEditor);
        abhijit.addRole(roleSalesperson);
        repo.save(abhijit);
    }

    @Test
    public void testDeleteUser(){
        Integer userId = 2;
        repo.deleteById(userId);
    }
}
