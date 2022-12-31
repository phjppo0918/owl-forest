package com.cbu.backend.member.service;

import com.cbu.backend.member.dto.response.MemberResponse;
import com.cbu.backend.member.entity.Member;
import com.cbu.backend.member.mapper.MemberMapper;
import com.cbu.backend.member.repository.MemberRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  private final MemberMapper memberMapper;

  @Transactional
  public void save(Member member) {
    memberRepository.save(member);
  }

  public Member getEntity(Long id) {
    return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public MemberResponse findById(Long id) {
    return memberMapper.toDto(
        memberRepository.findById(id).orElseThrow((EntityNotFoundException::new)));
  }
}