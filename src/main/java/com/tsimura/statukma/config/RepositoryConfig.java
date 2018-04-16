package com.tsimura.statukma.config;

import com.tsimura.statukma.entity.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(
                Student.class,
                Teacher.class,
                Speciality.class,
                Chair.class,
                Faculty.class,
                Diploma.class,
                StudentCard.class,
                Discipline.class,
                Enrollment.class
        );
    }
}
