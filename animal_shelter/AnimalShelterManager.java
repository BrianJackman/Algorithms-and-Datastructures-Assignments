// Animal Shelter Manager
// By: Brian Jackman
// 2025/02/16

package animal_shelter;

import java.util.LinkedList;

public class AnimalShelterManager {
    private LinkedList<Animal> dogs;
    private LinkedList<Animal> cats;
    private int order;

    public AnimalShelterManager() {
        dogs = new LinkedList<>();
        cats = new LinkedList<>();
        order = 0;
    }

    // Enqueue an animal
    public void enqueue(String type, String name) {
        Animal animal = new Animal(type, name, order++);
        if (type.equalsIgnoreCase("dog")) {
            dogs.addLast(animal);
        } else if (type.equalsIgnoreCase("cat")) {
            cats.addLast(animal);
        } else {
            System.out.println("Invalid animal type. Only dogs and cats are allowed.");
        }
    }

    // Dequeue any animal
    public Animal dequeueAny() {
        if (dogs.isEmpty() && cats.isEmpty()) {
            System.out.println("No animals available for adoption.");
            return null;
        } else if (dogs.isEmpty()) {
            return cats.poll();
        } else if (cats.isEmpty()) {
            return dogs.poll();
        } else {
            if (dogs.peek().getOrder() < cats.peek().getOrder()) {
                return dogs.poll();
            } else {
                return cats.poll();
            }
        }
    }

    // Dequeue a dog
    public Animal dequeueDog() {
        if (dogs.isEmpty()) {
            System.out.println("No dogs available for adoption.");
            return null;
        }
        return dogs.poll();
    }

    // Dequeue a cat
    public Animal dequeueCat() {
        if (cats.isEmpty()) {
            System.out.println("No cats available for adoption.");
            return null;
        }
        return cats.poll();
    }

    // Animal class
    private static class Animal {
        private String type;
        private String name;
        private int order;

        public Animal(String type, String name, int order) {
            this.type = type;
            this.name = name;
            this.order = order;
        }

        public int getOrder() {
            return order;
        }

        @Override
        public String toString() {
            return type + ": " + name;
        }
    }

    }
