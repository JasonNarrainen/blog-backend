package com.bloggers.blog.repository;

import com.bloggers.blog.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByTitleContaining(String title);
}
