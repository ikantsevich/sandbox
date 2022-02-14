package com.exadel.sandbox.bot.feign;

import com.exadel.sandbox.bot.dto.GetMeDto;
import com.exadel.sandbox.bot.utils.TelegramUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.GetUserProfilePhotos;
import org.telegram.telegrambots.meta.api.methods.StopMessageLiveLocation;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;

@FeignClient(name = "TelegramFeign", url = TelegramUtils.URL)
public interface TelegramFeign {
    @PostMapping("{token}/sendMessage")
    void sendMessage(@PathVariable String token,
                     @RequestBody SendMessage sendMessage);

    @PostMapping("{token}/getMe")
    GetMeDto getMe(@PathVariable String token);

    @PostMapping("{token}/sendPhoto")
    void sendPhoto(@PathVariable String token,
                   @RequestBody SendPhoto sendPhoto);

    @PostMapping("{token}/sendAudio")
    void sendAudio(@PathVariable String token,
                   @RequestBody SendAudio sendAudio);

    @PostMapping("{token}/sendDocument")
    void sendDocument(@PathVariable String token,
                      @RequestBody SendDocument sendDocument);

    @PostMapping("{token}/sendVideo")
    void sendVideo(@PathVariable String token,
                   @RequestBody SendVideo sendVideo);

    @PostMapping("{token}/sendAnimation")
    void sendAnimation(@PathVariable String token,
                       @RequestBody SendAnimation sendAnimation);

    @PostMapping("{token}/sendVoice")
    void sendVoice(@PathVariable String token,
                   @RequestBody SendVoice sendVoice);

    @PostMapping("{token}/sendVideoNote")
    void sendVideoNote(@PathVariable String token,
                       @RequestBody SendVideoNote sendVideoNote);

    @PostMapping("{token}/sendMediaGroup")
    void sendMediaGroup(@PathVariable String token,
                        @RequestBody SendMediaGroup sendMediaGroup);

    @PostMapping("{token}/sendLocation")
    void sendLocation(@PathVariable String token,
                      @RequestBody SendLocation sendLocation);

    @PostMapping("{token}/editMessageLiveLocation")
    void editMessageLiveLocation(@PathVariable String token,
                                 @RequestBody EditMessageLiveLocation editMessageLiveLocation);

    @PostMapping("{token}/stopMessageLiveLocation")
    void stopMessageLiveLocation(@PathVariable String token,
                                 @RequestBody StopMessageLiveLocation stopMessageLiveLocation);

    @PostMapping("{token}/sendVenue")
    void sendVenue(@PathVariable String token,
                   @RequestBody SendVenue sendVenue);

    @PostMapping("{token}/sendContact")
    void sendContact(@PathVariable String token,
                     @RequestBody SendContact sendContact);

    //    Can't find sendPoll so need to look another time
    @PostMapping("{token}/sendPoll")
    void sendPoll(@PathVariable String token,
                  @RequestBody Poll poll);

    @PostMapping("{token}/sendDice")
    void sendDice(@PathVariable String token,
                  @RequestBody SendDice sendDice);

    @PostMapping("{token}/sendChatAction")
    void sendChatAction(@PathVariable String token,
                        @RequestBody SendChatAction sendChatAction);

    @PostMapping("{token}/getUserProfilePhotos")
    void getUserProfilePhotos(@PathVariable String token,
                              @RequestBody GetUserProfilePhotos getUserProfilePhotos);

    @PostMapping("{token}/getFile")
    void getFile(@PathVariable String token,
                 @RequestBody GetFile getFile);

    @PostMapping("{token}/editMessageText")
    void editMessageText(@PathVariable String token,
                         @RequestBody EditMessageText editMessageText);

    @PostMapping("{token}/editMessageCaption")
    void editMessageCaption(@PathVariable String token,
                            @RequestBody EditMessageCaption editMessageCaption);

    @PostMapping("{token}/editMessageMedia")
    void editMessageMedia(@PathVariable String token,
                          @RequestBody EditMessageMedia editMessageMedia);

    @PostMapping("{token}/editMessageReplyMarkup")
    void editMessageReplyMarkup(@PathVariable String token,
                                @RequestBody EditMessageReplyMarkup editMessageReplyMarkup);

    @PostMapping("{token}/stopPoll")
    void stopPoll(@PathVariable String token,
                  @RequestBody Poll poll);

    //                        Delete message
    @PostMapping("{token}/deleteMessage")
    void deleteMessage(@PathVariable String token,
                       @RequestBody Message message);
}