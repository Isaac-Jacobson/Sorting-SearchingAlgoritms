package sorting;

public class SortingFramework {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int bestMergeCutoff = 10;

		tester(bestMergeCutoff);

	}

	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int left, int right) {

		int pivot = arr[left + (right - left) / 2];

		int low = left;
		int high = right;

		while (low <= high) {

			while (arr[low] < pivot) {
				low++;
			}

			while (arr[high] > pivot) {
				high--;
			}
			if (low <= high) {
				int temp = arr[high];
				arr[high] = arr[low];
				arr[low] = temp;
				low++;
				high--;
			}
		}

		if (left < high)
			quickSort(arr, left, high);
		if (low < right)
			quickSort(arr, low, right);
	}

	public static void bestMergeSort(int[] arr, int k) {
		mergeSort(arr, new int[arr.length], 0, arr.length - 1, k);
	}

	public static void mergeSort(int[] arr) {
		mergeSort(arr, new int[arr.length], 0, arr.length - 1, arr.length);
	}

	public static void mergeSort(int[] arr, int[] temp, int leftStart, int rightEnd, int k) {
		if (leftStart >= rightEnd) {
			return;
		} else if (arr.length <= k) {
			insertionSort(arr);
			return;
		}
		int middle = leftStart + (rightEnd - leftStart) / 2; // No int overflow
		mergeSort(arr, temp, leftStart, middle, k);
		mergeSort(arr, temp, middle + 1, rightEnd, k);
		mergeHalves(arr, temp, leftStart, rightEnd);
	}

	public static void mergeHalves(int[] arr, int[] temp, int leftStart, int rightEnd) {
		int leftEnd = (rightEnd + leftStart) / 2;
		int rightStart = leftEnd + 1;
		int size = rightEnd - leftStart + 1;

		int left = leftStart;
		int right = rightStart;
		int loc = leftStart;

		while (left <= leftEnd && right <= rightEnd) {
			if (arr[left] <= arr[right]) {
				temp[loc] = arr[left];
				left++;
			} else {
				temp[loc] = arr[right];
				right++;
			}
			loc++;
		}
		System.arraycopy(arr, left, temp, loc, leftEnd - left + 1);
		System.arraycopy(arr, right, temp, loc, rightEnd - right + 1);
		System.arraycopy(temp, leftStart, arr, leftStart, size);

	}

	public static void comparison(int[] arr, int k) {
		long insertionStart = System.nanoTime();
		insertionSort(arr);
		long insertionEnd = System.nanoTime();
		long insertionTime = insertionEnd - insertionStart;

		long selectionStart = System.nanoTime();
		selectionSort(arr);
		long selectionEnd = System.nanoTime();
		long selectionTime = selectionEnd - selectionStart;

		long bestMergeStart = System.nanoTime();
		bestMergeSort(arr, k);
		long bestMergeEnd = System.nanoTime();
		long bestMergeTime = bestMergeEnd - bestMergeStart;

		long quickSortStart = System.nanoTime();
		quickSort(arr);
		long quickSortEnd = System.nanoTime();
		long quickSortTime = quickSortEnd - quickSortStart;

		System.out.println("Insertion: " + insertionTime);
		System.out.println("Selection: " + selectionTime);
		System.out.println("Merge    : " + bestMergeTime);
		System.out.println("Quick    : " + quickSortTime);

	}

	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int loc = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[loc])
					loc = j;
			}
			int temp = arr[loc];
			arr[loc] = arr[i];
			arr[i] = temp;
		}
	}

	public static void insertionSort(int[] arr, int start, int end) {
		int temp;
		for (int i = start + 1; i < end; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}
			}
		}
	}

	public static void insertionSort(int[] arr) {
		insertionSort(arr, 0, arr.length);

	}

	// Length: 1 = short, 2 = medium, 3 = long;
	public static int[] repeat(int length) {
		int[] output = new int[(int) ((Math.pow(10, length)))];
		for (int i = 0; i < output.length; i++) {
			output[i] = (int) (Math.random() * 4) + 5;
		}
		return output;
	}

	// Length: 1 = short, 2 = medium, 3 = long;
	public static int[] close(int length) {
		int[] output = new int[(int) ((Math.pow(10, length)))];
		for (int i = 0; i < output.length; i++) {
			output[i] = i;
		}
		for (int j = 0; j < output.length / 5; j++) {
			int temp = output[(int) (Math.random() * output.length)];
			int temp2 = output[(int) (Math.random() * output.length)];
			output[temp] = output[temp2];
			output[temp2] = output[temp];
		}
		return output;
	}

	// Length: 1 = short, 2 = medium, 3 = long;
	public static int[] reverse(int length) {
		int[] temp = close(length);
		int[] output = new int[temp.length];

		for (int i = 0; i < output.length; i++) {
			output[i] = temp[temp.length - (1 + i)];
		}
		return output;
	}

	// Length: 1 = short, 2 = medium, 3 = long;
	public static int[] random(int length) {
		int[] output = new int[(int) ((Math.pow(10, length)))];

		for (int i = 0; i < output.length; i++) {
			output[i] = (int) (Math.random() * output.length);
		}
		return output;
	}

	public static void tester(int k) {

		// Length: 1 = short, 2 = medium, 3 = long;
		int shortArray = 1;
		int mediumArray = 2;
		int longArray = 3;

		int[] shortRepeat = repeat(shortArray);
		int[] shortClose = close(shortArray);
		int[] shortReverse = reverse(shortArray);
		int[] shortRandom = random(shortArray);
		int[] mediumRepeat = repeat(mediumArray);
		int[] mediumClose = close(mediumArray);
		int[] mediumReverse = reverse(mediumArray);
		int[] mediumRandom = random(mediumArray);
		int[] longRepeat = repeat(longArray);
		int[] longClose = close(longArray);
		int[] longReverse = reverse(longArray);
		int[] longRandom = random(longArray);

		System.out.println("shortRepeat");
		comparison(shortRepeat, k);
		System.out.println("shortClose");
		comparison(shortClose, k);
		System.out.println("shortReverse");
		comparison(shortReverse, k);
		System.out.println("shortRandom");
		comparison(shortRandom, k);
		System.out.println("mediumRepeat");
		comparison(mediumRepeat, k);
		System.out.println("mediumClose");
		comparison(mediumClose, k);
		System.out.println("mediumReverse");
		comparison(mediumReverse, k);
		System.out.println("mediumRandom");
		comparison(mediumRandom, k);
		System.out.println("longRepeat");
		comparison(longRepeat, k);
		System.out.println("longClose");
		comparison(longClose, k);
		System.out.println("longReverse");
		comparison(longReverse, k);
		System.out.println("longRandom");
		comparison(longRandom, k);
	}

}
