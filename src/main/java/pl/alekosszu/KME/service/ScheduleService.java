package pl.alekosszu.KME.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.employee.Schedule;
import pl.alekosszu.KME.entity.employee.Specialty;
import pl.alekosszu.KME.repository.ScheduleRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).get();
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public void update(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }

    public List<Schedule> findByEmployeeId(Long id) {
        return scheduleRepository.findByEmployeeId(id);
    }
}
