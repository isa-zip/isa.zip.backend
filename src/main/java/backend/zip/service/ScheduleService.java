package backend.zip.service;

import backend.zip.domain.schedule.Schedule;
import backend.zip.dto.schedule.request.AddScheduleRequest;
import backend.zip.dto.schedule.request.UpdateScheduleRequest;

import java.util.Optional;

public interface ScheduleService {
    Optional<Schedule> getScheduleByUserId(Long userId);

    Schedule addSchedule(Long userId, AddScheduleRequest request);

    Optional<Schedule> updateSchedule(Long userId, UpdateScheduleRequest request);

    void deleteSchedule(Long userId);
}
