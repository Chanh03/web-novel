package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.ReceiveNotificationDTO;
import com.anhngo.nhaichuttruyen.entity.Novel;
import com.anhngo.nhaichuttruyen.entity.ReceiveNotification;
import com.anhngo.nhaichuttruyen.entity.User;
import com.anhngo.nhaichuttruyen.repos.NovelRepository;
import com.anhngo.nhaichuttruyen.repos.ReceiveNotificationRepository;
import com.anhngo.nhaichuttruyen.repos.UserRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReceiveNotificationService {

    private final ReceiveNotificationRepository receiveNotificationRepository;
    private final UserRepository userRepository;
    private final NovelRepository novelRepository;

    public ReceiveNotificationService(
            final ReceiveNotificationRepository receiveNotificationRepository,
            final UserRepository userRepository, final NovelRepository novelRepository) {
        this.receiveNotificationRepository = receiveNotificationRepository;
        this.userRepository = userRepository;
        this.novelRepository = novelRepository;
    }

    public List<ReceiveNotificationDTO> findAll() {
        final List<ReceiveNotification> receiveNotifications = receiveNotificationRepository.findAll(Sort.by("id"));
        return receiveNotifications.stream()
                .map(receiveNotification -> mapToDTO(receiveNotification, new ReceiveNotificationDTO()))
                .toList();
    }

    public ReceiveNotificationDTO get(final Integer id) {
        return receiveNotificationRepository.findById(id)
                .map(receiveNotification -> mapToDTO(receiveNotification, new ReceiveNotificationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ReceiveNotificationDTO receiveNotificationDTO) {
        final ReceiveNotification receiveNotification = new ReceiveNotification();
        mapToEntity(receiveNotificationDTO, receiveNotification);
        return receiveNotificationRepository.save(receiveNotification).getId();
    }

    public void update(final Integer id, final ReceiveNotificationDTO receiveNotificationDTO) {
        final ReceiveNotification receiveNotification = receiveNotificationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(receiveNotificationDTO, receiveNotification);
        receiveNotificationRepository.save(receiveNotification);
    }

    public void delete(final Integer id) {
        receiveNotificationRepository.deleteById(id);
    }

    private ReceiveNotificationDTO mapToDTO(final ReceiveNotification receiveNotification,
                                            final ReceiveNotificationDTO receiveNotificationDTO) {
        receiveNotificationDTO.setId(receiveNotification.getId());
        receiveNotificationDTO.setCreateDate(receiveNotification.getCreateDate());
        receiveNotificationDTO.setUser(receiveNotification.getUser() == null ? null : receiveNotification.getUser().getId());
        receiveNotificationDTO.setNovel(receiveNotification.getNovel() == null ? null : receiveNotification.getNovel().getId());
        return receiveNotificationDTO;
    }

    private ReceiveNotification mapToEntity(final ReceiveNotificationDTO receiveNotificationDTO,
                                            final ReceiveNotification receiveNotification) {
        receiveNotification.setCreateDate(receiveNotificationDTO.getCreateDate());
        final User user = receiveNotificationDTO.getUser() == null ? null : userRepository.findById(receiveNotificationDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        receiveNotification.setUser(user);
        final Novel novel = receiveNotificationDTO.getNovel() == null ? null : novelRepository.findById(receiveNotificationDTO.getNovel())
                .orElseThrow(() -> new NotFoundException("novel not found"));
        receiveNotification.setNovel(novel);
        return receiveNotification;
    }

}
