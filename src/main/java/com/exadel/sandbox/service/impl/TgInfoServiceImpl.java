package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.entities.TgInfo;
import com.exadel.sandbox.repository.TgInfoRepository;
import com.exadel.sandbox.service.TgInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TgInfoServiceImpl implements TgInfoService {

    @Autowired
    TgInfoRepository tgInfoRepository;


    @Override
    public List<TgInfo> getTgInfos() {
        return tgInfoRepository.findAll();
    }

    @Override
    public TgInfo getTgInfoById(int id) {
        return tgInfoRepository.getOne(id);
    }

    @Override
    public TgInfo create(TgInfo tgInfo) {
        return tgInfoRepository.save(tgInfo);
    }

    @Override
    public void deleteById(int id) {
        tgInfoRepository.deleteById(id);
    }

    @Override
    public TgInfo update(int id, TgInfo tgInfo) {
        TgInfo newTgInfo = tgInfoRepository.getOne(id);

        newTgInfo.setInfoModified(tgInfo.getInfoModified());
        newTgInfo.setChatId(tgInfo.getChatId());
        newTgInfo.setUsername(tgInfo.getUsername());
        newTgInfo.setChatState(tgInfo.getChatState());

        return tgInfoRepository.save(newTgInfo);
    }
}
