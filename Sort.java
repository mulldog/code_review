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
      int input_number = Integer.parseInt(buffer_reader.readLine());
      int a[] = new int[input_number];
	  
      for (int i = 0; i < input_number; i++) {
        a[i] = Integer.parseInt(buffer_reader.readLine());
      }
	  
      switch (selected_menu) {
        case MENU_BUBBLE_SORT:
          BinarySort(a, input_number);
          break;
		  
        case MENU_SELECTION_SORT:
          SelectionSort(a, input_number);
          break;
		  
        case MENU_INSERT_SORT:
          InsertionSort(a, input_number);
          break;
		  
        case MENU_QUICK_SORT:
          int start = 0;
          int end = input_number - 1;
          QuickSort(a, start, end);
          print(a, input_number);
          break;
		  
        case MENU_MERGE_SORT:
          MergeSort(a, input_number);
          print(a, input_number);
          break;
      }
    } while (selected_menu != MENU_EXIT);
  }
  
  public static void BinarySort(int a[], int n) {
    int temp;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - 1; j++) {
        if (a[j] > a[(j + 1)]) {
          temp = a[j];
          a[j] = a[(j + 1)];
          a[(j + 1)] = temp;
        }
        //System.out.print(a[j]);
      }
      //System.out.println();
    }
    print(a, n);
  }
  
  public static void SelectionSort(int a[], int n) {
    for (int i = 0; i < n - 1; i++) {
      int imin = i;
      int temp;
      for (int j = i + 1; j < n; j++) {
        if (a[j] < a[imin])
          imin = j;
      }
      temp = a[i];
      a[i] = a[imin];
      a[imin] = temp;
    }
    print(a, n);
  }
  
  public static void InsertionSort(int a[], int n) {
    for (int i = 1; i < n; i++) {
      int val = a[i];
      int hole = i;
	  
      while (hole > 0 && a[hole - 1] > val) {
        a[hole] = a[hole - 1];
        hole = hole - 1;
      }
      a[hole] = val;
    }
    print(a, n);
  }
  
  public static void MergeSort(int a[], int n) {
    if (n <= 1) {
      return;
	}
	
    int mid = n / 2;
    int left[] = new int[mid];
    int right[] = new int[n - mid];
	
    for (int i = 0; i < mid; i++) {
      left[i] = a[i];
	}
	
    for (int i = mid; i < n; i++) {
      right[i - mid] = a[i];
	}
	
    MergeSort(left, mid);
    MergeSort(right, n - mid);
    Merge(left, right, a);
  }
  
  public static void Merge(int left[], int right[], int a[]) {
    int nL = left.length;
    int nR = right.length;
    int i, j, k;
    i = j = k = 0;
	
    while (i < nL && j < nR) {
      if (left[i] <= right[j]) {
        a[k] = left[i];
        i++;
        k++;
      } else {
        a[k] = right[j];
        j++;
        k++;
      }
    }
	
    while (i < nL) {
      a[k] = left[i];
      i++;
      k++;
    }
	
    while (j < nR) {
      a[k] = right[j];
      j++;
      k++;
    }
  }
  
  public static void QuickSort(int a[], int start, int end) {
    if (start < end) {
      int pIndex = QuickPartition(a, start, end);
      QuickSort(a, start, pIndex - 1);
      QuickSort(a, pIndex + 1, end);
    } else {
      return;
	}

  }
  
  public static int QuickPartition(int a[], int start, int end) {
    int temp;
    int pivot = a[end];
    int pIndex = start;
    for (int i = start; i < end; i++) {
      if (a[i] <= pivot) {
        temp = a[i];
        a[i] = a[pIndex];
        a[pIndex] = temp;
        pIndex++;
      }
    }
    temp = a[pIndex];
    a[pIndex] = a[end];
    a[end] = temp;
	
    return pIndex;
  }
  
  public static void print(int a[], int n) {
    System.out.println();
    for (int i = 0; i < n; i++)
      System.out.print(a[i] + "\t");
  }
}
