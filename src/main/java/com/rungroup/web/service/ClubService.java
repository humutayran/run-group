package com.rungroup.web.service;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();

    Club addClub(ClubDto clubDto);

    ClubDto findClubById(Long clubId);

    ClubDto updateClub(long clubId, ClubDto clubDto);

    void deleteClub(long clubId);

    List<ClubDto> searchClubs(String query);
}
