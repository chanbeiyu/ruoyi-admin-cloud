package org.dromara.social;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * 启动程序
 *
 * @author chanbeiyu
 */
@EnableDubbo
@SpringBootApplication(scanBasePackages = {"club.ensoul", "org.dromara"})
public class SocialApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SocialApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Alkaid 启动成功   ლ(´ڡ`ლ)ﾞ");
    }

}
