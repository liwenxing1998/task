package com.puhui.controller;

import com.puhui.dao.impl.MessageRepository;
import com.puhui.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName MessageController
 */
@RestController
@RequestMapping("/")
public class MessageController {

    /**
     * @GetMapping(value="/xxx") 等价于
     * @RequestMapping(value = "/xxx",method = RequestMethod.GET)
     * @PostMapping(value="/xxx") 等价于
     * @RequestMapping(value = "/xxx",method = RequestMethod.POST)
     * @PutMapping(value="/xxx") 等价于
     * @RequestMapping(value = "/xxx",method = RequestMethod.PUT)
     * @DeleteMapping(value="/xxx") 等价于
     * @RequestMapping(value = "/xxx",method = RequestMethod.DELETE)
     * @PatchMapping(value="/xxx") 等价于
     * @RequestMapping(value = "/xxx",method = RequestMethod.PATCH)
     */

    /*封装Restful的处理*/
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("findAll")
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    /**
     * 保存是post
     *
     * @param message
     * @return
     */
    @PostMapping("save")
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @PutMapping("update")
    public Message update(Message message) {
        return messageRepository.update(message);
    }

    /**
     * 更新消息的 text 字段
     *
     * @param message
     * @return
     */
    @PatchMapping("update/text")
    public Message modify(Message message) {
        return messageRepository.updateText(message);
    }

    /**
     * 根据id
     *
     * @return
     */
    @GetMapping("message/{id}")
    public Message findMessage(@PathVariable Long id) {
        return messageRepository.findMessage(id);
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        messageRepository.deleteMessage(id);
    }


}
