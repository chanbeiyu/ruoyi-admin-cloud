package org.dromara.common.seata.config;

import org.dromara.common.core.factory.YmlPropertySourceFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.PropertySource;

/**
 * seata 配置
 *
 * @author Lion Li
 */
@AutoConfiguration
@ConditionalOnProperty(value = "seata.enabled", havingValue = "true")
@PropertySource(value = "classpath:common-seata.yml", factory = YmlPropertySourceFactory.class)
public class SeataConfiguration {

}
