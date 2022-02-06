package com.exadel.sandbox.mapper.impl;

import com.exadel.sandbox.dto.TgInfoDto;
import com.exadel.sandbox.entities.TgInfo;
import com.exadel.sandbox.mapper.TgInfoMapper;

public class TgInfoMapperImpl implements TgInfoMapper {
    @Override
    public TgInfo toEntity(TgInfoDto tgInfoDto) {
        return new TgInfo(
                tgInfoDto.getInfoId(),
                tgInfoDto.getChatId(),
                tgInfoDto.getChatState(),
                tgInfoDto.getUsername(),
                tgInfoDto.getInfoCreated(),
                tgInfoDto.getInfoModified()
        );
    }

    @Override
    public TgInfoDto toDto(TgInfo tgInfo) {
        return new TgInfoDto(
                tgInfo.getInfoId(),
                tgInfo.getChatId(),
                tgInfo.getChatState(),
                tgInfo.getUsername(),
                tgInfo.getInfoCreated(),
                tgInfo.getInfoModified()
        );
    }
}
