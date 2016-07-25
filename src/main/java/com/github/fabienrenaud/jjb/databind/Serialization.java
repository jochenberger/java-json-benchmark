package com.github.fabienrenaud.jjb.databind;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bluelinelabs.logansquare.LoganSquare;
import com.github.fabienrenaud.jjb.JsonBench;
import com.github.fabienrenaud.jjb.JsonUtils;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.ByteArrayOutputStream;

public class Serialization extends JsonBench {

    @Benchmark
    @Override
    public Object gson() {
        StringBuilder b = JsonUtils.stringBuilder();
        JSON_SOURCE.provider().gson().toJson(JSON_SOURCE.nextPojo(), b);
        return b;
    }

    @Benchmark
    @Override
    public Object jackson() throws Exception {
        ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
        JSON_SOURCE.provider().jackson().writeValue(baos, JSON_SOURCE.nextPojo());
        return baos;
    }

    @Benchmark
    @Override
    public Object jackson_afterburner() throws Exception {
        ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
        JSON_SOURCE.provider().jacksonAfterburner().writeValue(baos, JSON_SOURCE.nextPojo());
        return baos;
    }

    @Benchmark
    @Override
    public Object genson() {
        ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
        JSON_SOURCE.provider().genson().serialize(JSON_SOURCE.nextPojo(), baos);
        return baos;
    }

    @Benchmark
    @Override
    public Object fastjson() throws Exception {
        ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
        JSON.writeJSONString(baos, JSON_SOURCE.nextPojo(), SerializerFeature.EMPTY);
        return baos;
    }

    @Benchmark
    @Override
    public Object flexjson() {
        return JSON_SOURCE.provider().flexjsonSer().serialize(JSON_SOURCE.nextPojo());
    }

    @Benchmark
    @Override
    public Object boon() {
        ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
        JSON_SOURCE.provider().boon().writeValue(baos, JSON_SOURCE.nextPojo());
        return baos;
    }

    @Benchmark
    @Override
    public Object johnson() {
        ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
        JSON_SOURCE.provider().johnson().writeObject(JSON_SOURCE.nextPojo(), baos);
        return baos;
    }

    @Benchmark
    @Override
    public Object jsonsmart() throws Exception {
        StringBuilder b = JsonUtils.stringBuilder();
        net.minidev.json.JSONValue.writeJSONString(JSON_SOURCE.nextPojo(), b);
        return b;
    }

    @Benchmark
    @Override
    public Object dsljson() throws Exception {
        ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
        com.dslplatform.json.JsonWriter jw = JsonUtils.dslJsonWriter();
        JSON_SOURCE.provider().dsljson().serialize(jw, JSON_SOURCE.nextPojo());
        jw.toStream(baos);
        return baos;
    }

    @Benchmark
    @Override
    public Object logansquare() throws Exception {
        ByteArrayOutputStream baos = JsonUtils.byteArrayOutputStream();
        LoganSquare.serialize(JSON_SOURCE.nextPojo(), baos);
        return baos;
    }
}