package com.example.rest.article.entity;

import com.example.rest.global.jpa.BaseEntity;
import com.example.rest.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Article extends BaseEntity {

    private String subject;
    private String content;

    @ManyToOne
    private Member member;

}
