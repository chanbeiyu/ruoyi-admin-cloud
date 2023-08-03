package org.dromara.biz.admin;

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
@SpringBootApplication(scanBasePackages = {"org.dromara"})
public class PlatformApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(org.dromara.biz.admin.PlatformApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  admin 后台启动成功   ლ(´ڡ`ლ)ﾞ");
    }

}
