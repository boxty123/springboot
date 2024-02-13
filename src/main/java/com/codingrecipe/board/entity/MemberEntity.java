package com.codingrecipe.board.entity;

import com.codingrecipe.board.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="member_table")
public class MemberEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String password;

    @Column
    private String email;

    public static MemberEntity toSaveEntity(MemberDTO memberDTO){
        MemberEntity memberEntity=new MemberEntity();

        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setId((memberDTO.getId()));
        memberEntity.setName(memberDTO.getName());
        memberEntity.setEmail(memberDTO.getEmail());


        return memberEntity;
    }
}
