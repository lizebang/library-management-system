package com.library.server.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EventRepository extends CrudRepository<Event, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Event WHERE phone = ?1 AND isbn = ?2")
    void deleteByPhoneAndIsbn(String phone, String isbn);

    Page<Event> findUserByPhone(String phone, Pageable page);
}
