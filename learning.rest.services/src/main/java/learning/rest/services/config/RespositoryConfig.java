package learning.rest.services.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import learning.rest.services.model.CourseAttributes;
import learning.rest.services.model.CourseProvider;
import learning.rest.services.model.LearningCourseItems;
import learning.rest.services.model.LearningLineItems;
import learning.rest.services.model.LearningRequisitions;
import learning.rest.services.model.Users;

@Configuration
public class RespositoryConfig extends RepositoryRestConfigurerAdapter {
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Users.class);
        config.exposeIdsFor(CourseProvider.class);
        config.exposeIdsFor(CourseAttributes.class);
        config.exposeIdsFor(LearningCourseItems.class);
        config.exposeIdsFor(LearningRequisitions.class);
        config.exposeIdsFor(LearningLineItems.class);
    }
}
