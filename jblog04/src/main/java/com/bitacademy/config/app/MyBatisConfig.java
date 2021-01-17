package com.bitacademy.config.app;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:com/bitacademy/jblog/config/app/mybatis/configuration.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	
	
	@Bean
	public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
}


/*
		<!-- MyBatis SqlSessionFactoryBean --> 
		<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean"> 
			<property name="dataSource" ref="dataSource" /> 
			<property name="configLocation" value="classpath:mybatis/configuration.xml" /> 
		</bean>
		
		<!-- MyBatis SqlSessionTemplate --> 
		<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
			<constructor-arg index="0" ref="sqlSessionFactory" />
		</bean>

*/