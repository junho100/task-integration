package com.bizcolab.bizcolab.domains.groups;

import com.bizcolab.bizcolab.domains.groups.entity.MondayGroups;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MondayGroupRepository extends JpaRepository<MondayGroups, Long> {
    boolean existsByIdInTool(String idInTool);

    Optional<MondayGroups> findByIdInTool(String idInTool);
}
