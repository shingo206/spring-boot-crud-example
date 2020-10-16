package com.javatechie.springbootcrudexample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @NonNull
    private String name;
    @Size(max = 120)
    private int age;
    @NonNull
    private Gender gender;
}
