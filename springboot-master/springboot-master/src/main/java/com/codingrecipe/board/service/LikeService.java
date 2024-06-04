package com.codingrecipe.board.service;

import com.codingrecipe.board.entity.BoardEntity;
import com.codingrecipe.board.entity.LikeEntity;
import com.codingrecipe.board.entity.MemberEntity;
import com.codingrecipe.board.repository.BoardRepository;
import com.codingrecipe.board.repository.LikeRepository;
import com.codingrecipe.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public String check(Long boardId, Long memberId){
        BoardEntity boardEntity = boardRepository.findById(boardId)
            .orElseThrow(() -> new IllegalStateException("게시글을 찾을 수 없습니다."));
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("회원을 찾을 수 없습니다."));
        Optional<LikeEntity> byBoardEntityAndMemberEntity = likeRepository.findByBoardEntityAndMemberEntity(boardEntity, memberEntity);
        if(byBoardEntityAndMemberEntity.isPresent()){
            return "exist";
        }else{
            return "none";
        }
    }



    public void downLike(Long id, Long memberId){
        BoardEntity byId = boardRepository.findById(id).get();
        MemberEntity byId1 = memberRepository.findById(memberId).get();

        Optional<LikeEntity> byBoardEntityAndMemberEntity = likeRepository.findByBoardEntityAndMemberEntity(byId, byId1);
        Long id1 = byBoardEntityAndMemberEntity.get().getId();
        likeRepository.deleteById(id1);
    }

    public void uplike(Long id, Long memberId) {
        BoardEntity byId = boardRepository.findById(id).get();
        MemberEntity byId1 = memberRepository.findById(memberId).get();

        LikeEntity likeEntity=new LikeEntity();
        likeEntity.setMemberEntity(byId1);
        likeEntity.setBoardEntity(byId);
        likeRepository.save(likeEntity);

    }
}
