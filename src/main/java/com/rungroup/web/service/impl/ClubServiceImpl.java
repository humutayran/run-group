package com.rungroup.web.service.impl;

import com.rungroup.web.mapper.ClubMapper;
import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.repository.ClubRepository;
import com.rungroup.web.service.ClubService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroup.web.mapper.ClubMapper.mapToClubDto;
import static com.rungroup.web.mapper.ClubMapper.mapToClub;

@Service
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }




    @Override
    public Club addClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
        return club;
    }

    @Override
    public ClubDto findClubById(Long clubId) {
        return mapToClubDto(clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("Club with ID " + clubId + " not found")));
    }

    public Club findClubByGiven(Long clubId) {
        return clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("Club with ID " + clubId + " not found"));
    }

    @Override
    @Transactional
    public ClubDto updateClub(long clubId, ClubDto clubDto) {
        Club club = findClubByGiven(clubId);
        club.setTitle(clubDto.getTitle());
        club.setContent(clubDto.getContent());
        club.setPhotoUrl(clubDto.getPhotoUrl());
        return mapToClubDto(club);
    }

    @Override
    public void deleteClub(long clubId) {

        ClubDto clubDto = findClubById(clubId);
        clubRepository.deleteById(clubId);
    }
}
