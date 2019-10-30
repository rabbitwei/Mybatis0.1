package cn.rabbit.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class MybatisUtil {
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();
    private static SqlSessionFactory factory = null;

    /**
     * 禁止外界通过new方法创建
     */
    private MybatisUtil() {
    }

    /*
     * 加载位于 resources/mybatis-config.xml 配置文件
     */
    static {
        try {
            //读取配置文件
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //创建 SqlSessionFactory 对象
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException io) {
            io.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 获取SqlSession
     */
    public static SqlSession getSqlSession() {
        //从当前线程中获取SqlSession对象
        SqlSession sqlSession = threadLocal.get();
        //如果SqlSession对象为空
        if (sqlSession == null) {
            //在SqlSessionFactory非空的情况下，获取SqlSession对象
            sqlSession = factory.openSession();
            //将SqlSession对象与当前线程绑定在一起
            threadLocal.set(sqlSession);
        }
        //返回SqlSession对象
        return sqlSession;
    }

    /**
     * 关闭SqlSession与当前线程分开
     */
    public static void closeSqlSession() {
        //从当前线程中获取SqlSession对象
        SqlSession sqlSession = threadLocal.get();
        //如果SqlSession对象非空
        if (sqlSession != null) {
            //关闭SqlSession对象
            sqlSession.close();
            //分开当前线程与SqlSession对象的关系，目的是让GC尽早回收
            threadLocal.remove();
        }
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        String ret = MybatisUtil.getSqlSession() != null ? "链接成功" : "链接失败";
        System.out.println(ret);
    }

}
