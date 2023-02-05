package com.cbu.backend.studygroup.query.dto;

import com.cbu.backend.studygroup.command.domain.StudyGroupStatus;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class StudyGroupResponse {
    private UUID id;
    private String name;
    private String description;
    private Integer likeCount;
    private StudyGroupStatus studyGroupStatus;
    private StudyMember studyLeader;
    private List<StudyMember> participants;

    public StudyGroupResponse(StudyGroupResponse studyGroup, List<StudyMember> participants) {
        this.id = studyGroup.getId();
        this.name = studyGroup.getName();
        this.description = studyGroup.getDescription();
        this.likeCount = studyGroup.getLikeCount();
        this.studyGroupStatus = studyGroup.getStudyGroupStatus();
        this.studyLeader = studyGroup.getStudyLeader();
        this.participants = participants;
    }

    @QueryProjection
    public StudyGroupResponse(
            UUID id,
            String name,
            String description,
            Integer likeCount,
            StudyGroupStatus studyGroupStatus,
            StudyMember studyLeader) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.likeCount = likeCount;
        this.studyGroupStatus = studyGroupStatus;
        this.studyLeader = studyLeader;
    }
}
