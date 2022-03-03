package com.exadel.telegrambot.bot.feign;

import com.exadel.telegrambot.bot.utils.TelegramUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.GetUserProfilePhotos;
import org.telegram.telegrambots.meta.api.methods.StopMessageLiveLocation;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;

@FeignClient(name = "TelegramFeign", url = TelegramUtils.FULL_REQUEST)
public interface TelegramFeign {
    @PostMapping("/sendMessage")
    void sendMessage(@RequestBody SendMessage sendMessage);

    @PostMapping("/sendPhoto")
    void sendPhoto(@RequestBody SendPhoto sendPhoto);

    @PostMapping("/sendAudio")
    void sendAudio(@RequestBody SendAudio sendAudio);

    @PostMapping("/sendDocument")
    void sendDocument(@RequestBody SendDocument sendDocument);

    @PostMapping("/sendVideo")
    void sendVideo(@RequestBody SendVideo sendVideo);

    @PostMapping("/sendAnimation")
    void sendAnimation(@RequestBody SendAnimation sendAnimation);

    @PostMapping("/sendVoice")
    void sendVoice(@RequestBody SendVoice sendVoice);

    @PostMapping("/sendVideoNote")
    void sendVideoNote(@RequestBody SendVideoNote sendVideoNote);

    @PostMapping("/sendMediaGroup")
    void sendMediaGroup(@RequestBody SendMediaGroup sendMediaGroup);

    @PostMapping("/sendLocation")
    void sendLocation(@RequestBody SendLocation sendLocation);

    @PostMapping("/editMessageLiveLocation")
    void editMessageLiveLocation(@RequestBody EditMessageLiveLocation editMessageLiveLocation);

    @PostMapping("/stopMessageLiveLocation")
    void stopMessageLiveLocation(@RequestBody StopMessageLiveLocation stopMessageLiveLocation);

    @PostMapping("/sendVenue")
    void sendVenue(@RequestBody SendVenue sendVenue);

    @PostMapping("/sendContact")
    void sendContact(@RequestBody SendContact sendContact);

    //    Can't find sendPoll so need to look another time
    @PostMapping("/sendPoll")
    void sendPoll(@RequestBody Poll poll);

    @PostMapping("/sendDice")
    void sendDice(@RequestBody SendDice sendDice);

    @PostMapping("/sendChatAction")
    void sendChatAction(@RequestBody SendChatAction sendChatAction);

    @PostMapping("/getUserProfilePhotos")
    void getUserProfilePhotos(@RequestBody GetUserProfilePhotos getUserProfilePhotos);

    @PostMapping("/getFile")
    void getFile(@RequestBody GetFile getFile);

    @PostMapping("/editMessageText")
    void editMessageText(@RequestBody EditMessageText editMessageText);

    @PostMapping("/editMessageCaption")
    void editMessageCaption(@RequestBody EditMessageCaption editMessageCaption);

    @PostMapping("/editMessageMedia")
    void editMessageMedia(@RequestBody EditMessageMedia editMessageMedia);

    @PostMapping("/editMessageReplyMarkup")
    void editMessageReplyMarkup(@RequestBody EditMessageReplyMarkup editMessageReplyMarkup);

    @PostMapping("/stopPoll")
    void stopPoll(@RequestBody Poll poll);

    //                        Delete message
    @DeleteMapping("/deleteMessage")
    void deleteMessage(@RequestParam(name = "chat_id") String chatId,
                       @RequestParam(name = "message_id") Integer messageId);

}