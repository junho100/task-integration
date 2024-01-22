package com.bizcolab.bizcolab.domains.projects;

import com.bizcolab.bizcolab.domains.projects.entity.MondayProjects;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MondayProjectRepository extends JpaRepository<MondayProjects, Long> {
    boolean existsByIdInTool(String idInTool);
    Optional<MondayProjects> findByIdInTool(String idInTool);
}
