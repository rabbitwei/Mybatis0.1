package cn.rabbit;

import cn.rabbit.dao.CategoryDAO;
import cn.rabbit.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TransactionManagerTest {



    @Test
    public void addTwoCategory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();


        Category c1 = new Category();
        c1.setName("长度够短的名称");
        session.insert("add", c1);


        Category c2 = new Category();
        c2.setName("超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称超过最大长度30的名称");
        session.insert("add", c2);


        session.commit();
        session.close();


    }
}
