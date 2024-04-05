package com.rungroup.web.controller;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.repository.ClubRepository;
import com.rungroup.web.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> addClub(@Valid @RequestBody ClubDto newClub, BindingResult bindingResult ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Club club = clubService.addClub(newClub);
        return new ResponseEntity<>(club, CREATED);
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<ClubDto> getClub(@PathVariable("clubId") long clubId) {
        ClubDto club = clubService.findClubById(clubId);
        return new ResponseEntity<>(club, OK);
    }

    @PutMapping("/{clubId}")
    public ResponseEntity<?> updateClub(@PathVariable long clubId, @Valid @RequestBody ClubDto clubDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        ClubDto club = clubService.updateClub(clubId, clubDto);
        return new ResponseEntity<>(club, HttpStatus.OK);
    }

    @DeleteMapping("/{clubId}/delete")
    public ResponseEntity<String> deleteClub(@PathVariable("clubId") long clubId) {
        clubService.deleteClub(clubId);
        return ResponseEntity.status(OK).body("club has been removed successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClubDto>> searchClubs(@RequestParam(value = "query") String query){
        List<ClubDto> clubs = clubService.searchClubs(query);
        return new ResponseEntity<>(clubs, OK);
    }

}
