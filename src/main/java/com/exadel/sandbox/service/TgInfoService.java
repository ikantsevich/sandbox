package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.TgInfoDto;
import com.exadel.sandbox.entities.Employee;
import com.exadel.sandbox.entities.TgInfo;

import java.util.List;

public interface TgInfoService {
    List<TgInfo> getTgInfos();

    TgInfo getTgInfoById(int id);

    TgInfo create(TgInfo tgInfo);

    void deleteById(int id);

    TgInfo update(int id, TgInfo tgInfo);
}
