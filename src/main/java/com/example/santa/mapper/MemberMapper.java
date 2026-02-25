package com.example.santa.mapper;

import com.example.santa.dto.MemberDTO;
import com.example.santa.dto.MemberResponseDTO;
import com.example.santa.entity.Member;
import com.example.santa.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "team", ignore = true)
    Member toEntity(MemberDTO memberDto);

    MemberDTO toDto(Member member);

    @Mapping(source = "team", target = "teamCode")
    @Mapping(source = "member", target = "interests", qualifiedByName = "toInterests")
    MemberResponseDTO toResponseDto(Member member);

    default String map(Team team){
        return team.getTeamCode();
    }

    @Named("toInterests")
    default List<String> toInterests(Member member){
        return List.of(member.getInterest1(), member.getInterest2(), member.getInterest3())
                .stream()
                .filter(i -> i != null)
                .toList();
    }
}
