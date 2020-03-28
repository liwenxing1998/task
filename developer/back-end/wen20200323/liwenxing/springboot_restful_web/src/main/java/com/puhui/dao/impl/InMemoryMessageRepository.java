package com.puhui.dao.impl;

import com.puhui.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName InMemoryMessageRepository
 */
@Repository
public class InMemoryMessageRepository implements MessageRepository {

    /**
     * tomicLongA 作用是对长整形进行原子操作，可以在高并场景下获取到唯一的 Long 值。
     */
    private static AtomicLong counter = new AtomicLong();
    /**
     * 使用 ConcurrentHashMap 来模拟存储 Message 对象的增删改查
     * ConcurrentHashMap 是 Java 中高性能并发的 Map 接口
     */
    private final ConcurrentHashMap<Long, Message> messages = new ConcurrentHashMap<>();

    @Override
    public List<Message> findAll() {

        ArrayList<Message> messageList = new ArrayList<>(this.messages.values());
        System.out.println(messageList.size() + "===============");
        return messageList;
    }

    @Override
    public Message save(Message message) {
        Long id = message.getId();
        if (id == null) {
            id = counter.incrementAndGet();
            message.setId(id);
        }
        this.messages.put(id, message);
        return message;
    }

    @Override
    public Message update(Message message) {
        // 因为是模拟,所以可以直接覆盖
        messages.put(message.getId(), message);
        return message;
    }

    @Override
    public Message updateText(Message message) {
        Message message1 = messages.get(message.getId());
        message1.setText(message.getText());
        messages.put(message1.getId(), message1);
        return message1;
    }

    @Override
    public Message findMessage(Long id) {

        return messages.get(id);
    }

    @Override
    public void deleteMessage(Long id) {
        messages.remove(id);
    }
}
