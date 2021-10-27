package com.kangbakso.fdbjava.controller;

import com.apple.foundationdb.tuple.Tuple;
import com.kangbakso.fdbjava.db.FdbProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

@RestController
public class WebController {
    @Autowired
    FdbProvider fdbProvider;

    Random rand = new Random();
    byte[] array = new byte[7];

    @PostMapping("/simplePost")
    public ResponseEntity simplePost(@RequestParam String id) {
        rand.nextBytes(array);
        String generatedName = new String(array, Charset.forName("UTF-8"));

        try {
            String result = fdbProvider.db.run(tr -> {
                tr.set(fdbProvider.userSubspace.pack(Tuple.from(id)), Tuple.from(generatedName, 0).pack());
                return generatedName;
            });
            return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/simpleGet")
    public ResponseEntity simpleGet(@RequestParam String id) {
        try {
            String result = fdbProvider.db.run(tr -> {
                byte[] resultByte = tr.get(fdbProvider.userSubspace.subspace(Tuple.from(id)).pack()).join();
                List<Object> resultList = Tuple.fromBytes(resultByte).getItems();
                return resultList.toString();
            });
            return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/simpleUpdate")
    public ResponseEntity simpleUpdate(@RequestParam String id) {
        try {
            String result = fdbProvider.db.run(tr -> {
                byte[] resultByte = tr.get(fdbProvider.userSubspace.subspace(Tuple.from(id)).pack()).join();

                if (resultByte != null) {
                    tr.clear(fdbProvider.userSubspace.pack(Tuple.from(id)));
                }

                List<Object> resultList = Tuple.fromBytes(resultByte).getItems();
                resultList.set(1, Integer.valueOf(resultList.get(1).toString()) + 1);

                tr.set(fdbProvider.userSubspace.pack(Tuple.from(id)), Tuple.from(resultList.get(0), resultList.get(1)).pack());
                return resultList.toString();
            });
            return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/simpleDelete")
    public ResponseEntity simpleDelete(@RequestParam String id) {
        try {
            String result = fdbProvider.db.run(tr -> {
                tr.clear(fdbProvider.userSubspace.pack(Tuple.from(id)));
                return id;
            });
            return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }
}
