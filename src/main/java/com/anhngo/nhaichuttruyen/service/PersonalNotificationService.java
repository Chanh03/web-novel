package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.PersonalNotificationDTO;
import com.anhngo.nhaichuttruyen.entity.PersonalNotification;
import com.anhngo.nhaichuttruyen.entity.User;
import com.anhngo.nhaichuttruyen.repos.PersonalNotificationRepository;
import com.anhngo.nhaichuttruyen.repos.UserRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonalNotificationService {

    private final PersonalNotificationRepository personalNotificationRepository;
    private final UserRepository userRepository;

    public PersonalNotificationService(
            final PersonalNotificationRepository personalNotificationRepository,
            final UserRepository userRepository) {
        this.personalNotificationRepository = personalNotificationRepository;
        this.userRepository = userRepository;
    }

    public List<PersonalNotificationDTO> findAll() {
        final List<PersonalNotification> personalNotifications = personalNotificationRepository.findAll(Sort.by("id"));
        return personalNotifications.stream()
                .map(personalNotification -> mapToDTO(personalNotification, new PersonalNotificationDTO()))
                .toList();
    }

    public PersonalNotificationDTO get(final Integer id) {
        return personalNotificationRepository.findById(id)
                .map(personalNotification -> mapToDTO(personalNotification, new PersonalNotificationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final PersonalNotificationDTO personalNotificationDTO) {
        final PersonalNotification personalNotification = new PersonalNotification();
        mapToEntity(personalNotificationDTO, personalNotification);
        return personalNotificationRepository.save(personalNotification).getId();
    }

    public void update(final Integer id, final PersonalNotificationDTO personalNotificationDTO) {
        final PersonalNotification personalNotification = personalNotificationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(personalNotificationDTO, personalNotification);
        personalNotificationRepository.save(personalNotification);
    }

    public void delete(final Integer id) {
        personalNotificationRepository.deleteById(id);
    }

    private PersonalNotificationDTO mapToDTO(final PersonalNotification personalNotification,
                                             final PersonalNotificationDTO personalNotificationDTO) {
        personalNotificationDTO.setId(personalNotification.getId());
        personalNotificationDTO.setTitle(personalNotification.getTitle());
        personalNotificationDTO.setText(personalNotification.getText());
        personalNotificationDTO.setCreateDate(personalNotification.getCreateDate());
        personalNotificationDTO.setIsRead(personalNotification.getIsRead());
        personalNotificationDTO.setUser(personalNotification.getUser() == null ? null : personalNotification.getUser().getId());
        return personalNotificationDTO;
    }

    private PersonalNotification mapToEntity(final PersonalNotificationDTO personalNotificationDTO,
                                             final PersonalNotification personalNotification) {
        personalNotification.setTitle(personalNotificationDTO.getTitle());
        personalNotification.setText(personalNotificationDTO.getText());
        personalNotification.setCreateDate(personalNotificationDTO.getCreateDate());
        personalNotification.setIsRead(personalNotificationDTO.getIsRead());
        final User user = personalNotificationDTO.getUser() == null ? null : userRepository.findById(personalNotificationDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        personalNotification.setUser(user);
        return personalNotification;
    }

}
