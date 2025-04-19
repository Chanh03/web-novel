package com.anhngo.nhaichuttruyen.service;

import com.anhngo.nhaichuttruyen.DTO.UserDTO;
import com.anhngo.nhaichuttruyen.entity.*;
import com.anhngo.nhaichuttruyen.repos.*;
import com.anhngo.nhaichuttruyen.util.NotFoundException;
import com.anhngo.nhaichuttruyen.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final NovelRepository novelRepository;
    private final TransactionRepository transactionRepository;
    private final UserRankRepository userRankRepository;
    private final PersonalNotificationRepository personalNotificationRepository;
    private final ReceiveNotificationRepository receiveNotificationRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserRoleRepository userRoleRepository;
    private final FavoriteRepository favoriteRepository;
    private final PurchasRepository purchasRepository;

    public UserService(final UserRepository userRepository, final NovelRepository novelRepository,
                       final TransactionRepository transactionRepository,
                       final UserRankRepository userRankRepository,
                       final PersonalNotificationRepository personalNotificationRepository,
                       final ReceiveNotificationRepository receiveNotificationRepository,
                       final BookmarkRepository bookmarkRepository,
                       final UserRoleRepository userRoleRepository,
                       final FavoriteRepository favoriteRepository,
                       final PurchasRepository purchasRepository) {
        this.userRepository = userRepository;
        this.novelRepository = novelRepository;
        this.transactionRepository = transactionRepository;
        this.userRankRepository = userRankRepository;
        this.personalNotificationRepository = personalNotificationRepository;
        this.receiveNotificationRepository = receiveNotificationRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.userRoleRepository = userRoleRepository;
        this.favoriteRepository = favoriteRepository;
        this.purchasRepository = purchasRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final UUID id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final UUID id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final UUID id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFullName(user.getFullName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setIsEnabled(user.getEnabled());
        userDTO.setCreateDate(user.getCreateDate());
        userDTO.setWalletBalance(user.getWalletBalance());
        userDTO.setUpdateDate(user.getUpdateDate());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setUsername(userDTO.getUsername());
        user.setFullName(userDTO.getFullName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setAvatar(userDTO.getAvatar());
        user.setEnabled(userDTO.getIsEnabled());
        user.setCreateDate(userDTO.getCreateDate());
        user.setWalletBalance(userDTO.getWalletBalance());
        user.setUpdateDate(userDTO.getUpdateDate());
        return user;
    }

    public ReferencedWarning getReferencedWarning(final UUID id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Novel authorNovel = novelRepository.findFirstByAuthor(user);
        if (authorNovel != null) {
            referencedWarning.setKey("user.novel.author.referenced");
            referencedWarning.addParam(authorNovel.getId());
            return referencedWarning;
        }
        final Transaction userTransaction = transactionRepository.findFirstByUser(user);
        if (userTransaction != null) {
            referencedWarning.setKey("user.transaction.user.referenced");
            referencedWarning.addParam(userTransaction.getId());
            return referencedWarning;
        }
        final UserRank userUserRank = userRankRepository.findFirstByUser(user);
        if (userUserRank != null) {
            referencedWarning.setKey("user.userRank.user.referenced");
            referencedWarning.addParam(userUserRank.getId());
            return referencedWarning;
        }
        final PersonalNotification userPersonalNotification = personalNotificationRepository.findFirstByUser(user);
        if (userPersonalNotification != null) {
            referencedWarning.setKey("user.personalNotification.user.referenced");
            referencedWarning.addParam(userPersonalNotification.getId());
            return referencedWarning;
        }
        final ReceiveNotification userReceiveNotification = receiveNotificationRepository.findFirstByUser(user);
        if (userReceiveNotification != null) {
            referencedWarning.setKey("user.receiveNotification.user.referenced");
            referencedWarning.addParam(userReceiveNotification.getId());
            return referencedWarning;
        }
        final Bookmark userBookmark = bookmarkRepository.findFirstByUser(user);
        if (userBookmark != null) {
            referencedWarning.setKey("user.bookmark.user.referenced");
            referencedWarning.addParam(userBookmark.getId());
            return referencedWarning;
        }
        final UserRole userUserRole = userRoleRepository.findFirstByUser(user);
        if (userUserRole != null) {
            referencedWarning.setKey("user.userRole.user.referenced");
            referencedWarning.addParam(userUserRole.getId());
            return referencedWarning;
        }
        final Favorite userFavorite = favoriteRepository.findFirstByUser(user);
        if (userFavorite != null) {
            referencedWarning.setKey("user.favorite.user.referenced");
            referencedWarning.addParam(userFavorite.getId());
            return referencedWarning;
        }
        final Purchase userPurchase = purchasRepository.findFirstByUser(user);
        if (userPurchase != null) {
            referencedWarning.setKey("user.purchas.user.referenced");
            referencedWarning.addParam(userPurchase.getId());
            return referencedWarning;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
