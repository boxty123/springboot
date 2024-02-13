package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.MemberDTO;
import com.codingrecipe.board.entity.MemberEntity;
import com.codingrecipe.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO){
        MemberEntity memberEntity=MemberEntity.toSaveEntity(memberDTO);
        CheckSave(memberEntity);
        memberRepository.save(memberEntity);
    }


    public void CheckSave(MemberEntity memberEntity){
        memberRepository.findByName(memberEntity.getName()).ifPresent(m->{
            throw new IllegalStateException(("이미 존재"));
        } );
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byName = memberRepository.findByName(memberDTO.getName());
        if(byName.isPresent()){
            MemberEntity memberEntity = byName.get();
            if(memberEntity.getPassword().equals(memberDTO.getPassword())){
                MemberDTO saveDTO = MemberDTO.toSaveDTO(memberEntity);
                return saveDTO;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}