package com.bcit.discoverAR.api.controllers;

import com.bcit.discoverAR.api.payload.response.ApiResponse;
import com.bcit.discoverAR.models.ApplicationUser;
import com.bcit.discoverAR.models.Journey;
import com.bcit.discoverAR.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/journeys")
public class JourneyController {
    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private AuthHelper authHelper;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestHeader("Authorization") String accessToken) {
        ApplicationUser user = authHelper.getUser(accessToken);

        Journey journey = new Journey();
        journey.setUser(user);
        Journey result = journeyRepository.save(journey);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<?> getUserJourneys(@RequestHeader("Authorization") String accessToken) {
        ApplicationUser user = authHelper.getUser(accessToken);

        Optional<List<Journey>> result = journeyRepository.findByUserId(user.getId());

        if (!result.isPresent()) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        List<Journey> journeys = journeyRepository.findByUserId(user.getId()).get();

        return ResponseEntity.ok(journeys);
    }

    @GetMapping("/{journeyId}")
    public ResponseEntity<?> getJourney(@RequestHeader("Authorization") String accessToken, @PathVariable long journeyId) {
        ApplicationUser user = authHelper.getUser(accessToken);
        Optional<Journey> result = journeyRepository.findByUserIdAndId(user.getId(), journeyId);
        if (!result.isPresent()) {
            return new ResponseEntity(new ApiResponse(false, "Journey not found"),
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result.get());
    }

    @PutMapping("/update/{journeyId}")
    public ResponseEntity<?> updateJourney(@RequestHeader("Authorization") String accessToken, @RequestBody Journey journey, @PathVariable long journeyId) {
        ApplicationUser user = authHelper.getUser(accessToken);
        Optional<Journey> maybeJourney = journeyRepository.findByUserIdAndId(user.getId(), journeyId);

        if (!maybeJourney.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        journey.setUser(user);
        journey.setId(journeyId);
        journey.setCreatedAt(maybeJourney.get().getCreatedAt());
        journeyRepository.save(journey);

        return ResponseEntity.ok(journey);
    }

    @DeleteMapping("/delete/{journeyId}")
    public ResponseEntity<?> removeJourney(@RequestHeader("Authorization") String accessToken, @PathVariable long journeyId) {
        ApplicationUser user = authHelper.getUser(accessToken);
        Optional<Journey> maybeJourney = journeyRepository.findByUserIdAndId(user.getId(), journeyId);

        if (!maybeJourney.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        journeyRepository.deleteById(maybeJourney.get().getId());
        return ResponseEntity.ok(new ApiResponse(true, "Journey deleted"));
    }
}
