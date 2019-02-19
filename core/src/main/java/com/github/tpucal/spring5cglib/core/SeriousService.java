package com.github.tpucal.spring5cglib.core;

import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

public class SeriousService
{
    private Map<String, String> values = Map.of("foo", "foo-return", "bar", "bar-return");

    @Cacheable(cacheNames = "cache")
    public String getResult(String key) {
        return values.getOrDefault(key, "not-found2");
    }
}
