package fr.sdv.strategy;

public class InsertionSort implements Strategy {

    @Override
    public void trier(Integer[] arr) {
        for (int k = 1; k < arr.length; k++) {
            int temp = arr[k];
            int j = k - 1;
            while (j >= 0 && temp <= arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }
}
