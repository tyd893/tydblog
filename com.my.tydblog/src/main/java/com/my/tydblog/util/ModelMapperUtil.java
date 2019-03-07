package com.my.tydblog.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * Model Mapper Util
 *
 * @author Yeager
 */
public class ModelMapperUtil {

    public static ModelMapper get() {
        return new ModelMapper();
    }

    /**
     * 严格匹配模式
     * @return modelMapper
     */
    public static ModelMapper getStrictModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

}
