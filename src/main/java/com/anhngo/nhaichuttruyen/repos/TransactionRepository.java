package com.anhngo.nhaichuttruyen.repos;

import com.anhngo.nhaichuttruyen.entity.Transaction;
import com.anhngo.nhaichuttruyen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findFirstByUser(User user);

}
