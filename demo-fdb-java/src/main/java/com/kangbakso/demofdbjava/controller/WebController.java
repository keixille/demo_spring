package com.kangbakso.demofdbjava.controller;

import com.apple.foundationdb.tuple.Tuple;
import com.kangbakso.demofdbjava.db.FdbProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

@RestController
public class WebController {
    @Autowired
    FdbProvider fdbProvider;

    Random rand = new Random();
    byte[] array = new byte[7];

    @GetMapping("/simpleGet")
    public String simpleGet(@RequestParam String id) {
        try {
            return fdbProvider.db.run(tr -> {
                byte[] resultByte = tr.get(fdbProvider.userSubspace.subspace(Tuple.from(id)).pack()).join();
                List<Object> resultList = Tuple.fromBytes(resultByte).getItems();
                return resultList.toString();
            });
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/simplePost")
    public String simplePost(@RequestParam String id) {
        rand.nextBytes(array);
        String generatedName = new String(array, Charset.forName("UTF-8"));
        try {
            return fdbProvider.db.run(tr -> {
                tr.set(fdbProvider.userSubspace.pack(Tuple.from(id)), Tuple.from(generatedName, 0).pack());
                return generatedName;
            });
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @GetMapping("/simpleUpdate")
    public String simpleUpdate(@RequestParam String id) {
        try {
            return fdbProvider.db.run(tr -> {
                byte[] resultByte = tr.get(fdbProvider.userSubspace.subspace(Tuple.from(id)).pack()).join();
                List<Object> resultList = Tuple.fromBytes(resultByte).getItems();
                int counter = Integer.valueOf(resultList.get(1).toString()) + 1;

                tr.set(fdbProvider.userSubspace.pack(Tuple.from(id)), Tuple.from(resultList.get(0), counter).pack());
                return resultList.toString();
            });
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/simpleDelete")
    public String simpleDelete(@RequestParam String id) {
        try {
            return fdbProvider.db.run(tr -> {
                tr.clear(fdbProvider.userSubspace.pack(Tuple.from(id)));
                return id;
            });
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
