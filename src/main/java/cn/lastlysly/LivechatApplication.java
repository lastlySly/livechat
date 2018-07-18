package cn.lastlysly;

import cn.lastlysly.myutils.interceptor.CustomSocketChannelInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling    //启用定时任务
public class LivechatApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return super.configure(builder);
		return builder.sources(LivechatApplication.class);
	}

	public static void main(String[] args) {
//		SpringApplication.run(LivechatApplication.class, args);
		SpringApplication springApplication = new SpringApplication(LivechatApplication.class);
		//		springApplication.setBannerMode(Banner.Mode.OFF); 关闭banner
		springApplication.run(args);
	}


}
