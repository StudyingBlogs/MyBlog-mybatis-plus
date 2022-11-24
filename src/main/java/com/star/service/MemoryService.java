package com.star.service;

import com.star.domain.entity.Memory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Participate
* @description 针对表【t_memory】的数据库操作Service
* @createDate 2022-11-20 23:37:31
*/
public interface MemoryService extends IService<Memory> {

    List<Memory> listMemory();

    boolean saveMemory(Memory memory);
}
