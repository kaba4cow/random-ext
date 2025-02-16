package com.kaba4cow.randomext;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * {@code RandomExt} is an extension of the {@link Random} class providing additional utility methods for generating random
 * values with enhanced flexibility and usability. It includes methods for generating random numbers within a range, selecting
 * random elements from collections or arrays, and working with enums.
 * <p>
 * Key features include:
 * <ul>
 * <li>Support for generating random numbers ({@code int}, {@code long}, {@code float}, {@code double}) within both open and
 * closed intervals.</li>
 * <li>Ability to randomly select elements from collections, lists, or arrays.</li>
 * <li>Thread-local singleton access through {@link #getInstance()}.</li>
 * <li>Custom random boolean generation with a specified probability.</li>
 * </ul>
 * <p>
 * The class is thread-safe when accessed through the {@link #getInstance()} method, which ensures each thread gets its own
 * instance.
 */
public class RandomExt extends Random {

	private static final long serialVersionUID = -2767045398894666514L;

	private static final ThreadLocal<RandomExt> instance = ThreadLocal.withInitial(RandomExt::new);

	/**
	 * Creates a new {@code RandomExt} instance with a default seed.
	 */
	public RandomExt() {
		super();
	}

	/**
	 * Creates a new {@code RandomExt} instance with the specified seed.
	 *
	 * @param seed the initial seed
	 */
	public RandomExt(long seed) {
		super(seed);
	}

	/**
	 * Returns a thread-local instance of {@code RandomExt}.
	 * 
	 * @return the thread-local instance of {@code RandomExt}
	 */
	public static RandomExt getInstance() {
		return instance.get();
	}

	/**
	 * Generates a random boolean with the specified probability of being {@code true}.
	 * 
	 * @param probability the probability of {@code true} (0.0 to 1.0 inclusive)
	 * 
	 * @return {@code true} with the given probability, {@code false} otherwise
	 */
	public boolean nextBoolean(double probability) {
		return nextDouble() <= probability;
	}

	/**
	 * Generates a random {@code byte} within the specified open range {@code [min, max)}.
	 * 
	 * @param min the minimum value (inclusive)
	 * @param max the maximum value (exclusive)
	 * 
	 * @return a random {@code byte} within the range {@code [min, max)}
	 * 
	 * @throws IllegalArgumentException if {@code max} is not greater than {@code min}
	 */
	public byte nextByte(byte min, byte max) {
		if (max <= min)
			requireMaxGreaterThanMin();
		return (byte) nextInt(min, max);
	}

	/**
	 * Generates a random {@code byte} within the specified closed range {@code [min, max]}.
	 * 
	 * @param min the minimum value (inclusive)
	 * @param max the maximum value (inclusive)
	 * 
	 * @return a random {@code byte} within the range {@code [min, max]}
	 * 
	 * @throws IllegalArgumentException if {@code max} is less than {@code min}
	 */
	public byte nextByteClosed(byte min, byte max) {
		if (max < min)
			requireMaxGreaterOrEqualThanMin();
		return (byte) nextIntClosed(min, max);
	}

	/**
	 * Generates a random {@code short} within the specified open range {@code [min, max)}.
	 * 
	 * @param min the minimum value (inclusive)
	 * @param max the maximum value (exclusive)
	 * 
	 * @return a random {@code short} within the range {@code [min, max)}
	 * 
	 * @throws IllegalArgumentException if {@code max} is not greater than {@code min}
	 */
	public short nextShort(short min, short max) {
		if (max <= min)
			requireMaxGreaterThanMin();
		return (short) nextInt(min, max);
	}

	/**
	 * Generates a random {@code short} within the specified closed range {@code [min, max]}.
	 * 
	 * @param min the minimum value (inclusive)
	 * @param max the maximum value (inclusive)
	 * 
	 * @return a random {@code short} within the range {@code [min, max]}
	 * 
	 * @throws IllegalArgumentException if {@code max} is less than {@code min}
	 */
	public short nextShortClosed(short min, short max) {
		if (max < min)
			requireMaxGreaterOrEqualThanMin();
		return (short) nextIntClosed(min, max);
	}

	/**
	 * Generates a random {@code char} within the specified open range {@code [min, max)}.
	 * 
	 * @param min the minimum value (inclusive)
	 * @param max the maximum value (exclusive)
	 * 
	 * @return a random {@code char} within the range {@code [min, max)}
	 * 
	 * @throws IllegalArgumentException if {@code max} is not greater than {@code min}
	 */
	public char nextChar(char min, char max) {
		if (max <= min)
			requireMaxGreaterThanMin();
		return (char) nextInt(min, max);
	}

	/**
	 * Generates a random {@code char} within the specified closed range {@code [min, max]}.
	 * 
	 * @param min the minimum value (inclusive)
	 * @param max the maximum value (inclusive)
	 * 
	 * @return a random {@code char} within the range {@code [min, max]}
	 * 
	 * @throws IllegalArgumentException if {@code max} is less than {@code min}
	 */
	public char nextCharClosed(char min, char max) {
		if (max < min)
			requireMaxGreaterOrEqualThanMin();
		return (char) nextIntClosed(min, max);
	}

	/**
	 * Generates a random {@code int} within the specified open range {@code [min, max)}.
	 * 
	 * @param min the minimum value (inclusive)
	 * @param max the maximum value (exclusive)
	 * 
	 * @return a random {@code int} within the range {@code [min, max)}
	 * 
	 * @throws IllegalArgumentException if {@code max} is not greater than {@code min}
	 */
	public int nextInt(int min, int max) {
		if (max <= min)
			requireMaxGreaterThanMin();
		return min + nextInt(max - min);
	}

	/**
	 * Generates a random {@code int} within the specified closed range {@code [min, max]}.
	 * 
	 * @param min the minimum value (inclusive)
	 * @param max the maximum value (inclusive)
	 * 
	 * @return a random {@code int} within the range {@code [min, max]}
	 * 
	 * @throws IllegalArgumentException if {@code max} is less than {@code min}
	 */
	public int nextIntClosed(int min, int max) {
		if (max < min)
			requireMaxGreaterOrEqualThanMin();
		return min + nextInt(max - min + 1);
	}

	/**
	 * Generates a random {@code long} within the specified open range {@code [min, max)}.
	 * 
	 * @param min the minimum value (inclusive)
	 * @param max the maximum value (exclusive)
	 * 
	 * @return a random {@code long} within the range {@code [min, max)}
	 * 
	 * @throws IllegalArgumentException if {@code max} is not greater than {@code min}
	 */
	public long nextLong(long min, long max) {
		if (max <= min)
			requireMaxGreaterThanMin();
		return min + (long) ((max - min) * nextDouble());
	}

	/**
	 * Generates a random {@code long} within the specified closed range {@code [min, max]}.
	 * 
	 * @param min the minimum value (inclusive)
	 * @param max the maximum value (inclusive)
	 * 
	 * @return a random {@code long} within the range {@code [min, max]}
	 * 
	 * @throws IllegalArgumentException if {@code max} is less than {@code min}
	 */
	public long nextLongClosed(long min, long max) {
		if (max < min)
			requireMaxGreaterOrEqualThanMin();
		return min + (long) ((max - min + 1) * nextDouble());
	}

	/**
	 * Generates a random {@code float} within the specified open range {@code [min, max)}.
	 * 
	 * @param min the minimum value (inclusive)
	 * @param max the maximum value (exclusive)
	 * 
	 * @return a random {@code float} within the range {@code [min, max)}
	 * 
	 * @throws IllegalArgumentException if {@code max} is not greater than {@code min}
	 */
	public float nextFloat(float min, float max) {
		if (max <= min)
			requireMaxGreaterThanMin();
		return min + (max - min) * nextFloat();
	}

	/**
	 * Generates a random {@code double} within the specified open range {@code [min, max)}.
	 * 
	 * @param min the minimum value (inclusive)
	 * @param max the maximum value (exclusive)
	 * 
	 * @return a random {@code double} within the range {@code [min, max)}
	 * 
	 * @throws IllegalArgumentException if {@code max} is not greater than {@code min}
	 */
	public double nextDouble(double min, double max) {
		if (max <= min)
			requireMaxGreaterThanMin();
		return min + (max - min) * nextDouble();
	}

	/**
	 * Generates a Gaussian-distributed random number with a specified mean and standard deviation. This method uses the
	 * standard Gaussian distribution and adjusts it by the provided mean and deviation.
	 *
	 * @param mean      the mean (average) of the Gaussian distribution
	 * @param deviation the standard deviation of the Gaussian distribution
	 * 
	 * @return a Gaussian-distributed random number adjusted by the given mean and deviation
	 */
	public double nextGaussian(double mean, double deviation) {
		return mean + deviation * nextGaussian();
	}

	/**
	 * Selects a random character from the given {@link CharSequence}.
	 * 
	 * @param string the {@link CharSequence} to pick a character from
	 * 
	 * @return a randomly selected character from the given {@code string}
	 * 
	 * @throws IllegalArgumentException if {@code string} is empty
	 * @throws NullPointerException     if {@code string} is {@code null}
	 */
	public char nextChar(CharSequence string) {
		return string.charAt(nextInt(0, string.length()));
	}

	/**
	 * Selects a random constant from the specified {@code enum} type.
	 * 
	 * @param <T>  the type of the {@code enum}
	 * @param type the {@code enum} class
	 * 
	 * @return a random constant from the {@code enum} type
	 * 
	 * @throws NullPointerException     if the {@code enum} type is {@code null}
	 * @throws IllegalArgumentException if the {@code enum} type contains no constants
	 */
	public <T extends Enum<T>> T nextEnum(Class<T> type) {
		return nextElement(type.getEnumConstants());
	}

	/**
	 * Shuffles the elements of a list using the Fisher-Yates algorithm.
	 *
	 * @param list the list to be shuffled
	 * @param <T>  the type of elements in the list
	 * 
	 * @return the shuffled list
	 * 
	 * @throws NullPointerException if the list is {@code null}
	 */
	public <T> List<T> shuffle(List<T> list) {
		Collections.shuffle(requireNonNull(list, "List"), this);
		return list;
	}

	/**
	 * Selects a random element from the given collection.
	 * 
	 * @param <T>        the type of elements in the collection
	 * @param collection the collection to select from
	 * 
	 * @return a random element from the collection
	 * 
	 * @throws NullPointerException     if the collection is {@code null}
	 * @throws IllegalArgumentException if the collection is empty
	 */
	public <T> T nextElement(Collection<T> collection) {
		if (requireNonNull(collection, "Collection").isEmpty())
			requireNonEmpty("Collection");
		int targetIndex = nextInt(collection.size());
		int currentIndex = 0;
		for (T element : collection)
			if (currentIndex++ == targetIndex)
				return element;
		throw new IllegalStateException("Unreachable: could not retrieve element from collection");
	}

	/**
	 * Selects a random element from the given list.
	 * 
	 * @param <T>  the type of elements in the list
	 * @param list the list to select from
	 * 
	 * @return a random element from the list
	 * 
	 * @throws NullPointerException     if the list is {@code null}
	 * @throws IllegalArgumentException if the list is empty
	 */
	public <T> T nextElement(List<T> list) {
		if (requireNonNull(list, "List").isEmpty())
			requireNonEmpty("List");
		return list.get(nextInt(list.size()));
	}

	/**
	 * Selects a random element from the given array.
	 * 
	 * @param <T>   the type of elements in the array
	 * @param array the array to select from
	 * 
	 * @return a random element from the array
	 * 
	 * @throws NullPointerException     if the array is {@code null}
	 * @throws IllegalArgumentException if the array is empty
	 */
	public <T> T nextElement(T[] array) {
		if (requireNonNull(array, "Array").length == 0)
			requireNonEmpty("Array");
		return array[nextInt(array.length)];
	}

	/**
	 * Selects a random element from the given {@code boolean} array.
	 * 
	 * @param array the array to select from
	 * 
	 * @return a random element from the array
	 * 
	 * @throws NullPointerException     if the array is {@code null}
	 * @throws IllegalArgumentException if the array is empty
	 */
	public boolean nextElement(boolean[] array) {
		if (requireNonNull(array, "Array").length == 0)
			requireNonEmpty("Array");
		return array[nextInt(array.length)];
	}

	/**
	 * Selects a random element from the given {@code byte} array.
	 * 
	 * @param array the array to select from
	 * 
	 * @return a random element from the array
	 * 
	 * @throws NullPointerException     if the array is {@code null}
	 * @throws IllegalArgumentException if the array is empty
	 */
	public byte nextElement(byte[] array) {
		if (requireNonNull(array, "Array").length == 0)
			requireNonEmpty("Array");
		return array[nextInt(array.length)];
	}

	/**
	 * Selects a random element from the given {@code short} array.
	 * 
	 * @param array the array to select from
	 * 
	 * @return a random element from the array
	 * 
	 * @throws NullPointerException     if the array is {@code null}
	 * @throws IllegalArgumentException if the array is empty
	 */
	public short nextElement(short[] array) {
		if (requireNonNull(array, "Array").length == 0)
			requireNonEmpty("Array");
		return array[nextInt(array.length)];
	}

	/**
	 * Selects a random element from the given {@code char} array.
	 * 
	 * @param array the array to select from
	 * 
	 * @return a random element from the array
	 * 
	 * @throws NullPointerException     if the array is {@code null}
	 * @throws IllegalArgumentException if the array is empty
	 */
	public char nextElement(char[] array) {
		if (requireNonNull(array, "Array").length == 0)
			requireNonEmpty("Array");
		return array[nextInt(array.length)];
	}

	/**
	 * Selects a random element from the given {@code int} array.
	 * 
	 * @param array the array to select from
	 * 
	 * @return a random element from the array
	 * 
	 * @throws NullPointerException     if the array is {@code null}
	 * @throws IllegalArgumentException if the array is empty
	 */
	public int nextElement(int[] array) {
		if (requireNonNull(array, "Array").length == 0)
			requireNonEmpty("Array");
		return array[nextInt(array.length)];
	}

	/**
	 * Selects a random element from the given {@code long} array.
	 * 
	 * @param array the array to select from
	 * 
	 * @return a random element from the array
	 * 
	 * @throws NullPointerException     if the array is {@code null}
	 * @throws IllegalArgumentException if the array is empty
	 */
	public long nextElement(long[] array) {
		if (requireNonNull(array, "Array").length == 0)
			requireNonEmpty("Array");
		return array[nextInt(array.length)];
	}

	/**
	 * Selects a random element from the given {@code float} array.
	 * 
	 * @param array the array to select from
	 * 
	 * @return a random element from the array
	 * 
	 * @throws NullPointerException     if the array is {@code null}
	 * @throws IllegalArgumentException if the array is empty
	 */
	public float nextElement(float[] array) {
		if (requireNonNull(array, "Array").length == 0)
			requireNonEmpty("Array");
		return array[nextInt(array.length)];
	}

	/**
	 * Selects a random element from the given {@code double} array.
	 * 
	 * @param array the array to select from
	 * 
	 * @return a random element from the array
	 * 
	 * @throws NullPointerException     if the array is {@code null}
	 * @throws IllegalArgumentException if the array is empty
	 */
	public double nextElement(double[] array) {
		if (requireNonNull(array, "Array").length == 0)
			requireNonEmpty("Array");
		return array[nextInt(array.length)];
	}

	private <T> T requireNonNull(T object, String type) {
		if (Objects.isNull(object))
			throw new NullPointerException(type.concat(" must no be null"));
		else
			return object;
	}

	private void requireMaxGreaterThanMin() {
		throw new IllegalArgumentException("Max must be greater than min");
	}

	private void requireMaxGreaterOrEqualThanMin() {
		throw new IllegalArgumentException("Max must be greater or equal than min");
	}

	private void requireNonEmpty(String type) {
		throw new IllegalArgumentException(String.format("%s must not be empty", type));
	}

}
