package com.javatechie.springbootcrudexample.test;

import com.javatechie.springbootcrudexample.entity.Gender;
import com.javatechie.springbootcrudexample.entity.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = getPerson();

        // Filter
        System.out.println(people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList()));
        // Sort
        System.out.println(people.stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList()));
        // All match
        System.out.println(people.stream()
                .allMatch(person -> person.getAge() > 5));
        // Any match
        System.out.println(people.stream()
                .anyMatch(person -> person.getAge() > 17));
        // None match
        System.out.println(people.stream()
                .noneMatch(person -> person.getAge() > 5));
        // Max
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
        // Min
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
        // Group
        Map<Gender, List<Person>> genderListMap = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));
        System.out.println(genderListMap);
        // Oldest Female
        people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName)
                .ifPresent(System.out::println);
    }

    private static List<Person> getPerson() {
        return List.of(
                new Person("James Bond", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }
}
