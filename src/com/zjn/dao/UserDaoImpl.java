package com.zjn.dao;

import com.zjn.domain.User;

import com.zjn.util.TransactionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @param conn
     * @return 查找到的用户, 如果找不到返回null
     */

    public User findUserByName(String username, Connection conn) {
        String sql = "select * from users where username = ?";
        try{
            QueryRunner runner = new QueryRunner(TransactionManager.getSource());
            return runner.query(sql, new BeanHandler<User>(User.class),username);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {
        String sql = "insert into users values(null,?,?,?,?,?,?,?,null)";
        try{
            QueryRunner runner = new QueryRunner();
            runner.update(sql,user.getUsername(),user.getPassword(),user.getNickname(),user.getEmail(),user.getRole(),user.getState(),user.getActivecode());
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User findUserByName(String username) {
        String sql = "select * from users where username = ?";
        try{
            QueryRunner runner = new QueryRunner();
            return runner.query(sql, new BeanHandler<User>(User.class),username);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delUser(int id) {
        String sql = "delete from users where id = ?";
        try{
            QueryRunner runner = new QueryRunner(TransactionManager.getSource());
            runner.update(sql,id);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User findUserByActivecode(String activecode) {
        String sql = "select * from users where activecode = ?";
        try{
            QueryRunner runner = new QueryRunner(TransactionManager.getSource());
            return runner.query(sql, new BeanHandler<User>(User.class),activecode);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateState(int id, int state) {
        String sql = "update users set state = ? where id=?";
        try{
            QueryRunner runner = new QueryRunner(TransactionManager.getSource());
            runner.update(sql,state,id);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User finUserByNameAndPsw(String username, String password) {
        String sql = "select * from users where username = ? and password = ?";
        try{
            QueryRunner runner = new QueryRunner(TransactionManager.getSource());
            return runner.query(sql, new BeanHandler<User>(User.class) ,username ,password);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id查找用户
     *
     * @param user_id
     * @return
     */
    @Override
    public User findUserById(int user_id) {
        String sql="select * from users where id=?";
        try {
            QueryRunner runner=new QueryRunner(TransactionManager.getSource());
            return runner.query(sql,new BeanHandler<User>(User.class),user_id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
