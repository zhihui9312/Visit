package com.guc.visit.domain;

import com.alibaba.fastjson.JSON;

public class Test {
    public static void main(String[] args) {
        PregnantAddDTO dto = new PregnantAddDTO();
        dto.setName("ddd");
        System.out.println(JSON.toJSON(dto));
    }
}
