package cn.edu.scu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;

/**
 * @author TZQ
 * @Description DataSourceConfig
 */
@SpringBootConfiguration
public class DataSourceConfig {
    @Bean
    @ConditionalOnMissingBean
    @Autowired
    public JdbcDaoImpl jdbcDaoImpl(DataSource dataSource) {
        final JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
        jdbcDaoImpl.setDataSource(dataSource);
        return jdbcDaoImpl;
    }
}
