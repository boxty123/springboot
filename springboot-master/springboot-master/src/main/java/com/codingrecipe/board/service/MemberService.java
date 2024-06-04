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
    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
        if (idCheck(memberEntity.getName()) == null) {
            System.out.println("fail");
            throw new IllegalStateException(("이미 존재"));
        }else {
            memberRepository.save(memberEntity);
        }
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

    public String idCheck(String memberID) {
        Optional<MemberEntity> byName = memberRepository.findByName(memberID);
        if(byName.isPresent()){
            return null;
        }else{
            return "ok";
        }
    }

    public Long findByName(String name){
        Optional<MemberEntity> byName = memberRepository.findByName(name);
        return byName.map(MemberEntity::getId).orElse(null);
    }
}