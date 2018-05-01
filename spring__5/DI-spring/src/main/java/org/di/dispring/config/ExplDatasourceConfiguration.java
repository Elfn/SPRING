package org.di.dispring.config;

import org.di.dispring.beans.ExplDataSource;
import org.di.dispring.beans.SecondDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Created by Elimane on Oct, 2017, at 13:13
 */
@Configuration
@PropertySources({
        @PropertySource("classpath:data.properties"),
        @PropertySource("classpath:sdata.properties")
})
public class ExplDatasourceConfiguration {

    @Autowired
    private Environment env;

    @Value("${data.username}")
    private String uname;
//    @Value("${data.password}")
//    private String pwd;

    @Value("${second.data.username}")
    private String suname;


    @Bean
    public ExplDataSource datasource()
    {
        return new ExplDataSource(uname,env.getProperty("PASSWORD"));
    }

    @Bean
    public SecondDataSource secondDatasource()
    {
        return new SecondDataSource(suname,env.getProperty("SECONDPASSWORD"));
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer properties()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
