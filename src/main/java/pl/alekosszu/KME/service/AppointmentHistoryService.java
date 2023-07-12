package pl.alekosszu.KME.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.user.AppointmentHistory;
import pl.alekosszu.KME.repository.AppointmentHistoryRepository;

import java.util.Collection;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentHistoryService {

    private final AppointmentHistoryRepository appointmentHistoryRepository;

    public void save(AppointmentHistory appointmentHistory) {
        appointmentHistoryRepository.save(appointmentHistory);
    }

    public AppointmentHistory findById(Long id) {
        return appointmentHistoryRepository.findById(id).get();
    }

    public List<AppointmentHistory> findAll() {
        return appointmentHistoryRepository.findAll();
    }

    public void update(AppointmentHistory appointmentHistory) {
        appointmentHistoryRepository.save(appointmentHistory);
    }

    public void deleteById(Long id) {
    }

    public AppointmentHistory findByAppointmentId(Long appointmentId){
        return appointmentHistoryRepository.findByAppointmentId(appointmentId);
    };

    public Collection<AppointmentHistory> findAllByUserId(Long userId){
        return appointmentHistoryRepository.findAllByUserId(userId);
    };

}


