package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.PurchasDTO;
import com.anhngo.nhaichuttruyen.entity.Chapter;
import com.anhngo.nhaichuttruyen.entity.Purchase;
import com.anhngo.nhaichuttruyen.entity.User;
import com.anhngo.nhaichuttruyen.repos.ChapterRepository;
import com.anhngo.nhaichuttruyen.repos.PurchasRepository;
import com.anhngo.nhaichuttruyen.repos.UserRepository;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PurchasService {

    private final PurchasRepository purchasRepository;
    private final UserRepository userRepository;
    private final ChapterRepository chapterRepository;

    public PurchasService(final PurchasRepository purchasRepository,
                          final UserRepository userRepository, final ChapterRepository chapterRepository) {
        this.purchasRepository = purchasRepository;
        this.userRepository = userRepository;
        this.chapterRepository = chapterRepository;
    }

    public List<PurchasDTO> findAll() {
        final List<Purchase> purchases = purchasRepository.findAll(Sort.by("id"));
        return purchases.stream()
                .map(purchas -> mapToDTO(purchas, new PurchasDTO()))
                .toList();
    }

    public PurchasDTO get(final Integer id) {
        return purchasRepository.findById(id)
                .map(purchas -> mapToDTO(purchas, new PurchasDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final PurchasDTO purchasDTO) {
        final Purchase purchase = new Purchase();
        mapToEntity(purchasDTO, purchase);
        return purchasRepository.save(purchase).getId();
    }

    public void update(final Integer id, final PurchasDTO purchasDTO) {
        final Purchase purchase = purchasRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(purchasDTO, purchase);
        purchasRepository.save(purchase);
    }

    public void delete(final Integer id) {
        purchasRepository.deleteById(id);
    }

    private PurchasDTO mapToDTO(final Purchase purchase, final PurchasDTO purchasDTO) {
        purchasDTO.setId(purchase.getId());
        purchasDTO.setPrice(purchase.getPrice());
        purchasDTO.setPurchaseDate(purchase.getPurchaseDate());
        purchasDTO.setPaymentStatus(purchase.getPaymentStatus());
        purchasDTO.setUser(purchase.getUser() == null ? null : purchase.getUser().getId());
        purchasDTO.setChapter(purchase.getChapter() == null ? null : purchase.getChapter().getId());
        return purchasDTO;
    }

    private Purchase mapToEntity(final PurchasDTO purchasDTO, final Purchase purchase) {
        purchase.setPrice(purchasDTO.getPrice());
        purchase.setPurchaseDate(purchasDTO.getPurchaseDate());
        purchase.setPaymentStatus(purchasDTO.getPaymentStatus());
        final User user = purchasDTO.getUser() == null ? null : userRepository.findById(purchasDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        purchase.setUser(user);
        final Chapter chapter = purchasDTO.getChapter() == null ? null : chapterRepository.findById(purchasDTO.getChapter())
                .orElseThrow(() -> new NotFoundException("chapter not found"));
        purchase.setChapter(chapter);
        return purchase;
    }

}
