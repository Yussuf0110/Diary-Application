package com.example.newdiaryapp.repositories;

import com.example.newdiaryapp.models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
