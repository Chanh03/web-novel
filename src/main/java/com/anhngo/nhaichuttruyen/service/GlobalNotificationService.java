package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.GlobalNotificationDTO;
import com.anhngo.nhaichuttruyen.entity.GlobalNotification;
import com.anhngo.nhaichuttruyen.repos.GlobalNotificationRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GlobalNotificationService {

    private final GlobalNotificationRepository globalNotificationRepository;

    public GlobalNotificationService(
            final GlobalNotificationRepository globalNotificationRepository) {
        this.globalNotificationRepository = globalNotificationRepository;
    }

    public List<GlobalNotificationDTO> findAll() {
        final List<GlobalNotification> globalNotifications = globalNotificationRepository.findAll(Sort.by("id"));
        return globalNotifications.stream()
                .map(globalNotification -> mapToDTO(globalNotification, new GlobalNotificationDTO()))
                .toList();
    }

    public GlobalNotificationDTO get(final Integer id) {
        return globalNotificationRepository.findById(id)
                .map(globalNotification -> mapToDTO(globalNotification, new GlobalNotificationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final GlobalNotificationDTO globalNotificationDTO) {
        final GlobalNotification globalNotification = new GlobalNotification();
        mapToEntity(globalNotificationDTO, globalNotification);
        return globalNotificationRepository.save(globalNotification).getId();
    }

    public void update(final Integer id, final GlobalNotificationDTO globalNotificationDTO) {
        final GlobalNotification globalNotification = globalNotificationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(globalNotificationDTO, globalNotification);
        globalNotificationRepository.save(globalNotification);
    }

    public void delete(final Integer id) {
        globalNotificationRepository.deleteById(id);
    }

    private GlobalNotificationDTO mapToDTO(final GlobalNotification globalNotification,
                                           final GlobalNotificationDTO globalNotificationDTO) {
        globalNotificationDTO.setId(globalNotification.getId());
        globalNotificationDTO.setTitle(globalNotification.getTitle());
        globalNotificationDTO.setText(globalNotification.getText());
        globalNotificationDTO.setCreateDate(globalNotification.getCreateDate());
        return globalNotificationDTO;
    }

    private GlobalNotification mapToEntity(final GlobalNotificationDTO globalNotificationDTO,
                                           final GlobalNotification globalNotification) {
        globalNotification.setTitle(globalNotificationDTO.getTitle());
        globalNotification.setText(globalNotificationDTO.getText());
        globalNotification.setCreateDate(globalNotificationDTO.getCreateDate());
        return globalNotification;
    }

}
