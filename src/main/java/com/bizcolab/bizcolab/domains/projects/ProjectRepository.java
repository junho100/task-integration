package com.bizcolab.bizcolab.domains.projects;

import com.bizcolab.bizcolab.domains.projects.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Projects, Long> {

}
