package com.bizcolab.bizcolab.domains.groups;

import com.bizcolab.bizcolab.domains.groups.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groups, Long> {

}
