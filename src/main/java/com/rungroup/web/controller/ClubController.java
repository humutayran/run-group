package com.rungroup.web.controller;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.repository.ClubRepository;
import com.rungroup.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin
@RequestMapping("/clubs")
public class ClubController {
    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<List<ClubDto>> listClubs() {
        List<ClubDto> clubs = clubService.findAllClubs();
        return new ResponseEntity<>(clubs, OK);
    }

    @PostMapping
    public ResponseEntity<Club> addClub(@RequestBody ClubDto newClub) {
        Club club = clubService.addClub(newClub);
        return new ResponseEntity<>(club, CREATED);
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<ClubDto> getClub(@PathVariable("clubId") long clubId) {
        ClubDto club = clubService.findClubById(clubId);
        return new ResponseEntity<>(club, OK);
    }

    @PutMapping("/{clubId}")
    public ResponseEntity<ClubDto> updateClub(@PathVariable long clubId, @RequestBody ClubDto clubDto) {
        ClubDto club = clubService.updateClub(clubId, clubDto);
        return new ResponseEntity<>(club, OK);
    }

}
