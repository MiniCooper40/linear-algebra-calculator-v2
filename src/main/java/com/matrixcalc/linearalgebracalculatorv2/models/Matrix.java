package com.matrixcalc.linearalgebracalculatorv2.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.matrixcalc.linearalgebracalculatorv2.processing.MatrixDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = MatrixDeserializer.class)
public class Matrix {
    private double[][] matrix;
}
