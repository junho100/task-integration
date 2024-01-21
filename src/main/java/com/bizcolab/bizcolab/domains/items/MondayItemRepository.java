package com.bizcolab.bizcolab.domains.items;

import com.bizcolab.bizcolab.domains.items.entity.MondayItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MondayItemRepository extends JpaRepository<MondayItems, Long> {

}
