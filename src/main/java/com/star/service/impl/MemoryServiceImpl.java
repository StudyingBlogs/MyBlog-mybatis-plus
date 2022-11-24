package com.star.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.domain.entity.Memory;
import com.star.service.MemoryService;
import com.star.mapper.MemoryMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Participate
* @description 针对表【t_memory】的数据库操作Service实现
* @createDate 2022-11-20 23:37:31
*/
@Service
public class MemoryServiceImpl extends ServiceImpl<MemoryMapper, Memory>
    implements MemoryService{

    @Override
    //@Cacheable(value = "memoryList",key = "'memory'")
    public List<Memory> listMemory() {
        LambdaQueryWrapper<Memory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Memory::getId);
        return list(queryWrapper);
    }

    @Override
    public boolean saveMemory(Memory memory) {
        return save(memory);
    }

}




