package net.yosifov.filipov.training.accounting.acc20.config;

import net.yosifov.filipov.training.accounting.acc20.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;


import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;
import java.util.stream.Collectors;

@Configuration
public class MyRepositoryRestConfigurerAdapter implements RepositoryRestConfigurer {
    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        // https://stackoverflow.com/questions/30912826/expose-all-ids-when-using-spring-data-rest
        config.exposeIdsFor(entityManager
                .getMetamodel()
                .getEntities()
                .stream()
                .map(Type::getJavaType)
                .toArray(Class[]::new));
    }

}
