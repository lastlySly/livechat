package cn.lastlysly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
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
