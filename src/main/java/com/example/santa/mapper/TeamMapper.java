package com.example.santa.mapper;

import com.example.santa.dto.TeamDTO;
import com.example.santa.dto.TeamSummaryDTO;
import com.example.santa.dto.TeamViewDTO;
import com.example.santa.entity.Account;
import com.example.santa.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(target = "owner", ignore = true)
    Team toEntity(TeamDTO teamDto);

    @Mapping(source = "owner", target = "ownerId")
    TeamDTO toDto(Team team);

    @Mapping(source = "teamCode", target = "code")
    TeamSummaryDTO toSummaryDto(Team team);

    @Mapping(source = "teamCode", target = "code")
    @Mapping(source = "owner", target = "ownerName")
    @Mapping(target = "members", ignore = true)
    TeamViewDTO toViewDto(Team team);


    default Long mapId(Account owner){
        return owner.getId();
    }
    default String mapName(Account owner){
        return owner.getName();
    }
}
