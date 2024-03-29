package com.demo.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 相同字符判断结果类
 *
 * @author joe wong
 */
public class IdenticalResult {
    /**
     * 是否有相同字符
     */
    private boolean ifIdentical = false;

    /**
     * 相同字符Set
     */
    private Set<Character> charSet = new HashSet<>();

    /**
     * 相同字符连续出现的次数
     */
    private int count = 0;

    public boolean getIfIdentical() {
        return ifIdentical;
    }

    public void setIfIdentical(boolean ifIdentical) {
        this.ifIdentical = ifIdentical;
    }

    public Set<Character> getCharSet() {
        return charSet;
    }

    public void setCharSet(Set<Character> charSet) {
        this.charSet = charSet;
    }

    public void addChar(Character aChar) {
        charSet.add(aChar);
    }

    public boolean isIfIdentical() {
        return ifIdentical;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "IdenticalResult{" +
                "ifIdentical=" + ifIdentical +
                ", charSet=" + charSet +
                '}';
    }
}
