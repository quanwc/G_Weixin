package com.quanwc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主类：
 * @SpringBootApplication注解 来表明是一个主程序类，是一个spring boot应用
 *                            该类是spring boot的主配置类，spring boot就应该运行这个类的main方法来启动spring boot应用
 */
@SpringBootApplication
public class WeixinServiceApplication {

	public static void main(String[] args) {

		/**
		 * 自定义菜单 自动回复（TextHandler） 特色的功能
		 */
		SpringApplication.run(WeixinServiceApplication.class, args);
	}
}
