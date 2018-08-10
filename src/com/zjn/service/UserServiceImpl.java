package com.zjn.service;

import com.zjn.dao.UserDao;
import com.zjn.domain.User;
import com.zjn.factory.BasicFactory;
import com.zjn.util.DaoUtils;
import org.apache.commons.dbutils.DbUtils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.util.Properties;
import java.util.UUID;

public class UserServiceImpl implements UserService{
    private UserDao dao = BasicFactory.getFactory().getInstance(UserDao.class);
    /**
     * 注册用户
     *
     * @param user 封装了用户数据的userbean
     */
    @Override
    public void regist(User user) {
        Connection conn=null;
        try {
            conn = DaoUtils.getConn();
            conn.setAutoCommit(false);
            //1.检验用户名是否已经存在
            if (dao.findUserByName(user.getUsername(), conn) != null) {
                throw new RuntimeException("用户名已经存在");
            }
            //2.调用dao中的方法添加用户到数据库
            user.setRole("user");
            user.setState(0);
            user.setActivecode(UUID.randomUUID().toString());
            dao.addUser(user,conn);

            //3.发送激活邮件
                Properties prop = new Properties();
                prop.setProperty("mail.transport.protocol", "smtp");//协议
                prop.setProperty("mail.smtp.host", "localhost");//主机名
                prop.setProperty("mail.smtp.auth", "true");//是否开启权限控制
                prop.setProperty("mail.debug", "true");//如果设置为true则在发送邮件时会打印发送时信息
                //创建程序到邮件服务器之间的一次会话
                Session session = Session.getInstance(prop);
                //获取邮件对象
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress("aa@zheng.com"));
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
                msg.setSubject(user.getUsername() + ",来自store的激活邮件");
                msg.setText(user.getUsername() + "点击如下链接激活账户，如果不能点击请复制到浏览器地址栏访问：http://localhost:8080/ActiveServlet?activecode=" + user.getActivecode());
                //找到邮递员
                Transport trans = session.getTransport();
                trans.connect("aa", "123");
                trans.sendMessage(msg, msg.getAllRecipients());

                DbUtils.commitAndCloseQuietly(conn);
        }catch (Exception e){
            DbUtils.rollbackAndCloseQuietly(conn);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 激活用户的方法
     *
     * @param activecode 激活码
     */
    @Override
    public void acitveUser(String activecode) {
        //1.调用dao根据激活码查找用户
        User user=dao.findUserByActivecode(activecode);
        //2.如果找不到提示激活码无效
        if(user==null){
            throw new RuntimeException("激活码不正确！！");
        }
        //3.如果用户已经激活过，提示不要重复激活
        if(user.getState()==1){
            throw new RuntimeException("此用户已经激活过！不要重复激活");
        }
        //4.如果没激活但是激活码已经超时，则提示，并删除此用户
        if(System.currentTimeMillis()-user.getUpdatetime().getTime()>1000*3600*24){
            dao.delUser(user.getId());
            throw new RuntimeException("激活码已经超时，请重新注册并在24小时内激活");
        }
        //5.调用dao中修改用户激活状态的方法
        dao.updateState(user.getId(),1);

    }

    /**
     * 根据用户名密码查找用户
     *
     * @param username
     * @param password
     */
    @Override
    public User getUserByNameAndPsw(String username, String password) {
        return dao.finUserByNameAndPsw(username,password);
    }
}
