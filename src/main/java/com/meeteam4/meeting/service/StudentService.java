package com.meeteam4.meeting.service;

import com.meeteam4.meeting.dto.*;
import com.meeteam4.meeting.entity.Poster;
import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.entity.User;
import com.meeteam4.meeting.exception.SaveException;
import com.meeteam4.meeting.repository.AccountMapper;
import com.meeteam4.meeting.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private UserMapper userMapper;
    // 학생 공고 다중 조회 및 검색 필터
    @Transactional(rollbackFor = Exception.class)
    public List<SearchPosterRespDto> getStudentPosters(SearchPosterReqDto searchPosterReqDto) {
        List<Integer> posterIds = new ArrayList<>();
        posterIds.addAll(accountMapper.searchPosterIds(
                searchPosterReqDto.getRegionIds(),
                searchPosterReqDto.getSubjectIds(),
                searchPosterReqDto.getDateIds(),
                searchPosterReqDto.getStudentTypeIds()));
        if(posterIds.isEmpty()) {
            return null;
        }
        List<Poster> posters = accountMapper.getPosters(posterIds);
        List<SearchPosterRespDto> searchPosters = new ArrayList<>();

        for (Poster poster : posters) {
            SearchPosterRespDto searchPosterRespDto = poster.toSearchPosterRespDto();
            searchPosterRespDto.setPosterId(poster.getPosterId());
            searchPosterRespDto.setGenderType(poster.getGender().getGenderType());
            searchPosterRespDto.setStudentType(poster.getStudentType().getStudentType());
            searchPosterRespDto.setRegionName(poster.getRegion().getRegionName());

            List<String> subjectNames = poster.getPosterSubjectRegister().stream()
                    .map(ps -> ps.getSubject().getSubjectName())
                    .distinct()
                    .collect(Collectors.toList());
            searchPosterRespDto.setSubjectName(subjectNames);
            List<String> dateTypes = poster.getPosterDateRegister().stream()
                    .map(pd -> pd.getDate().getDateType())
                    .distinct()
                    .collect(Collectors.toList());
            searchPosterRespDto.setDateType(dateTypes);
            List<String> classTypes = poster.getPosterClassTypeRegister().stream()
                    .map(pct -> pct.getClassType().getClassType())
                    .distinct()
                    .collect(Collectors.toList());
            searchPosterRespDto.setClassType(classTypes);
            searchPosters.add(searchPosterRespDto);
        }
        return searchPosters;
    }
    // 공고 단건 조회
    public SearchPosterRespDto getStudentPoster(int posterId) {
        Poster poster = accountMapper.getPoster(posterId);
        SearchPosterRespDto searchPosterRespDto = poster.toSearchPosterRespDto();
        searchPosterRespDto.setPosterId(poster.getPosterId());
        searchPosterRespDto.setGenderType(poster.getGender().getGenderType());
        searchPosterRespDto.setStudentType(poster.getStudentType().getStudentType());
        searchPosterRespDto.setRegionName(poster.getRegion().getRegionName());
        searchPosterRespDto.setCreateDate(poster.getCreateDate());
        searchPosterRespDto.setUpdateDate(poster.getUpdateDate());

        List<String> subjectNames = poster.getPosterSubjectRegister().stream()
                .map(ps -> ps.getSubject().getSubjectName())
                .distinct()
                .collect(Collectors.toList());
        searchPosterRespDto.setSubjectName(subjectNames);
        List<String> dateTypes = poster.getPosterDateRegister().stream()
                .map(pd -> pd.getDate().getDateType())
                .distinct()
                .collect(Collectors.toList());
        searchPosterRespDto.setDateType(dateTypes);
        List<String> classTypes = poster.getPosterClassTypeRegister().stream()
                .map(pct -> pct.getClassType().getClassType())
                .distinct()
                .collect(Collectors.toList());
        searchPosterRespDto.setClassType(classTypes);
        return searchPosterRespDto;
    }
    //학생 정보 조회
    public StudentProfileRespDto getStudentProfile(int userId){
        if(userId == 0) {
            return null;
        }
        User studentProfile = accountMapper.getStudentProfile(userId);

        return studentProfile.toStudentProfileRespDto();
    }
    // 학생 본인 공고 리스트 조회
    public List<SearchPosterRespDto> getStudentMyPosters(int userId) {
        List<Poster> posters = accountMapper.getStudentMyPosters(userId);
        List<SearchPosterRespDto> searchPosters = new ArrayList<>();
        for (Poster poster : posters) {
            SearchPosterRespDto searchPosterRespDto = poster.toSearchPosterRespDto();
            searchPosterRespDto.setPosterId(poster.getPosterId());
            searchPosterRespDto.setGenderType(poster.getGender().getGenderType());
            searchPosterRespDto.setStudentType(poster.getStudentType().getStudentType());
            searchPosterRespDto.setRegionName(poster.getRegion().getRegionName());
            List<String> subjectNames = poster.getPosterSubjectRegister().stream()
                    .map(ps -> ps.getSubject().getSubjectName())
                    .distinct()
                    .collect(Collectors.toList());
            searchPosterRespDto.setSubjectName(subjectNames);
            List<String> dateTypes = poster.getPosterDateRegister().stream()
                    .map(pd -> pd.getDate().getDateType())
                    .distinct()
                    .collect(Collectors.toList());
            searchPosterRespDto.setDateType(dateTypes);
            List<String> classTypes = poster.getPosterClassTypeRegister().stream()
                    .map(pct -> pct.getClassType().getClassType())
                    .distinct()
                    .collect(Collectors.toList());
            searchPosterRespDto.setClassType(classTypes);
            searchPosters.add(searchPosterRespDto);
        }
        return searchPosters;
    }
    // 본인 공고 단건 조회
    public SearchPosterRespDto getMyposter(int posterId) {
        Poster poster = accountMapper.getStudentMyPoster(posterId);
        SearchPosterRespDto searchPosterRespDto = poster.toSearchPosterRespDto();
        searchPosterRespDto.setPosterId(poster.getPosterId());
        searchPosterRespDto.setGenderType(poster.getGender().getGenderType());
        searchPosterRespDto.setStudentType(poster.getStudentType().getStudentType());
        searchPosterRespDto.setRegionName(poster.getRegion().getRegionName());
        List<String> subjectNames = poster.getPosterSubjectRegister().stream()
                .map(ps -> ps.getSubject().getSubjectName())
                .distinct()
                .collect(Collectors.toList());
        searchPosterRespDto.setSubjectName(subjectNames);
        List<String> dateTypes = poster.getPosterDateRegister().stream()
                .map(pd -> pd.getDate().getDateType())
                .distinct()
                .collect(Collectors.toList());
        searchPosterRespDto.setDateType(dateTypes);
        List<String> classTypes = poster.getPosterClassTypeRegister().stream()
                .map(pct -> pct.getClassType().getClassType())
                .distinct()
                .collect(Collectors.toList());
        searchPosterRespDto.setClassType(classTypes);

        return searchPosterRespDto;
    }

}
