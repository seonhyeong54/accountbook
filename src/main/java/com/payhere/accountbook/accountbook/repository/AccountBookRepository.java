package com.payhere.accountbook.accountbook.repository;

import com.payhere.accountbook.accountbook.entity.AccountBook;
import com.payhere.accountbook.accountbook.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountBookRepository extends JpaRepository<AccountBook, Long> {
    Optional<AccountBook> findByAccountBookIdAndUser(Long accountBookId, User user);
    Page<AccountBook> findByUserAndActive(Pageable pageable, User user, Boolean active);
}
