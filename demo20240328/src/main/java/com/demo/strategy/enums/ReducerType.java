package com.demo.strategy.enums;

/**
 * @author joe
 */
public enum ReducerType {

    /**
     * 默认缩减器
     */
    DEFAULT(false),

    /**
     * 替换字符缩减器
     */
    REPLACE_CHARACTER(true);

    private final Boolean ifReplaceCharacter;

    ReducerType(Boolean ifReplaceCharacter) {
        this.ifReplaceCharacter = ifReplaceCharacter;
    }

    public Boolean getIfReplaceCharacter() {
        return ifReplaceCharacter;
    }

}
