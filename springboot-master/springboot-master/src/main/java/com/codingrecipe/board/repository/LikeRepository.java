package com.codingrecipe.board.repository;

import com.codingrecipe.board.entity.BoardEntity;
import com.codingrecipe.board.entity.LikeEntity;
import com.codingrecipe.board.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity,Long> {

    Optional<LikeEntity >findByBoardEntityAndMemberEntity(BoardEntity boardEntity, MemberEntity memberEntity);
}
