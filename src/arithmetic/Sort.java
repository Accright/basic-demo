package arithmetic;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args){
        int[] l = {1,32,43,12,312,3,3,54,32,322,31,65,87,435,123,8,76,76567,5};
        //new Sort().bubbleSort(l);
        //new Sort().bubbleSoryAdvance(l);
        //new Sort().quickSort(l,0,l.length - 1);
        //new Sort().insertSort(l);
        //new Sort().binaryInsertSort(l);
        //new Sort().chooseSort(l);
        new Sort().shellSort(l);

        System.out.println("排序之后的结果是"+Arrays.toString(l));
    }

    //冒泡排序
    public void bubbleSort(int[] x){
        for (int i = 0;i < x.length; i++){
            for (int j = 0;j < x.length - i - 1;j++){
                if (x[j] > x[j+1]){
                    swap(j,j+1,x);
                }
            }
        }
    }

    //改进的冒泡排序
    public void bubbleSoryAdvance(int x[]){
        int low = 0;
        int high = x.length - 1;
        while (low < high){
            //从两端分别开始冒泡
            for (int i = low;i < high;i++){
                if (x[i] > x[i + 1]){
                    swap(i,i+1,x);
                }
            }
            --high;//排完一次序需要移动low和high
            for (int j = high;j > low;j--){
                if (x[j] < x[j - 1]){
                    swap(j,j-1,x);
                }
            }
            ++low;
        }
    }

    //快排的recursive
    public void quickSort(int x[],int low,int high){
        if (low < high){
            int middle = this.getKey(x,low,high);
            this.quickSort(x,0,middle - 1);
            this.quickSort(x,middle + 1,high);
        }
    }

    //快排 交换并返回目前的基准值
    public int getKey(int[] x,int low,int high){
        int temp = x[low];
        while (low < high){
            while (low < high && x[high] >= temp){
                high --;
            }
            x[low] = x[high];
            while (low < high && x[low] <= temp){
                low ++;
            }
            x[high] = x[low];
        }
        x[low] = temp;
        return low;
    }

    //直接插入排序
    public void insertSort(int[] x){
        for (int i = 0;i < x.length;i++){
            int temp = x[i];
            int j;
            for (j = i - 1;j >= 0 && x[j] > temp;j--){
                x[j+1] = x[j];
            }
            x[j+1] = temp;
        }
    }

    //折半插入排序 适合于对于排好序的 插入一个为排序的数
    public void binaryInsertSort(int[] x){
        for (int i = 0;i < x.length;i++){
            int temp = x[i];
            int left = 0;
            int right = i - 1;
            int mid;
            while(left <= right){
                mid = (left + right)/2;
                if (temp < x[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }
            for (int j = i-1;j >= left;j--){
                x[j+1] = x[j];
            }
            if (left != i){
                x[left] = temp;
            }
        }
    }

    //选择排序 通过比较未排序序列 选择最小的放到队首
    public void chooseSort(int[] x){
        int min ;
        for (int i = 0;i < x.length; i++){
            min = i;
            for (int j = i+1;j < x.length;j++){//循环未排序序列 找最小值
                if (x[j] < x[min]){
                    min = j;
                }
            }
            swap(min,i,x);
        }
    }

    //希尔排序 希尔排序是插入排序的一种优化 插入排序适用于将未排序的一个值插入已排序的
    //希尔排序将大数组分成小数组进行排序 然后再形成基本有序的大数组排序
    public void shellSort(int[] x){
        int dk = x.length/2;
        while (dk >= 1){
            shellInsert(x,dk);
            dk = dk / 2;
        }
    }

    public void shellInsert(int[] x,int dk){
        for (int i = dk;i < x.length;i++){
            int temp = x[i];
            int j;
            for (j=i-dk;j>=0 && x[j]>temp;j=j-dk){
                //将大于temp的向后移动k位
                x[j+dk] = x[j];
            }
            x[j+dk] = temp;
        }
    }

    //归并排序
    public void mergeSort(int[] x){

    }

    //堆排序
    public void heapSort(int[] x){

    }

    //交换
    public void swap(int ov,int nv,int[] x){
        int temp = x[ov];
        x[ov] = x[nv];
        x[nv] = temp;
    }
}
