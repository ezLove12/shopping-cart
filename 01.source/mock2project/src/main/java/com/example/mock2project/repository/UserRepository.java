package com.example.mock2project.repository;

import com.example.mock2project.Entity.User;

import com.example.mock2project.dto.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);

    @Transactional
    @Modifying
    @Query("update  User u set u.status = true where u.email=?1")
    int updateStatus(String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update user_role set role_id=1 where user_id=?1")
    int updateRole(Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update user set password = ?1 where user_id=?2")
    int changePassword(String password, Long id);

    @Query("Select u.password from User u where u.id=?1")
    String getEncodedPassword(Long id);

    @Query("Select u from User u")
    Page<User> findAll(Pageable pageable);

    Optional<User> findUserById(Long id);


    @Query("select u from User u where concat(u.email,u.username, u.userDetail.address, u.userDetail.date, u.userDetail.fullname, u.userDetail.gender) like ?1")
    Page<User> findUserByField(String search, Pageable pageable);

    @Query("select new com.example.mock2project.dto.UserInfo(u.email, u.username, ud.address, ud.date," +
            "ud.fullname, ud.gender)"
            + "from User u inner join UserDetail ud on ud.id = ?1")
    UserInfo getInfoUser(Long id);


}
