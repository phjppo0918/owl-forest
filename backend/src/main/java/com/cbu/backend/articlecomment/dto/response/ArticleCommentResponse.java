package com.cbu.backend.articlecomment.dto.response;

import com.cbu.backend.member.dto.response.MemberResponse;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ArticleCommentResponse {
  private Long id;
  private String content;
  private MemberResponse writer;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
