package com.puhui.dao.impl;

import com.puhui.domain.Message;

import java.util.List;

/**
 * @ClassName MessageRepository
 */
public interface MessageRepository {

    /**
     * 查询所有
     *
     * @return
     */
    List<Message> findAll();

    /**
     * 保存
     *
     * @param message
     * @return
     */
    Message save(Message message);

    /**
     * 修改
     *
     * @param message
     * @return
     */
    Message update(Message message);

    /**
     * 修改text字段
     *
     * @param message
     * @return
     */
    Message updateText(Message message);

    /**
     * 查询单个
     *
     * @param id
     * @return
     */
    Message findMessage(Long id);

    /**
     * 删除
     *
     * @param id
     */
    void deleteMessage(Long id);


}
