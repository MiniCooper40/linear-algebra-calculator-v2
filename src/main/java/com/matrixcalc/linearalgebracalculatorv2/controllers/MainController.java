package com.matrixcalc.linearalgebracalculatorv2.controllers;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.matrixcalc.linearalgebracalculatorv2.models.Matrix;
import com.matrixcalc.linearalgebracalculatorv2.tools.Operations;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@RestController
@AllArgsConstructor
public class MainController {

    private final ObjectMapper mapper;

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody ObjectNode node) throws IOException {
        node.fieldNames().forEachRemaining(System.out::println);
        try {
            double[][] A = mapper.treeToValue(node.get("A"), double[][].class);
            double[][] B = mapper.treeToValue(node.get("B"), double[][].class);

            System.out.println(Arrays.deepToString(A));
            System.out.println(Arrays.deepToString(B));

            return new ResponseEntity<>(Operations.add(A, B), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid matrices for addition", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/subtract")
    public ResponseEntity<Object> subtract(@RequestBody ObjectNode node) throws IOException {
        node.fieldNames().forEachRemaining(System.out::println);
        try {
            double[][] A = mapper.treeToValue(node.get("A"), double[][].class);
            double[][] B = mapper.treeToValue(node.get("B"), double[][].class);

            System.out.println(Arrays.deepToString(A));
            System.out.println(Arrays.deepToString(B));

            return new ResponseEntity<>(Operations.subtract(A, B), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid matrices for addition", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/multiply")
    public ResponseEntity<Object> multiply(@RequestBody ObjectNode node) throws IOException {
        node.fieldNames().forEachRemaining(System.out::println);
        try {
            double[][] A = mapper.treeToValue(node.get("A"), double[][].class);
            double[][] B = mapper.treeToValue(node.get("B"), double[][].class);

            System.out.println(Arrays.deepToString(A));
            System.out.println(Arrays.deepToString(B));

            return new ResponseEntity<>(Operations.multiply(A, B), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid matrices for addition", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/rref")
    public ResponseEntity<Object> rref(@RequestBody ObjectNode node) throws IOException {
        node.fieldNames().forEachRemaining(System.out::println);
        try {
            double[][] A = mapper.treeToValue(node.get("A"), double[][].class);

            System.out.println(Arrays.deepToString(A));

            return new ResponseEntity<>(Operations.rref(A), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid matrices for addition", HttpStatus.BAD_REQUEST);
        }
    }
}
