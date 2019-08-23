package biz.tugay.ctci.ch03;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnimalShelterTest {

    AnimalShelter animalShelter = new AnimalShelter();

    @Test
    public void dequeueEmpty() {
        assertThat(animalShelter.dequeue(), nullValue());
    }

    @Test
    public void dequeueDogEmpty() {
        assertThat(animalShelter.dequeueDog(), nullValue());
    }

    @Test
    public void dequeueCatEmpty() {
        assertThat(animalShelter.dequeueCat(), nullValue());
    }

    @Test
    public void enqueueDogDequeue() {
        animalShelter.enqueue(new Dog());
        assertThat(animalShelter.dequeue() instanceof Dog, is(true));
    }

    @Test
    public void enqueueDogDequeueDog() {
        animalShelter.enqueue(new Dog());
        assertThat(animalShelter.dequeueDog() instanceof Dog, is(true));
    }

    @Test
    public void enqueueDogDequeueCat() {
        animalShelter.enqueue(new Dog());
        assertThat(animalShelter.dequeueCat(), nullValue());
    }

    @Test
    public void enqDogEnqCatDeqDeq() throws InterruptedException {
        animalShelter.enqueue(new Dog());
        Thread.sleep(5);
        animalShelter.enqueue(new Cat());
        assertThat(animalShelter.dequeue() instanceof Dog, is(true));
        assertThat(animalShelter.dequeue() instanceof Cat, is(true));
    }

    @Test
    public void enqDogEnqCatDeqDogDeq() {
        animalShelter.enqueue(new Dog());
        animalShelter.enqueue(new Cat());
        assertThat(animalShelter.dequeueDog() instanceof Dog, is(true));
        assertThat(animalShelter.dequeue() instanceof Cat, is(true));
    }

    @Test
    public void enqDogEnqCatEnqDogDeqDeqDeq() throws InterruptedException {
        animalShelter.enqueue(new Dog());
        Thread.sleep(5);
        animalShelter.enqueue(new Cat());
        Thread.sleep(5);
        animalShelter.enqueue(new Dog());
        assertThat(animalShelter.dequeue() instanceof Dog, is(true));
        assertThat(animalShelter.dequeue() instanceof Cat, is(true));
        assertThat(animalShelter.dequeue() instanceof Dog, is(true));
    }
}
