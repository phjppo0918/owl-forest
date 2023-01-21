package com.cbu.backend.studycrew.command.service;

import com.cbu.backend.authaccount.command.domain.AccountNo;
import com.cbu.backend.studycrew.command.domain.StudyCrew;
import com.cbu.backend.studycrew.command.domain.StudyCrewNo;
import com.cbu.backend.studycrew.command.dto.StudyCrewRequest;
import com.cbu.backend.studycrew.command.dto.StudyCrewResponse;
import com.cbu.backend.studycrew.command.infra.StudyCrewRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class StudyCrewService {

    private final StudyCrewRepository studyCrewRepository;

    public StudyCrewNo save(StudyCrewRequest studyCrewRequest) {
        checkParticipantDuplicated(studyCrewRequest.getParticipants());
        return studyCrewRepository.save(studyCrewRequest.toEntity()).getId();
    }

    @Transactional
    public StudyCrewResponse updateStudyCrew(StudyCrewNo id, StudyCrewRequest studyCrewRequest) {
        StudyCrew studyCrew = getEntity(id);
        checkParticipantDuplicated(studyCrewRequest.getParticipants());
        return new StudyCrewResponse(studyCrew.updateStudyCrew(studyCrewRequest));
    }

    @Transactional
    public void addLike(StudyCrewNo id) {
        getEntity(id).addLike();
    }

    @Transactional
    public void cancelLike(StudyCrewNo id) {
        getEntity(id).cancelLike();
    }

    @Transactional
    public void finishStudyCrew(StudyCrewNo id) {
        getEntity(id).finishStudyCrew();
    }

    @Transactional
    public void deleteStudyCrew(StudyCrewNo id) {
        getEntity(id).deleteStudyCrew();
    }

    private void checkParticipantDuplicated(List<AccountNo> studyCrewParticipants) {
        if (studyCrewParticipants.size() != getRequestCount(studyCrewParticipants)) {
            throw new ParticipantDuplicatedException();
        }
    }

    private long getRequestCount(List<AccountNo> studyCrewParticipants) {
        return studyCrewParticipants.stream().distinct().count();
    }

    private StudyCrew getEntity(StudyCrewNo id) {
        return studyCrewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
