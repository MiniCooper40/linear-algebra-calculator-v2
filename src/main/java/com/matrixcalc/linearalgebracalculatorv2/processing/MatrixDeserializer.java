package com.matrixcalc.linearalgebracalculatorv2.processing;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.matrixcalc.linearalgebracalculatorv2.models.Matrix;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class MatrixDeserializer extends JsonDeserializer<Matrix> {
    @Override
    public Matrix deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        System.out.println("In deseralize, node type is " + node.getNodeType().name());
        node.fieldNames().forEachRemaining(System.out::println);
        try {
            double[][] A =  deserializationContext.readTreeAsValue(node, double[][].class);
            System.out.println(Arrays.deepToString(A));
            return new Matrix(A);
        } catch (Exception e) {
            return null;
        }
    }
}
