package biz.tugay.ctci.ch03;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

class AnimalShelter {

    Queue<AnimalRecord> dog = new LinkedList<>(), cat = new LinkedList<>();

    static class AnimalRecord {
        Instant instant = Instant.now();
        Animal animal;

        AnimalRecord(Animal animal) {
            this.animal = animal;
        }
    }

    void enqueue(Animal animal) {
        AnimalRecord animalRecord = new AnimalRecord(animal);
        if (animal instanceof Dog) dog.add(animalRecord);
        if (animal instanceof Cat) cat.add(animalRecord);
    }

    Animal dequeue() {
        if (dog.isEmpty() && cat.isEmpty()) return null;
        if (dog.isEmpty()) return cat.element().animal;
        if (cat.isEmpty()) return dog.element().animal;
        return dog.peek().instant.isBefore(cat.peek().instant) ? dog.poll().animal : cat.poll().animal;
    }

    Animal dequeueDog() {
        return Optional.ofNullable(dog.poll()).orElse(new AnimalRecord(null)).animal;
    }

    Animal dequeueCat() {
        return Optional.ofNullable(cat.poll()).orElse(new AnimalRecord(null)).animal;
    }

}

class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}
