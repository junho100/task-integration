package com.bizcolab.bizcolab.common.schedulers;

import com.bizcolab.bizcolab.common.utils.DateService;
import com.bizcolab.bizcolab.domains.monday.MondayService;
import com.bizcolab.bizcolab.domains.task_integration_configurations.TaskConfigService;
import com.bizcolab.bizcolab.domains.task_integration_configurations.model.dto.GetMondayApiKeyWithLastUpdatedAtDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MondayScheduler {
    private final TaskConfigService taskConfigService;
    private final MondayService mondayService;
    private final DateService dateService;

    @Scheduled(fixedRate = 1000 * 60)
    public void syncMondayData() {
        List<GetMondayApiKeyWithLastUpdatedAtDto> getMondayApiKeyWithLastUpdatedAtDto = taskConfigService.getMondayApiKeyWithLastUpdatedAt();
        getMondayApiKeyWithLastUpdatedAtDto.forEach(dto -> {
            mondayService.syncMondayDataSinceLastUpdatedAt(dto.getApiKey(), dto.getLastUpdatedAt(), dto.getConfigId());
            taskConfigService.updateLastUpdatedAt(dto.getConfigId(), dateService.getNowLocalDateTime());
        });
    }
}
