package com.bizcolab.bizcolab.common.utils;

import com.bizcolab.bizcolab.common.exception.BaseException;
import com.bizcolab.bizcolab.common.exception.BaseExceptionStatus;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.springframework.stereotype.Service;

@Service
public class DateService {
    public LocalDateTime convertUTCStringToLocalDateTime(String utcString) {
        try {
            LocalDateTime dateTime = LocalDateTime.from(
                Instant.from(
                    DateTimeFormatter.ISO_DATE_TIME.parse(utcString)
                ).atZone(ZoneId.of("Asia/Seoul"))
            );
            return dateTime;
        } catch (DateTimeParseException ex) {
            throw new BaseException(BaseExceptionStatus.INVALID_DATE_FORMAT);
        }
    }

    public LocalDateTime getNowLocalDateTime() {
        return LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}

// TODO : ID는 각 항목 내에서만 유일하다.