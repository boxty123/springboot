package com.codingrecipe.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne
    @JoinColumn(name="board_id")
    private BoardEntity boardEntity;


    @ManyToOne
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

}
