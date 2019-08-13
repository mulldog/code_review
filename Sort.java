import java.io.*;
import java.lang.*;

class Sort {
  private enum Menu {
    MENU_BUBBLE_SORT(1),
    MENU_SELECTION_SORT(2),
    MENU_INSERT_SORT(3),
    MENU_QUICK_SORT(4),
    MENU_MERGE_SORT(5),
    MENU_EXIT(6)
	
    private final menu;
    Menu(int menu) {
      this.menu = menu;
    }
    public getValue() {
      return this.menu;
    }
  };
  
  public static void main(String args[]) throws IOException {
    Menu selected_menu;
    BufferedReader buffer_reader = new BufferedReader(new InputStreamReader(System.in));
	
    do {
      System.out.println("\n\n\n" +
                         "1.Bubble Sort\n" +
                         "2.Selection Sort\n" +
                         "3.Insertion Sort.\n" +
                         "4.Quick Sort.\n" +
                         "5.Merge Sort.\n" +
                         "6.Exit.");
						 
      selected_menu = new Menu(Integer.parseInt(buffer_reader.readLine()));
      if (selected_menu == EXIT) {
        return;
	  }
	  
      System.out.println("Enter n");
      int data_size = Integer.parseInt(buffer_reader.readLine());
      int data_array[] = new int[data_size];
	  
      for (int i = 0; i < data_size; i++) {
        data_array[i] = Integer.parseInt(buffer_reader.readLine());
      }
	  
      switch (selected_menu) {
        case MENU_BUBBLE_SORT:
          BinarySort(data_array, data_size);
          break;
		  
        case MENU_SELECTION_SORT:
          SelectionSort(data_array, data_size);
          break;
		  
        case MENU_INSERT_SORT:
          InsertionSort(data_array, data_size);
          break;
		  
        case MENU_QUICK_SORT:
          int start = 0;
          int end = data_size - 1;
          QuickSort(data_array, start, end);
          print(data_array, data_size);
          break;
		  
        case MENU_MERGE_SORT:
          MergeSort(data_array, data_size);
          print(data_array, data_size);
          break;
      }
    } while (selected_menu != MENU_EXIT);
  }
  
  public static void BinarySort(int array[], int size) {
    int temp;
    for (int i = 0; i < size - 1; i++) {
      for (int j = 0; j < size - 1; j++) {
        if (array[j] > array[(j + 1)]) {
          temp = array[j];
          array[j] = array[(j + 1)];
          array[(j + 1)] = temp;
        }
        //System.out.print(array[j]);
      }
      //System.out.println();
    }
    print(array, size);
  }
  
  public static void SelectionSort(int array[], int size) {
    for (int i = 0; i < size - 1; i++) {
      int imin = i;
      int temp;
      for (int j = i + 1; j < size; j++) {
        if (array[j] < array[imin])
          imin = j;
      }
      temp = array[i];
      array[i] = array[imin];
      array[imin] = temp;
    }
    print(array, size);
  }
  
  public static void InsertionSort(int array[], int size) {
    for (int i = 1; i < size; i++) {
      int val = array[i];
      int hole = i;
	  
      while (hole > 0 && array[hole - 1] > val) {
        array[hole] = array[hole - 1];
        hole = hole - 1;
      }
      array[hole] = val;
    }
    print(array, size);
  }
  
  public static void MergeSort(int array[], int size) {
    if (size <= 1) {
      return;
	}
	
    int mid = size / 2;
    int left[] = new int[mid];
    int right[] = new int[size - mid];
	
    for (int i = 0; i < mid; i++) {
      left[i] = array[i];
	}
	
    for (int i = mid; i < size; i++) {
      right[i - mid] = array[i];
	}
	
    MergeSort(left, mid);
    MergeSort(right, n - mid);
    Merge(left, right, array);
  }
  
  public static void Merge(int left[], int right[], int array[]) {
    int nL = left.length;
    int nR = right.length;
    int i, j, k;
    i = j = k = 0;
	
    while (i < nL && j < nR) {
      if (left[i] <= right[j]) {
        array[k] = left[i];
        i++;
        k++;
      } else {
        array[k] = right[j];
        j++;
        k++;
      }
    }
	
    while (i < nL) {
      array[k] = left[i];
      i++;
      k++;
    }
	
    while (j < nR) {
      array[k] = right[j];
      j++;
      k++;
    }
  }
  
  public static void QuickSort(int array[], int start, int end) {
    if (start < end) {
      int pIndex = QuickPartition(array, start, end);
      QuickSort(array, start, pIndex - 1);
      QuickSort(array, pIndex + 1, end);
    } else {
      return;
	}

  }
  
  public static int QuickPartition(int array[], int start, int end) {
    int temp;
    int pivot = array[end];
    int pIndex = start;
    for (int i = start; i < end; i++) {
      if (array[i] <= pivot) {
        temp = array[i];
        array[i] = array[pIndex];
        array[pIndex] = temp;
        pIndex++;
      }
    }
    temp = array[pIndex];
    array[pIndex] = array[end];
    array[end] = temp;
	
    return pIndex;
  }
  
  public static void print(int array[], int size) {
    System.out.println();
    for (int i = 0; i < size; i++)
      System.out.print(array[i] + "\t");
  }
}
