package com.bcit.discoverAR.repository;

import com.bcit.discoverAR.models.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {
    Optional<List<Journey>> findByUserId(Long userId);
    Optional<Journey> findByUserIdAndId(Long userId, Long id);
    Optional<Journey> findById(Long journeyId);
}
